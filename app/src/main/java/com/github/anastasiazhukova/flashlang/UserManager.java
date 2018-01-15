package com.github.anastasiazhukova.flashlang;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;

public final class UserManager {

    private static User sCurrentUser;
    private static final Object lock = new Object();

    public static User getCurrentUser() {
        synchronized (lock) {
            return sCurrentUser;
        }
    }

    public static void setCurrentUser(final User pUser) {
        sCurrentUser = pUser;
    }

}
