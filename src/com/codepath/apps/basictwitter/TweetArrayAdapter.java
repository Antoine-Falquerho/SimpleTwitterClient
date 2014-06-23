package com.codepath.apps.basictwitter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
	       Tweet tweet = getItem(position);    
	       // Check if an existing view is being reused, otherwise inflate the view
	       View v;
	       if (convertView == null) {
	          LayoutInflater inflator = LayoutInflater.from(getContext());
	          v = inflator.inflate(R.layout.tweet_item, parent, false);
	       } else{
	    	   v = convertView;
	       }
	       ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
	       // Lookup view for data population
	       TextView tvScreenName = (TextView) v.findViewById(R.id.tvScreenName);
	       TextView tvName = (TextView) v.findViewById(R.id.tvName);
	       TextView tvCreatedAt = (TextView) v.findViewById(R.id.tvCreatedAt);	       
	       TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
	       ivProfileImage.setImageResource(android.R.color.transparent);
	       ImageLoader imageLoader = ImageLoader.getInstance();
	       imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
	       // Populate the data into the template view using the data object
	       tvScreenName.setText(tweet.getUser().getScreenName());
	       tvCreatedAt.setText(tweet.getCreatedAt());
	       tvBody.setText(tweet.getBody());
	       tvName.setText(tweet.getUser().getName());
	       // Return the completed view to render on screen
	       return v;
	}

}
