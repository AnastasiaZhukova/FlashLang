package com.github.anastasiazhukova.flashlang.ui.viewholder;

import android.view.View;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.api.utils.LanguageUtils;
import com.github.anastasiazhukova.flashlang.domain.models.collection.ICollection;
import com.github.anastasiazhukova.flashlang.ui.domain.RecyclerClickListener;
import com.github.anastasiazhukova.flashlang.utils.DrawableUtils;
import com.github.anastasiazhukova.lib.imageloader.ILouvre;
import com.github.anastasiazhukova.lib.logs.Log;

public class TargetLanguageViewHolder extends BaseLanguageViewHolder {

    private static final String LOG_TAG = TargetLanguageViewHolder.class.getSimpleName();

    public TargetLanguageViewHolder(final View pItemView, final RecyclerClickListener pClickListener) {
        super(pItemView, pClickListener);
    }

    @Override
    public void setInfo(final ICollection pCollection) {
        mLanguageName.setText(LanguageUtils.getLanguageName(pCollection.getTargetLanguage()));
        try {
            ILouvre.Impl.getInstance()
                    .newRequest()
                    .to(mImage)
                    .from(pCollection.getTargetLanguageCover())
                    .setErrorImage(DrawableUtils.bitmapFromDrawable(R.drawable.moon))
                    .load();
        } catch (final Exception pE) {
            Log.e(LOG_TAG, "setInfo: ", pE);
        }

    }
}
