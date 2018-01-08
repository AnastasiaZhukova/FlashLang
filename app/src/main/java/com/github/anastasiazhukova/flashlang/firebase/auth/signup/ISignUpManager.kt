package com.github.anastasiazhukova.flashlang.firebase.auth.signup

import com.github.anastasiazhukova.flashlang.firebase.auth.IFirebaseUserManager
import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest

interface ISignUpManager {

    fun signUp(pRequest: IAuthRequest, pCallback: IFirebaseUserManager.IAuthCallback)
}