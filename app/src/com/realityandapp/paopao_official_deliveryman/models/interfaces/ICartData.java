package com.realityandapp.paopao_official_deliveryman.models.interfaces;

import java.util.List;

/**
 * Created by dd on 14-9-18.
 */
public interface ICartData extends IBase {
    public String get_shop_id();
    public String get_shop_name();
    public int get_shop_discount();
    public List<ICartGoodsData> get_shop_goods_data();
    public float get_shop_delivery_price();
    public IShop get_shop();
}