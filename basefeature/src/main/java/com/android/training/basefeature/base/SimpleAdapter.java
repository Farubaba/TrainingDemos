package com.android.training.basefeature.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一个简单的Adapter，用来构建拥有一个Item或者几个Item的布局时使用。<br>
 * {@link #setData(Object)}用于构建唯一的一个Item<br>
 * {@link #setData(List)} 用于动态构建几个Item,当然，List.size() == 1时和
 * {@link #setData(Object)}效果一样。<br>
 * <p>
 * 配合{@link FengjrTemplateView}使用，使View的Item实现通过
 * {@link #renderItemView(int, View)}来实现分离。Item样式及数据绑定都交给用户自己处理。
 * 
 * <b>备注：该Adapter只实用于拥有少量数目Item的View，Item数量过多时，不推荐使用.</b>
 * <br>
 * @param <T>
 */
public abstract class SimpleAdapter<T> extends BaseAdapter {
	public static final int ONE = 1;
	public Context mContext;
	public AtomicBoolean isSingleItemMode = new AtomicBoolean(true);
	public List<T> mData = new ArrayList<T>();
	public T mSingleItemData;
	public int mItemLayoutId;

	public SimpleAdapter(Context context, int itemLayoutId) {
		this.mContext = context;
		this.mItemLayoutId = itemLayoutId;
	}

	public void setData(List<T> data) {
		isSingleItemMode.set(false);
		if (data == null || data.isEmpty()) {
			mData.clear();
		} else {
			mData = data;
		}
		notifyDataSetChanged();
	}

	public void setData(T data) {
		this.mSingleItemData = data;
		isSingleItemMode.set(true);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (isSingleItemMode.get()) {
			return ONE;
		} else {
			return mData == null ? 0 : mData.size();
		}
	}

	@Override
	public T getItem(int position) {
		if (isSingleItemMode.get()) {
			return mSingleItemData;
		} else {
			return mData == null ? null : mData.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext)
				.inflate(mItemLayoutId, null);
		convertView.setId(position);
		renderItemView(position, convertView);
		return convertView;
	}

	protected abstract void renderItemView(int position, View convertView);
}
