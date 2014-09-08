package moofwd.towns.square;

import moofwd.towns.square.adapter.NavigationDrawerAdapter;
import moofwd.towns.square.fragments.AboutFragment;
import moofwd.towns.square.fragments.DepartmentInfoFragment;
import moofwd.towns.square.fragments.MySubmissionsFragment;
import moofwd.towns.square.fragments.PublicSafetyFragment;
import moofwd.towns.square.fragments.TabHostViewPagerFragment;
import moofwd.towns.square.fragments.TellUsFragment;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Main Township Activity
 * 
 * @author Cesar Oyarzun
 * 
 */
public class MoofwdTownActivity extends ActionBarActivity {

	private static final String TITLE = "title";
	private String[] mSlideMenuItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;

	private Integer[] mSlideMenuItemsImages = { R.drawable.slide_menu_icon_home, R.drawable.slide_menu_icon_tell_us, 
			R.drawable.slide_menu_icon_my_reports, R.drawable.slide_menu_icon_department, R.drawable.slide_menu_icon_public_safety };

	@TargetApi(14)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moofwd_town);
		if (savedInstanceState != null) {
			mTitle = (CharSequence) savedInstanceState.get(TITLE);
			setTitle(mTitle);
		} else {
			mTitle = getTitle();
		}
		mSlideMenuItems = getResources().getStringArray(R.array.slide_menu_array);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new NavigationDrawerAdapter(this, mSlideMenuItemsImages, mSlideMenuItems));

		// enable ActionBar app icon to behave as action to toggle nav drawer

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setIcon(R.drawable.top_header_menu_icon);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		// ActionBar actionBar = getActionBar();
		// actionBar.setDisplayShowCustomEnabled(true);
		// actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionBar.setCustomView(getLayoutInflater().inflate(R.layout.custom_action_bar,
		// null),
		// new ActionBar.LayoutParams(
		// ActionBar.LayoutParams.WRAP_CONTENT,
		// ActionBar.LayoutParams.MATCH_PARENT
		// )
		// );
		//

		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				setTitleActionBar();
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				setTitleActionBar();
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.content_frame);
		if (fragment == null) {
			fragment = createFragment(0);
			fm.beginTransaction().add(R.id.content_frame, fragment, String.valueOf(0)).commit();
			setTitle(mSlideMenuItems[0]);
		}

	}

	private void setTitleActionBar() {
		// View customView = getActionBar().getCustomView();
		// TextView textView = (TextView)
		// customView.findViewById(R.id.titleTextActionBar);
		// textView.setText(mTitle);
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mSlideMenuItems[position]);
		mDrawerLayout.closeDrawer(mDrawerList);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.content_frame);
		if (fragment == null) {
			fragment = createFragment(position);
			fm.beginTransaction().add(R.id.content_frame, fragment, String.valueOf(position)).commit();
		} else {
			fragment = createFragment(position);
			fm.beginTransaction().replace(R.id.content_frame, fragment, String.valueOf(position)).commit();
		}

	}

	/**
	 * Create fragment from navigation drawer
	 * 
	 * @param position
	 * @return
	 */
	public Fragment createFragment(int position) {
		Fragment frag = null;
		switch (position) {
		case 0:
			frag = new TabHostViewPagerFragment();
			break;
		case 1:
			frag = new TellUsFragment();
			break;
		case 2:
			frag = new MySubmissionsFragment();
			break;
		case 3:
			frag = new DepartmentInfoFragment();
			break;
		case 4:
			frag = new PublicSafetyFragment();
			break;
		case 5:
			frag = new AboutFragment();
			break;
		default:
			break;
		}
		return frag;
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		setTitleActionBar();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	// The method is in MainActivity.java
	public void resetActionBar(boolean childAction, int drawerMode) {
		if (childAction) {
			// [Undocumented?] trick to get up button icon to show
			mDrawerToggle.setDrawerIndicatorEnabled(false);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			mDrawerToggle.setDrawerIndicatorEnabled(true);
		}

		mDrawerLayout.setDrawerLockMode(drawerMode);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putCharSequence(TITLE, mTitle);
		super.onSaveInstanceState(outState);
	}

}
