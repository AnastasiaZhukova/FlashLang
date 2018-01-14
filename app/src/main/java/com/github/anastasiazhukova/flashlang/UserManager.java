package com.github.anastasiazhukova.flashlang;

import com.github.anastasiazhukova.flashlang.domain.models.user.User;

public class UserManager {

    private static User sCurrentUser;

    public static User getCurrentUser() {
        return sCurrentUser;
    }

    public static void setCurrentUser(final User pUser) {
        sCurrentUser = pUser;
    }

}
