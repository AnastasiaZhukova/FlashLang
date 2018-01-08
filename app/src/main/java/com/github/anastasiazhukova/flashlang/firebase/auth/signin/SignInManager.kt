package com.github.anastasiazhukova.flashlang.firebase.auth.signin

import com.github.anastasiazhukova.flashlang.firebase.auth.request.EmailAuthInfo
import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.response.AuthResponse
import com.github.anastasiazhukova.flashlang.firebase.auth.response.IAuthResponse
import com.github.anastasiazhukova.flashlang.firebase.utils.AuthUtils
import com.google.firebase.auth.FirebaseAuth

class SignInManager : ISignInManager {

    private val mAuth: FirebaseAuth
        get() = FirebaseAuth.getInstance()

    override fun signIn(pAuthRequest: IAuthRequest): IAuthResponse {
        val authInfo = pAuthRequest.authInfo
        return when (authInfo) {
            is EmailAuthInfo -> authWithEmail(authInfo)
            else -> {
                val response = AuthResponse()
                response.error = IllegalStateException("No such auth type")
                response
            }
        }
    }

    private fun authWithEmail(pAuthInfo: EmailAuthInfo): IAuthResponse {
        val email = pAuthInfo.email
        val password = pAuthInfo.password
        var response: IAuthResponse = AuthResponse()

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener({ task ->
                    response = AuthUtils.getResponse(task)
                })

        return response
    }

}