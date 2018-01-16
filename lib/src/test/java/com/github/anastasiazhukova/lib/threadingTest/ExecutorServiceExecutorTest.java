package com.github.anastasiazhukova.lib.threadingTest;

import android.os.Handler;

import com.github.anastasiazhukova.lib.TestConstants;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.threading.executors.ExecutorServiceExecutor;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.threading.publisher.IPublisher;
import com.github.anastasiazhukova.lib.threading.publisher.Publisher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.Scheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

//TODO fix
@RunWith(RobolectricTestRunner.class)
@Config(
        sdk = TestConstants.SDK_VERSION
)
public class ExecutorServiceExecutorTest {

    @Captor
    private ArgumentCaptor<TestCallback> mCaptor;
    private IExecutor mExecutor;

    private IPublisher mPublisher;

    @Before
    public void setUp() {
        mExecutor = spy(getExecutor());
        mCaptor = ArgumentCaptor.forClass(TestCallback.class);
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mPublisher);
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

    private IExecutor getExecutor() {
        final Handler mockedHandler = mock(Handler.class);
        final Publisher publisher = new Publisher(mockedHandler);
        mPublisher = spy(publisher);
        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(final InvocationOnMock invocation) {
                final Runnable runnable = invocation.getArgument(0);

                final Scheduler backgroundThreadScheduler = Robolectric.getBackgroundThreadScheduler();

                backgroundThreadScheduler.pause();
                backgroundThreadScheduler.post(runnable);
                Robolectric.flushBackgroundThreadScheduler();
                backgroundThreadScheduler.unPause();

                return null;
            }
        }).when(mockedHandler).post(any(Runnable.class));
        return new ExecutorServiceExecutor(mPublisher, ExecutorServiceExecutor.Config.getDefaultConfig());
    }
}
