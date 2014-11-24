package com.realityandapp.paopao_official_deliveryman.utils;

import android.content.Context;
import android.widget.TextView;
import com.realityandapp.paopao_official_deliveryman.models.IMAccount;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;

/**
 * Created by dd on 14-11-23.
 */
public class IMAccountUtils {
    public static void sync_name(Context context, final String im_id, final TextView tv_name) {
        new IMAccountAsyncTask(context, im_id){
            @Override
            protected void onSuccess(String im_nickname) throws Exception {
                super.onSuccess(im_nickname);
                IMAccount im_account = new IMAccount();
                im_account.im_id = im_id;
                im_account.im_nickname = im_nickname;
                im_account.save();
                tv_name.setText(im_nickname);
            }
        }.execute();
    }

    public static class IMAccountAsyncTask extends PaopaoAsyncTask<String> {
        final String im_id;
        protected IMAccountAsyncTask(Context context, String im_id) {
            super(context);
            this.im_id = im_id;
        }

        @Override
        public String call() throws Exception {
            return DataProvider.im_nickname(im_id);
        }

        @Override
        protected void onSuccess(String s) throws Exception {
            super.onSuccess(s);
            IMAccount im_account = new IMAccount();
            im_account.im_id = im_id;
            im_account.im_nickname = s;
            im_account.save();
        }
    }
}
