package com.realityandapp.paopao_official_deliveryman;

import com.realityandapp.paopao_official_deliveryman.networks.HttpApi;

/**
 * Created by DD on 14-9-27.
 */
public class Constants {
    public static class Extra{
        public static final String SHOP = "shop";
        public static final String STEP = "step";
        public static final String ORDER = "order";
        public static final String DELIVERYMAN = "deliveryman";
        public static final String ADDRESS = "address";
        public static final String CART = "cart";
        public static final String SHOP_CART = "shop_cart";
        public static final String ORDER_ID = "order_id";
        public static final String SHOP_ID = "shop_id";
        public static final String SHOP_NAME = "shop_name";
        public static final String SHOW_QRCORD = "show_qrcode";
        public static final String SHOW_SCAN = "show_scan";
        public static final String QR_CODE = "qr_code";
    }

    public static class Request {
        public static final int ORDER = 11;
        public static final int ADDRESS = 12;
        public static final int CART = 13;
        public static final int SHOP_CART = 14;
        public static final int USER = 1;
        public static final int SETTING = 21;
        public static final int QR_CODE = 201;
    }
    
    public static class Format{
        public static final String PRICE = "￥%.2f";
        public static final String CONTACT = "%s(%s)";
        public static final String FULL_CONTACT = "%s %s(%s)";
        public static final String FULL_CONTACT_TOAST = "%s(%s)\n%s";
        public static final String PRICE_WITH_UNIT = "￥%.2f/%s";
        public static final String BTN_TOTAL = "结算(%d)";
        public static final String CART_GOODS_COUNT = "共%d件商品";
        public static final String ORDER_DESC = "总价: %.2f元";// 商品数量: %d";
        public static final String TOTAL_CALCULATE = "￥%.2f X %d%s";
        public static final String DISTANCT = "%d米";
        public static final String VERIFY_CODE_TIMEOUT = "%d后可以重新发送";
        public static final String QR_CODE = HttpApi.USER_SITE + "/orders/%s/qrcode";
    }
}
