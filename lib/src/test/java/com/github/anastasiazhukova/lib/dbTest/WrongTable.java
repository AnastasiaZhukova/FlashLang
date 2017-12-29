package com.github.anastasiazhukova.lib.dbTest;

import com.github.anastasiazhukova.lib.db.annotations.dbTable;
import com.github.anastasiazhukova.lib.db.annotations.type.dbForeignKey;

@dbTable(name = "wrongTable")
public class WrongTable {

    @dbForeignKey(referredTableName = "someTable", referredTableColumnName = "someColumn")
    int someInt;

}
