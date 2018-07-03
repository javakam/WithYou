package com.sq.withyou.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.sq.lib_common.mvp.MvpActivity;
import com.sq.withyou.util.BottomNavigationViewHelper;
import com.sq.withyou.R;
import com.sq.withyou.home.HomeFragment;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navMenu)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_bottom)
    BottomNavigationView navigation;

    private ActionBarDrawerToggle mDrawerListener;
    private MenuItem mLastMenuItem;

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setToolBar("首页");
//        mNavigationView.getMenu().findItem(R.id.navZhiHu).setChecked(false);
        mNavigationView.setNavigationItemSelectedListener(mNavMenuItemSelListener);

        mToolbar.setTitle(mNavigationView.getMenu().findItem(R.id.navZhiHu).getTitle().toString());
        mDrawerListener = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar
                , R.string.drawer_open, R.string.drawer_close);
        mDrawerListener.syncState();
        mDrawerLayout.addDrawerListener(mDrawerListener);

        navigation.setOnNavigationItemSelectedListener(mNavBottomItemSelListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_main_content, new HomeFragment(), "home_frag");
        transaction.commit();
    }

    /**
     * DrawerLayout Listener
     */
    private NavigationView.OnNavigationItemSelectedListener mNavMenuItemSelListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (mLastMenuItem != null) {
                mLastMenuItem.setChecked(false);
            }
            mLastMenuItem = item;
            item.setChecked(true);
            mToolbar.setTitle(item.getTitle());
            mDrawerLayout.closeDrawers();
            return false;
        }
    };

    /**
     * Bottom Nav Listener
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mNavBottomItemSelListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    };

    private void setToolBar(String title) {
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onBackPressedSupport();
//                finish();
                showExitDialog();
            }
        });
    }

    @Override
    public void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出WhitYou吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                App.getInstance().exitApp();
                System.exit(0);
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        if (mDrawerLayout != null) {
            mDrawerLayout.removeDrawerListener(mDrawerListener);
        }
        super.onDestroy();
    }
}