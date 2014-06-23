package com.codepath.apps.basictwitter;

import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NewStatusActivity extends Activity {
	private EditText etStatus;
	private ImageView ivProfileImage;
	private TextView tvName;
	private TextView tvScreenName;
	private TwitterClient client;
	private User user;
	private Menu menu;
	private MenuItem iTweetLenght;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false); // hide app name in action bar
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
		setContentView(R.layout.activity_new_status);
		etStatus = (EditText)findViewById(R.id.etStatus);
		tvName = (TextView)findViewById(R.id.tvName);
		tvScreenName = (TextView)findViewById(R.id.tvScreenName);
		ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);		
		
		client = TwitterApplication.getRestClient();
		getUserInfo();		
		etStatus.addTextChangedListener(new TextWatcher() {
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // Fires right as the text is being changed (even supplies the range of text)
		    	Log.d("debug", 140 - etStatus.length() + "");
		    	int length = 140 - etStatus.length();
		    	
		    	iTweetLenght = menu.findItem(R.id.iTweetLenght);		        
		    	iTweetLenght.setTitle(length + "");
		    }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
	    MenuInflater inflater = getMenuInflater();	    
	    inflater.inflate(R.menu.post_tweet, menu);	    
	   return super.onCreateOptionsMenu(menu);
	}
	
	
	public void onPostStatus(MenuItem mi){
		TwitterClient client = new TwitterClient(this);
		client.postStatus(etStatus.getText().toString(), new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject json) {
				Toast.makeText(getApplicationContext(), "Tweet posted", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.d("debug", arg0.toString());
				Log.d("debug", arg1.toString());
				Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
			}
		});
		finish();
	}
	
	public void getUserInfo(){		
		client.getUserInfo(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject json) {
				user = User.fromJson(json);
				tvName.setText(user.getName());
				tvScreenName.setText(user.getScreenName());
				
				ivProfileImage.setImageResource(android.R.color.transparent);
			    ImageLoader imageLoader = ImageLoader.getInstance();
			    imageLoader.displayImage(user.getProfileImageUrl(), ivProfileImage);
			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.d("debug", arg0.toString());
				Log.d("debug", arg1.toString());				
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
	            finish();
//	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
