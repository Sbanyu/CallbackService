package com.example.callbackservice.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.callbackservice.R;
import com.example.callbackservice.callback.MyCallbackHandler;
import com.example.callbackservice.view.MainActivity;

public class MyService extends Service {
    private MyCallback callback;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public interface MyCallback {
        void onDataSent(String data);
    }

    public void setCallback(MyCallback callback) {
        this.callback = callback;
    }

    public void sendData(String data) {
        if (callback != null) {
            callback.onDataSent(data);
        }
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}