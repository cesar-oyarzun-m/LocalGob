package moofwd.towns.square.fragments;

import moofwd.towns.square.R;
import moofwd.towns.square.adapter.ListAllAdapter;
import moofwd.towns.square.model.Model;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * News Fragment
 * @author Cesar Oyarzun
 *
 */
public class NewsFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_news, container,false);
		ListAllAdapter listAdapter= new ListAllAdapter(getActivity(),R.layout.list_news_events_row, Model.getInstance().listNewsMockup);
		setListAdapter(listAdapter);
		
		return inflate;
	}
}
