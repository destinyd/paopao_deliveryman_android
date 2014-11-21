package com.realityandapp.paopao_official_deliveryman.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.mindpin.android.loadingview.LoadingView;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IOrder;
import com.realityandapp.paopao_official_deliveryman.networks.DataProvider;

/**
 * Created by dd on 14-11-20.
 */
public class AsyncTasks {
    static public void take_away(final Activity activity, final IOrder order, final OnSuccessListener listener) {
        System.out.println("take_away");
        new OrderAsyncTask(activity, listener) {
            @Override
            public IOrder call() throws Exception {
                return DataProvider.take_away(order.get_id());
            }

            @Override
            protected void onSuccess(IOrder order) throws Exception {
                Toast.makeText(getContext(), "成功提交取货", Toast.LENGTH_LONG).show();
                super.onSuccess(order);
            }
        }.execute();
    }

    static public void accept(final Activity activity, final IOrder order, final OnSuccessListener listener) {
        System.out.println("accept");
        new OrderAsyncTask(activity, listener) {
            @Override
            public IOrder call() throws Exception {
                return DataProvider.accept(order.get_id());
            }

            @Override
            protected void onSuccess(IOrder order) throws Exception {
                Toast.makeText(getContext(), "成功接受订单", Toast.LENGTH_LONG).show();
                super.onSuccess(order);
            }
        }.execute();
    }

    static public abstract class OrderAsyncTask extends PaopaoAsyncTask<IOrder>{
        final LoadingView loading_view;
        private final OnSuccessListener listener;

        protected OrderAsyncTask(Activity activity, final OnSuccessListener listener) {
            super(activity);
            this.listener = listener;
            loading_view = (LoadingView) activity.findViewById(R.id.loading_view);
        }

        @Override
        protected void onPreExecute() throws Exception {
            loading_view.show();
        }

        @Override
        protected void onSuccess(IOrder order) throws Exception {
            if(listener != null)
                listener.run();
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            super.onException(e);
            loading_view.hide();
        }
    }

    static public abstract class LoadingAsyncTask<T> extends PaopaoAsyncTask<T>{
        final LoadingView loading_view;

        protected LoadingAsyncTask(Activity activity) {
            super(activity);
            loading_view = (LoadingView) activity.findViewById(R.id.loading_view);
        }

        @Override
        protected void onPreExecute() throws Exception {
            loading_view.show();
        }

        @Override
        protected void onFinally() throws RuntimeException {
            super.onFinally();
            loading_view.hide();
        }
    }

    static public interface OnSuccessListener{
        void run();
    };
}
