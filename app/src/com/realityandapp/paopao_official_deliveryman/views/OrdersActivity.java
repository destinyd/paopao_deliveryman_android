package com.realityandapp.paopao_official_deliveryman.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.mindpin.android.loadingview.LoadingView;
import com.realityandapp.paopao_official_deliveryman.Constants;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.networks.HttpApi;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
import com.realityandapp.paopao_official_deliveryman.views.adapter.OrdersAdapter;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseActivity;
import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeButton;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * Created by dd on 14-9-18.
 */
public class OrdersActivity extends PaopaoBaseActivity implements View.OnClickListener {
    @InjectView(R.id.fabtn_back)
    FontAwesomeButton fabtn_back;
    @InjectView(R.id.lv_orders)
    ListView lv_orders;
    @InjectView(R.id.loading_view)
    LoadingView loading_view;
    protected List<IOrder> orders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders);

        setTitle("我的订单");
        init_views();
        get_datas();
    }

    private void init_views() {
        fabtn_back.setOnClickListener(this);
    }

    private void get_datas() {
        new PaopaoAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                get_orders();
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

    protected void get_orders() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        orders = DataProvider.orders();
    }

    private void build_view() {
        final OrdersAdapter adapter =
                new OrdersAdapter(this, orders);
        lv_orders.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabtn_back:
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.Request.ORDER:
                if (resultCode == RESULT_OK) {
                    get_datas();
                }
                break;
        }
    }
}