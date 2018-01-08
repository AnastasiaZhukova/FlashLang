package com.github.anastasiazhukova.flashlang.firebase

import com.github.anastasiazhukova.flashlang.firebase.auth.IFirebaseUserManager
import com.github.anastasiazhukova.flashlang.firebase.db.IFirebaseDbManager
import com.github.anastasiazhukova.flashlang.firebase.storage.IFirebaseStorageManager

interface IFirebaseManager {

    val userManager: IFirebaseUserManager

    val dbManger: IFirebaseDbManager

    val storageManager: IFirebaseStorageManager

    class Impl {
        val firebaseManager: IFirebaseManager = FirebaseManager()
    }
}
