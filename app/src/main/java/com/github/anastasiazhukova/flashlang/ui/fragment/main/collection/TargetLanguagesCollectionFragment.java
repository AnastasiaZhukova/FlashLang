package com.github.anastasiazhukova.flashlang.ui.fragment.main.collection;

import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.domain.models.collection.ICollection;
import com.github.anastasiazhukova.flashlang.ui.adapter.LanguageItemType;
import com.github.anastasiazhukova.flashlang.ui.adapter.LanguagesRecyclerViewCursorAdapter;
import com.github.anastasiazhukova.flashlang.ui.contract.TargetLanguagesCollectionContract;
import com.github.anastasiazhukova.flashlang.ui.domain.IRecycleClickCallback;
import com.github.anastasiazhukova.flashlang.ui.presenter.TargetLanguagesCollectionPresenter;
import com.github.anastasiazhukova.flashlang.utils.DrawableUtils;
import com.github.anastasiazhukova.flashlang.utils.SystemConfigUtils;
import com.github.anastasiazhukova.lib.imageloader.ILouvre;
import com.github.anastasiazhukova.lib.logs.Log;

public class TargetLanguagesCollectionFragment extends Fragment implements TargetLanguagesCollectionContract.View, View.OnClickListener {

    private static final String LOG_TAG = TargetLanguagesCollectionFragment.class.getSimpleName();
    public static final String SOURCE_LANGUAGE_KEY = "sourcelangkey";

    private TargetLanguagesCollectionContract.Presenter mPresenter;
    private View mView;
    private ImageView mSourceLanguageImageView;
    private String mSourceLanguageImage;
    private RecyclerView mRecyclerView;
    private LanguagesRecyclerViewCursorAdapter mAdapter;
    private IChildFragmentListener<ICollection> mListener;
    private String mSourceLanguageKey;

    public TargetLanguagesCollectionFragment() {
    }

    public void setChildFragmentListener(final IChildFragmentListener<ICollection> pListener) {
        mListener = pListener;
    }

    public void setSourceLanguageKey(final String pSourceLanguageKey) {
        mSourceLanguageKey = pSourceLanguageKey;
    }

    public void setSourceLanguageImage(final String pUrl) {
        mSourceLanguageImage = pUrl;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (mPresenter == null) {
            mPresenter = new TargetLanguagesCollectionPresenter();
        }
        if (savedInstanceState != null && mSourceLanguageKey == null) {
            mSourceLanguageKey = savedInstanceState.getString(SOURCE_LANGUAGE_KEY);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart() called");
        mPresenter.attachView(this);
        initRecyclerView();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater, @Nullable final ViewGroup pContainer, @Nullable final Bundle pSavedInstanceState) {
        final ViewGroup rootView = (ViewGroup) pInflater.inflate(R.layout.fragment_target_languages, pContainer, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View pView, @Nullable final Bundle pSavedInstanceState) {
        super.onViewCreated(pView, pSavedInstanceState);
        mView = pView;
        init();
    }

    @Override
    public void onClick(final View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.source_language_image_view: {
                mListener.onBackClick();
            }
        }
    }

    private void init() {
        mRecyclerView = mView.findViewById(R.id.target_languages_recycler_view);
        mSourceLanguageImageView = mView.findViewById(R.id.source_language_image_view);
        mSourceLanguageImageView.setOnClickListener(this);
        loadSourceImage();
    }

    private void loadSourceImage() {
        try {
            ILouvre.Impl.getInstance()
                    .newRequest()
                    .to(mSourceLanguageImageView)
                    .from(mSourceLanguageImage)
                    .setErrorImage(DrawableUtils.bitmapFromDrawable(R.drawable.moon))
                    .load();
        } catch (Exception pE) {
        }
    }

    private void initRecyclerView() {
        mAdapter = new LanguagesRecyclerViewCursorAdapter(new IRecycleClickCallback<ICollection>() {

            @Override
            public void onClick(final ICollection pElement) {
                mListener.onItemClick(pElement);
            }
        });
        mAdapter.setLanguageItemType(LanguageItemType.TARGET);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(getOrientation());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.load();

    }

    @Override
    public String getSourceLanguageKey() {
        return mSourceLanguageKey;
    }

    @Override
    public void onLoaded(final Cursor pCursor) {
        mAdapter.setCursor(pCursor);
    }

    @Override
    public void onError(final String pErrorMessage) {
        Log.d(LOG_TAG, "onError() called with: pErrorMessage = [" + pErrorMessage + "]");
    }

    public int getOrientation() {
        final int orientation = SystemConfigUtils.gerOrientation();
        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT: {
                return LinearLayoutManager.VERTICAL;
            }
            case Configuration.ORIENTATION_LANDSCAPE: {
                return LinearLayoutManager.HORIZONTAL;
            }
            default: {
                return LinearLayoutManager.VERTICAL;
            }
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SOURCE_LANGUAGE_KEY, mSourceLanguageKey);
    }
}
