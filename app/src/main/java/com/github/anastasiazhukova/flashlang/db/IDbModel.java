package com.github.anastasiazhukova.flashlang.db;

import com.github.anastasiazhukova.flashlang.domain.models.IIdentifiable;

import java.util.HashMap;

public interface IDbModel<IdType> extends IIdentifiable<IdType> {

    HashMap<String, Object> convertToInsert();

    HashMap<String, Object> convertToUpdate();
}
