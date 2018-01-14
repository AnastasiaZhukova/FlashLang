package com.github.anastasiazhukova.flashlang.ui.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.domain.models.card.Card;
import com.github.anastasiazhukova.flashlang.domain.models.card.ICard;
import com.github.anastasiazhukova.flashlang.ui.domain.IRecycleClickCallback;
import com.github.anastasiazhukova.flashlang.ui.domain.RecyclerClickListener;
import com.github.anastasiazhukova.flashlang.ui.viewholder.BaseCardViewHolder;
import com.github.anastasiazhukova.flashlang.ui.viewholder.CardWithImageViewHolder;
import com.github.anastasiazhukova.lib.utils.StringUtils;

public class CardsRecyclerViewCursorAdapter extends RecyclerView.Adapter<BaseCardViewHolder> implements RecyclerClickListener {

    private Cursor mCursor;
    private final IRecycleClickCallback<ICard> mCallback;
    private final Card.CursorConverter mConverter;
    private CardItemType mItemType;

    public CardsRecyclerViewCursorAdapter(final IRecycleClickCallback<ICard> pCallback) {
        mCallback = pCallback;
        mConverter = new Card.CursorConverter();
    }

    public void setCursor(final Cursor pCursor) {
        mCursor = pCursor;
        notifyDataSetChanged();
    }

    public void setCardType(final CardItemType pCardType) {
        mItemType = pCardType;
    }

    @Override
    public BaseCardViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        if (mItemType == CardItemType.CARD) {
            return getCardViewHolder(pParent, pViewType);
        } else if (mItemType == CardItemType.ROW) {
            return getCardRowViewHolder(pParent, pViewType);
        }
        return null;
    }

    private BaseCardViewHolder getCardViewHolder(final ViewGroup pParent, final int pViewType) {
        int viewId;
        switch (pViewType) {
            case CardType.NO_IMAGE: {
                viewId = R.layout.card_view_no_image;
            }
            case CardType.WITH_IMAGE: {
                viewId = R.layout.card_view_with_image;
                final View view = LayoutInflater.from(pParent.getContext()).inflate(viewId, pParent, false);
                return new CardWithImageViewHolder(view, this);
            }
            default: {
                viewId = R.layout.card_view_no_image;
            }
        }

        final View view = LayoutInflater.from(pParent.getContext()).inflate(viewId, pParent, false);
        return new BaseCardViewHolder(view, this);
    }

    private BaseCardViewHolder getCardRowViewHolder(final ViewGroup pParent, final int pViewType) {
        final int viewId = R.layout.card_row;
        final View view = LayoutInflater.from(pParent.getContext()).inflate(viewId, pParent, false);
        return new BaseCardViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(final BaseCardViewHolder pHolder, final int pPosition) {
        if (mCursor == null) {
            return;
        }
        mCursor.moveToPosition(pPosition);
        pHolder.setInfo(mConverter.convert(mCursor));
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        } else {
            return mCursor.getCount();
        }
    }

    @Override
    public int getItemViewType(final int position) {
        if (mCursor == null) {
            return 0;
        } else {
            mCursor.move(position);
            final Card card = mConverter.convert(mCursor);
            if (StringUtils.isNullOrEmpty(card.getPictureUrl())) {
                return CardType.NO_IMAGE;
            } else {
                return CardType.WITH_IMAGE;
            }
        }
    }

    @Override
    public void onClick(final int pPosition) {
        if (mCursor == null) {
            return;
        }
        mCursor.moveToPosition(pPosition);
        mCallback.onClick(mConverter.convert(mCursor));
    }
}
