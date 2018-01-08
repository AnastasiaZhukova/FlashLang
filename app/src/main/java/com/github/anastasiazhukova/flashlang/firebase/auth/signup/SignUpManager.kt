package com.github.anastasiazhukova.flashlang.firebase.auth.signup

import com.github.anastasiazhukova.flashlang.firebase.auth.request.EmailAuthInfo
import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.response.AuthResponse
import com.github.anastasiazhukova.flashlang.firebase.auth.response.IAuthResponse
import com.github.anastasiazhukova.flashlang.firebase.utils.AuthUtils
import com.google.firebase.auth.FirebaseAuth

class SignUpManager : ISignUpManager {

    private val mAuth: FirebaseAuth
        get() = FirebaseAuth.getInstance()

    override fun signUp(pRequest: IAuthRequest): IAuthResponse {
        val authInfo = pRequest.authInfo
        return when (authInfo) {
            is EmailAuthInfo -> signUpWithEmail(authInfo)
            else -> {
                val response = AuthResponse()
                response.error = IllegalStateException("No such auth type")
                response
            }
        }
    }

    private fun signUpWithEmail(pRequestInfo: EmailAuthInfo): IAuthResponse {
        val email = pRequestInfo.email
        val password = pRequestInfo.password
        var response: IAuthResponse = AuthResponse()

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener({ task ->
                    response = AuthUtils.getResponse(task)
                })

        return response
    }


}