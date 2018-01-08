package com.github.anastasiazhukova.flashlang.firebase.auth.response

import com.github.anastasiazhukova.flashlang.domain.models.user.IUser

class AuthResponse : IAuthResponse {

    override var user: IUser? = null

    override var error: Throwable? = null
}
