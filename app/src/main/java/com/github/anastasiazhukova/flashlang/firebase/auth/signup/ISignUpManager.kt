package com.github.anastasiazhukova.flashlang.firebase.auth.signup

import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.response.IAuthResponse

interface ISignUpManager {

    fun signUp(pRequest: IAuthRequest): IAuthResponse
}