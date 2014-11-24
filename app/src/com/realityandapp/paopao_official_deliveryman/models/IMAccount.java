package com.realityandapp.paopao_official_deliveryman.models;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;
import com.mindpin.android.authenticator.IUser;
import com.realityandapp.paopao_official_deliveryman.models.http.Uploader;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IIMAccount;
import com.realityandapp.paopao_official_deliveryman.networks.HttpApi;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by dd on 14-6-10.
 */
@Table(name = "IMAccounts")
public class IMAccount extends IIMAccount {
    @Column(name = "im_id", unique = true, index = true)
    public String im_id;

    @Column(name = "im_nickname")
    public String im_nickname;

    @Override
    public boolean equals(Object o) {
        try {
            IMAccount tmp = (IMAccount) o;
            return tmp.im_id.equals(im_id);
        } catch (Exception ex) {
            return super.equals(o);
        }
    }

    public static void delete_all() {
        new Delete().from(IMAccount.class).execute();
    }

    @Override
    public String get_im_nickname() {
        return im_nickname;
    }

    @Override
    public String get_im_id() {
        return im_id;
    }
}
