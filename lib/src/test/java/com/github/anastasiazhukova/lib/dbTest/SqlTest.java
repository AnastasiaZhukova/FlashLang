package com.github.anastasiazhukova.lib.dbTest;

import com.github.anastasiazhukova.lib.db.utils.SqlUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SqlTest {

    private static final String CORRECT_SQL = "CREATE TABLE IF NOT EXISTS testTable (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "mInt INTEGER," +
            "mLong BIGINT," +
            "mString TEXT," +
            "FOREIGN KEY (mInt) REFERENCES someTable(someColumn)," +
            "FOREIGN KEY (mLong) REFERENCES someTable(someColumn)," +
            "FOREIGN KEY (mString) REFERENCES someTable(someColumn))";

    @Test
    public void getCreateCorrectTableSql() throws Exception {
        assertEquals(CORRECT_SQL, SqlUtils.getCreateTableSql(CorrectTableElement.class));
    }

    @Test
    public void getCreateWrongTableSQL() throws Exception {
        assertNull(SqlUtils.getCreateTableSql(WrongTable.class));
    }
}