package com.github.anastasiazhukova.flashlang.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.domain.models.collection.ICollection;
import com.github.anastasiazhukova.flashlang.ui.domain.RecyclerClickListener;

public abstract class BaseLanguageViewHolder extends RecyclerView.ViewHolder {

    protected final ImageView mImage;
    protected final TextView mLanguageName;

    public BaseLanguageViewHolder(final View pItemView, final RecyclerClickListener pClickListener) {
        super(pItemView);
        mImage = pItemView.findViewById(R.id.language_image_view);
        mLanguageName=pItemView.findViewById(R.id.language_name_text_view);
        pItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final int position = getAdapterPosition();
                pClickListener.onClick(position);
            }
        });
    }

    public abstract void setInfo(ICollection pCollection);
}
