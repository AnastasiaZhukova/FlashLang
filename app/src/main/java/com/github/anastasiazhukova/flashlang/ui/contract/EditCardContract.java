package com.github.anastasiazhukova.flashlang.ui.contract;

import android.graphics.drawable.Drawable;

import com.github.anastasiazhukova.flashlang.domain.models.card.ICard;
import com.github.anastasiazhukova.flashlang.ui.presenter.BasePresenter;

public interface EditCardContract {

    interface View {

        Drawable getCardPicture();

        String getSourceText();

        String getTranslatedText();

    }

    interface Presenter extends BasePresenter<EditCardContract.View> {

        ICard getCard();

        void edit();
    }

}
