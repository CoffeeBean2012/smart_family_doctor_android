package com.medicalcare.activity;

import com.medicalcare.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PersonalPhoneActivity extends Activity implements OnClickListener {
	private LinearLayout linearlayout;
	private ImageButton back;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.persion_phone);
		
		linearlayout = (LinearLayout)findViewById(R.id.linearlayout);
		linearlayout.setOnClickListener(this);
		
		back = (ImageButton)findViewById(R.id.add_back);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId())
		{
			case R.id.linearlayout:
				((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			case R.id.add_back:
				Intent persionphone = new Intent(PersonalPhoneActivity.this,PersonalActivity.class);
				startActivity(persionphone);
				finish();
				break;
			default:
				break;
		}
	}
}
