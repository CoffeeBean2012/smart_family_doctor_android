package com.medicalcare.adapter;

import com.medicalcare.R;
import com.medicalcare.other.FunctionBaseView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Description:gridview鐨凙dapter
 * @author http://blog.csdn.net/finddreams
 */
public class FunctionAdapter extends BaseAdapter {
	private Context mContext;

	public String[] img_text = { "转账", "余额宝", "手机充值", "信用卡还款", "淘宝电影", "彩票",
			"当面付", "亲密付" };
	public int[] imgs = { 
			R.drawable.flag, R.drawable.flag,
			R.drawable.flag, R.drawable.flag,
			R.drawable.flag, R.drawable.flag,
			R.drawable.flag, R.drawable.flag, 
			R.drawable.flag,R.drawable.flag,
			R.drawable.flag,R.drawable.flag};
	
	public FunctionAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return img_text.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.function_item, parent, false);
		}
		TextView tv = FunctionBaseView.get(convertView, R.id.tv_item);
		ImageView iv = FunctionBaseView.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(imgs[position]);

		tv.setText(img_text[position]);
		return convertView;
	}

}
