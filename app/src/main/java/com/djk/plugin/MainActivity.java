package com.djk.plugin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.morgoo.droidplugin.pm.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    // apk插件路径
    private String mApkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "yaoyiyao.apk";
    private PackageManager mPm;
    private String mPackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 有了apk路径是可以获取apk的包名
        mPm = getPackageManager();
        PackageInfo info = mPm.getPackageArchiveInfo(mApkPath, PackageManager.GET_ACTIVITIES);
        mPackageName = info.packageName;
    }


    /**
     * 安装
     */
    public void installApk(View view) {
        try {
            int result = PluginManager.getInstance().installPackage(mApkPath, 0);
            Toast.makeText(this, "result = " + result, Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 卸载
     */
    public void unInstallApk(View view) {
        try {
            PluginManager.getInstance().deletePackage(mPackageName, 0);
            Toast.makeText(this, "卸载成功", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动
     */
    public void startApk(View view) {
        Intent intent = mPm.getLaunchIntentForPackage(mPackageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user_name", "Dengzi");
        startActivity(intent);
    }
}
