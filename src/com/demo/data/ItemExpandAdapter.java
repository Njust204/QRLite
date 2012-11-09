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

	/*
	第一篇总论
	第一章  概述
	第二章  自行火炮大件分解结合
	  第一节  武器大件分解结合
	  第二节  底盘大件分解结合
	第二篇武器部分
	第三章炮身与炮闩
         第一节炮身
         第二节炮闩
         （一）闭锁装置
         （二）半自动装置
	  （三）击发装置
	  （四）保险装置
	  （五）挡弹装置
	 第四章  半自动装坟机构
	第五章  摇架和反后坐装
	第六章  托架和平衡机
	第七章  方向装置和高低装置
	第八章  瞄准装置
	第九章炮塔
	第十章炮弹构造
	第十一章附件
	第三篇底盘部分
	第四篇电气部分
	第五篇自行火炮的勤务



*/
	// 组名称  
    private String[] group = new String[] { "第一篇总论","  第一章  概述","  第二章  自行火炮大件分解结合", "   第一节  武器大件分解结合","   第二节  底盘大件分解结合",
    		"第二篇武器部分","  第三章炮身与炮闩","   第一节炮身","   第二节炮闩","    （一）闭锁装置","    （二）半自动装置","    （三）击发装置","    （四）保险装置","    （五）挡弹装置",
    		"  第四章  半自动装坟机构","  第五章  摇架和反后坐装","  第六章  托架和平衡机","  第七章  方向装置和高低装置","  第八章  瞄准装置","  第九章炮塔","  第十章炮弹构造","  第十一章附件",
    		"第三篇底盘部分",
    		"第四篇电气部分",
    		"第五篇自行火炮的勤务"};  
    
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
		case 9:
			result = s1.get(childPosition);
			break;
		case 10:
			result = s2.get(childPosition);
			break;
		case 11:
			result = s3.get(childPosition);
			break;
		case 12:
			result = s4.get(childPosition);
			break;
		case 13:
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
		case 9:
			result = s1.size();
			break;
		case 10:
			result = s2.size();
			break;
		case 11:
			result = s3.size();
			break;
		case 12:
			result = s4.size();
			break;
		case 13:
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
