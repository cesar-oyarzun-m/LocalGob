package moofwd.towns.square.fragments;

import moofwd.towns.square.R;
import moofwd.towns.square.adapter.ListAllAdapter;
import moofwd.towns.square.model.Model;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * All News Events Fragment
 * @author Cesar Oyarzun
 *
 */
public class AllNewsEventsFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_all_news_events, container,false);
		ListAllAdapter listAdapter= new ListAllAdapter(getActivity(),R.layout.list_news_events_row, Model.getInstance().listAllMockup);
		setListAdapter(listAdapter);
		return inflate;
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
