package com.github.anastasiazhukova.flashlang.firebase.auth.signin

import com.github.anastasiazhukova.flashlang.firebase.auth.IFirebaseUserManager
import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest

interface ISignInManager {

    fun signIn(pAuthRequest: IAuthRequest, pCallback: IFirebaseUserManager.IAuthCallback)

}