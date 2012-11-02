package com.vertigem.dukex;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    PostsPagerAdapter postPagerAdapter;

    ViewPager postsPager;
    
    private final ArrayList<Post> posts = new ArrayList<Post>();
    
    
    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    	posts.add(new Post(
    			"A Felicidade Ficar", 
    			"O importante é a felicidade ficar!\nAfinal tudo vai, vem, foi, é\nNada precisamos suportar\nUm dia temos tudo\nNo outro nada vai estar"));
    	
    	posts.add(new Post(
    			"Duke por Duke", 
    			"Me traz essa menina\nquero de volta\nSeu amor e sua dor"));
    	
    	postPagerAdapter = new PostsPagerAdapter(getSupportFragmentManager(), posts);
  
    	
        postsPager = (ViewPager) findViewById(R.id.pager);
        postsPager.setAdapter(postPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class PostsPagerAdapter extends FragmentPagerAdapter {

		
    	
        private ArrayList<Post> posts;

		public PostsPagerAdapter(FragmentManager fm, ArrayList<Post> posts) {
            super(fm);
            this.posts = posts;
        }
        
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new PostFragment(this.posts.get(i));
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return posts.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	return (CharSequence) posts.get(position).title;
        }
    }

    public static class PostFragment extends Fragment{
   	 	public Post post;
   	 
    	public PostFragment(Post post){
    		this.post = post;
    	}

    	 
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.post, container, false);
            
            TextView textView = (TextView) layout.findViewById(R.id.text);
            textView.setText(post.text);
            
            TextView titleView = (TextView) layout.findViewById(R.id.title);
            titleView.setText(post.title);
            
            return layout;
        }
    	
    }
}
