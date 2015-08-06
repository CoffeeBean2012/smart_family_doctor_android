package com.medicalcare;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.medicalcare.activity.FunctionActivity;
import com.medicalcare.activity.PersonalActivity;
import com.residemenu.main.lib.DragLayout;
public class MainActivity extends Activity implements LocationSource,AMapLocationListener,OnCheckedChangeListener,OnClickListener{

	/** 左边侧滑菜单 */
	private DragLayout mDragLayout;
	private ImageButton menuSettingBtn;// 菜单按钮
	private LinearLayout menu_header;          //侧拉界面头布局
//	private TextView menu_setting;      //低下设置按钮
	
	
	private RelativeLayout rl1;
	private RelativeLayout rl2;
	private RelativeLayout rl3;
	private RelativeLayout rl4;
	
	/** 地图 */
	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private RadioGroup mGPSModeGroup;
	
/*	private Button chooes1;
	private Button chooes2;
	private ViewFlipper flipper; */
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** 地图 */
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		mapInit();  //调用方法
		
		rl1 = (RelativeLayout)findViewById(R.id.relativeLayout1);
		rl1.setOnClickListener(this);
		rl2 = (RelativeLayout)findViewById(R.id.relativeLayout2);
		rl2.setOnClickListener(this);
		rl3 = (RelativeLayout)findViewById(R.id.relativeLayout3);
		rl3.setOnClickListener(this);
		rl4 = (RelativeLayout)findViewById(R.id.relativeLayout4);
		rl4.setOnClickListener(this);
		
		
		/** 底端按钮 */
		/*chooes1 = (Button)findViewById(R.id.searchBtn);
		chooes2 = (Button)findViewById(R.id.historyBtn);
		flipper=(ViewFlipper)findViewById(R.id.flipper);
		
		chooes1.setOnClickListener(new OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                Toast.makeText(MainActivity.this, "aaaa", Toast.LENGTH_LONG).show();
                flipper.setDisplayedChild(0); 
            } 
        }); 
		chooes2.setOnClickListener(new OnClickListener() { 
            @Override 
            public void onClick(View v) { 
            	Toast.makeText(MainActivity.this, "bbbb", Toast.LENGTH_LONG).show();
                flipper.setDisplayedChild(1); 
            } 
        }); */
		
		menu_header = (LinearLayout) this.findViewById(R.id.menu_header);
		menu_header.setOnClickListener(this);
		
		/**
		 * 如果需要在别的Activity界面中也实现侧滑菜单效果，需要在布局中引入DragLayout（同本Activity方式），
		 * 然后在onCreate中声明使用; Activity界面部分，需要包裹在MyRelativeLayout中.
		 */
		mDragLayout = (DragLayout) findViewById(R.id.dl);

		// 添加监听，可点击呼出菜单
		menuSettingBtn = (ImageButton) findViewById(R.id.menu_imgbtn);
		menuSettingBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.menu_imgbtn:
				mDragLayout.open();
				break;
			case R.id.menu_header:
				Toast.makeText(MainActivity.this, "进入个人中心界面", Toast.LENGTH_LONG).show();
				break;
			case R.id.relativeLayout1:
				//Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
				Intent function = new Intent(MainActivity.this,FunctionActivity.class);
				startActivity(function);
				finish();
				break;
			case R.id.relativeLayout2:
				//Toast.makeText(MainActivity.this, "2", Toast.LENGTH_LONG).show();
				Intent personal = new Intent(MainActivity.this,PersonalActivity.class);
				startActivity(personal);
				finish();
				break;
			case R.id.relativeLayout3:
				Toast.makeText(MainActivity.this, "3", Toast.LENGTH_LONG).show();
				break;
			case R.id.relativeLayout4:
				Toast.makeText(MainActivity.this, "4", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
		}
	}	
	
	public void mapInit(){
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
		mGPSModeGroup = (RadioGroup) findViewById(R.id.gps_radio_group);
		mGPSModeGroup.setOnCheckedChangeListener(this);
	}
	private void setUpMap() {
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
	}
	
	/** 选择定位的方式  */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.gps_locate_button:
			// 设置定位的类型为定位模式
			aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
			break;
		case R.id.gps_follow_button:
			// 设置定位的类型为 跟随模式
			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
			break;
		case R.id.gps_rotate_button:
			// 设置定位的类型为根据地图面向方向旋转
			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
			break;
		}

	}
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * 此方法已经废弃
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				//获取位置信息
				Double geoLat = amapLocation.getLatitude();
	            Double geoLng = amapLocation.getLongitude(); 
	           // System.out.println(geoLat+"  "+geoLng);
	            Toast.makeText(MainActivity.this,geoLat+"  "+geoLng, Toast.LENGTH_LONG).show();
			} else {
				//0表示正常、21表示IO 操作异常、22表示连接异常、23表示连接超时、24表示无效的参数、25表示空指针异常、26表示url 异常、27表示未知主机、28表示服务器连接失败、29表示协议解析错误
				//30表示http 连接失败、31表示未知的错误、32表示key 鉴权失败
				Log.e("AmapErr","Location ERR:" + amapLocation.getAMapException().getErrorCode());
				Log.e("AmapErr2","Location ERR2"+ amapLocation.getAMapException().getErrorMessage());
			}
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用destroy()方法
			// 其中如果间隔时间为-1，则定位只定一次
			// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
			mAMapLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 60000, 10, this);  //6000代表回调的时间
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}

}
