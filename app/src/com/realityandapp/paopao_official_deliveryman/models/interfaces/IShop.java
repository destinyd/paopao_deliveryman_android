package com.realityandapp.paopao_official_deliveryman.models.interfaces;

/**
 * Created by dd on 14-9-18.
 */
public interface IShop extends IBase {
    public String get_name();
    public String get_address();
    public String get_description();
    public String get_contact();
    public java.util.List<Float> get_coordinates();
    public boolean is_shop_delivery();
    public String get_avatar();
    public Float get_latitude();
    public Float get_longitude();
}