package com.github.anastasiazhukova.flashlang.firebase.auth.signin

import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.response.IAuthResponse

interface ISignInManager {

    fun signIn(pAuthRequest: IAuthRequest):IAuthResponse

}