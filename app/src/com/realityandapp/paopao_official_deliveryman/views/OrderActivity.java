package com.realityandapp.paopao_official_deliveryman.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mindpin.android.loadingview.LoadingView;
import com.realityandapp.paopao_official_deliveryman.Constants;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.Order;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.utils.AsyncTasks;
import com.realityandapp.paopao_official_deliveryman.utils.ListViewUtils;
import com.realityandapp.paopao_official_deliveryman.utils.PaopaoAsyncTask;
import com.realityandapp.paopao_official_deliveryman.views.adapter.OrderGoodsDataAdapter;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseActivity;
import com.realityandapp.paopao_official_deliveryman.views.im.ChatActivity;
import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeButton;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by dd on 14-9-18.
 */
public class OrderActivity extends PaopaoBaseActivity implements View.OnClickListener {
    @InjectExtra(Constants.Extra.ORDER_ID)
    public String order_id;

    @InjectView(R.id.loading_view)
    LoadingView loading_view;
    @InjectView(R.id.tv_order_total)
    TextView tv_order_total;
    @InjectView(R.id.tv_delivery_price)
    TextView tv_delivery_price;
    @InjectView(R.id.tv_contact)
    TextView tv_contact;
    @InjectView(R.id.tv_address)
    TextView tv_address;
    @InjectView(R.id.tv_order_status)
    TextView tv_order_status;
    @InjectView(R.id.lv_order_goods_data)
    ListView lv_order_goods_data;
    @InjectView(R.id.fatv_destroy)
    FontAwesomeButton fabtn_destroy;
    @InjectView(R.id.fabtn_edit)
    FontAwesomeButton fatv_edit;
    @InjectView(R.id.fabtn_back)
    FontAwesomeButton fabtn_back;
    @InjectView(R.id.btn_submit)
    Button btn_submit;
    @InjectView(R.id.tv_user)
    TextView tv_user;
    @InjectView(R.id.rl_user)
    RelativeLayout rl_user;
    @InjectView(R.id.rl_order_address)
    RelativeLayout rl_order_address;

    private AlertDialog dialog_confirm;

