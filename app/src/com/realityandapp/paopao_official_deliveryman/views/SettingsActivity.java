package com.realityandapp.paopao_official_deliveryman.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseActivity;
import roboguice.inject.InjectView;

/**
 * Created by dd on 14-6-12.
 */
public class SettingsActivity extends PaopaoBaseActivity {
//    AuthenticatorsController myAuthenticator;
//    User current_user;
    @InjectView(R.id.btn_signout)
    Button btn_signout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
        setTitle("设置");
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("sign out");
                DataProvider.sign_out();
                PaopaoOfficialDeliverymanApplication.getInstance().logout();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
