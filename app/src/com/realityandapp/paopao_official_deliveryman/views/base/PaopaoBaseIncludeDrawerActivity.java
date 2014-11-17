package com.realityandapp.paopao_official_deliveryman.views.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.realityandapp.paopao_official_deliveryman.Constants;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.views.*;
import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeButton;
import roboguice.inject.InjectView;

/**
 * Created by dd on 14-9-18.
 */
public class PaopaoBaseIncludeDrawerActivity extends PaopaoBaseActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @InjectView(R.id.left_drawer)
    LinearLayout left_drawer;
    @InjectView(R.id.fatv_menu)
    FontAwesomeButton fatv_menu;
    @InjectView(R.id.menu_home)
    LinearLayout menu_home;
//    @InjectView(R.id.menu_favorites)
//    LinearLayout menu_favorites;
    @InjectView(R.id.menu_orders)
    LinearLayout menu_orders;
    @InjectView(R.id.menu_addresses)
    LinearLayout menu_addresses;
    @InjectView(R.id.menu_settings)
    LinearLayout menu_settings;
    @InjectView(R.id.menu_exit)
    LinearLayout menu_exit;

    @Override
    protected void onStart() {
        super.onStart();
        _init_views();
    }

    private void _init_views() {
        fatv_menu.setOnClickListener(this);
        menu_home.setOnClickListener(this);
//        menu_favorites.setOnClickListener(this);
        menu_orders.setOnClickListener(this);
        menu_addresses.setOnClickListener(this);
        menu_settings.setOnClickListener(this);
        menu_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fatv_menu:
                if(drawer_layout.isDrawerOpen(left_drawer))
                    drawer_layout.closeDrawer(left_drawer);
                else
                    drawer_layout.openDrawer(left_drawer);
                break;
            case R.id.menu_home:
                if(!this.getClass().getName().equals(RealMainActivity.class.getName())) {
                    startActivity(new Intent(this, RealMainActivity.class));
                    finish();
                }
                break;
//            case R.id.menu_favorites:
//                break;
            case R.id.menu_orders:
//                startActivity(new Intent(this, MyOrdersActivity.class));
                break;
            case R.id.menu_addresses:
//                startActivity(new Intent(this, AddressesActivity.class));
                break;
            case R.id.menu_settings:
//                startActivityForResult(new Intent(this, SettingsActivity.class), Constants.Request.SETTING);
                break;
            case R.id.menu_exit:
                alert_exit();
                break;
            default:
                super.onClick(v);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(drawer_layout.isDrawerOpen(left_drawer)){
                drawer_layout.closeDrawer(left_drawer);
                return true;
            }else{
                alert_exit();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void alert_exit() {
        new AlertDialog.Builder(this)
                .setMessage("要退出吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.Request.SETTING && resultCode == RESULT_OK){
            // logout
            finish();
            System.exit(1);
        }
    }
}