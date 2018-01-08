package com.github.anastasiazhukova.flashlang.firebase.auth.response

import com.google.firebase.auth.FirebaseUser

interface IAuthResponse {

    val user: FirebaseUser?

    val error: Throwable?

}
