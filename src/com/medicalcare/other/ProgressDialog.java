package com.medicalcare.other;

import com.medicalcare.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 进度条对话框
 */
public class ProgressDialog extends Dialog {

	private static ProgressDialog myProgressDialog;

	private ProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param cancelable
	 * 
	 * @return
	 */
	public static ProgressDialog show(Context context, String title,String message, boolean cancelable) {
		myProgressDialog = new ProgressDialog(context,R.style.CustomProgressDialog);
		myProgressDialog.setContentView(R.layout.progress_dialog);
		myProgressDialog.setCancelable(cancelable);
		myProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		TextView tv_message = (TextView) myProgressDialog.findViewById(R.id.tv_message);
		if (null == message || "".equals(message))
			tv_message.setVisibility(View.GONE);
		else
			tv_message.setText(message);
		return myProgressDialog;
	}

	public static ProgressDialog show(Context context, String message) {
		return show(context, "", message, false);
	}

	@Override
	public void cancel() {
		super.cancel();
		myProgressDialog = null;
	}

	@Override
	public void dismiss() {
		super.dismiss();
		myProgressDialog = null;
	}

}