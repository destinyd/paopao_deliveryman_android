package com.realityandapp.paopao_official_deliveryman.views;

import android.content.Intent;
import android.os.Bundle;
import com.easemob.chat.EMChat;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.User;
import roboguice.activity.RoboActivity;

public class LauncherActivity extends RoboActivity {
    PaopaoOfficialDeliverymanApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        application = (PaopaoOfficialDeliverymanApplication) getApplication();

        EMChat.getInstance().setAppInited();
        // todo init here
        application.init_image_config();
        go_to_main();
    }

    private void go_to_main() {
        User user = User.current();
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