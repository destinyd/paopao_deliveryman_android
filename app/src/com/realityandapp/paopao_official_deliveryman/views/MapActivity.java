package com.realityandapp.paopao_official_deliveryman.views;

import android.os.Bundle;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.realityandapp.paopao_official_deliveryman.Constants;
import com.realityandapp.paopao_official_deliveryman.R;
import com.realityandapp.paopao_official_deliveryman.models.http.Address;
import com.realityandapp.paopao_official_deliveryman.models.interfaces.IAddress;
import com.realityandapp.paopao_official_deliveryman.views.base.PaopaoBaseActivity;
import roboguice.inject.InjectView;

/**
 * Created by dd on 14-11-22.
 */
public class MapActivity extends PaopaoBaseActivity {
    @InjectView(R.id.bmapView)
    MapView bmapView;
    private BaiduMap mBaiduMap;
    IAddress address;
    private BitmapDescriptor mCurrentMarker;
    //定义Maker坐标点 柳州市 109.422402, 24.329053
    private static LatLng point_liuzhou = new LatLng(24.329053, 109.422402);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // todo change to application if use baidu map other place
        setContentView(R.layout.map);
        mBaiduMap = bmapView.getMap();
        //构建Marker图标
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        if (getIntent().getSerializableExtra(Constants.Extra.ADDRESS) != null) {
            address = (Address) getIntent().getSerializableExtra(Constants.Extra.ADDRESS);
            build_and_add_marker(new LatLng(address.get_latitude(), address.get_longitude()));
        } else {
            build_and_add_default_marker();
        }
    }

    private void build_and_add_marker(LatLng latLng) {
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(mCurrentMarker);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        map_center_to(latLng);
    }


    private void build_and_add_default_marker() {
        build_and_add_marker(point_liuzhou);
    }

    private void map_center_to(LatLng lat_lng) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(lat_lng)
                .zoom(15)
                .build();

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }


    @Override
    protected void onPause() {
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        bmapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        // MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
        bmapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        bmapView.onDestroy();
        super.onDestroy();
    }
}