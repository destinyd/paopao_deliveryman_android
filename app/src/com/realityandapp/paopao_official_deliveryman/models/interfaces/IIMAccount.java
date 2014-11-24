package com.realityandapp.paopao_official_deliveryman.models.interfaces;

import com.activeandroid.Model;

import java.io.Serializable;

/**
 * Created by dd on 14-9-18.
 */
public abstract class IIMAccount extends Model implements Serializable {
    public abstract String get_im_nickname();
    public abstract String get_im_id();
}
