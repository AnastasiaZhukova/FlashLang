package com.github.anastasiazhukova.flashlang.apiTest;

import android.content.Context;

import com.github.anastasiazhukova.flashlang.api.utils.LanguageUtils;
import com.github.anastasiazhukova.lib.BuildConfig;
import com.github.anastasiazhukova.lib.TestConstants;

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
public class LanguageTest {

    private Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = RuntimeEnvironment.application;
    }

    @Test
    public void shouldNotBeNull() {
        Assert.assertNotNull(mContext);
    }

    @Test
    public void getLanguageNameFromResource() throws Exception{

        final String languageName = LanguageUtils.getLanguageName(mContext, "ru");
        Assert.assertEquals("Russian", languageName);

    }
}
