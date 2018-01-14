package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.api.models.translation.ITranslation;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.card.CardBuilder;
import com.github.anastasiazhukova.flashlang.domain.models.user.IUser;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.IOperation;
import com.github.anastasiazhukova.lib.threading.ExecutorType;
import com.github.anastasiazhukova.lib.threading.ThreadingManager;
import com.github.anastasiazhukova.lib.threading.executors.IExecutor;
import com.github.anastasiazhukova.lib.utils.StringUtils;

public class SaveTranslationOperation implements IOperation<Void> {

    private static final String LOG_TAG = SaveTranslationOperation.class.getSimpleName();

    private final ITranslation mTranslation;
    private final IUser mUser;
    private final IExecutor mExecutor;

    public SaveTranslationOperation(final IUser pOwner, final ITranslation pTranslation) {
        mTranslation = pTranslation;
        mUser = pOwner;
        mExecutor = ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.EXECUTOR_SERVICE);
    }

    @Override
    public Void perform() throws Exception {
        final Card card = buildCard(mTranslation);
        uploadCardToDb(card);
        final String collectionId = OperationUtils.getCollectionId(mUser.getId(),
                mTranslation.getSourceLanguage().getLanguageCode(),
                mTranslation.getTargetLanguage().getLanguageCode());
        if (StringUtils.isNullOrEmpty(collectionId)) {
            createNewCollection(mUser, mTranslation);
        }
        return null;
    }

    private Card buildCard(final ITranslation pTranslation) {
        final String sourceLanguage = pTranslation.getSourceLanguage().getLanguageCode();
        final String sourceText = pTranslation.getSourceText();
        final String targetLanguage = pTranslation.getTargetLanguage().getLanguageCode();
        final String translatedText = pTranslation.getTranslatedText();
        return new CardBuilder()
                .setId(OperationUtils.getIdForCard())
                .setOwnerId(OperationUtils.getCollectionId(mUser.getId(), sourceLanguage, targetLanguage))
                .setSourceLanguageKey(sourceLanguage)
                .setSourceText(sourceText)
                .setTargetLanguageKey(targetLanguage)
                .setTranslatedText(translatedText)
                .createCard();
    }

    private void uploadCardToDb(final Card pCard) {
        final IOperation operation = new UploadCardToDbOperation(pCard);
        mExecutor.execute(operation);
    }

    private void createNewCollection(final IUser pUser, final ITranslation pTranslation) {
        final IOperation operation = new CreateCollectionOperation(pUser.getId(),
                pTranslation.getSourceLanguage().getLanguageCode(),
                pTranslation.getTargetLanguage().getLanguageCode());
        mExecutor.execute(operation);
    }

}
