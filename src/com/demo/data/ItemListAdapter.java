package com.demo.data;

import com.demo.qrlite.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends BaseAdapter implements Filterable {
	public final class ItemsViewHolder {
		public TextView mItemNameTV;
	}

	private ArrayFilter filter;
	private Context mContext;
	private LayoutInflater mInflater;

	private List<String> mItemNames;
	private List<String> mItemNamesBackUp;

	public ItemListAdapter(Context ctx) {
		mContext = ctx;
		mInflater = LayoutInflater.from(ctx);
	}
	
	public void setItemList(List<String> itemnames) {
		mItemNames = itemnames;
		mItemNamesBackUp = itemnames;
	}

	public int getCount() {
		if (null == mItemNames) {
			return 0;
		}
		return mItemNames.size();
	}

	public Object getItem(int position) {
		if (null == mItemNames) {
			return null;
		}
		if (position < 0 || position >= mItemNames.size()) {
			return null;
		}
		return mItemNames.get(position);
	}

	public long getItemId(int position) {
		if (null == mItemNames) {
			return 0;
		}
		if (position < 0 || position >= mItemNames.size()) {
			return 0;
		}
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == mItemNames) {
			return convertView;
		}
		if (position < 0 || position >= mItemNames.size()) {
			return convertView;
		}

		ItemsViewHolder viewHolder =  new ItemsViewHolder();;
		String itemName = mItemNames.get(position);

		if (null == convertView) {
			convertView = mInflater.inflate(R.layout.itemlist_picker, null);
			viewHolder.mItemNameTV = (TextView) convertView.findViewById(R.id.TV_ItemList_Name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ItemsViewHolder) convertView.getTag();
		}

		viewHolder.mItemNameTV.setText(itemName);

		return convertView;
	}

	public Filter getFilter() {
		if (filter == null) {
			filter = new ArrayFilter();
		}
		return filter;
	}

	private class ArrayFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			FilterResults results = new FilterResults();
			if (prefix == null || prefix.length() == 0) {
				mItemNames = mItemNamesBackUp;
				ArrayList<String> l = new ArrayList<String>(mItemNames);
				results.values = l;
				results.count = l.size();
			} else {
				mItemNames = mItemNamesBackUp;
				String prefixString = prefix.toString().toLowerCase();
				final ArrayList<String> values = new ArrayList<String>(mItemNames);
				final int count = values.size();
				final ArrayList<String> newValues = new ArrayList<String>(count);
				for (int i = 0; i < count; i++) {
					final String value = values.get(i);
					final String valueText = value;
					if (valueText.indexOf(prefixString) != -1) {
						newValues.add(value);
					}
				}
				results.values = newValues;
				results.count = newValues.size();
			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mItemNames = (ArrayList<String>) results.values;
			if (results.count > 0) {
				ItemListAdapter.this.notifyDataSetChanged();
			} else {
				ItemListAdapter.this.notifyDataSetInvalidated();
			}
		}
	}

}
