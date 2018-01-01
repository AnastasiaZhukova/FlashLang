package com.github.anastasiazhukova.flashlang.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface IDbModel<Element> {

    ContentValues convertToInsert();

    ContentValues convertToUpdate();
}
