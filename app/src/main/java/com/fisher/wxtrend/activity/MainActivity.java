package com.fisher.wxtrend.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.base.BaseActivity;
import com.fisher.wxtrend.fragment.ArticleFragment;
import com.fisher.wxtrend.fragment.ContactFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    private ArticleFragment articleFragment;
    private ContactFragment contactFragment;
    private Fragment currentFragment;

    int currentId = R.id.nav_weixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //防止软键盘弹出挤压界面变形
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        articleFragment = new ArticleFragment();
        switchFragment(articleFragment);

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

     /*   if(id==R.id.nav_weixin){
            if(appBarLayout.isShown())getActionBar().hide();
        }else{
            if(!getActionBar().isShowing())getActionBar().show();
        }
*/
        if (currentId != id) {
            switch (id) {
                case R.id.nav_weixin:
                    if (null == articleFragment) {
                        articleFragment = new ArticleFragment();
                    }
                    switchFragment(articleFragment);
                    break;
                case R.id.nav_contact:
                    if (null == contactFragment) {
                        contactFragment = new ContactFragment();
                    }
                    switchFragment(contactFragment);
                    break;
                default:
                    break;

            }
            currentId = id;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Fragment fragment) {
        super.switchFragment(currentFragment, fragment, R.id.content_frame);
        currentFragment = fragment;
    }

}
