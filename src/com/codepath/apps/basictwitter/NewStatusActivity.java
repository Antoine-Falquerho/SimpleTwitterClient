package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewStatusActivity extends Activity {
	private EditText etStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_status);
		etStatus = (EditText)findViewById(R.id.etStatus);
	}
	
	
	public void onPostStatus(View v){
		TwitterClient client = new TwitterClient(this);
		client.postStatus(etStatus.getText().toString());
		finish();
	}
}
