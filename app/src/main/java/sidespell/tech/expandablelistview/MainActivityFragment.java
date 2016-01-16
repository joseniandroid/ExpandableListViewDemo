package sidespell.tech.expandablelistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sidespell.tech.expandablelistview.adapters.ExpandableListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List<String>              mHeaders;
    private Map<String, List<String>> mChildData;

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Get the ListView
        ExpandableListView lvExpandable =
                (ExpandableListView) view.findViewById(R.id.expandableListView);

        // Prepare the list data
        prepareListData();

        // Create and set the adapter
        ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(), mHeaders, mChildData);
        lvExpandable.setAdapter(adapter);
    }

    private void prepareListData() {
        mHeaders = new ArrayList<>();
        mChildData = new HashMap<>();

        // Adding header data
        mHeaders.add("Top 250");
        mHeaders.add("Now Showing");
        mHeaders.add("Coming Soon...");

        // Adding child data
        List<String> top250 = new ArrayList<>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        mChildData.put(mHeaders.get(0), top250);
        mChildData.put(mHeaders.get(1), nowShowing);
        mChildData.put(mHeaders.get(2), comingSoon);
    }
}
