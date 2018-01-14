package com.github.anastasiazhukova.flashlang.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.domain.models.card.ICard;
import com.github.anastasiazhukova.flashlang.ui.domain.RecyclerClickListener;

public class BaseCardViewHolder extends RecyclerView.ViewHolder {

    private final TextView mSourceTextView;
    private final TextView mTargetTextView;

    public BaseCardViewHolder(final View pItemView, final RecyclerClickListener pClickListener) {
        super(pItemView);
        mSourceTextView = pItemView.findViewById(R.id.source_text_view);
        mTargetTextView = pItemView.findViewById(R.id.target_text_view);
        pItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final int position = getAdapterPosition();
                pClickListener.onClick(position);
            }
        });
    }

    public void setInfo(final ICard pCard) {
        mSourceTextView.setText(pCard.getSourceText());
        mTargetTextView.setText(pCard.getTranslatedText());
    }

}
