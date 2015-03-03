package com.realityandapp.paopao_official_deliveryman.models.interfaces;

import com.realityandapp.paopao_official_deliveryman.models.http.CartGoodsData;
import com.realityandapp.paopao_official_deliveryman.networks.HttpApi;

import java.util.Date;
import java.util.List;

/**
 * Created by dd on 14-9-18.
 */
public interface IOrder extends IBase {
    public String get_shop_id();
    public String get_shop_name();
    public IShop get_shop();
    public int get_distance();
    public List<CartGoodsData> get_order_items();
    public float get_delivery_price();
    public int get_delivery_type();
    public IAddress get_address();
    public float get_total();
    public IOrderStatus get_status();
    public IDeliveryman get_deliveryman();
    public void destroy() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException;
    public void set_address(IAddress address);
    public void save() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException;
    public String get_str_status();
    public void set_to_id(String to_id);
    public String get_to_id();
    public boolean is_accepted();
    public IHttpUser get_user();
    public String get_human_sent_to_before_at();

    public interface IOrderStatus {
    }
}