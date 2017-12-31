package com.github.anastasiazhukova.lib.dbTest;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.anastasiazhukova.lib.BuildConfig;
import com.github.anastasiazhukova.lib.TestConstants;
import com.github.anastasiazhukova.lib.db.IDbOperations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = TestConstants.SDK_VERSION
)
public class DbTest {

    private static final String TABLE_NAME = "testTable";
    private static final int ELEMENT_COUNT = 50;
    private static final String KEY_INT = "mInt";
    private static final String KEY_LONG = "mLong";
    private static final String KEY_STRING = "mString";
    private IDbOperations mDbOperations;

    @Before
    public void setUp() {
        mDbOperations = IDbOperations.Impl.newInstance(RuntimeEnvironment.application, new TestDb());
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mDbOperations);
    }

    @Test
    public void insert() {
        Assert.assertEquals(1,
                mDbOperations.insert(TABLE_NAME, generateContentValues()));
    }

    @Test
    public void bulkInsert() {
        Assert.assertEquals(ELEMENT_COUNT,
                mDbOperations.bulkInsert(TABLE_NAME, generateContentValuesArray()));
    }

    @Test
    public void delete() {
        bulkInsert();
        Assert.assertEquals(5,
                mDbOperations.delete(TABLE_NAME, KEY_INT + " LIKE 1", null));

    }

    @Test
    public void query() {
        bulkInsert();
        final Cursor cursor = mDbOperations.query(TABLE_NAME, null, KEY_INT + " LIKE 1", null, null);
        int count = 0;
        while (cursor.moveToNext()) {
            count++;
            Assert.assertEquals("ELEMENT " + 1, cursor.getString(cursor.getColumnIndex(KEY_STRING)));
        }

        Assert.assertEquals(5, count);

        cursor.close();
    }

    @Test
    public void update() {
        bulkInsert();
        Assert.assertEquals(5, mDbOperations.update(TABLE_NAME, generateContentValues(), KEY_INT + " LIKE 1", null));
    }

    private ContentValues generateContentValues() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_INT, 10);
        contentValues.put(KEY_LONG, Long.MAX_VALUE);
        contentValues.put(KEY_STRING, "ONE ELEMENT");

        return contentValues;
    }

    private ContentValues[] generateContentValuesArray() {
        int count = 1;
        final ContentValues[] elements = new ContentValues[ELEMENT_COUNT];
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            elements[i] = new ContentValues();
            elements[i].put(KEY_INT, count);
            elements[i].put(KEY_LONG, Long.MAX_VALUE - i);
            elements[i].put(KEY_STRING, "ELEMENT " + count);

            if (count == 10) {
                count = 1;
            } else {
                count++;

            }
        }
        return elements;

    }
}
