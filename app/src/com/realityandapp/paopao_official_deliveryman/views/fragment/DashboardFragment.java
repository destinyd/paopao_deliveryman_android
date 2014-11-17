package com.realityandapp.paopao_official_deliveryman.views.fragment;

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
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
//import com.realityandapp.paopao_official_deliveryman.views.ShopGoodsActivity;
import com.realityandapp.paopao_official_deliveryman.views.MyOrdersActivity;
import com.realityandapp.paopao_official_deliveryman.views.OrdersActivity;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseFragment;
import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeButton;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind_views();
        pending();
    }

    private void pending() {
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

    public void get_datas() {
        new PaopaoAsyncTask<Void>(getActivity()) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
//                shops = DataProvider.get_shops();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
            }

            @Override
            protected void onFinally() throws RuntimeException {
                super.onFinally();
                loading_view.hide();
            }
        }.execute();
    }

    private void build_view() {
//        ShopsAdapter adapter =
//                new ShopsAdapter(getActivity().getLayoutInflater(), shops);
//        gv_shops.setAdapter(adapter);
//        gv_shops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(), ShopGoodsActivity.class);
//                intent.putExtra(Constants.Extra.SHOP_ID, shops.get(i).get_id());
//                intent.putExtra(Constants.Extra.SHOP_NAME, shops.get(i).get_name());
//                startActivity(intent);
//            }
//        });
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
                refresh_today_income();
                break;
            case R.id.fa_btn_notices:
                go_to_notices();
                break;
            case R.id.fa_btn_orders:
                go_to_orders();
                break;
            case R.id.tv_come_off_work:
                come_off_work();
                break;
            case R.id.rl_working:
                go_to_my_orders();
                break;
        }
    }

    private void go_to_my_orders() {
        System.out.println("go_to_my_orders");
        startActivity(new Intent(getActivity(), MyOrdersActivity.class));
    }

    private void come_off_work() {
        System.out.println("come_off_work");
        pending();
    }

    private void go_to_orders() {
        System.out.println("go_to_orders");
        startActivity(new Intent(getActivity(), OrdersActivity.class));
    }

    private void go_to_notices() {
        System.out.println("go_to_notices");
    }

    private void refresh_today_income() {
        System.out.println("refresh_today_income");
    }

    private void refresh_funds() {
        System.out.println("refresh_funds");
    }

    private void work() {
        System.out.println("work");
        setTitle("控制面板(工作中)");
        rl_working.setVisibility(View.VISIBLE);
        rl_pending.setVisibility(View.GONE);
    }
}