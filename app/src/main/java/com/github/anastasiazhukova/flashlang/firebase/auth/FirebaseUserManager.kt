package com.github.anastasiazhukova.flashlang.firebase.auth

import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.signin.SignInManager
import com.github.anastasiazhukova.flashlang.firebase.auth.signup.SignUpManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseUserManager : IFirebaseUserManager {


    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    override fun signIn(pRequest: IAuthRequest, pCallback: IFirebaseUserManager.IAuthCallback) {
        SignInManager().signIn(pRequest, pCallback)
    }

    override fun signUp(pRequest: IAuthRequest, pCallback: IFirebaseUserManager.IAuthCallback) {
        SignUpManager().signUp(pRequest, pCallback)
    }

    override fun singOut() {
        mAuth.signOut()
    }
}
