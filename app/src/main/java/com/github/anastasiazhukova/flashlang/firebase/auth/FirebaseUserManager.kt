package com.github.anastasiazhukova.flashlang.firebase.auth

import com.github.anastasiazhukova.flashlang.firebase.auth.request.IAuthRequest
import com.github.anastasiazhukova.flashlang.firebase.auth.signin.SignInManager
import com.github.anastasiazhukova.flashlang.firebase.auth.signup.SignUpManager
import com.github.anastasiazhukova.flashlang.domain.models.user.IUser
import com.google.firebase.auth.FirebaseAuth

class FirebaseUserManager : IFirebaseUserManager {


    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun getCurrentUser(): IUser? {
        val currentUser = mAuth.currentUser
        val factory = UserFactory()
        return if (currentUser != null) {
            factory.createUser(currentUser)
        } else {
            return null
        }
    }

    override fun logIn(pRequest: IAuthRequest) = SignInManager().signIn(pRequest)

    override fun register(pRequest: IAuthRequest) = SignUpManager().signUp(pRequest)

    override fun singOut() {
        mAuth.signOut()
    }
}
