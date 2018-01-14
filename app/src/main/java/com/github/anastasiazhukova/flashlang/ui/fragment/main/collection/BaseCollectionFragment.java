package com.github.anastasiazhukova.flashlang.ui.fragment.main.collection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.domain.models.card.ICard;
import com.github.anastasiazhukova.flashlang.domain.models.collection.ICollection;
import com.github.anastasiazhukova.flashlang.ui.ViewConstants;
import com.github.anastasiazhukova.lib.logs.Log;

public class BaseCollectionFragment extends Fragment {

    private static final String LOG_TAG = BaseCollectionFragment.class.getSimpleName();

    FragmentManager mFragmentManager;
    private SourceLanguagesCollectionFragment mFragment;
    private int mCollectionContainer;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (mFragmentManager == null) {
            mFragmentManager = getChildFragmentManager();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater, @Nullable final ViewGroup pContainer, final Bundle pSavedInstanceState) {
        final ViewGroup rootView = (ViewGroup) pInflater.inflate(R.layout.fragment_collection_base, pContainer, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View pView, @Nullable final Bundle pSavedInstanceState) {
        super.onViewCreated(pView, pSavedInstanceState);
        mCollectionContainer = R.id.collection_container;
        if (pSavedInstanceState == null) {
            loadSourceLanguagesFragment();
        }
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG, "onResume() called");
        super.onResume();
    }

    private void loadSourceLanguagesFragment() {
        SourceLanguagesCollectionFragment fragment = (SourceLanguagesCollectionFragment) mFragmentManager.findFragmentByTag(ViewConstants.Collection.SOURCE_COLLECTION_FRAGMENT_TAG);
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new SourceLanguagesCollectionFragment();
            transaction.add(fragment, ViewConstants.Collection.SOURCE_COLLECTION_FRAGMENT_TAG);
        }
        fragment.setChildFragmentListener(new IChildFragmentListener<ICollection>() {

            @Override
            public void onItemClick(final ICollection pElement) {
                Log.d(LOG_TAG, "onItemClick() called with: pElement = [" + pElement + "]");
                loadTargetLanguagesFragment(pElement);
            }

            @Override
            public void onBackClick() {

            }
        });

        transaction.replace(mCollectionContainer, fragment)
                .commit();

    }

    private void loadTargetLanguagesFragment(final ICollection pSourceCollection) {
        TargetLanguagesCollectionFragment fragment = (TargetLanguagesCollectionFragment) mFragmentManager.findFragmentByTag(ViewConstants.Collection.TARGET_COLLECTION_FRAGMENT_TAG);
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (fragment == null) {
            fragment = new TargetLanguagesCollectionFragment();
            transaction.add(fragment, ViewConstants.Collection.TARGET_COLLECTION_FRAGMENT_TAG);
        }
        fragment.setSourceLanguageKey(pSourceCollection.getSourceLanguage());
        fragment.setSourceLanguageImage(pSourceCollection.getSourceLanguageCover());
        fragment.setChildFragmentListener(new IChildFragmentListener<ICollection>() {

            @Override
            public void onItemClick(final ICollection pElement) {
                loadCardsCollectionFragment(pElement);
            }

            @Override
            public void onBackClick() {
                loadSourceLanguagesFragment();
            }
        });
        transaction.replace(mCollectionContainer, fragment)
                .commit();

    }

    private void loadCardsCollectionFragment(final ICollection pCollection) {

    }

    private void startCardsGameActivity() {

    }

    private void startEditCardActivity(final ICard pCard) {

    }

}
