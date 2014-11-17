package com.realityandapp.paopao_official_deliveryman.models.interfaces;

/**
 * Created by dd on 14-9-18.
 */
public interface IGood extends IBase {
    public String get_name();
    public String get_description();
    public float get_price();
    public String get_unit();
    public Object get_image();
}