    protected IOrder order;
    protected boolean need_show_scan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        need_show_scan = getIntent().getBooleanExtra(Constants.Extra.SHOW_SCAN, false);
        setTitle("订单详情");
        get_data();
    }

    protected void get_data() {
        new PaopaoAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                order = DataProvider.order(order_id);
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_views();
                if (need_show_scan)
                    show_scan();
            }

            @Override
            protected void onFinally() throws RuntimeException {
                super.onFinally();
                loading_view.hide();
            }
        }.execute();
    }

    protected void build_views() {
        build_actionbar();
        build_status();
        build_user();
        build_total();
        build_delivery();
        build_address();
        build_cart_to_order();
        build_submit();
    }

    private void build_submit() {
        btn_submit.setOnClickListener(this);
        if (order.get_status() == Order.OrderStatus.paid) {
            set_btn_submit("接受");
        } else if (order.is_accepted()) {
            set_btn_submit("取货");
        } else if (order.get_status() == Order.OrderStatus.took_away) {
            set_btn_submit("扫码交货");
        } else {
            btn_submit.setVisibility(View.INVISIBLE);
        }
    }

    private void set_btn_submit(String text) {
        btn_submit.setText(text);
        btn_submit.setVisibility(View.VISIBLE);
    }

    private void build_actionbar() {
        fatv_edit.setOnClickListener(this);
        fabtn_destroy.setOnClickListener(this);
        fabtn_back.setOnClickListener(this);
        rl_order_address.setOnClickListener(this);
        boolean show = order.get_status() == Order.OrderStatus.pending;
        fatv_edit.setVisibility(show ? View.VISIBLE : View.GONE);
        fabtn_destroy.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void build_user() {
        rl_user.setOnClickListener(this);
        System.out.println("order.get_deliveryman():" + order.get_deliveryman());
        if (order.get_user() == null) {
            ((View) tv_user.getParent()).setVisibility(View.GONE);
        } else {
            tv_user.setText(order.get_user().get_im_nickname());
            ((View) tv_user.getParent()).setVisibility(View.VISIBLE);
        }
    }

    private void build_status() {
        tv_order_status.setText(order.get_str_status());
    }

    private void build_delivery() {
        tv_delivery_price.setText(String.format(Constants.Format.PRICE, order.get_delivery_price()));
    }

    private void build_cart_to_order() {
        OrderGoodsDataAdapter adapter =
                new OrderGoodsDataAdapter(getLayoutInflater(), order.get_order_items());
        lv_order_goods_data.setAdapter(adapter);
        ListViewUtils.setListViewHeightBasedOnChildren(lv_order_goods_data);
    }

    private void build_total() {
        tv_order_total.setText(String.format(Constants.Format.PRICE, order.get_total()));
    }

    private void build_address() {
        if (order.get_address() == null) {
            ((View) tv_address.getParent()).setVisibility(View.GONE);
        } else {
            tv_contact.setText(String.format(Constants.Format.CONTACT, order.get_address().get_realname(), order.get_address().get_phone()));
            tv_address.setText(order.get_address().get_address());
            ((View) tv_address.getParent()).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabtn_edit:
                go_to_edit_order();
                break;
            case R.id.fatv_destroy:
                confirm_destroy();
                break;
            case R.id.fabtn_back:
                finish();
                break;
            case R.id.btn_submit:
                submit();
                break;
            case R.id.rl_user:
                go_to_im();
                break;
            case R.id.rl_order_address:
                go_to_map_with_location();
                break;
        }
    }

    private void go_to_map_with_location() {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(Constants.Extra.ADDRESS, order.get_address());
        startActivity(intent);
    }

    private void confirm_destroy() {
        fabtn_destroy.setEnabled(false);
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(this)
                .setTitle("确定删除吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fabtn_destroy.setEnabled(true);
                    }
                })
                .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        destroy_order();
                    }
                });
        dialog_confirm = dialog_builder.create();
        dialog_confirm.show();
    }

    private void go_to_im() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userId", order.get_user().get_im_id());
        intent.putExtra("nickname", order.get_user().get_im_nickname());
        startActivity(intent);
    }

    private void submit() {
        System.out.println("submit");
        if (order.get_status() == Order.OrderStatus.paid) {
            // todo accept()
            accept();
        } else if (order.is_accepted()) {
            take_away();
        } else if (Order.OrderStatus.took_away == order.get_status()) {
            show_scan();
        }
    }

    private void take_away() {
        System.out.println("take_away");
        AsyncTasks.take_away(this, order, new AsyncTasks.OnSuccessListener() {
            @Override
            public void run() {
                get_data();
            }
        });
    }

    private void accept() {
        System.out.println("accept");
        AsyncTasks.accept(this, order, new AsyncTasks.OnSuccessListener() {
            @Override
            public void run() {
                get_data();
            }
        });
    }

    protected void show_scan() {
        System.out.println("show_scan");
//        ImageView iv_qrcode = new ImageView(this);
//        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(this)
//                .setTitle("请将此二维码出示给跑跑")
//                .setView(iv_qrcode)
//                ;
//        ImageLoader.getInstance().displayImage(String.format(Constants.Format.QR_CODE, order.get_id()), iv_qrcode);
//        dialog_builder.create().show();
    }

    private void go_to_edit_order() {
//        Intent intent = new Intent(OrderActivity.this, EditOrderActivity.class);
//        intent.putExtra(Constants.Extra.ORDER_ID, order.get_id());
//        startActivityForResult(intent, Constants.Request.ORDER);
    }

    private void destroy_order() {
        new PaopaoAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                order.destroy();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                finish_with_ok();
            }

            @Override
            protected void onFinally() throws RuntimeException {
                super.onFinally();
                loading_view.hide();
            }
        }.execute();
    }

    private void finish_with_ok() {
        set_result_ok();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.Request.ORDER:
                if (resultCode == RESULT_OK) {
                    set_result_ok();
                    get_data();
                }

                break;
        }
    }

    private void set_result_ok() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
    }
}