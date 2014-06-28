package com.codepath.apps.basictwitter;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {
	private ImageView ivProfileImage;
	private TextView tvName;
	private TextView tvdescription;
	private TextView tvFollwers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		tvName = (TextView)findViewById(R.id.tvName);
		tvdescription = (TextView)findViewById(R.id.tvdescription);
		tvFollwers = (TextView)findViewById(R.id.tvFollwers);
		ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
		
		TwitterApplication.getRestClient().getUserInfo(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject json) {
				User user = User.fromJson(json);
				getActionBar().setTitle(user.getScreenName());
				tvName.setText(user.getName());
				tvdescription.setText(user.getDescription());
				tvFollwers.setText(user.getFollowers() + " Followers    " + user.getFollowing() + " Following" );
				
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
}
