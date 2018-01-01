package com.github.anastasiazhukova.flashlang.dbTest;

import com.github.anastasiazhukova.flashlang.BuildConfig;
import com.github.anastasiazhukova.flashlang.TestConstants;
import com.github.anastasiazhukova.flashlang.db.connector.DbTableConnector;
import com.github.anastasiazhukova.flashlang.db.connector.IDbTableConnector;
import com.github.anastasiazhukova.lib.db.IDbOperations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = TestConstants.SDK_VERSION)
public class DbConnectorTest {

    private DbTableConnector<TestModel> mDbTableConnector;

    @Before
    public void setUp() throws Exception {
        IDbOperations.Impl.newInstance(RuntimeEnvironment.application, new TestDb());
        mDbTableConnector = new DbTableConnector<>(TestModel.TABLE_NAME);
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mDbTableConnector);
    }

    @Test
    public void insert() {
        final boolean inserted = mDbTableConnector.insert(new TestModel("0", "Insert 0"));
        Assert.assertTrue(inserted);
    }

    @Test
    public void bulkInsert() {
        final TestModel[] models = new TestModel[20];
        for (int i = 0; i < 10; i++) {
            models[i] = new TestModel(String.valueOf(i), "Model " + i);
        }
        for (int i = 0; i < 10; i++) {
            models[i + 10] = new TestModel(String.valueOf(i * 10), "Model " + i);
        }
        final boolean inserted = mDbTableConnector.insert(models);
        Assert.assertTrue(inserted);
    }

    @Test
    public void delete() {
        bulkInsert();
        final boolean deleted = mDbTableConnector.delete(new IDbTableConnector.ISelector.ByFieldSelector(TestModel.STRING_KEY, "Model 2"));
        Assert.assertTrue(deleted);
    }

    @Test
    public void get() {
        bulkInsert();
        final List<TestModel> testModels = mDbTableConnector.get(new IDbTableConnector.ISelector.ByFieldSelector(TestModel.STRING_KEY, "Model 2"),
                new TestModel.CursorConverter());

        Assert.assertEquals(2, testModels.size());
        Assert.assertEquals("Model 2", testModels.get(0).getSomeString());

    }

    @Test
    public void update() {
        bulkInsert();
        final TestModel model = new TestModel("1", "NEW STRING");
        final boolean updated = mDbTableConnector.update(model, new IDbTableConnector.ISelector.ByFieldSelector(TestModel.STRING_KEY, "Model 2"));
        Assert.assertTrue(updated);
    }

    @After
    public void tearDown() throws Exception {
        resetSingleton(IDbOperations.Impl.class);
    }

    private void resetSingleton(final Class clazz) {
        final Field instance;
        try {
            instance = clazz.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (final Exception e) {
            throw new RuntimeException();
        }
    }
}
