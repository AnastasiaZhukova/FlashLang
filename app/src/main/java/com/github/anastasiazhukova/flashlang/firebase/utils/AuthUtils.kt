package com.github.anastasiazhukova.flashlang.firebase.utils

import com.github.anastasiazhukova.flashlang.firebase.auth.UserFactory
import com.github.anastasiazhukova.flashlang.firebase.auth.response.AuthResponse
import com.github.anastasiazhukova.flashlang.firebase.auth.response.IAuthResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class AuthUtils {
    companion object {
        fun getResponse(pTask: Task<AuthResult>): IAuthResponse {
            val response = AuthResponse()
            if (pTask.isSuccessful) {
                val user = pTask.result.user!!
                response.user = UserFactory().createUser(user)
            } else {
                response.error = pTask.exception!!
            }
            return response
        }
    }
}