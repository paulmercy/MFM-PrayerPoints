package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    MediaPlayer song;
    private Activity context;
    private Map<String, List<String>> laptopCollections;
    private List<String> laptops;

    public ExpandableListAdapter(Activity context, List<String> laptops, Map<String, List<String>> laptopCollections) {
        this.context = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
    }

    public ExpandableListAdapter(OnClickListener onClickListener, List<String> list, Map<String, List<String>> map) {
    }

    public Object getChild(int groupPosition, int childPosition) {
        return ((List) this.laptopCollections.get(this.laptops.get(groupPosition))).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String laptop = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = this.context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }
        ((TextView) convertView.findViewById(R.id.laptop)).setText(laptop);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return this.laptopCollections.get(this.laptops.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return this.laptops.get(groupPosition);
    }

    public int getGroupCount() {
        return this.laptops.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @SuppressLint("WrongConstant")
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.group_item, null);
        }
        TextView item = convertView.findViewById(R.id.laptop);
        item.setTypeface(null, 1);
        item.setText(laptopName.trim());
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
