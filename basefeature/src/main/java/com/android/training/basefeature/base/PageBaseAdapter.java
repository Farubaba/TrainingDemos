package com.android.training.basefeature.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public abstract class PageBaseAdapter<T> extends BaseAdapter implements PageLoadListener {
	protected Context mContext;
	protected List<T> mList;
	protected int	  	mLayoutResourceId;
//	protected View		mFooter;
	protected ListView mListView;
	
	//设置Context和资源布局Layout编号
	public PageBaseAdapter(Context ctx, final int layoutResId){
		mContext = ctx;
		mLayoutResourceId = layoutResId;
		mList = new LinkedList<T>();
		
//		mFooter = LayoutInflater.from(mContext).inflate(R.layout.wt_at_listitem_loading, null);
//		mFooter.setVisibility(View.GONE);
		
	}
	
	public void setListView(ListView listview){
		mListView = listview;
//		mListView.addFooterView(mFooter);
	}
	
	public void setList(List<T> list){
		if(mList.size()==0){
			mList.addAll(list);
		}else{
			LinkedList<T> temp = new LinkedList<T>();
			
			for(T i : list){
				boolean exist = false;
				
				for( T l : mList){
					if(i.equals(l)){
						exist = true;
						break;
					}
				}
				if(!exist){
					temp.add(i);
				}
			}//end for
			
			//把增量数组加到所有列表中
			mList.addAll(temp);
		}
		stopLoading();
	}
	
	public List<T> getList(){
		return mList;
	}
	
	@Override
	public int getCount() {
		return mList==null ? 0 : mList.size();
	}

	@Override
	public T getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(mLayoutResourceId, null);
		}
		
		renderViewItem(position, convertView);
		
		return convertView;
	}

	public void startLoading(){
//		if(mListView!=null&&mFooter!=null&&mListView.getFooterViewsCount()==0){
			
//			mFooter.setVisibility(View.VISIBLE);
//		}
	}
	
	public void stopLoading(){
//		if(mListView!=null&&mFooter!=null&&mListView.getFooterViewsCount()>0){
//			mListView.removeFooterView(mFooter);
//			mFooter.setVisibility(View.GONE);
//		}
	}
	
	protected abstract void renderViewItem(int position, View view);
	public abstract void reset();

}
