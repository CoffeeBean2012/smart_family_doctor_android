package com.medicalcare.activity;

import com.medicalcare.MainActivity;
import com.medicalcare.R;
import com.medicalcare.adapter.FunctionAdapter;
import com.medicalcare.view.FunctionView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
/**
 * @Description:Ö÷Ò³
 * @author http://blog.csdn.net/finddreams
 */ 
public class FunctionActivity extends Activity{
	private FunctionView gridview;
	private FunctionView gridview2;
	private ImageButton back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);
		initView();
	}

	private void initView() {
		back = (ImageButton)findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent function = new Intent(FunctionActivity.this,MainActivity.class);
				startActivity(function);
				finish();
			}
			
		});
		
		gridview=(FunctionView)findViewById(R.id.gridview);
		gridview.setAdapter(new FunctionAdapter(this));
		
		//¼àÌý
		gridview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(FunctionActivity.this, "a"+arg2+"",Toast.LENGTH_LONG).show();
			}
		});
		
		gridview2 = (FunctionView)findViewById(R.id.gridview2);
		gridview2.setAdapter(new FunctionAdapter(this));
		gridview2.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(FunctionActivity.this, "b"+arg2+"",Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
