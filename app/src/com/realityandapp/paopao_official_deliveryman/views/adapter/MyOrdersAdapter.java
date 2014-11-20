package com.realityandapp.paopao_official_deliveryman.views.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.mindpin.android.loadingview.LoadingView;
import com.realityandapp.paopao_official_deliveryman.Constants;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.Order;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.utils.AsyncTasks;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
import com.realityandapp.paopao_official_deliveryman.views.OrderActivity;

import java.util.List;

/**
 * Created by dd on 14-9-18.
 */
public class MyOrdersAdapter extends OrdersAdapter {

    public MyOrdersAdapter(Activity activity,
                           final List<IOrder> items) {
        super(activity, items);
    }

    @Override
    protected void update_btn_action(IOrder order) {
        if (order.is_accepted()) {
            setText(3, "取货");
            getView(3, Button.class).setVisibility(View.VISIBLE);
        } else if (Order.OrderStatus.took_away == order.get_status()) {
            setText(3, "扫码交货");
            getView(3, Button.class).setVisibility(View.VISIBLE);
        } else {
            getView(3, Button.class).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void action_for_order_status(IOrder order) {
        if (order.is_accepted()) {
            take_away(order);
        } else if (Order.OrderStatus.took_away == order.get_status()) {
            go_to_order_and_show_qrcode_scan(order);
        }
    }

    private void take_away(final IOrder order) {
        System.out.println("take_away");
        AsyncTasks.take_away(activity, order, new AsyncTasks.OnSuccessListener() {
            @Override
            public void run() {
                refresh_activity();
            }
        });
//        new PaopaoAsyncTask<IOrder>(activity) {
//
//            @Override
//            protected void onPreExecute() throws Exception {
//                loading_view.show();
//            }
//
//            @Override
//            public IOrder call() throws Exception {
//                return DataProvider.take_away(order.get_id());
//            }
//
//            @Override
//            protected void onSuccess(IOrder order) throws Exception {
//                Toast.makeText(activity, "提交取货成功", Toast.LENGTH_LONG);
//                refresh_activity();
//            }
//
//            @Override
//            protected void onException(Exception e) throws RuntimeException {
//                super.onException(e);
//                loading_view.hide();
//            }
//        }.execute();
    }

    private void go_to_order_and_show_qrcode_scan(IOrder order) {
        System.out.println("go_to_order_and_show_qrcode_scan");
        Intent intent = new Intent(activity, OrderActivity.class);
        intent.putExtra(Constants.Extra.ORDER_ID, order.get_id());
        intent.putExtra(Constants.Extra.SHOW_SCAN, true);
        activity.startActivityForResult(intent, Constants.Request.ORDER);
    }
}
