package com.sq.withyou;

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

import com.sq.withyou.dummy.DummyContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener{
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_bottom)
    BottomNavigationView navigation;

    private ActionBarDrawerToggle mDrawerToggle;
    private MenuItem mLastMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_main_content, ItemFragment.newInstance(3));
        transaction.commit();
    }

    private void initViews() {
        setToolBar(mToolbar, "知乎日报");
        mNavigationView.getMenu().findItem(R.id.navZhiHu).setChecked(false);
        mNavigationView.setNavigationItemSelectedListener(mNavMenuItemSelListener);
        mToolbar.setTitle(mNavigationView.getMenu().findItem(R.id.navZhiHu).getTitle().toString());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar
                , R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        navigation.setOnNavigationItemSelectedListener(mNavBottomItemSelListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onBackPressedSupport();
//                finish();
                showExitDialog();
            }
        });
    }

    private NavigationView.OnNavigationItemSelectedListener mNavMenuItemSelListener = new NavigationView.OnNavigationItemSelectedListener() {
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
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
    }
}
