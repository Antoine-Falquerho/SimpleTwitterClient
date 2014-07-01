# Simple Twitter Client

Build a simple Twitter client that supports viewing a Twitter timeline and composing a new tweet.

Time spent: 14 hours spent in total

Completed user stories:

* [x] User can sign in to Twitter using OAuth login
* [x] User can view the tweets from their home timeline
* [x] User should be able to see the username, name, body and timestamp for each tweet
* [x] User should be displayed the relative timestamp for a tweet "8m", "7h"
* [x] User can view more tweets as they scroll with infinite pagination
* [x] Optional: Links in tweets are clickable and will launch the web browser (see autolink)
* [x] User can compose a new tweet
* [x] User can click a “Compose” icon in the Action Bar on the top right
* [x] User can then enter a new tweet and post this to twitter
* [x] User is taken back to home timeline with new tweet visible in timeline
* [x] Optional: User can see a counter with total number of characters left for tweet

The following advanced user stories are optional:

* [x] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
* [ ] Advanced: User can open the twitter app offline and see last loaded tweets
Tweets are persisted into sqlite and can be displayed from the local DB
* [ ] Advanced: User can tap a tweet to display a "detailed" view of that tweet
* [ ] Advanced: User can select "reply" from detail view to respond to a tweet
* [x] Advanced: Improve the user interface and theme the app to feel "twitter branded"
* [ ] Bonus: User can see embedded image media within the tweet detail view
* [ ] Bonus: Compose activity is replaced with a modal overlay

Walkthrough of all user stories:

![Video Walkthrough](simpleTwitterClient.gif)


* [x] User can switch between Timeline and Mention views using tabs.
* [x] User can view their home timeline tweets.
* [x] User can view the recent mentions of their username.
* [x] User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll")
* [x] User can navigate to view their own profile
* [x] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* [x] User can click on the profile image in any tweet to see another user's profile.
* [x] User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
* [x] Profile view should include that user's timeline

![Video Walkthrough](simpleTwitterClient2.gif)