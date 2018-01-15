package com.github.anastasiazhukova.flashlang.operations;

import com.github.anastasiazhukova.flashlang.api.models.translation.ITranslation;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.card.CardBuilder;
import com.github.anastasiazhukova.flashlang.domain.models.user.IUser;
import com.github.anastasiazhukova.flashlang.utils.OperationUtils;
import com.github.anastasiazhukova.lib.contracts.ICallback;
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
    public Void perform() {
        final String collectionId = OperationUtils.getCollectionId(mUser.getId(),
                mTranslation.getSourceLanguage().getLanguageCode(),
                mTranslation.getTargetLanguage().getLanguageCode());
        if (StringUtils.isNullOrEmpty(collectionId)) {
            createNewCollection(mUser, mTranslation, new ICallback<String>() {

                @Override
                public void onSuccess(final String pS) {
                    final Card card = buildCard(pS, mTranslation);
                    uploadCardToDb(card);
                    increaseUserAchivement(mUser.getId(),1,2);

                }

                @Override
                public void onError(final Throwable pThrowable) {

                }
            });
        } else {
            final Card card = buildCard(collectionId, mTranslation);
            uploadCardToDb(card);
            increaseUserAchivement(mUser.getId(),0,2);
        }

        return null;
    }

    private Card buildCard(final String pCollectionId, final ITranslation pTranslation) {
        final String sourceLanguage = pTranslation.getSourceLanguage().getLanguageCode();
        final String sourceText = pTranslation.getSourceText();
        final String targetLanguage = pTranslation.getTargetLanguage().getLanguageCode();
        final String translatedText = pTranslation.getTranslatedText();
        return new CardBuilder()
                .setId(OperationUtils.getIdForCard())
                .setOwnerId(pCollectionId)
                .setSourceLanguageKey(sourceLanguage)
                .setSourceText(sourceText)
                .setTargetLanguageKey(targetLanguage)
                .setTranslatedText(translatedText)
                .setPictureUrl("")
                .createCard();
    }

    private void uploadCardToDb(final Card pCard) {
        final IOperation operation = new UploadCardToDbOperation(pCard);
        mExecutor.execute(operation);
    }

    private void createNewCollection(final IUser pUser, final ITranslation pTranslation, final ICallback<String> pCallback) {
        final IOperation operation = new CreateCollectionOperation(pUser.getId(),
                pTranslation.getSourceLanguage().getLanguageCode(),
                pTranslation.getTargetLanguage().getLanguageCode(), pCallback);
        mExecutor.execute(operation);
    }

    private void increaseUserAchivement(final String pUserId, final int pConnectionsCount, final int pWordsCount) {
        final IOperation increaseUserAchievementOperation = new IncreaseUserAchivementOperation(pUserId, pConnectionsCount, pWordsCount);
        ThreadingManager.Imlp.getThreadingManager().getExecutor(ExecutorType.THREAD)
                .execute(increaseUserAchievementOperation);
    }

}
