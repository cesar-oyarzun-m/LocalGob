package moofwd.towns.square.fragments;

import java.util.List;
import java.util.Vector;

import moofwd.towns.square.R;
import moofwd.towns.square.adapter.PagerFragmentAdapter;
import moofwd.towns.square.task.RefreshNewsEventsTask;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

/**
 * TabHost View Pager fragment
 * 
 * @author Cesar Oyarzun
 * 
 */
public class TabHostViewPagerFragment extends Fragment implements
		TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

	private String EVENTS = null;
	private String NEWS = null;
	private String ALL = null;
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private PagerFragmentAdapter mPagerAdapter;

	private class TabInfo {
		private String tag;
		private Class<?> clss;
		private Bundle args;

		TabInfo(String tag, Class<?> clazz, Bundle args) {
			this.tag = tag;
		}

	}

	/**
	 * A simple factory that returns dummy views to the Tabhost
	 * 
	 */
	class TabFactory implements TabContentFactory {

		private final Context mContext;

		public TabFactory(Context context) {
			mContext = context;
		}

		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		EVENTS = getResources().getString(R.string.events_segment);
		NEWS = getResources().getString(R.string.news_segment);
		ALL = getResources().getString(R.string.all_segment);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_tab_host, container,
				false);
		this.intialiseViewPager(inflate);
		this.initialiseTabHost(savedInstanceState, inflate);
		return inflate;
	}

	/**
	 * Initialise ViewPager
	 * 
	 * @param inflate
	 * @param inflater
	 */
	private void intialiseViewPager(View inflate) {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments
				.add(Fragment.instantiate(
						getActivity().getApplicationContext(),
						AllNewsEventsFragment.class.getName()));
		fragments.add(Fragment.instantiate(getActivity()
				.getApplicationContext(), NewsFragment.class.getName()));
		fragments.add(Fragment.instantiate(getActivity()
				.getApplicationContext(), EventsFragment.class.getName()));
		this.mPagerAdapter = new PagerFragmentAdapter(getActivity()
				.getSupportFragmentManager(), fragments);
		this.mViewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);
		
	}

	/**
	 * Initialise the Tab Host
	 * 
	 * @param inflate
	 */
	private void initialiseTabHost(Bundle args, View inflate) {
		mTabHost = (TabHost) inflate.findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		TabHostViewPagerFragment
				.AddTab(this,
						this.mTabHost,
						this.mTabHost.newTabSpec(ALL).setIndicator(
								createTabView(mTabHost.getContext(), ALL)),
						(tabInfo = new TabInfo(ALL,
								AllNewsEventsFragment.class, args)));
		TabHostViewPagerFragment.AddTab(
				this,
				this.mTabHost,
				this.mTabHost.newTabSpec(NEWS).setIndicator(
						createTabView(mTabHost.getContext(), NEWS)),
				(tabInfo = new TabInfo(NEWS, NewsFragment.class, args)));
		TabHostViewPagerFragment.AddTab(
				this,
				this.mTabHost,
				this.mTabHost.newTabSpec(EVENTS).setIndicator(
						createTabView(mTabHost.getContext(), EVENTS)),
				(tabInfo = new TabInfo(EVENTS, EventsFragment.class, args)));
		mTabHost.setOnTabChangedListener(this);

	}

	/**
	 * Create Custom View for Tab
	 * @param context
	 * @param text
	 * @return
	 */
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tab_indicator_layout, null);
		TextView tv = (TextView) view.findViewById(R.id.title);
		tv.setText(text);
		return view;
	}

	/**
	 * Add Tab content to the Tabhost
	 * 
	 * @param fragment
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private static void AddTab(TabHostViewPagerFragment fragment,
			TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		// Attach a Tab view factory to the spec
		tabSpec.setContent(fragment.new TabFactory(fragment.getActivity()
				.getApplicationContext()));
		tabHost.addTab(tabSpec);
	}
	
	/**
	 * On Tab Change
	 */
	public void onTabChanged(String tag) {
		int pos = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		this.mTabHost.setCurrentTab(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_action_bar_refresh, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_refresh:
			RefreshNewsEventsTask refresh=new RefreshNewsEventsTask(this);
			refresh.execute("");
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
