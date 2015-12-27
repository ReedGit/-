package com.reed.permissiondemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private final static String TAG = MainActivity.class.toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Map<String, Boolean> permissionMap = permissionNeeded(MainActivity.this, permissions);
        if (permissionMap.size() == 0) {
            Log.i(TAG, "获取全部权限");
        } else {
            List<String> permissionList = permissionShow(permissionMap);
            if (permissionList.size() == 0) {
                //进行权限请求
                ActivityCompat.requestPermissions(MainActivity.this, permissionMap.keySet().toArray(new String[permissionMap
                        .size()]), 1);
            } else {
                //需要进行权限提示
                showRequestMessage(MainActivity.this, "需要以下权限。。。。。。", permissionMap.keySet().toArray(new String[permissionMap
                        .size()]), 1);
            }
        }
    }

    //用户确认提示后进行的处理
    @Override
    protected void grantedPermission(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(MainActivity.this, permissions, requestCode);
    }

    //权限请求结果回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                for (int grantResult : grantResults) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        Log.i(TAG, "获得权限");
                    } else {
                        Log.i(TAG, "获得权限失败");
                    }
                }
        }
    }
}
