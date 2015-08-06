package com.medicalcare.activity;

import com.medicalcare.MainActivity;
import com.medicalcare.R;
import com.medicalcare.other.AlertDialog;
import com.medicalcare.other.ProgressDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("WorldWriteableFiles")
public class LoginActivity extends Activity {

	private Context mContext;
	private RelativeLayout all_layout;
	private RelativeLayout rl_user;  //布局
	private Button mLogin;       //登录
	private TextView register;        //注册
	
	private EditText account;
	private EditText password;
	
	public SharedPreferences pre;   //标志自动登录
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mContext=this;
		findView();
		init();
	}
	
	@SuppressWarnings("deprecation")
	private void findView(){
		all_layout = (RelativeLayout) findViewById(R.id.relativelayout);
		rl_user=(RelativeLayout) findViewById(R.id.rl_user);
		account = (EditText)findViewById(R.id.account);
		password = (EditText)findViewById(R.id.password);
		mLogin=(Button) findViewById(R.id.login);
		register=(TextView) findViewById(R.id.register);
		
		
		pre=getSharedPreferences("longinvalue", MODE_WORLD_WRITEABLE);
		
		Intent login = getIntent();
		Bundle bundle = login.getExtras();
		String flag = bundle.getString("flag");
		if(flag.equals("1")){
			pre = null;
			//pre.edit().putBoolean("islgs", true).putString("account", null).putString("password", null).commit();
		}
		if(pre!=null){
			//Toast.makeText(LoginActivity.this, "aa", Toast.LENGTH_LONG).show();
			if(pre.getBoolean("islgs", false)==true){
				
	        	   //isLoginSelf.setChecked(true);
	        	//   creatDialog();  //对话框    只要用来显示
	        	 //  new Thread(){     
	        		//   public void run() {
	        			//   try {
	        				  // Toast.makeText(LoginActivity.this, "登录成功YES", Toast.LENGTH_LONG).show();
	        				//   Thread.sleep(3000);
	      				//  	if(mDialog.isShowing()){
						//		  mDialog.dismiss();
	      				//  	}
								//Loading();   //等待进度条
	        				   Intent intent2=new Intent(LoginActivity.this,MainActivity.class);  //跳转
	        				   startActivity(intent2);
	        				   finish();
	      				//  	Toast.makeText(LoginActivity.this, "登录成功YES", Toast.LENGTH_LONG).show();
					//	} catch (Exception e) {
							// TODO: handle exception
					//	}
	        		//   }
	        	//   }.start();
	           }
		}
	}

	private void init(){
		Animation anim=AnimationUtils.loadAnimation(mContext, R.anim.login_anim);
		anim.setFillAfter(true);
		rl_user.startAnimation(anim);
		all_layout.setOnClickListener(relativelayoutOnClickListener);
		mLogin.setOnClickListener(loginOnClickListener);
		register.setOnClickListener(registerOnClickListener);
	}
	
	private OnClickListener loginOnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(!account.getText().toString().equals("") && !password.getText().toString().equals("")){
				pre.edit().putBoolean("islgs", true).putString("account", account.getText().toString()).putString("password", password.getText().toString()).commit();
				Loading();
				/*Intent intent=new Intent(mContext,MainActivity.class);
				startActivity(intent);
				finish();*/
			}else{
				new AlertDialog(LoginActivity.this).builder()
				.setMsg("亲，帐号或密码不能为空哦！")
				.setNegativeButton("确定", new OnClickListener() {
					@Override
					public void onClick(View v) {
					}
				}).show();
			}
		}
	};
	
	private OnClickListener registerOnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Toast.makeText(LoginActivity.this, "login", Toast.LENGTH_LONG).show();
		//	Intent intent=new Intent(mContext, RegisterPhoneActivity.class);
		//	startActivity(intent);
			
		}
	};
	
	private OnClickListener relativelayoutOnClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//点击空白处隐藏虚拟键盘
			((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}
	};
	
	private void Loading()
	{
		final ProgressDialog progressDialog = ProgressDialog.show(this, "加载中...");;
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
				}finally{
					Intent intent=new Intent(mContext,MainActivity.class);
					startActivity(intent);
					finish();
				}
			   }
		}).start();
	}
}
