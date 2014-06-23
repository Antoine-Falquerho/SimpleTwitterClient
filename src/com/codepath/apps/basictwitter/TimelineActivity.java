package com.codepath.apps.basictwitter;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
	private TwitterClient client;
	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private ListView lvTweets;
	private static int REQUEST_CODE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false); // hide app name in action bar
		setContentView(R.layout.activity_timeline);
		client = TwitterApplication.getRestClient();
		populateTimeline(0);
		lvTweets = (ListView)findViewById(R.id.lvTweets);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(this, tweets);
		lvTweets.setAdapter(aTweets);
		
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
		    @Override
		    public void onLoadMore(int page, int totalItemsCount) {
		    	Tweet last_tweet = tweets.get(tweets.size() - 1);
		    	loadMoreTweets(last_tweet.getUid());
		    }			
	    });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();	    
	    inflater.inflate(R.menu.main, menu);	    
	   return super.onCreateOptionsMenu(menu);
	}
	
	public void populateTimeline(long maxId){
		client.getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray json) {
				aTweets.addAll(Tweet.fromJSONArray(json));
			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.d("debug", arg0.toString());
				Log.d("debug", arg1.toString());
			}
		}, maxId);
	}
	
	public void onNewStatus(MenuItem mi) {
		// TODO Auto-generated method stub	
		Intent i = new Intent(this, NewStatusActivity.class);
		startActivityForResult(i, REQUEST_CODE);
	}
	
	private void loadMoreTweets(long maxId) {
		populateTimeline(maxId);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {		
		Tweet tweet = (Tweet) data.getSerializableExtra("tweet");
		tweets.add(0, tweet);
		aTweets.notifyDataSetChanged();

	} 
}
