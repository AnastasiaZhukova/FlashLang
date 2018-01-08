package com.github.anastasiazhukova.flashlang.firebase.auth

import com.github.anastasiazhukova.flashlang.domain.models.user.IUser
import com.github.anastasiazhukova.flashlang.domain.models.user.User
import com.google.firebase.auth.FirebaseUser

class UserFactory {

    fun createUser(pUser: FirebaseUser): IUser {
        return User(pUser)
    }
}