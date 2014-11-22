package com.realityandapp.paopao_official_deliveryman.views;

import android.content.Intent;
import android.os.Bundle;
import com.easemob.chat.EMChat;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.User;
import com.realityandapp.paopao_official_deliveryman.utils.AsyncTasks;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
import roboguice.activity.RoboActivity;

public class LauncherActivity extends RoboActivity {
    PaopaoOfficialDeliverymanApplication application;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        application = PaopaoOfficialDeliverymanApplication.getInstance();

        EMChat.getInstance().setAppInited();
        // todo init here
        application.init_image_config();

        user = User.current();
        if(user != null)
            get_deliveryman_info_from_http();
        else
            go_to_main();
    }

    private void get_deliveryman_info_from_http() {
        new PaopaoAsyncTask<Void>(this) {
            @Override
            public Void call() throws Exception {
                PaopaoOfficialDeliverymanApplication.getInstance().update_deliveryman_info();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                super.onSuccess(aVoid);
                go_to_main();
            }
        }.execute();
    }

    private void go_to_main() {
        if(user != null) {
            // todo
            PaopaoOfficialDeliverymanApplication.getInstance().im_login();
            startActivity(new Intent(this, RealMainActivity.class));
        }else{
            startActivity(new Intent(LauncherActivity.this, SignInActivity.class));
        }
        finish();
    }
}