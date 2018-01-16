package com.github.anastasiazhukova.lib.threadingTest;

import com.github.anastasiazhukova.lib.BuildConfig;
import com.github.anastasiazhukova.lib.TestConstants;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.executors.AsyncTaskExecutor;
import com.github.anastasiazhukova.lib.threading.executors.ExecutorServiceExecutor;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.threading.executors.ThreadExecutor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = TestConstants.SDK_VERSION
)
public class ThreadingManagerTest {

    private IThreadingManager mThreadingManager;

    @Before
    public void setUp() {
        final IThreadingManager.Config config = new IThreadingManager.Config();
        mThreadingManager = IThreadingManager.Imlp.getThreadingManager();
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mThreadingManager);
    }

    @Test
    public void getAsyncTask() {
        final IExecutor executor = mThreadingManager.getExecutor(ExecutorType.ASYNC_TASK);
        Assert.assertTrue(executor instanceof AsyncTaskExecutor);
    }

    @Test
    public void getExecutorService() {
        final IExecutor executor = mThreadingManager.getExecutor(ExecutorType.EXECUTOR_SERVICE);
        Assert.assertTrue(executor instanceof ExecutorServiceExecutor);
    }

    @Test
    public void getThread() {
        final IExecutor executor = mThreadingManager.getExecutor(ExecutorType.THREAD);
        Assert.assertTrue(executor instanceof ThreadExecutor);
    }
}
