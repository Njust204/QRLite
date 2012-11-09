package com.demo.qrlite;

import java.util.List;

import com.demo.data.DataHolder;
import com.demo.data.ItemExpandAdapter;
import com.demo.data.ItemListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;

public class AllActivity extends Activity {

	private static final int ToSearchAct = 0;
	private static final int ToDeleteAct = 1;
	private static final int ToEditAct = 2;
//	private static final int ToNoAct = 3;

	private Button clearFilterBtn;
	private EditText searchFilterET;
//	private ListView allItemNamesLV;
//	private ItemListAdapter itemListAdapter;
	private ExpandableListView allItemsExpandableListView;
	private ItemExpandAdapter allItemsExpandAdapter;
//	private List<String> mItems = null;
	
	private List<String> s1 = null;
    private List<String> s2 = null;
    private List<String> s3 = null;
    private List<String> s4 = null;
    private List<String> s5 = null;
    
    private String[] group = new String[] { "闭锁装置", "半自动装置", "击发装置", "保险装置", "挡弹装置" };  
	private String Opr[] = new String[] { "查看", "删除", "编辑" };
	private String clickItemName;
	private Intent intent = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all);

		clearFilterBtn = (Button) findViewById(R.id.All_Btn_Clear);
		searchFilterET = (EditText) findViewById(R.id.All_ET_Filter);
		clearFilterBtn.setVisibility(View.GONE);
		searchFilterET.setVisibility(View.GONE);
		
		allItemsExpandableListView = (ExpandableListView)findViewById(R.id.All_EPList_Items);
		
//		super.registerForContextMenu(allItemsExpandableListView);
		
		s1 = DataHolder.dtManager.queryByGroup(group[0]);
	    s2 = DataHolder.dtManager.queryByGroup(group[1]);
	    s3 = DataHolder.dtManager.queryByGroup(group[2]);
	    s4 = DataHolder.dtManager.queryByGroup(group[3]);
	    s5 = DataHolder.dtManager.queryByGroup(group[4]);
	    
	    allItemsExpandAdapter = new ItemExpandAdapter(this,s1,s2,s3,s4,s5);
		allItemsExpandableListView.setAdapter(allItemsExpandAdapter);
//		allItemNamesLV = (ListView) findViewById(R.id.All_List_ItemNames);

//		allItemNamesLV.setOnItemClickListener(allListListener);
//		searchFilterET.addTextChangedListener(filterTextWatcher);
//		clearFilterBtn.setOnClickListener(clearClickListener);

//		mItems = DataHolder.dtManager.queryByName();
//
//		itemListAdapter = new ItemListAdapter(this);
//		itemListAdapter.setItemList(this.mItems);
//		allItemNamesLV.setAdapter(itemListAdapter);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
//		mItems = DataHolder.dtManager.queryByName();
//		itemListAdapter = new ItemListAdapter(this);
//		itemListAdapter.setItemList(this.mItems);
//		allItemNamesLV.setAdapter(itemListAdapter);
//		searchFilterET.setText("");
		s1 = DataHolder.dtManager.queryByGroup(group[0]);
	    s2 = DataHolder.dtManager.queryByGroup(group[1]);
	    s3 = DataHolder.dtManager.queryByGroup(group[2]);
	    s4 = DataHolder.dtManager.queryByGroup(group[3]);
	    s5 = DataHolder.dtManager.queryByGroup(group[4]);
	    
	    allItemsExpandAdapter = new ItemExpandAdapter(this,s1,s2,s3,s4,s5);
		allItemsExpandableListView.setAdapter(allItemsExpandAdapter);
		allItemsExpandableListView.setOnChildClickListener(new OnChileClickListenerImpl());
		super.onResume();
	}
	
	private class OnChileClickListenerImpl implements OnChildClickListener{

		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			//子项被选中
//			Toast.makeText(AllActivity.this, "组"+groupPosition+" 子"+childPosition, Toast.LENGTH_SHORT).show();
			switch (groupPosition) {
			case 9:
				clickItemName = s1.get(childPosition);
				break;
			case 10:
				clickItemName = s2.get(childPosition);
				break;
			case 11:
				clickItemName = s3.get(childPosition);
				break;
			case 12:
				clickItemName = s4.get(childPosition);
				break;
			case 13:
				clickItemName = s5.get(childPosition);
				break;
			}
			Dialog dialog = new AlertDialog.Builder(AllActivity.this).setTitle("你需要的操作")
					.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					}).setItems(AllActivity.this.Opr, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case ToSearchAct:
								DataHolder.all2SearchName = clickItemName;
								intent = new Intent(AllActivity.this, SearchActivity.class);
								AllActivity.this.startActivity(intent);
								break;

							case ToDeleteAct:
								DataHolder.all2DeleteName = clickItemName;
								intent = new Intent(AllActivity.this, DeleteActivity.class);
								AllActivity.this.startActivity(intent);
								break;

							case ToEditAct:
								DataHolder.all2EditName = clickItemName;
								intent = new Intent(AllActivity.this, EditActivity.class);
								AllActivity.this.startActivity(intent);
								break;
							default:
								break;
							}
						}
					}).create();
			dialog.show();
			return false;
		}
		
	}
	
	private class OnGroupClickListenerImpl implements OnGroupClickListener{

		public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
			//分组被选中
			return false;
		}
		
	}
	
	private class OnGroupCollapseListenerImpl implements OnGroupCollapseListener{

		public void onGroupCollapse(int groupPosition) {
			//关闭分组
		}
		
	}
	
	private class OnGroupExpandListenerImpl implements OnGroupExpandListener{

		public void onGroupExpand(int groupPosition) {
			//打开分组
		}
		
	}
	
	
/*
	private TextWatcher filterTextWatcher = new TextWatcher() {

		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before, int count) {
			itemListAdapter.getFilter().filter(s);
		}

		public void afterTextChanged(Editable s) {
			if (clearFilterBtn != null) {
				if (s.length() == 0) {
					itemListAdapter.setItemList(AllActivity.this.mItems);
					allItemNamesLV.setAdapter(itemListAdapter);
					clearFilterBtn.setVisibility(View.GONE);
				} else {
					clearFilterBtn.setVisibility(View.VISIBLE);
				}
			}
		}
	};
	private OnClickListener clearClickListener = new OnClickListener() {

		public void onClick(View v) {
			searchFilterET.setText("");
		}
	};
	private OnItemClickListener allListListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			clickItemName = (String) AllActivity.this.itemListAdapter.getItem(position);
			Dialog dialog = new AlertDialog.Builder(AllActivity.this).setTitle("你需要的操作")
					.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					}).setItems(AllActivity.this.Opr, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case ToSearchAct:
								DataHolder.all2SearchName = clickItemName;
								intent = new Intent(AllActivity.this, SearchActivity.class);
								AllActivity.this.startActivity(intent);
								break;

							case ToDeleteAct:
								DataHolder.all2DeleteName = clickItemName;
								intent = new Intent(AllActivity.this, DeleteActivity.class);
								AllActivity.this.startActivity(intent);
								break;

							case ToEditAct:
								DataHolder.all2EditName = clickItemName;
								intent = new Intent(AllActivity.this, EditActivity.class);
								AllActivity.this.startActivity(intent);
								break;

							default:
								break;
							}
						}
					}).create();
			dialog.show();
		}
	};
*/
	
	
}
