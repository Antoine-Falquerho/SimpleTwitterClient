package com.codepath.apps.basictwitter.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basictwitter.EndlessScrollListener;
import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragment extends TweetsListFragment {
	private TwitterClient client;
	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private ListView lvTweets;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
		populateTimeline(0);
	}
	
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
			lvTweets = (ListView) v.findViewById(R.id.lvTweets);
			lvTweets.setAdapter(aTweets);
			
			lvTweets.setOnScrollListener(new EndlessScrollListener() {
			    @Override
			    public void onLoadMore(int page, int totalItemsCount) {
			    	Log.d("debug", "11111111111111");
			    	try{
			    	Tweet last_tweet = tweets.get(tweets.size() - 1);
			    	loadMoreTweets(last_tweet.getUid() - 1);
			    	
			    	} catch(Exception e){
			    		Log.d("debug", e.toString());
			    	}
			    }			
		    });
		
			return super.onCreateView(inflater, container, savedInstanceState);
		}
	
	public void populateTimeline(long maxId){
		client.getMentionsTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray json) {
				addAll(Tweet.fromJSONArray(json));
			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.d("debug", arg0.toString());
				Log.d("debug", arg1.toString());
			}
		}, maxId);
	}
	
	private void loadMoreTweets(long maxId) {
		populateTimeline(maxId);
	}
}
