package com.github.anastasiazhukova.flashlang.firebase

import com.github.anastasiazhukova.flashlang.firebase.auth.FirebaseUserManager
import com.github.anastasiazhukova.flashlang.firebase.auth.IFirebaseUserManager
import com.github.anastasiazhukova.flashlang.firebase.db.FirebaseDbManger
import com.github.anastasiazhukova.flashlang.firebase.db.IFirebaseDbManager
import com.github.anastasiazhukova.flashlang.firebase.storage.FirebaseStorageManager
import com.github.anastasiazhukova.flashlang.firebase.storage.IFirebaseStorageManager

class FirebaseManager : IFirebaseManager {

    override val userManager: IFirebaseUserManager
        get() = FirebaseUserManager()

    override val dbManger: IFirebaseDbManager
        get() = FirebaseDbManger()

    override val storageManager: IFirebaseStorageManager
        get() = FirebaseStorageManager()
}
