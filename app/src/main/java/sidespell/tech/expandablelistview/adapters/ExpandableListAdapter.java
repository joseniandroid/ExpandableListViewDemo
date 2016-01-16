package sidespell.tech.expandablelistview.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sidespell.tech.expandablelistview.R;

/**
 * A custom adapter to be used for the ExpandaleListView.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    // header titles
    private List<String> mListHeader;

    // child data in format of header title, child title
    private Map<String, List<String>> mListChild;

    public ExpandableListAdapter(Context context, List<String> headers,
                                 Map<String, List<String>> childData) {
        mContext = context;
        mListHeader = headers;
        mListChild = childData;
    }

    @Override
    public int getGroupCount() {
        return mListHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListChild.get(mListHeader.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return mListHeader.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mListChild.get(mListHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        GroupViewHolder viewHolder;

        if (convertView == null) {
            // Inflate the list group layout
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_group, parent, false);

            // Create the view holder
            viewHolder = new GroupViewHolder();
            viewHolder.tvListHeader = (TextView) convertView.findViewById(R.id.tvListHeader);

            // Set the view holder tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }

        if (viewHolder.tvListHeader != null) {
            final String headerTitle = getGroup(groupPosition);

            viewHolder.tvListHeader.setTypeface(null, Typeface.BOLD);
            viewHolder.tvListHeader.setText(headerTitle);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;

        if (convertView == null) {
            // Inflate the list item layout
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

            // Create the view holder
            viewHolder = new ChildViewHolder();
            viewHolder.tvListItem = (TextView) convertView.findViewById(R.id.tvListItem);

            // Set the view holder tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        if (viewHolder.tvListItem != null) {
            final String childText = getChild(groupPosition, childPosition);
            viewHolder.tvListItem.setText(childText);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static final class GroupViewHolder {
        TextView tvListHeader;
    }

    private static final class ChildViewHolder {
        TextView tvListItem;
    }
}
