package com.github.anastasiazhukova.flashlang.domain.models.user;

import com.github.anastasiazhukova.flashlang.domain.models.IIdentifiable;

public interface IUser extends IIdentifiable<String> {

    String getName();

    String getPictureUrl();

    int getWordCount();

    int getConnectionCount();
}
