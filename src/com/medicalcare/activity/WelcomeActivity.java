package com.medicalcare.activity;

import com.medicalcare.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	protected static final String TAG = "WelcomeActivity";
	private Context mContext;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mContext = this;
		findView();
		init();
	}

	private void findView() {
		mImageView = (ImageView) findViewById(R.id.iv_welcome);
	}

	private void init() {
		mImageView.postDelayed(new Runnable() {
			@Override
			public void run() {
					Intent intent = new Intent(mContext, LoginActivity.class);
					Bundle bundle = new Bundle();
					bundle.putCharSequence("flag", "0");
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
			}
		},2000);
		
	}
}
