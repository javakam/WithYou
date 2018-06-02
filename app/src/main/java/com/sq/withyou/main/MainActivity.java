package com.sq.withyou.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.sq.data.wandroid.net.http.RetrofitModule;
import com.sq.data.wandroid.repository.Repository;
import com.sq.data.wandroid.repository.server.NetRepositoryImpl;
import com.sq.lib_common.base.BaseApplication;
import com.sq.withyou.BottomNavigationViewHelper;
import com.sq.withyou.R;
import com.sq.withyou.home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_main_content, HomeFragment.newInstance(3));
        transaction.commit();
    }

    private void initViews() {
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

        // TODO: 2018/6/2   测试访问 -- 成功！！！
        Repository mRepository = new Repository(new NetRepositoryImpl
                (RetrofitModule.getRequestApi(BaseApplication.baseUrl)));
        mRepository.getHomeArticleList(10);
    }

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

    /**
     * 左侧菜单选择监听
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
     * 底部导航监听
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

    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出GeekNews吗");
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
