package com.wkweather.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyLocation extends AppCompatActivity {
    public LocationClient mLocationClient;
    //private MapView mapview;
    private TextView positionText;
    private StringBuilder currentPosition;
    private ImageView indicator;
    private ImageView[] indicators;
    private boolean isContinue=true;
    private ViewPager viewPager;
    private ViewGroup group;
    private AtomicInteger index=new AtomicInteger();
    private Handler viewHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(msg.what);
        }
    };
    public MyLocation() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient((getApplicationContext()));
        mLocationClient.registerNotifyLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_my_location);
        initView();
        positionText = (TextView)findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();
        //如果没有启动下面权限，就询问用户让用户打开
        if(ContextCompat.checkSelfPermission(MyLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MyLocation.this,Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MyLocation.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MyLocation.this, permissions, 1);
        }
        else {
            requestLocation();
        }
    }

    /*初始化函数，并启动位置客户端LocationClient*/
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    /*初始化函数*/
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
         option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    /*只有同意打开相关权限才可以开启本程序*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    /*监听线程，获得当前的经纬度，并显示*/
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            runOnUiThread(() -> {
                currentPosition = new StringBuilder();
                currentPosition.append("纬度:").append(location.getLatitude()).append("\n");
                currentPosition.append("经度:").append(location.getLongitude()).append("\n");
                currentPosition.append("国家:").append(location.getCountry()).append("\n");
                currentPosition.append("省:").append(location.getProvince()).append("\n");
                currentPosition.append("市:").append(location.getCity()).append("\n");
                currentPosition.append("区:").append(location.getDistrict()).append("\n");
                currentPosition.append("街道:").append(location.getStreet()).append("\n");
                currentPosition.append("定位方式：");
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    currentPosition.append("GPS");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    currentPosition.append("网络");
                }

                    positionText.setText(currentPosition);
                });
    }
}

    public void onConnectHotSpotMessage(String s, int i) {

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
    }

    private void initView(){
        viewPager=findViewById(R.id.vp_adv);
        group=findViewById(R.id.view_indicators);
        List<View>listpics=new ArrayList<>();
        ImageView img1=new ImageView(this);
        img1.setBackgroundResource(R.drawable.wk_life);
        listpics.add(img1);
        ImageView img2=new ImageView(this);
        img2.setBackgroundResource(R.drawable.wk_life1);
        listpics.add(img2);
        ImageView img3=new ImageView(this);
        img3.setBackgroundResource(R.drawable.wk_life2);
        listpics.add(img3);
        ImageView img4=new ImageView(this);
        img4.setBackgroundResource(R.drawable.wk_life3);
        listpics.add(img4);

        //动态设置
        indicators=new ImageView[listpics.size()];
        for(int i=0;i<indicators.length;i++){
            indicator= new ImageView(this);
            indicator.setLayoutParams(new LinearLayout.LayoutParams(40,40));
            indicator.setPadding(5,5,5,5);
            indicators[i]=indicator;
            if(i==0){
                indicators[i].setBackgroundResource(R.drawable.shape_point);
            }else{
                indicators[i].setBackgroundResource(R.drawable.shape_point1);
            }
            group.addView(indicators[i]);
        }
        //适配器
        viewPager.setAdapter(new com.wkweather.map.MyPagerAdapter(listpics));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index.getAndSet(position);
                for(int i=0;i<indicators.length;i++){
                    if(i==position){
                        indicators[i].setBackgroundResource(R.drawable.shape_point);
                    }else{
                        indicators[i].setBackgroundResource(R.drawable.shape_point1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isContinue=false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue=true;
                        break;
                }
                return false;
            }
        });
//使用多线程自动切换
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(isContinue){
                        viewHandler.sendEmptyMessage(index.get());
                        whatOption();
                    }
                }
            }
        }).start();
    }
    private void whatOption(){
        index.incrementAndGet();
        if(index.get()>indicators.length-1){
            index.getAndAdd(-4);
        }
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

class MyPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    public MyPagerAdapter(List<View> viewList){
        this.viewList=viewList;
    }
    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position),0);
        return viewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

}
