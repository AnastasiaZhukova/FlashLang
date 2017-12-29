package com.github.anastasiazhukova.lib.dbTest;

import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;
import com.github.anastasiazhukova.lib.db.annotations.type.dbInteger;
import com.github.anastasiazhukova.lib.db.annotations.type.dbLong;
import com.github.anastasiazhukova.lib.db.annotations.type.dbString;

@dbTable(name = "testTable")
public class CorrectTable {

    @dbForeignKey(referredTableName = "someTable", referredTableColumnName = "someColumn")
    @dbInteger(name = "mInt")
    int mInt;

    @dbForeignKey(referredTableName = "someTable", referredTableColumnName = "someColumn")
    @dbLong(name = "mLong")
    long mLong;

    @dbForeignKey(referredTableName = "someTable", referredTableColumnName = "someColumn")
    @dbString(name = "mString")
    String mString;
}
