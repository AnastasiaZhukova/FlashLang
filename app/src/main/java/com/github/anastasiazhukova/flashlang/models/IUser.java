package com.github.anastasiazhukova.flashlang.models;

public interface IUser extends IIdentifiable<String> {

    String getName();

    String getPictureUrl();
}
