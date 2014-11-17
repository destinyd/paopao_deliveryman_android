package com.realityandapp.paopao_official_deliveryman.views;

import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;
import com.realityandapp.paopao_official_deliveryman.networks.HttpApi;

/**
 * Created by dd on 14-9-18.
 */
public class MyOrdersActivity extends OrdersActivity {
    @Override
    protected void get_orders() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        orders = DataProvider.my_orders();
    }
}