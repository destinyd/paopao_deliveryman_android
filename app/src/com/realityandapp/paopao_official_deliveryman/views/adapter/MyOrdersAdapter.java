package com.realityandapp.paopao_official_deliveryman.views.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import com.realityandapp.paopao_official_deliveryman.models.http.Order;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;

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
        if (is_accepted(order)) {
            setText(3, "取货");
            getView(3, Button.class).setVisibility(View.VISIBLE);
        } else if (Order.OrderStatus.took_away == order.get_status()) {
            setText(3, "扫码交货");
            getView(3, Button.class).setVisibility(View.VISIBLE);
        } else {
            getView(3, Button.class).setVisibility(View.INVISIBLE);
        }
    }

    private boolean is_accepted(IOrder item) {
        return Order.OrderStatus.accepted == item.get_status() || Order.OrderStatus.system_accepted == item.get_status();
    }

    @Override
    protected void action_for_order_status(IOrder order) {
        if (is_accepted(order)) {
            take_away(order);
        } else if (Order.OrderStatus.took_away == order.get_status()) {
            go_to_order_and_show_qrcode_scan(order);
        }
    }

    private void take_away(IOrder order) {
        System.out.println("take_away");
    }

    private void go_to_order_and_show_qrcode_scan(IOrder order) {
        System.out.println("go_to_order_and_show_qrcode_scan");
//        Intent intent = new Intent(activity, OrderActivity.class);
//        intent.putExtra(Constants.Extra.ORDER_ID, order.get_id());
//        intent.putExtra(Constants.Extra.SHOW_QRCORD, true);
//        activity.startActivityForResult(intent, Constants.Request.ORDER);
    }
}
