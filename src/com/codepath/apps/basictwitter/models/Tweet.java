package com.codepath.apps.basictwitter.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.text.format.DateUtils;

public class Tweet {
	private String body;
	private long uid;
	private String createdAt;
	private User user;
	
	public static Tweet fromJson(JSONObject jsonObject){
		Tweet tweet = new Tweet();
		try{
			tweet.body = jsonObject.getString("text");
			tweet.uid = jsonObject.getLong("id");
			tweet.createdAt = jsonObject.getString("created_at");		
			tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
		} catch(JSONException e){
			e.printStackTrace();
			return null;
		}
		return tweet;
	}

	public String getBody() {
		return body;
	}

	public long getUid() {
		return uid;
	}

	public String getCreatedAt(){
		return getRelativeTimeAgo(createdAt);
	}

	public User getUser() {
		return user;
	}

	public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
	      // Process each result in json array, decode and convert to business object
	      for (int i=0; i < jsonArray.length(); i++) {
	          JSONObject businessJson = null;
	          try {
	          	businessJson = jsonArray.getJSONObject(i);
	          } catch (Exception e) {
	              e.printStackTrace();
	              continue;
	          }

	          Tweet tweet = Tweet.fromJson(businessJson);
	          if (tweet != null) {
	          	tweets.add(tweet);
	          }
	      }

	      return tweets;
	}
	
	@Override
	public String toString(){
		return body;
	}
	
	public String getRelativeTimeAgo(String rawJsonDate){
		String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
		sf.setLenient(true);
	 
		String relativeDate = "";
		try {
			long dateMillis = sf.parse(rawJsonDate).getTime();
			relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		return relativeDate;
	}
}
