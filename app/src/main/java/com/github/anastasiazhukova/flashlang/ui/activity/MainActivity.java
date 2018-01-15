package com.github.anastasiazhukova.flashlang.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.github.anastasiazhukova.flashlang.R;
import com.github.anastasiazhukova.flashlang.UserManager;
import com.github.anastasiazhukova.flashlang.domain.models.user.User;
import com.github.anastasiazhukova.flashlang.operations.LoadUserInfoOperation;
import com.github.anastasiazhukova.flashlang.ui.adapter.ScrollPagerAdapter;
import com.github.anastasiazhukova.flashlang.ui.domain.ScrollPageListener;
import com.github.anastasiazhukova.flashlang.ui.fragment.main.TranslateFragment;
import com.github.anastasiazhukova.flashlang.ui.fragment.main.UserInfoFragment;
import com.github.anastasiazhukova.flashlang.ui.fragment.main.collection.BaseCollectionFragment;
import com.github.anastasiazhukova.lib.contracts.IOperation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadUser();
    }

    private void loadUser() {
        final User currentUser = UserManager.getCurrentUser();
        if (currentUser == null) {
            final IOperation<User> loadUserOperation = new LoadUserInfoOperation();
            try {
                final User user = loadUserOperation.perform();
                UserManager.setCurrentUser(user);
            } catch (final Exception pE) {
                throw new IllegalStateException("Can't laod current user");
            }
        }
    }

    private void init() {
        mViewPager = findViewById(R.id.activity_main_view_pager);
        initViewPager();
    }

    private void initViewPager() {
        final HorizontalScrollView scrollView = findViewById(R.id.background_scroll_view);
        final ImageView background = findViewById(R.id.background_image_view);

        final ScrollPagerAdapter adapter = new ScrollPagerAdapter(this, getSupportFragmentManager(), getFragmentsNames());
        final ViewPager.OnPageChangeListener scrollPageListener = new ScrollPageListener(mViewPager, scrollView, background);
        mViewPager.addOnPageChangeListener(scrollPageListener);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
    }

    private List<String> getFragmentsNames() {
        //todo add more fragments
        final List<String> names = new ArrayList<>();
        names.add(BaseCollectionFragment.class.getName());
        names.add(TranslateFragment.class.getName());
        names.add(UserInfoFragment.class.getName());
        return names;
    }



}

