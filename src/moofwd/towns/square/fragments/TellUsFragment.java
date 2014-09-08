package moofwd.towns.square.fragments;

import moofwd.towns.square.R;
import moofwd.towns.square.adapter.SingleListAdapter;
import moofwd.towns.square.model.Model;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Tell Us Fragment
 * 
 * @author Cesar Oyarzun
 * 
 */
public class TellUsFragment extends Fragment {

	public static final String TITLE = "title";
	public static final String TELL_US_REPORT = "Tell_Us_Report";
	public static final String CRIME_TIP = "Crime_Tip";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_tell_us, container, false);
		ListView listViewPolice = (ListView) inflate.findViewById(R.id.listPolice);
		ListView listViewStreet = (ListView) inflate.findViewById(R.id.listStreet);
		ListView listViewOthers = (ListView) inflate.findViewById(R.id.listOthers);

		listViewPolice.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				Fragment fragment = fm.findFragmentById(R.id.content_frame);
				if (fragment == null) {
					fragment = createFragment(fm, position);
				} else {
					fragment = replaceFragment(fm, position);
				}
			}
		});

		listViewStreet.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				Fragment fragment = fm.findFragmentById(R.id.content_frame);
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

				if (fragment == null) {
					fragment = new TellUsReportFragment();
					fragment.setArguments(createTitleStreets(position));
					transaction.add(R.id.content_frame, fragment,TELL_US_REPORT).addToBackStack(TELL_US_REPORT).commit();
					fm.executePendingTransactions();
				} else {
					fragment = new TellUsReportFragment();
					fragment.setArguments(createTitleStreets(position));
					transaction.replace(R.id.content_frame, fragment,TELL_US_REPORT).addToBackStack(TELL_US_REPORT).commit();
					fm.executePendingTransactions();
				}
			}
		});

		listViewOthers.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				Fragment fragment = fm.findFragmentById(R.id.content_frame);
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

				if (fragment == null) {
					fragment = new TellUsReportFragment();
					fragment.setArguments(createTitlOthers(position));
					transaction.add(R.id.content_frame, fragment,TELL_US_REPORT).addToBackStack(TELL_US_REPORT).commit();
					fm.executePendingTransactions();
				} else {
					fragment = new TellUsReportFragment();
					fragment.setArguments(createTitlOthers(position));
					transaction.replace(R.id.content_frame, fragment,TELL_US_REPORT).addToBackStack(TELL_US_REPORT).commit();
					fm.executePendingTransactions();
				}

			}
		});
		SingleListAdapter listPoliceAdapter = new SingleListAdapter(getActivity(), R.layout.list_simple_row, Model.getInstance().listTellUsPoliceMockup);
		SingleListAdapter listStreetAdapter = new SingleListAdapter(getActivity(), android.R.layout.simple_list_item_1, Model.getInstance().listTellUsStreetMockup);
		SingleListAdapter listOthersAdapter = new SingleListAdapter(getActivity(), android.R.layout.simple_list_item_1, Model.getInstance().listTellUsOthersMockup);
		listViewPolice.setAdapter(listPoliceAdapter);
		listViewStreet.setAdapter(listStreetAdapter);
		listViewOthers.setAdapter(listOthersAdapter);
		return inflate;
	}

	/**
	 * Create Fragment to report issue
	 * 
	 * @param fm
	 * @param position
	 * @return
	 */
	private Fragment createFragment(FragmentManager fm, int position) {
		Fragment fragment = null;
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		switch (position) {
		case 1:
			fragment = new CrimeTipFragment();
			fragment.setArguments(createTitlePolice(position));
			transaction.add(R.id.content_frame, fragment,CRIME_TIP).addToBackStack(CRIME_TIP).commit();
			fm.executePendingTransactions();
			break;
		default:
			fragment = new TellUsReportFragment();
			fragment.setArguments(createTitlePolice(position));
			transaction.replace(R.id.content_frame, fragment,TELL_US_REPORT).addToBackStack(TELL_US_REPORT).commit();
			fm.executePendingTransactions();
			break;
		}
		return fragment;
	}

	/**
	 * Replace Fragment to report issue
	 * 
	 * @param fm
	 * @param position
	 * @return
	 */
	private Fragment replaceFragment(FragmentManager fm, int position) {
		Fragment fragment = null;
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

		switch (position) {
		case 1:
			fragment = new CrimeTipFragment();
			fragment.setArguments(createTitlePolice(position));
			transaction.replace(R.id.content_frame, fragment,CRIME_TIP).addToBackStack(CRIME_TIP).commit();
			fm.executePendingTransactions();
			break;
		default:
			fragment = new TellUsReportFragment();
			fragment.setArguments(createTitlePolice(position));
			transaction.replace(R.id.content_frame, fragment,TELL_US_REPORT).addToBackStack(TELL_US_REPORT).commit();
			fm.executePendingTransactions();
			break;
		}

		return fragment;
	}

	/**
	 * Create Title Action Bar Title Streets
	 * @param position
	 * @return
	 */
	private Bundle createTitleStreets(int position) {
		Bundle bundle = new Bundle();
		switch (position) {
		case 0:
			bundle.putString(TITLE, getActivity().getString(R.string.report_pot_hole));
			break;
		case 1:
			bundle.putString(TITLE, getActivity().getString(R.string.report_traffic_light_out));
			break;
		case 2:
			bundle.putString(TITLE, getActivity().getString(R.string.report_road_kill));
		default:
			break;
		}
		return bundle;
	}

	/**
	 * Create Title Action Bar Police
	 * @param position
	 * @return
	 */
	private Bundle createTitlePolice(int position) {
		Bundle bundle = new Bundle();
		switch (position) {
		case 0:
			bundle.putString(TITLE, getActivity().getString(R.string.suspicious_activity));
			break;
		case 1:
			bundle.putString(TITLE, getActivity().getString(R.string.crime_tip));
			break;
		default:
			break;
		}
		return bundle;
	}

	/**
	 * Create Title Action Bar Others
	 * @param position
	 * @return
	 */
	private Bundle createTitlOthers(int position) {
		Bundle bundle = new Bundle();
		switch (position) {
		case 0:
			bundle.putString(TITLE, getActivity().getString(R.string.report_graffiti));
			break;
		case 1:
			bundle.putString(TITLE, getActivity().getString(R.string.report_neighbordhood_nuisances));
			break;
		case 2:
			bundle.putString(TITLE, getActivity().getString(R.string.report_other_issues));
			break;
		default:
			break;
		}
		return bundle;
	}
	
}
