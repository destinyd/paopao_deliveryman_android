package com.realityandapp.paopao_official_deliveryman.models.http;

import com.realityandapp.paopao_official_deliveryman.models.interfaces.IFunds;

/**
 * Created by dd on 14-11-18.
 */
public class Funds implements IFunds {
    public Float balance, today, yesterday, month, last_month;

    @Override
    public Float get_balance() {
        return balance;
    }

    @Override
    public Float get_today() {
        return today;
    }

    @Override
    public Float get_yesterday() {
        return yesterday;
    }

    @Override
    public Float get_month() {
        return month;
    }

    @Override
    public Float get_last_month() {
        return last_month;
    }

    @Override
    public String get_id() {
        return null;
    }
}
