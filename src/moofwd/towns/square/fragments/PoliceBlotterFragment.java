package moofwd.towns.square.fragments;

import moofwd.towns.square.MoofwdTownActivity;
import moofwd.towns.square.R;
import moofwd.towns.square.adapter.PoliceBlotterAdapter;
import moofwd.towns.square.model.Model;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Police Blotter Fragment
 * @author Cesar Oyarzun
 *
 */
public class PoliceBlotterFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		MoofwdTownActivity activity = (MoofwdTownActivity) getActivity();
		activity.resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		activity.getSupportActionBar().setTitle(R.string.police_blotter);
		activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP| ActionBar.DISPLAY_SHOW_TITLE);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_police_blotter, container,false);
		PoliceBlotterAdapter listAdapter= new PoliceBlotterAdapter(getActivity(),R.layout.list_police_blotter_row, Model.getInstance().listPoliceBlotterMockup);
		setListAdapter(listAdapter);
		return inflate;
	}
	
	@TargetApi(14)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			MoofwdTownActivity activity = (MoofwdTownActivity) getActivity();
			activity.getSupportFragmentManager().popBackStack();
			activity.resetActionBar(false, DrawerLayout.LOCK_MODE_UNLOCKED);
			activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			activity.getSupportActionBar().setTitle(R.string.public_safety);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				activity.getSupportActionBar().setHomeButtonEnabled(true);
				activity.getSupportActionBar().setIcon(R.drawable.top_header_menu_icon);
				activity.getSupportActionBar().setDisplayOptions(
						ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		switch (transit) {
		default:
			if (enter) {
				return AnimationUtils.loadAnimation(getActivity(),
						android.R.anim.fade_in);
			} else {
				return AnimationUtils.loadAnimation(getActivity(),
						android.R.anim.fade_out);
			}
		}
	}
}
