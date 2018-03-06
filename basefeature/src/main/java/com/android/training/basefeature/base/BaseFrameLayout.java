package com.android.training.basefeature.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * 抽象FrameLayout，通过泛型来绑定数据。
 * 
 * @param <T>
 *            数据对象，子类决定需要绑定什么类型的数据
 */
public abstract class BaseFrameLayout<T> extends FrameLayout {
	public static final int DEF_STYLE = 0;
	protected OnClickListener mOnClickListener;
	protected OnLongClickListener mOnLongClickListener;
	public T mData;
	protected Context mContext;
	protected AttributeSet mAttrs;
	protected int mDefStyle;
	protected LayoutInflater mLayoutInflater;

	public BaseFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	public BaseFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, DEF_STYLE);
	}

	public BaseFrameLayout(Context context) {
		super(context);
		init(context, null, DEF_STYLE);
	}

	public BaseFrameLayout(Context context, T data,
                           OnClickListener clickListener) {
		super(context);
		this.mData = data;
		this.mOnClickListener = clickListener;
		init(context, null, DEF_STYLE);
	}

	/**
	 * 当前View的初始化方法，首先被调用
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	protected void init(Context context, AttributeSet attrs, int defStyle) {
		this.mContext = context;
		this.mAttrs = attrs;
		this.mDefStyle = defStyle;
		buildViews();
		checkToSetListeners();
		checkToRefreshData();
	}

	private void checkToSetListeners() {
		if (mOnClickListener != null) {
			onClickListenerChange(mOnClickListener);
		}
	}

	private void checkToRefreshData() {
		if (mData != null) {
			onDataChange(mData);
		}
	}

	/**
	 * 这里负责创建View，动态创建或者使用LayoutInflater来创建View。<br>
	 * 事件绑定推荐在{@link #onLongClickListenerChange(OnLongClickListener)}和
	 * {@link #onClickListenerChange(OnClickListener)}等类似回调方法中设置。
	 */
	protected abstract void buildViews();

	/**
	 * 设置新的点击事件对象，并触发回调重设当前View或者Child View中设置的onClick事件
	 */
	public void setClickListener(OnClickListener clickListener) {
		this.mOnClickListener = clickListener;
		onClickListenerChange(mOnClickListener);
	}

	/**
	 * 设置新的长按事件对象，并触发回调重设当前View或者Child View中设置的LongClick事件
	 */
	public void setOnLongClickListener(OnLongClickListener longClickListener) {
		this.mOnLongClickListener = longClickListener;
		onLongClickListenerChange(mOnLongClickListener);
	}

	/**
	 * 覆盖此方法，为需要设置LongClick事件监听的View设置监听器
	 * 
	 * @param longClickListener
	 */
	protected void onLongClickListenerChange(
			OnLongClickListener longClickListener) {
	}

	/**
	 * 为当前View设置数据模板对象，并触发{@link #onDataChange(Object)}
	 * 回调，通常我们需要在这个回调方法中完成View的刷新。
	 * 
	 * @param data
	 */
	public void setDate(T data) {
		this.mData = data;
		onDataChange(data);
	}

	/**
	 * 获取当前View所绑定的数据。
	 * 
	 * @return
	 */
	public T getData() {
		return this.mData;
	}

	/**
	 * 覆盖此方法，为需要设置Click事件监听的View设置监听器
	 * 
	 * @param onClickListener
	 */
	protected abstract void onClickListenerChange(
			OnClickListener onClickListener);

	/**
	 * 当数据更新时，回调该方法，通常我们需要在这个回调方法中完成View的刷新
	 * 
	 * @param data
	 */
	protected abstract void onDataChange(T data);
}
