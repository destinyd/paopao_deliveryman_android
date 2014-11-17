package com.realityandapp.paopao_official_deliveryman.models.http;

import com.realityandapp.paopao_official_deliveryman.models.interfaces.IUrlObject;

/**
 * Created by DD on 14-10-17.
 */
public class UrlObject implements IUrlObject {
    String url;
    @Override
    public String get_url() {
        return url;
    }
}
