package com.codepath.apps.basictwitter;

import com.codepath.apps.basictwitter.fragments.HomeTimelineFragment;
import com.codepath.apps.basictwitter.fragments.MentionsTimelineFragment;
import com.codepath.apps.basictwitter.fragments.TweetsListFragment;
import com.codepath.apps.basictwitter.listeners.FragmentTabListener;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class TimelineActivity extends FragmentActivity {
	
	private static int REQUEST_CODE = 10;
	private FragmentTransaction fragment;    


	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		setupTabs();
		showProgressBar();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();	    
	    inflater.inflate(R.menu.main, menu);	    
	   return super.onCreateOptionsMenu(menu);
	}
		
	
	public void onNewStatus(MenuItem mi) {
		// TODO Auto-generated method stub	
		Intent i = new Intent(this, NewStatusActivity.class);
		startActivityForResult(i, REQUEST_CODE);
	}
	
	public void onProfileView(MenuItem mi){
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
	}
	
//	private void loadMoreTweets(long maxId) {
//		populateTimeline(maxId);
//	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
//			Tweet tweet = (Tweet) data.getSerializableExtra("tweet");
//			fragmentTweetsList.add(0, tweet);
//			aTweets.notifyDataSetChanged();
			Log.d("debug", "backup");
//			fragment.addTweet();
		}

	} 
	
	private void setupTabs() {		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
		    .newTab()
		    .setText("Home")
		    .setTag("HomeTimelineFragment")
		    .setTabListener(new FragmentTabListener<HomeTimelineFragment>(R.id.flContainer, this,
                        "home", HomeTimelineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
		    .newTab()
		    .setText("Mentions")
		    .setTag("MentionsTimelineFragment")
		    .setTabListener(new FragmentTabListener<MentionsTimelineFragment>(R.id.flContainer, this,
                        "mentions", MentionsTimelineFragment.class));
		actionBar.addTab(tab2);	
	}
	
	public void FragmentTabListener(FragmentActivity activity, String tag, Class<HomeTimelineFragment> clz) {
		Log.d("debug", clz.toString());
	}
	
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		Log.d("debug", tab.toString());
	}
	
	public void showProgressBar() {
        setProgressBarIndeterminateVisibility(true); 
    }
    
    // Should be called when an async task has finished
    public void hideProgressBar() {
    	setProgressBarIndeterminateVisibility(false); 
    }
	
}
