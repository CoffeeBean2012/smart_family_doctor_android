package com.medicalcare.activity;

import com.medicalcare.MainActivity;
import com.medicalcare.R;
import com.medicalcare.other.ActionSheetDialog;
import com.medicalcare.other.ActionSheetDialog.OnSheetItemClickListener;
import com.medicalcare.other.ActionSheetDialog.SheetItemColor;
import com.medicalcare.other.ProgressDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PersonalActivity extends Activity implements OnClickListener,OnTouchListener{

	private ImageButton add_back;          //返回
	private RelativeLayout add_layout_01;
	private RelativeLayout add_layout_02;
	private RelativeLayout add_layout_03;
	private RelativeLayout add_layout_04;
	private RelativeLayout add_layout_05;
	private Button back;                     //注销用户
	
	ProgressDialog progressDialog ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.persion);
		
		add_back=(ImageButton)findViewById(R.id.add_back);
		add_back.setOnClickListener(this);
		
		add_layout_01=(RelativeLayout)findViewById(R.id.add_layout_01);
		add_layout_01.setOnClickListener(this);
		
		add_layout_02=(RelativeLayout)findViewById(R.id.add_layout_02);
		add_layout_02.setOnClickListener(this);
		
		add_layout_03=(RelativeLayout)findViewById(R.id.add_layout_03);
		add_layout_03.setOnClickListener(this);
		
		add_layout_04=(RelativeLayout)findViewById(R.id.add_layout_04);
		add_layout_04.setOnClickListener(this);
		
		add_layout_05=(RelativeLayout)findViewById(R.id.add_layout_05);
		add_layout_05.setOnClickListener(this);
		
		back = (Button)findViewById(R.id.back);
		back.setOnClickListener(this);
		
		progressDialog = ProgressDialog.show(this, "加载中...");
	}

    @Override
    public void onClick(View v)
    {
       switch (v.getId()) {
       		case R.id.add_back:
       			Intent personal = new Intent(PersonalActivity.this,MainActivity.class);
				startActivity(personal);
				finish();
       			break;
       		case R.id.add_layout_01:
       			Toast.makeText(PersonalActivity.this, "01", Toast.LENGTH_LONG).show();
       			break;	
       		case R.id.add_layout_02: 
       			Toast.makeText(PersonalActivity.this, "02", Toast.LENGTH_LONG).show();
				break;		
       		case R.id.add_layout_03:
       			Toast.makeText(PersonalActivity.this, "03", Toast.LENGTH_LONG).show();
				break;
       		case R.id.add_layout_04:
       			Intent persion = new Intent(PersonalActivity.this,PersonalPhoneActivity.class);
       			startActivity(persion);
       			finish();
				break;
       		case R.id.add_layout_05:
       			//Toast.makeText(PersonalActivity.this, "05", Toast.LENGTH_LONG).show();
       			Loading();
				break;
       		case R.id.back:
       			new ActionSheetDialog(PersonalActivity.this)
				.builder()
				.setTitle("确定要注销用户？")
				.setCancelable(false)
				.setCanceledOnTouchOutside(false)
				.addSheetItem("确定", SheetItemColor.Red,
						new OnSheetItemClickListener() {
							@Override
							public void onClick(int which) {
								//Toast.makeText(PersonalActivity.this, "确定", Toast.LENGTH_LONG).show();
								Intent persion = new Intent(PersonalActivity.this,LoginActivity.class);
								Bundle bundle = new Bundle();
								bundle.putCharSequence("flag", "1");
								persion.putExtras(bundle);
								startActivity(persion);
								finish();
							}
						}).show();
       			break;
       		default:
       			break;
       }
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				Toast.makeText(PersonalActivity.this, "04", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
		}
		return false;
	}
	@SuppressLint("HandlerLeak")
	private void Loading()
	{
		final Handler handler = new Handler(){
			public void handleMessage(Message msg){
				if(msg.what == 0){
					progressDialog.show();   //显示对话框
				}
				else if(msg.what == 1){
					progressDialog.dismiss();    //关闭对话框
				}
			}
		};

		new Thread(new Runnable() {                    
			   @Override
			   public void run() {
				   try {
					handler.sendEmptyMessage(0);
					Thread.sleep(5000);
					handler.sendEmptyMessage(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
		}).start();
	}
}
