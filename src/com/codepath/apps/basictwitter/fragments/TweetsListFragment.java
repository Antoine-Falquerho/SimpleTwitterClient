package com.codepath.apps.basictwitter.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TweetArrayAdapter;
import com.codepath.apps.basictwitter.models.Tweet;

public class TweetsListFragment extends Fragment {

	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private ListView lvTweets;	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
		lvTweets = (ListView) v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(aTweets);
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(getActivity(), tweets);
		
//		lvTweets.setOnScrollListener(new EndlessScrollListener() {
//		    @Override
//		    public void onLoadMore(int page, int totalItemsCount) {
//		    	try{
//		    	Tweet last_tweet = tweets.get(tweets.size() - 1);
//		    	loadMoreTweets(last_tweet.getUid() - 1);
//		    	} catch(Exception e){
//		    		Log.d("debug", e.toString());
//		    	}
//		    }			
//	    });
//		
//		lvTweets.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the list contents
//                // Make sure you call listView.onRefreshComplete()
//                // once the loading is done. This can be done from here or any
//                // place such as when the network request has completed successfully.
//                fetchTimelineAsync(0);
//            }
//        });
	}
	
	public void  addAll(ArrayList<Tweet> tweets){
		aTweets.addAll(tweets);
	}
	
//	public void fetchTimelineAsync(int page) {
//        client.getHomeTimeline(new JsonHttpResponseHandler() {
//            public void onSuccess(JSONArray json) {
//                // ...the data has come back, finish populating listview...
//                // Now we call onRefreshComplete to signify refresh has finished
//            	populateTimeline(0);
//                lvTweets.onRefreshComplete();
//            }
//
//            public void onFailure(Throwable e) {
//                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
//            }
//        }, 0);
//    }
}
