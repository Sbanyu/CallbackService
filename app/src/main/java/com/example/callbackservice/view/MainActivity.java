package com.example.callbackservice.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callbackservice.R;
import com.example.callbackservice.service.MyService;

public class MainActivity extends AppCompatActivity {
    private Button btnStartService;
    private Button btnSendData;
    private TextView tvResult;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.button_start_service);
        btnSendData = findViewById(R.id.button_send_data);
        tvResult = findViewById(R.id.textView);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myService != null) {
                    sendDataToService();
                }else {
                    Toast.makeText(MainActivity.this,"Tekan Start Service Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            myService.setCallback(new MyService.MyCallback() {
                @Override
                public void onDataSent(String data) {
                    tvResult.setText(data);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };

    public void sendDataToService() {
        if (myService != null) {
            myService.sendData("Data from MainActivity");
        }
    }
}