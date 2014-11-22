package com.realityandapp.paopao_official_deliveryman.views.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.mindpin.android.loadingview.LoadingView;
import com.realityandapp.paopao_official_deliveryman.Constants;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.Order;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.utils.AsyncTasks;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
import com.realityandapp.paopao_official_deliveryman.views.OrderActivity;
import com.realityandapp.paopao_official_deliveryman.views.OrdersActivity;
//import com.realityandapp.paopao_official_deliveryman.views.OrderActivity;
//import com.realityandapp.paopao_official_deliveryman.views.PayActivity;

import java.util.List;

/**
 * Created by dd on 14-9-18.
 */
public class OrdersAdapter extends SingleTypeAdapter<IOrder> implements View.OnClickListener {

    protected final List<IOrder> goods;
    protected final OrdersActivity activity;
    protected final LoadingView loading_view;

    public OrdersAdapter(Activity activity,
                         final List<IOrder> items) {
        super(activity, R.layout.orders_list_item);
        this.activity = (OrdersActivity) activity;
        loading_view = (LoadingView) activity.findViewById(R.id.loading_view);
        goods = items;
        setItems(goods);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{
                R.id.order_shop_name, R.id.order_status, R.id.order_desc, R.id.btn_action, R.id.rl_order
        };
    }

    @Override
    protected void update(int position, IOrder item) {
        setText(0, item.get_shop_name());
        setText(1, item.get_str_status());
        setText(2, String.format(Constants.Format.ORDER_DESC, item.get_total()));
        update_btn_action(item);
    }

    protected void update_btn_action(IOrder item) {
        if (Order.OrderStatus.paid == item.get_status()) {
            setText(3, "接受");
            getView(3, Button.class).setVisibility(View.VISIBLE);
        } else {
            getView(3, Button.class).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        IOrder order = getItem(position);
        switch (v.getId()) {
            case R.id.btn_action:
                action_for_order_status(order);
                break;
            case R.id.rl_order:
                go_to_order(order);
                break;
        }
    }

    protected void go_to_order(IOrder order) {
        Intent intent = new Intent(activity, get_target_activity());
        intent.putExtra(Constants.Extra.ORDER_ID, order.get_id());
        activity.startActivityForResult(intent, Constants.Request.ORDER);
    }

    protected void action_for_order_status(IOrder order) {
        if (order.get_status() == Order.OrderStatus.paid) {
            accept(order);
        }
    }

    private void accept(final IOrder order) {
        System.out.println("accept");
        AsyncTasks.accept(activity, order, new AsyncTasks.OnSuccessListener() {
            @Override
            public void run() {
                refresh_activity();
            }
        });
    }

    protected void refresh_activity() {
        activity.get_datas();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        bind_views(position);
        return view;
    }

    private void bind_views(int position) {
        Button button = getView(3, Button.class);
        button.setTag(position);
        button.setOnClickListener(this);

        RelativeLayout relativeLayout = getView(4, RelativeLayout.class);
        relativeLayout.setTag(position);
        relativeLayout.setOnClickListener(this);
    }

    protected Class<?> get_target_activity(){
        return OrderActivity.class;
    }
}
