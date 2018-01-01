package com.github.anastasiazhukova.flashlang.db;

import android.content.ContentValues;

public interface IDbModel {

    ContentValues convertToInsert();

    ContentValues convertToUpdate();
}
