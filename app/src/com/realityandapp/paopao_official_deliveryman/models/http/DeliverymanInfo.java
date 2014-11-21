package com.realityandapp.paopao_official_deliveryman.models.http;

import com.realityandapp.paopao_official_deliveryman.models.interfaces.IDeliverymanInfo;

/**
 * Created by dd on 14-11-18.
 */
public class DeliverymanInfo implements IDeliverymanInfo {
    private boolean is_working;
    private String im_id;
    private String im_key;
    private String im_nickname;

    @Override
    public boolean is_working() {
        return is_working;
    }

    @Override
    public String get_id() {
        return null;
    }

    public String im_id() {
        return im_id;
    }

    public String im_key() {
        return im_key;
    }

    public String im_nickname() {
        return im_nickname;
    }
}
