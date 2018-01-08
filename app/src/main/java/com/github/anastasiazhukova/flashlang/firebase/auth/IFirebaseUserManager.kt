package com.github.anastasiazhukova.flashlang.firebase.auth

import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.response.IAuthResponse
import com.github.anastasiazhukova.flashlang.domain.models.user.IUser

interface IFirebaseUserManager {

    fun getCurrentUser(): IUser?

    fun register(pRequest: IAuthRequest): IAuthResponse?

    fun logIn(pRequest: IAuthRequest): IAuthResponse?

    fun singOut()

}
