package com.demo.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ItemExpandAdapter extends BaseExpandableListAdapter{

	// 组名称  
    private String[] group = new String[] { "闭锁装置", "半自动装置", "击发装置", "保险装置", "挡弹装置" };  
    
    private List<String> s1 = null;
    private List<String> s2 = null;
    private List<String> s3 = null;
    private List<String> s4 = null;
    private List<String> s5 = null;
    
    private Context context = null;  
    
 // 构造函数
    public ItemExpandAdapter(Context context,List<String> l1,List<String> l2,List<String> l3,List<String> l4,List<String> l5){
    	this.context = context; 
    	this.s1 = l1;
    	this.s2 = l2;
    	this.s3 = l3;
    	this.s4 = l4;
    	this.s5 = l5;
    }
    
	public Object getChild(int groupPosition, int childPosition) {
		
		String result = null;
		switch (groupPosition) {
		case 0:
			result = s1.get(childPosition);
			break;
		case 1:
			result = s2.get(childPosition);
			break;
		case 2:
			result = s3.get(childPosition);
			break;
		case 3:
			result = s4.get(childPosition);
			break;
		case 4:
			result = s5.get(childPosition);
			break;
		}
		return result; 
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	
	private TextView buildTextView() {  
        //LayoutParams AbsListView扩展提供一个位置来保存视图类型。  
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(  
                ViewGroup.LayoutParams.FILL_PARENT, 70);  
        TextView textView = new TextView(this.context);  
        textView.setLayoutParams(params);  
        //大小  
        textView.setTextSize(22.0f);  
        textView.setGravity(Gravity.LEFT+3);  
        textView.setPadding(40, 8, 3, 3);  
        return textView;  
    }  
	
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		TextView textView = buildTextView();
		textView.setText(getChild(groupPosition, childPosition).toString());
		return textView;
	}

	public int getChildrenCount(int groupPosition) {
		int result = 0 ;
		switch (groupPosition) {
		case 0:
			result = s1.size();
			break;
		case 1:
			result = s2.size();
			break;
		case 2:
			result = s3.size();
			break;
		case 3:
			result = s4.size();
			break;
		case 4:
			result = s5.size();
			break;
		}
		return result;
	}

	public Object getGroup(int groupPosition) {
		 
		return this.group[groupPosition];
	}

	public int getGroupCount() {
		return this.group.length;
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		TextView textView = buildTextView();
		textView.setText(getGroup(groupPosition).toString());
		return textView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
