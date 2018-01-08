package com.github.anastasiazhukova.flashlang.domain.models.user;

import com.github.anastasiazhukova.flashlang.db.IDbModel;

public interface IUser extends IDbModel<String> {

    String getName();

    String getPictureUrl();
}
