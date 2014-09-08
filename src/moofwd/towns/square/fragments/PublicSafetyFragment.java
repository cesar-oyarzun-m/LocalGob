package moofwd.towns.square.fragments;

import moofwd.towns.square.R;
import moofwd.towns.square.adapter.SingleListAdapter;
import moofwd.towns.square.model.Model;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

/**
 * Public Safety Fragment
 * @author Cesar Oyarzun
 *
 */
public class PublicSafetyFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_public_safety, container,false);
		SingleListAdapter listAdapter= new SingleListAdapter(getActivity(),R.layout.list_news_events_row, Model.getInstance().listPublicSafetyMockup);
		setListAdapter(listAdapter);
		return inflate;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		if(position==0){
			FragmentManager fm = getActivity().getSupportFragmentManager();
			Fragment fragment = fm.findFragmentById(R.id.content_frame);
			if (fragment == null) {
				fragment = new PoliceBlotterFragment();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				transaction.add(R.id.content_frame, fragment).addToBackStack(null).commit();
				fm.executePendingTransactions();
			}else{
				fragment = new PoliceBlotterFragment();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				transaction.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
				fm.executePendingTransactions();
			}
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
