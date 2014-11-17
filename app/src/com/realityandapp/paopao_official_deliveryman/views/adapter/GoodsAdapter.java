package com.realityandapp.paopao_official_deliveryman.views.adapter;

//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.realityandapp.paopao_official_deliveryman.Constants;
//import com.realityandapp.paopao_official_deliveryman.R;
//import com.realityandapp.paopao_official_deliveryman.models.interfaces.IGood;
//import com.realityandapp.paopao_official_deliveryman.models.interfaces.IShopCart;
//import com.realityandapp.paopao_official_deliveryman.views.ShopGoodsActivity;
//import com.realityandapp.paopao_official_deliveryman.widget.FontAwesomeButton;
//
//import java.util.List;
//
///**
// * Created by dd on 14-9-18.
// */
//public class GoodsAdapter extends //MultiTypeAdapter implements View.OnClickListener{
//        SingleTypeAdapter<IGood> implements View.OnClickListener {
////        SectionSingleTypeAdapter<IGood>{//} implements View.OnClickListener {
//
//    private final List<IGood> goods;
//    private final ShopGoodsActivity activity;
//    private final IShopCart shop_cart;
//
//    public GoodsAdapter(ShopGoodsActivity activity,
//                        final List<IGood> items, IShopCart shop_cart) {
//        super(activity, R.layout.goods_list_item);
//        this.activity = activity;
//        this.shop_cart = shop_cart;
//        goods = items;
//        setItems(goods);
//    }
//
//    @Override
//    protected int[] getChildViewIds() {
//        return new int[]{
//                R.id.iv_good_icon, R.id.good_title, R.id.good_price, R.id.good_desc, R.id.tv_amount,
//                R.id.fabtn_minus, R.id.fabtn_add, R.id.tv_add_to_cart, R.id.ll_amount, R.id.ll_amount_none};
//    }
//
//    @Override
//    protected void update(int position, IGood item) {
//        ImageLoader.getInstance().displayImage(item.get_image().toString(), imageView(0));
//        setText(1, item.get_name());
//        setText(2, String.format(Constants.Format.PRICE_WITH_UNIT, item.get_price(), item.get_unit()));
//        setText(3, item.get_description());
//        int good_amount = shop_cart.get_good_amount(item.get_id());
//        show_amount(good_amount);
//    }
//
//    private void show_amount(int good_amount) {
//        boolean is_show = good_amount > 0;
//        getView(8, LinearLayout.class).setVisibility(is_show ? View.VISIBLE : View.INVISIBLE);
//        getView(9, LinearLayout.class).setVisibility(is_show ? View.INVISIBLE : View.VISIBLE);
//        setNumber(4, good_amount);
//    }
//
//    private void init_font_awesome_button(int id, IGood good) {
//        FontAwesomeButton fabtn = getView(id, FontAwesomeButton.class);
//        fabtn.setTag(good);
//        fabtn.setOnClickListener(this);
//    }
//
//    //
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fabtn_minus:
//                System.out.println("fabtn_minus");
//                update_amount(v, -1);
//                break;
//            case R.id.fabtn_add:
//                System.out.println("fabtn_add");
//                update_amount(v, 1);
//                break;
//            case R.id.tv_add_to_cart:
//                System.out.println("tv_add_to_cart");
//                update_amount(v, 1);
//                break;
//        }
//    }
//
//    private void update_amount(View v, Integer plus) {
//        IGood good;
//        good = (IGood) v.getTag();
//        add_to_cart(good, plus);
//        update_amount_from_view(v);
//        show_amount_from_view(v);
//        activity.build_total();
//    }
//
//    private void show_amount_from_view(View v) {
//        IGood good = (IGood) v.getTag();
//        boolean is_show = shop_cart.get_good_amount(good.get_id()) > 0;
//        RelativeLayout relativeLayout = (RelativeLayout) v.getParent().getParent();
//        relativeLayout.findViewById(R.id.ll_amount).setVisibility(is_show ? View.VISIBLE : View.INVISIBLE);
//        relativeLayout.findViewById(R.id.ll_amount_none).setVisibility(is_show ? View.INVISIBLE : View.VISIBLE);
//    }
//
//    private void update_amount_from_view(View v) {
//        IGood good = (IGood) v.getTag();
//        ((TextView) ((RelativeLayout) v.getParent().getParent()).findViewById(R.id.tv_amount))
//                .setText(String.valueOf(shop_cart.get_good_amount(good.get_id())));
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = super.getView(position, convertView, parent);
//        IGood iGood = getItem(position);
//        String good_id = iGood.get_id();
//        init_font_awesome_button(5, iGood);
//        init_font_awesome_button(6, iGood);
//        bind_tv_dian(iGood);
//        return view;
//    }
//
//    private void bind_tv_dian(IGood good) {
//        TextView textView = getView(7, TextView.class);
//        textView.setTag(good);
//        textView.setOnClickListener(this);
//    }
//
//    private void add_to_cart(IGood good, int amount){
//        shop_cart.add_good(good, amount);
//    }
//}
