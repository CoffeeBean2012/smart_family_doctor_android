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

	/** ��߲໬�˵� */
	private DragLayout mDragLayout;
	private ImageButton menuSettingBtn;// �˵���ť
	private LinearLayout menu_header;          //��������ͷ����
//	private TextView menu_setting;      //�������ð�ť
	
	
	private RelativeLayout rl1;
	private RelativeLayout rl2;
	private RelativeLayout rl3;
	private RelativeLayout rl4;
	
	/** ��ͼ */
	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private RadioGroup mGPSModeGroup;
	
/*	private Button chooes1;
	private Button chooes2;
	private ViewFlipper flipper; */
	
	//adfasdfasdfdsfa宿舍
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** ��ͼ */
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// �˷���������д
		mapInit();  //���÷���
		
		rl1 = (RelativeLayout)findViewById(R.id.relativeLayout1);
		rl1.setOnClickListener(this);
		rl2 = (RelativeLayout)findViewById(R.id.relativeLayout2);
		rl2.setOnClickListener(this);
		rl3 = (RelativeLayout)findViewById(R.id.relativeLayout3);
		rl3.setOnClickListener(this);
		rl4 = (RelativeLayout)findViewById(R.id.relativeLayout4);
		rl4.setOnClickListener(this);
		
		
		/** �׶˰�ť */
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
		 * �����Ҫ�ڱ��Activity������Ҳʵ�ֲ໬�˵�Ч����Ҫ�ڲ���������DragLayout��ͬ��Activity��ʽ����
		 * Ȼ����onCreate������ʹ��; Activity���沿�֣���Ҫ�����MyRelativeLayout��.
		 */
		mDragLayout = (DragLayout) findViewById(R.id.dl);

		// ��Ӽ���ɵ�������˵�
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
				Toast.makeText(MainActivity.this, "����������Ľ���", Toast.LENGTH_LONG).show();
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
		aMap.setLocationSource(this);// ���ö�λ����
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// ����Ĭ�϶�λ��ť�Ƿ���ʾ
		aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
		// ���ö�λ������Ϊ��λģʽ �������ɶ�λ��������ͼ�����������ת����
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
	}
	
	/** ѡ��λ�ķ�ʽ  */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.gps_locate_button:
			// ���ö�λ������Ϊ��λģʽ
			aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
			break;
		case R.id.gps_follow_button:
			// ���ö�λ������Ϊ ����ģʽ
			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
			break;
		case R.id.gps_rotate_button:
			// ���ö�λ������Ϊ��ݵ�ͼ��������ת
			aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
			break;
		}

	}
	/**
	 * ����������д
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * �˷����Ѿ�����
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
	 * ��λ�ɹ���ص�����
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// ��ʾϵͳС����
				//��ȡλ����Ϣ
				Double geoLat = amapLocation.getLatitude();
	            Double geoLng = amapLocation.getLongitude(); 
	           // System.out.println(geoLat+"  "+geoLng);
	            Toast.makeText(MainActivity.this,geoLat+"  "+geoLng, Toast.LENGTH_LONG).show();
			} else {
				//0��ʾ��21��ʾIO �����쳣��22��ʾ�����쳣��23��ʾ���ӳ�ʱ��24��ʾ��Ч�Ĳ���25��ʾ��ָ���쳣��26��ʾurl �쳣��27��ʾδ֪����28��ʾ����������ʧ�ܡ�29��ʾЭ���������
				//30��ʾhttp ����ʧ�ܡ�31��ʾδ֪�Ĵ���32��ʾkey ��Ȩʧ��
				Log.e("AmapErr","Location ERR:" + amapLocation.getAMapException().getErrorCode());
				Log.e("AmapErr2","Location ERR2"+ amapLocation.getAMapException().getErrorMessage());
			}
		}
	}

	/**
	 * ���λ
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ�����Ļ�����������ģ�
			// ע�����ú��ʵĶ�λʱ��ļ������С���֧��Ϊ2000ms���������ں���ʱ�����removeUpdates()������ȡ��λ����
			// �ڶ�λ������ں��ʵ��������ڵ���destroy()����
			// ���������ʱ��Ϊ-1����λֻ��һ��
			// �ڵ��ζ�λ����£���λ���۳ɹ���񣬶��������removeUpdates()�����Ƴ����󣬶�λsdk�ڲ����Ƴ�
			mAMapLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 60000, 10, this);  //6000���ص���ʱ��
		}
	}

	/**
	 * ֹͣ��λ
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
