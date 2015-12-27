package com.reed.permissiondemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 伟 on 2015/12/26.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 用于判断需要请求的权限中哪些是已经获取的，并判断未获取权限中哪些是需要进行说明提示
     *
     * @param activity
     * @param permissions 需要申请的权限数组
     * @return 以Map<String, Boolean>的形式返回需要用户确认的权限，key是权限名，value是Boolean值，需要提示说明的权限的value值是false
     */
    protected final Map<String, Boolean> permissionNeeded(Activity activity, String[] permissions) {
        Map<String, Boolean> map = new HashMap<>();
        for (String permission : permissions) {
            int hasPermission = ContextCompat.checkSelfPermission(activity, permission);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                map.put(permission, true);
            }
        }
        for (String permission : map.keySet()) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                map.put(permission, false);
            }
        }
        return map;
    }

    /**
     * 展示权限说明提示
     *
     * @param context     上下文
     * @param message     提示说明
     * @param permissions 所有需要用户手动确认的权限数组
     * @param requestCode 请求码
     */
    protected final void showRequestMessage(Context context, String message, final String[] permissions, final int requestCode) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("我已了解", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        grantedPermission(permissions, requestCode);
                    }
                })
                .create()
                .show();
    }

    /**
     * 找出需要进行提示的权限
     *
     * @param permissionMap
     * @return 需要进行提示的权限集合
     */
    protected final List<String> permissionShow(Map<String, Boolean> permissionMap) {
        List<String> showPermission = new ArrayList<>();
        for (String permission : permissionMap.keySet()) {
            if (!permissionMap.get(permission)) {
                showPermission.add(permission);
            }
        }
        return showPermission;
    }

    /**
     * 用户在了解提示后程序所做操作
     *
     * @param permissions
     * @param requestCode
     */
    protected abstract void grantedPermission(String[] permissions, int requestCode);

}
