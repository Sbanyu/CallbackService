package com.example.callbackservice.callback;

import android.widget.TextView;

import com.example.callbackservice.service.MyService;

public class MyCallbackHandler implements MyService.MyCallback {
    private TextView textView;

    public MyCallbackHandler(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onDataSent(String data) {
        textView.setText(data);
    }
}