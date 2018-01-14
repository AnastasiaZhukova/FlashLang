package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.api.ITranslator;
import com.github.anastasiazhukova.flashlang.api.models.translation.ITranslation;
import com.github.anastasiazhukova.flashlang.api.request.ITranslationRequest;
import com.github.anastasiazhukova.lib.context.ContextHolder;
import com.github.anastasiazhukova.lib.contracts.IOperation;

public class TranslateOperation implements IOperation<ITranslation> {

    private static final String LOG_TAG = TranslateOperation.class.getSimpleName();

    private final ITranslationRequest mRequest;

    public TranslateOperation(final ITranslationRequest pRequest) {
        mRequest = pRequest;
    }

    @Override
    public ITranslation perform() throws Exception {
        return ITranslator.Impl.getTranslator(ContextHolder.getContext())
                .translate(mRequest);
    }

}
