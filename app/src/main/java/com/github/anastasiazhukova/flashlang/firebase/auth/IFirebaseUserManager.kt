package com.github.anastasiazhukova.flashlang.firebase.auth

import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.lib.contracts.ICallback
import com.google.firebase.auth.FirebaseUser

interface IFirebaseUserManager {

    fun getCurrentUser(): FirebaseUser?

    fun signUp(pRequest: IAuthRequest, pCallback: IAuthCallback)

    fun signIn(pRequest: IAuthRequest, pCallback: IAuthCallback)

    fun singOut()

    interface IAuthCallback : ICallback<FirebaseUser>

    class Impl {
        companion object {
            fun getInstance(): IFirebaseUserManager {
                return FirebaseUserManager();
            }
        }
    }

}
