package com.github.anastasiazhukova.flashlang.ui.presenter;

import com.github.anastasiazhukova.flashlang.api.ITranslator;
import com.github.anastasiazhukova.flashlang.api.models.languages.ILanguage;
import com.github.anastasiazhukova.flashlang.api.models.translation.ITranslation;
import com.github.anastasiazhukova.flashlang.api.request.ITranslationRequest;
import com.github.anastasiazhukova.flashlang.api.request.TranslationRequestBuilder;
import com.github.anastasiazhukova.flashlang.operations.GetTranslateApiKeyOperation;
import com.github.anastasiazhukova.flashlang.operations.TranslateOperation;
import com.github.anastasiazhukova.flashlang.ui.contract.TranslateContract;
import com.github.anastasiazhukova.flashlang.utils.ConnectionManager;
import com.github.anastasiazhukova.flashlang.utils.UiPublisher;
import com.github.anastasiazhukova.lib.context.ContextHolder;
import com.github.anastasiazhukova.lib.contracts.ICallback;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.logs.Log;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.IThreadingManager;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;

import java.util.List;

public class TranslatePresenter implements TranslateContract.Presenter {

    private static final String LOG_TAG = TranslatePresenter.class.getSimpleName();

    private TranslateContract.View mView;
    private final UiPublisher mUiPublisher;
    private String mApiKey;
    private List<ILanguage> mSupportedLanguages;
    private final IThreadingManager mThreadingManager;

    public TranslatePresenter() {
        mUiPublisher = new UiPublisher();
        mThreadingManager = ThreadingManager.Imlp.getThreadingManager();
    }

    @Override
    public void attachView(final TranslateContract.View pView) {
        mView = pView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void translate() {
        if (mView != null) {

            if (!ConnectionManager.isNetworkAvailable()) {
                publishTranslationError(new Exception("Network is not available"));
            }

            try {
                final TranslationRequestBuilder builder = new TranslationRequestBuilder();
                builder.setSourceLanguageKey(mView.getSourceLanguage());
                builder.setTargetLanguageKey(mView.getTargetLanguage());
                builder.setInputText(mView.getSourceText());

                if (mApiKey == null) {
                    final IOperation getTranslateApiKeyOperation = new GetTranslateApiKeyOperation(new ICallback<String>() {

                        @Override
                        public void onSuccess(final String pS) {
                            Log.d(LOG_TAG, "onSuccess() called with: pS = [" + pS + "]");
                            mApiKey = pS;
                            builder.setApiKey(pS);
                            performTranslation(builder.createTranslationRequest());
                        }

                        @Override
                        public void onError(final Throwable pThrowable) {
                            Log.d(LOG_TAG, "onError() called with: pThrowable = [" + pThrowable + "]");
                            publishTranslationError(pThrowable);
                        }
                    });
                    mThreadingManager.getExecutor(ExecutorType.THREAD)
                            .execute(getTranslateApiKeyOperation);
                } else {
                    builder.setApiKey(mApiKey);
                    performTranslation(builder.createTranslationRequest());
                }
            } catch (Exception pE) {
                publishTranslationError(pE);
            }
        }

    }

    @Override
    public List<ILanguage> loadSupportedLanguages() {
        if (mSupportedLanguages == null) {
            final List<ILanguage> supportedLanguages = ITranslator.Impl.getTranslator(ContextHolder.getContext())
                    .getSupportedLanguages();
            if (!supportedLanguages.isEmpty()) {
                mSupportedLanguages = supportedLanguages;
            }
        }
        return mSupportedLanguages;
    }

    private void publishTranslation(final ITranslation pTranslation) {
        if (mView != null) {
            mView.onTranslationSuccess(pTranslation.getTranslatedText());
        }
    }

    private void publishTranslationError(final Throwable pThrowable) {
        if (mView != null) {
            mView.onTranslateError(pThrowable.getMessage());
        }

    }

    private void performTranslation(final ITranslationRequest pRequest) {
        final IOperation<ITranslation> translateOperation = new TranslateOperation(pRequest);
        mThreadingManager.getExecutor(ExecutorType.THREAD).execute(translateOperation, new ICallback<ITranslation>() {

            @Override
            public void onSuccess(final ITranslation pTranslation) {
                publishTranslation(pTranslation);
            }

            @Override
            public void onError(final Throwable pThrowable) {
                publishTranslationError(pThrowable);
            }
        });
    }

}
