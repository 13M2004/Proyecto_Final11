package com.umg.proyecto_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listaCategorias;
    private HashMap<String, List<String>> listaItems;

    public ExpandableListAdapter(Context context, List<String> listaCategorias, HashMap<String, List<String>> listaItems) {
        this.context = context;
        this.listaCategorias = listaCategorias;
        this.listaItems = listaItems;
    }

    @Override
    public int getGroupCount() {
        return listaCategorias.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listaItems.get(listaCategorias.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listaCategorias.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listaItems.get(listaCategorias.get(groupPosition)).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String tituloGrupo = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(tituloGrupo);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String tituloItem = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_2, null);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(tituloItem);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
