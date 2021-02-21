package com.wkweather.map;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.wkweather.map.Fragment.Fragment1;
import com.wkweather.map.Fragment.Fragment2;
import com.wkweather.map.Fragment.Fragment3;
import com.wkweather.map.tool.ToastUtils;


import java.util.ArrayList;
import java.util.List;

import presenter.MyFragmentAdapter;

public class MyMenu extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fgList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
      //  getSupportActionBar().hide();//隐藏标题栏
        fgList.add(new Fragment1());
        fgList.add(new Fragment2());
        fgList.add(new Fragment3());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawe_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.mipmap.caidan);//设置导航按钮图标
        }
//侧滑栏
        navView.setCheckedItem(R.id.nav_call);//设置默认选中项
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();//关闭抽屉
                ToastUtils.showShort("点击了"+item.getTitle());
                return true;
            }
        });



        
        list.add("主页");            	//往我们tab底部文字的list里添加文字
        list.add("地图");
        list.add("设置");
        

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fgList, list, getSupportFragmentManager()); //实例化我们的适配器，并把我们的碎片list和文字list传进去，第三个参数固定这样写
        viewPager.setAdapter(myFragmentAdapter);    //为我们的ViewPger添加适配器
        tabLayout.setupWithViewPager(viewPager); 	//把我们的TabLayout与我们的ViewPager绑定起来



        tabLayout.getTabAt(0).setIcon(R.drawable.home_color);        //设置我们底部图片的是否被点击状态
        tabLayout.getTabAt(1).setIcon(R.drawable.issue_color);
        tabLayout.getTabAt(2).setIcon(R.drawable.platform_color);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {        //选中tab的处理逻辑
                if(tab.getPosition()==0)       tab.setIcon(R.drawable.home_color);    //如果当前点击第一个Tab,就把改tab的图片设置为点击状态的图片
                else if(tab.getPosition()==1)  tab.setIcon(R.drawable.issue_color);
                else    tab.setIcon(R.drawable.platform_color);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {   //未选中的tab的处理逻辑
                if(tab.getPosition()==0)       tab.setIcon(R.drawable.home_color);  // 如果当前的tab未点击，就把改图片设置为未点击的图片
                else if(tab.getPosition()==1)  tab.setIcon(R.drawable.issue_color);
                else   tab.setIcon(R.drawable.platform_color);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,  menu);
        return true;
    }
@Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;


        }
        return true;
}

}
