package com.realityandapp.paopao_official_deliveryman.models.http;

import com.realityandapp.paopao_official_deliveryman.models.interfaces.ICartData;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IFunds;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IHttpUser;

import java.util.Date;
import java.util.List;

/**
 * Created by dd on 14-11-18.
 */
public class HttpUser implements IHttpUser {
    String id, phone, im_id, im_nickname;
    @Override
    public String get_im_nickname() {
        return im_nickname;
    }

    @Override
    public String get_im_id() {
        return im_id;
    }

    @Override
    public String get_phone() {
        return phone;
    }

    @Override
    public String get_id() {
        return id;
    }
}
