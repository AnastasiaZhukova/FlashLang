package com.github.anastasiazhukova.flashlang.utils;

import com.github.anastasiazhukova.flashlang.firebase.auth.FirebaseUserManager;
import com.google.firebase.auth.FirebaseUser;

public final class UserUtils {

    public static String getCurrentUserId() {
        final FirebaseUser currentUser = FirebaseUserManager.Impl.Companion.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUid();
        } else {
            throw new IllegalStateException("Current user is null!");
        }
    }

}
