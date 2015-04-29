package com.realityandapp.paopao_official_deliveryman.models.interfaces;

/**
 * Created by dd on 14-11-18.
 */
public interface IFunds extends IBase {
    public Float get_balance();
    public Float get_user_balance();
    public Float get_today();
    public Float get_yesterday();
    public Float get_month();
    public Float get_last_month();
}
