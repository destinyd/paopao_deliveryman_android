package com.realityandapp.paopao_official_deliveryman.networks;


import com.google.gson.JsonObject;
import com.realityandapp.paopao_official_deliveryman.models.User;
import com.realityandapp.paopao_official_deliveryman.models.http.Cart;
import com.realityandapp.paopao_official_deliveryman.models.http.DeliverymanInfo;
import com.realityandapp.paopao_official_deliveryman.models.http.Funds;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.*;

import java.util.List;

public class DataProvider {

    public static List<IShop> get_shops() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.shops();
    }

    public static List<IGood> get_goods(String shop_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.shop_goods(shop_id);
//        List<IGood> list = new ArrayList<IGood>();
//        for(int i=0; i< 10; i++) {
//            Good good = new Good();
//            list.add(good);
//        }
//        return list;
    }

    public static ICart get_cart() {
        return new Cart();
    }

    public static IAddress get_default_address() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.default_address();
    }

    public static IOrder deliveryman_order(String s) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.deliveryman_order(s);
    }

    public static List<IAddress> get_addresses()  throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.my_addresses();
    }

    public static void destroy_order(String order_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        HttpApi.destroy_order(order_id);
    }

    public static IAddress save_address(IAddress address) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.save_address(address);
    }

    public static Boolean save_order(IOrder order) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.save_order(order);
    }

    public static IAddress set_default_address(String address_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.set_default_address(address_id);
    }

    public static List<IOrder> orders() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.orders();
    }

    public static void sign_out() {
        User.current().delete();
    }

    public static IOrder shop_cart_to_order(IShopCart shop_cart) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.shop_cart_to_order(shop_cart);
//        return new Order(shop_cart);
    }

    public static IShopCart get_cart(String shop_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_cart(shop_id);
    }

    public static IGood get_good(String good_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.good(good_id);
    }

    public static IShop get_shop(String shop_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.shop(shop_id);
    }

    public static JsonObject calculate_distance_and_pricing(String shop_id, String address_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.calculate_distance_and_pricing(shop_id, address_id);
    }

    public static IShopCart save_shop_cart(IShopCart shop_cart) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.save_shop_cart(shop_cart);
    }

    public static String sign_up(String phone, String verify_code, String password, String name, String email) {
        return HttpApi.sign_up(phone, verify_code, password, name, email);
    }

    public static Integer get_verify_code(String phone) {
        return HttpApi.get_verify_code(phone);
    }

    public static User user_info() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.user_info();
    }

    public static List<IOrder> my_orders() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.deliveryman_orders();
    }

    public static Funds today_income() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.today_income();
    }

    public static IOrder take_away(String order_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.take_away(order_id);
    }

    public static IOrder accept(String order_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.accept(order_id);
    }

    public static DeliverymanInfo deliveryman_info() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.deliveryman_info();
    }

    public static Boolean work() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.work();
    }

    public static Boolean rest() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.rest();
    }

    public static String im_nickname(String im_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.im_nickname(im_id);
    }

    public static IOrder order(String order_id)  throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.order(order_id);
    }
}
