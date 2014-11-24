package com.realityandapp.paopao_official_deliveryman.views.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mindpin.android.loadingview.LoadingView;
import com.realityandapp.paopao_official_deliveryman.PaopaoOfficialDeliverymanApplication;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.Funds;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.utils.AsyncTasks;
//import com.realityandapp.paopao_official_deliveryman.views.ShopGoodsActivity;
import com.realityandapp.paopao_official_deliveryman.views.DeliveryOrdersActivity;
import com.realityandapp.paopao_official_deliveryman.views.OrdersActivity;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseFragment;
import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeButton;
import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeTextView;
import roboguice.inject.InjectView;

/**
 * Created by dd on 14-10-14.
 */
public class DashboardFragment extends PaopaoBaseFragment implements View.OnClickListener {
    @InjectView(R.id.loading_view)
    LoadingView loading_view;
    //    private List<IShop> shops;
    @InjectView(R.id.btn_start_work)
    Button btn_start_work;
    @InjectView(R.id.ll_refresh_funds)
    LinearLayout ll_refresh_funds;
    @InjectView(R.id.ll_refresh_today_income)
    LinearLayout ll_refresh_today_income;
    @InjectView(R.id.fa_btn_notices)
    FontAwesomeButton fa_btn_notices;
    @InjectView(R.id.fa_btn_orders)
    FontAwesomeButton fa_btn_orders;
    //    @InjectView(R.id.fa_btn_my_orders)
//    FontAwesomeTextView fa_btn_my_orders;
    @InjectView(R.id.rl_pending)
    RelativeLayout rl_pending;
    @InjectView(R.id.rl_working)
    RelativeLayout rl_working;

    @InjectView(R.id.tv_come_off_work)
    TextView tv_come_off_work;
    @InjectView(R.id.fatv_today)
    FontAwesomeTextView fatv_today;
    @InjectView(R.id.fatv_balance)
    FontAwesomeTextView fatv_balance;
    private Funds funds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind_views();
        bind_deliveryman_info();
    }

    private void show_rest() {
        setTitle("控制面板(待命)");
        rl_working.setVisibility(View.GONE);
        rl_pending.setVisibility(View.VISIBLE);
    }

    private void bind_views() {
        btn_start_work.setOnClickListener(this);
        ll_refresh_funds.setOnClickListener(this);
        ll_refresh_today_income.setOnClickListener(this);
        fa_btn_notices.setOnClickListener(this);
        fa_btn_orders.setOnClickListener(this);
        tv_come_off_work.setOnClickListener(this);
        rl_working.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_work:
                work();
                break;
            case R.id.ll_refresh_funds:
                refresh_funds();
                break;
            case R.id.ll_refresh_today_income:
                refresh_funds();
                break;
            case R.id.fa_btn_notices:
                go_to_notices();
                break;
            case R.id.fa_btn_orders:
                go_to_orders();
                break;
            case R.id.tv_come_off_work:
                alert_rest();
                break;
            case R.id.rl_working:
                go_to_my_orders();
                break;
        }
    }

    private void alert_rest() {
        // todo 应该还要判断是否有未完成的单子
        new AlertDialog.Builder(getActivity())
                .setTitle("确定下班了吗？")
                .setNegativeButton("取消", null)
                .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rest();
                    }
                })
                .create()
                .show();
    }

    private void work() {
        System.out.println("work");
        new AsyncTasks.LoadingAsyncTask<Boolean>(getActivity()) {
            @Override
            public Boolean call() throws Exception {
                boolean b = DataProvider.work();
                if(b)
                    PaopaoOfficialDeliverymanApplication.getInstance().update_deliveryman_info();
                return b;
            }

            @Override
            protected void onSuccess(Boolean aBoolean) throws Exception {
                show_work();
                super.onSuccess(aBoolean);
            }
        }.execute();
    }

    private void go_to_my_orders() {
        System.out.println("go_to_my_orders");
        startActivity(new Intent(getActivity(), DeliveryOrdersActivity.class));
    }

    private void rest() {
        System.out.println("rest");
        new AsyncTasks.LoadingAsyncTask<Boolean>(getActivity()) {
            @Override
            public Boolean call() throws Exception {
                boolean b = DataProvider.rest();
                if(b)
                    PaopaoOfficialDeliverymanApplication.getInstance().update_deliveryman_info();
                return b;
            }

            @Override
            protected void onSuccess(Boolean aBoolean) throws Exception {
                show_rest();
                super.onSuccess(aBoolean);
            }
        }.execute();
    }

    private void go_to_orders() {
        System.out.println("go_to_orders");
        startActivity(new Intent(getActivity(), OrdersActivity.class));
    }

    private void go_to_notices() {
        System.out.println("go_to_notices");
    }

    private void refresh_funds() {
        System.out.println("refresh_funds");
        new AsyncTasks.LoadingAsyncTask<Void>(getActivity()) {
            @Override
            public Void call() throws Exception {
                funds = DataProvider.today_income();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                today_income_to_view();
                super.onSuccess(aVoid);
            }
        }.execute();
    }

    private void today_income_to_view() {
        System.out.println(funds);
        fatv_today.setText(String.valueOf(funds.get_today()));
        fatv_balance.setText(String.valueOf(funds.get_balance()));
    }

    private void show_work() {
        System.out.println("show_work");
        setTitle("控制面板(工作中)");
        rl_working.setVisibility(View.VISIBLE);
        rl_pending.setVisibility(View.GONE);
    }

    private void bind_deliveryman_info() {
        if (PaopaoOfficialDeliverymanApplication.getInstance().get_deliveryman_info() != null && PaopaoOfficialDeliverymanApplication.getInstance().get_deliveryman_info().is_working())
            show_work();
        else
            show_rest();
    }
}