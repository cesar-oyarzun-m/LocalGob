package moofwd.towns.square.fragments;

import moofwd.towns.square.R;
import moofwd.towns.square.adapter.SubmissionAdapter;
import moofwd.towns.square.model.Model;
import moofwd.towns.square.task.RefreshNewsEventsTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * My Submission Fragment
 * 
 * @author Cesar Oyarzun
 * 
 */
public class MySubmissionsFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_my_submissions, container, false);
		SubmissionAdapter listAdapter = new SubmissionAdapter(getActivity(), R.layout.list_submission_row, Model.getInstance().listSubmissionMockup);
		setListAdapter(listAdapter);
		return inflate;
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
			RefreshNewsEventsTask refresh = new RefreshNewsEventsTask(this);
			refresh.execute("");
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
		switch (transit) {
		default:
			if (enter) {
				return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
			} else {
				return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
			}
		}
	}
}
