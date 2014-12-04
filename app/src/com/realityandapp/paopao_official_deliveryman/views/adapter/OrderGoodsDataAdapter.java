package com.realityandapp.paopao_official_deliveryman.views.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.CartGoodsData;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;

import java.util.List;

/**
 * Created by dd on 14-9-18.
 */
public class OrderGoodsDataAdapter extends SingleTypeAdapter<CartGoodsData> implements View.OnClickListener {
    private static final String FORMAT_PRICE = "￥%.2f";
    private static final String FORMAT_TOTAL_CALCULATE = "￥%.2f X %d %s";
    private final List<CartGoodsData> cart_data;
    private final LayoutInflater inflater;

    public OrderGoodsDataAdapter(LayoutInflater inflater,
                                 final List<CartGoodsData> items) {
        super(inflater, R.layout.order_goods_data_list_item);
        this.inflater = inflater;
        cart_data = items;
        setItems(cart_data);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_good_name, R.id.tv_unit_price, R.id.tv_good_total, R.id.tv_plus, R.id.rl_order_plus};
    }

    @Override
    protected void update(int position, CartGoodsData item) {
        setText(0, item.get_good_name());
        setText(1, String.format(FORMAT_TOTAL_CALCULATE, item.get_price(), item.get_amount(), item.get_good_unit()));
        setText(2, String.format(FORMAT_PRICE, item.get_amount() * item.get_price()));
        setText(3, "备注:" + (item.get_plus() == null || "".equals(item.get_plus()) ? "无" : item.get_plus()));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        bind_views(position);
        return view;
    }

    private void bind_views(int position) {
        RelativeLayout relativeLayout = getView(4, RelativeLayout.class);
        relativeLayout.setTag(position);
        relativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag();
        CartGoodsData good_data = getItem(position);
        switch (v.getId()) {
            case R.id.rl_order_plus:
                new AlertDialog.Builder(inflater.getContext())
                        .setTitle("备注详情")
                        .setMessage(good_data.get_plus())
                        .create()
                        .show();
                ;
                break;
        }
    }
}
