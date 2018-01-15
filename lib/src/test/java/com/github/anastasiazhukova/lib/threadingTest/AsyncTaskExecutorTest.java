package com.github.anastasiazhukova.lib.threadingTest;

import com.github.anastasiazhukova.lib.BuildConfig;
import com.github.anastasiazhukova.lib.TestConstants;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.threading.executors.AsyncTaskExecutor;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = TestConstants.SDK_VERSION
)
public class AsyncTaskExecutorTest {

    @Captor
    private
    ArgumentCaptor<TestCallback> mCaptor;

    private IExecutor mExecutor;

    @Before
    public void setUp() {
        mExecutor = spy(getExecutor());
        mCaptor = ArgumentCaptor.forClass(TestCallback.class);
    }

    private IExecutor getExecutor() {
        return new AsyncTaskExecutor(AsyncTaskExecutor.Config.getDefaultConfig());
    }

    @Test
    public void execute() {
        final TestOperation executable = new TestOperation();
        final ICallback<String> testCallback = new TestCallback();
        mExecutor.execute(executable, testCallback);
        verify(mExecutor).execute(eq(executable), mCaptor.capture());
        final TestCallback callback = mCaptor.getValue();
        Assert.assertEquals("Success", callback.getMessage());

    }

}
