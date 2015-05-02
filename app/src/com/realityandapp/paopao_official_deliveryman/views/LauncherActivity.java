package com.realityandapp.paopao_official_deliveryman.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.SDKInitializer;
import com.easemob.chat.EMChat;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.User;
import com.realityandapp.paopao_official_deliveryman.utils.AsyncTasks;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UpdateConfig;
import roboguice.activity.RoboActivity;

public class LauncherActivity extends RoboActivity {
    private AlertDialog alertDialog;
    private boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        UpdateConfig.setDebug(true);
        // 静默下载更新 wifi
        UmengUpdateAgent.silentUpdate(this);

        //
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
        mPushAgent.onAppStart();

        if (savedInstanceState != null && savedInstanceState.getBoolean("loaded", false)) {
            loaded = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!loaded) {
            load();
        }

        if (User.current() != null && PaopaoOfficialDeliverymanApplication.getInstance().get_deliveryman_info() == null)
            get_deliveryman_info_from_http();
        else
            go_to_main();

    }

    private void load() {
        EMChat.getInstance().setAppInited();
        PaopaoOfficialDeliverymanApplication.getInstance().init_image_config();
        SDKInitializer.initialize(getApplicationContext());
    }

    private void get_deliveryman_info_from_http() {
        new PaopaoAsyncTask<Void>(this) {
            @Override
            public Void call() throws Exception {
                PaopaoOfficialDeliverymanApplication.getInstance().update_deliveryman_info();
//                PaopaoOfficialDeliverymanApplication.getInstance().im_login();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                super.onSuccess(aVoid);
                PaopaoOfficialDeliverymanApplication.getInstance().im_login();
                go_to_main();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("读取服务器资料失败，请确认网络已正常链接")
                        .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                finish();
                            }
                        })
                        .setNeutralButton("重新登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PaopaoOfficialDeliverymanApplication.getInstance().logout();
                                startActivity(new Intent(LauncherActivity.this, SignInActivity.class));
                                finish();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                alertDialog.dismiss();
                                finish();
                            }
                        }).create();
                alertDialog.show();
            }
        }.execute();
    }

    private void go_to_main() {
        if (User.current() != null) {
            startActivity(new Intent(this, RealMainActivity.class));
        } else {
            startActivity(new Intent(LauncherActivity.this, SignInActivity.class));
        }
        finish();
    }
}