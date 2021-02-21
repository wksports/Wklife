package com.wkweather.map.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.wkweather.map.MainActivity;
import com.wkweather.map.MyLocation;
import com.wkweather.map.R;

import org.jetbrains.annotations.Nullable;

public class Fragment2 extends Fragment {


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2,container,false);
        Button button=view.findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"跳转到地图",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),MainActivity.class);
                getActivity().startActivity(intent);//当然也可以写成getContext()
            }
        });

        return view;
    }



}
