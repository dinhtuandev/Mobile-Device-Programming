package com.example.uth.smarttasks.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// A data class to hold the user profile information
data class UserProfile(
    var name: String = "",
    var email: String = "",
    var dateOfBirth: String = "",
    var avatarUrl: String? = null // URL to the profile picture
) {
    // No-argument constructor required for Firebase deserialization
    constructor() : this("", "", "", null)
}

class ProfileViewModel : ViewModel() {
    var userProfile by mutableStateOf(UserProfile())
        private set

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()

    private val userId: String?
        get() = auth.currentUser?.uid

    init {
        loadProfile()
    }

    fun loadProfile() {
        userId?.let { uid ->
            firestore.collection("users").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        userProfile = document.toObject(UserProfile::class.java) ?: UserProfile()
                        Log.d("ProfileViewModel", "Profile loaded: $userProfile")
                    } else {
                        Log.d("ProfileViewModel", "No such document for user $uid")
                        // Initialize with current user's email if available
                        auth.currentUser?.email?.let { email ->
                            userProfile = userProfile.copy(email = email)
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("ProfileViewModel", "Error loading profile: ", exception)
                }
        } ?: run {
            Log.e("ProfileViewModel", "User not logged in.")
            // Handle case where user is not logged in, maybe navigate to login
        }
    }

    fun onNameChange(newName: String) {
        userProfile = userProfile.copy(name = newName)
    }

    fun onDateOfBirthChange(newDate: String) {
        userProfile = userProfile.copy(dateOfBirth = newDate)
    }

    fun onAvatarChange(newAvatarUri: String) {
        userProfile = userProfile.copy(avatarUrl = newAvatarUri)
    }

    fun saveProfile() {
        userId?.let { uid ->
            viewModelScope.launch {
                try {
                    // If avatarUrl is a local URI, upload it first
                    val currentAvatarUri = userProfile.avatarUrl
                    if (currentAvatarUri != null && !currentAvatarUri.startsWith("http")) {
                        val downloadUrl = uploadImageToFirebaseStorage(Uri.parse(currentAvatarUri), uid)
                        userProfile = userProfile.copy(avatarUrl = downloadUrl)
                    }

                    firestore.collection("users").document(uid)
                        .set(userProfile, SetOptions.merge())
                        .await()
                    Log.d("ProfileViewModel", "Profile saved successfully: $userProfile")
                    // You might want to show a toast message or navigate back
                } catch (e: Exception) {
                    Log.e("ProfileViewModel", "Error saving profile: ", e)
                }
            }
        } ?: Log.e("ProfileViewModel", "User not logged in, cannot save profile.")
    }

    private suspend fun uploadImageToFirebaseStorage(imageUri: Uri, uid: String): String {
        val storageRef = storage.reference
        val avatarRef = storageRef.child("avatars/$uid/${imageUri.lastPathSegment}")
        val uploadTask = avatarRef.putFile(imageUri).await()
        return avatarRef.downloadUrl.await().toString()
    }
}