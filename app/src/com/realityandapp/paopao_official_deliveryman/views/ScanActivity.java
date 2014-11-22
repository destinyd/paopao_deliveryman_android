package com.realityandapp.paopao_official_deliveryman.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mindpin.android.codescanview.CodeScanListener;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseActivity;
import roboguice.inject.InjectView;
import com.mindpin.android.codescanview.CodeScanView;

public class ScanActivity extends PaopaoBaseActivity
{
    private static final String TAG = "ScanActivity";
    @InjectView(R.id.code_scan_view)
    CodeScanView code_scan_view;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);

        code_scan_view = (CodeScanView)findViewById(R.id.code_scan_view);

        code_scan_view.set_code_scan_listener(new CodeScanListener(){
            public void on_code_read(String result){
                Toast.makeText(ScanActivity.this, result, Toast.LENGTH_LONG).show();
                Log.e(TAG, result);
//                Intent intent = new Intent(ScanActivity.this, TargetActivity.class);
//                intent.putExtra("result", result);
//                startActivity(intent);
            };

            public void camera_not_found(){
                Toast.makeText(ScanActivity.this, "没有找到摄像头", Toast.LENGTH_LONG).show();
            };
            public void on_code_not_read(){
                Toast.makeText(ScanActivity.this, "没有找到可以读取的条码/二维码", Toast.LENGTH_LONG).show();
            };
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        code_scan_view.start_preview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        code_scan_view.stop_preview();
    }
}
