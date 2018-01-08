package com.github.anastasiazhukova.flashlang.firebase.auth.response

import com.github.anastasiazhukova.flashlang.domain.models.user.IUser

interface IAuthResponse {

    val user: IUser?

    val error: Throwable?

}
