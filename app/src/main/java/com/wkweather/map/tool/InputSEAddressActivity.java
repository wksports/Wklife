package com.wkweather.map.tool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.wkweather.map.R;

import org.jetbrains.annotations.Nullable;

import java.text.BreakIterator;
import java.util.ArrayList;

public class InputSEAddressActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnBike;
    private Button btnDrive;
    private EditText start_edt_city, start_edt_address, end_edt_city, end_edt_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_adress);
        button();
    }

    private void button() {
        Button btnWalk = findViewById(R.id.btn_walk_school);
        btnBike = findViewById(R.id.btn_bike_school);
        btnDrive = findViewById(R.id.btn_drive_school);

//                start_edt_city = findViewById(R.id.Start_Edt_City_school);
        start_edt_address = findViewById(R.id.Start_Edt_Address_school);
//       end_edt_city = findViewById(R.id.End_Edt_City_school);
        end_edt_address = findViewById(R.id.End_Edt_Address_school);

        //按钮事件处理
        btnWalk.setOnClickListener(this);
        btnBike.setOnClickListener(this);
        btnDrive.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        SDKInitializer.initialize(getApplicationContext());
        Intent intentaddress=getIntent();
        String startCity = "海口";
        String startAddress = start_edt_address.getText().toString();
        String endCity = "海口";
        String endAddress = end_edt_address.getText().toString();

        ArrayList<String> addressList = new ArrayList<>();
        addressList.add(startCity);
        addressList.add(startAddress);
        addressList.add(endCity);
        addressList.add(endAddress);
        //先判断起始点地址框输入是否为空，为空则提示
        if (startCity.isEmpty() || startAddress.isEmpty() || endCity.isEmpty() || endAddress.isEmpty()) {
            Toast.makeText(this, "起点和终点不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("address", addressList);
            switch (v.getId()) {
                case R.id.btn_walk_school: {
                    setResult(1, intent);
                    break;                             //实现不同的路线规划
                }
                case R.id.btn_bike_school: {
                    setResult(2, intent);
                    break;
                }
                case R.id.btn_drive_school:{
                    setResult(3, intent);
                    break;
                }
            }
            finish();
        }
    }
}

