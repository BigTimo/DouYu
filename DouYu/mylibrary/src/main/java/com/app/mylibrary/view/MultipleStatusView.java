package com.app.mylibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.mylibrary.R;

import static com.app.mylibrary.view.MultipleStatusView.ViewStatus.CONTENT;
import static com.app.mylibrary.view.MultipleStatusView.ViewStatus.LOADING;


/**
 * 对不同状态切换相应的显示
 */
public class MultipleStatusView extends RelativeLayout {


    public enum ViewStatus {
        CONTENT,
        LOADING,
        EMPTY,
        ERROR,
        NO_NETWORK
    }


    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;
    private View mNoNetworkView;
    private View mContentView;
    private int mEmptyViewResId;
    private int mErrorViewResId;
    private int mLoadingViewResId;
    private int mNoNetworkViewResId;
    private int mContentViewResId;


    private ViewStatus mViewStatus;

    private OnRetryClickListener mOnRetryClickListener;

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
        mEmptyViewResId = a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.multiple_empty);
        mErrorViewResId = a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.multiple_error);
        mLoadingViewResId = a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.multiple_loading);
        mNoNetworkViewResId = a.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.multiple_no_network);
        mContentViewResId = a.getResourceId(R.styleable.MultipleStatusView_contentView, R.layout.multiple_no_network);
        a.recycle();

    }


    public ViewStatus getViewStatus() {
        return mViewStatus;
    }


    public void setContentView(int resId) {
        setMultipleView(ViewStatus.CONTENT, resId);
        showContentView();
        mViewStatus = LOADING;
    }

    public void setMultipleView(ViewStatus status, int resId) {
        setMultipleView(status, null, resId);
    }

    public void setMultipleView(ViewStatus status, View view) {
        setMultipleView(status, view, 0);
    }

    private void setMultipleView(ViewStatus status, View view, int resId) {
        if (view == null && resId == 0) {
            return;
        }
        switch (status) {
            case LOADING:
                mLoadingView = view;
                mLoadingViewResId = resId;
                break;
            case EMPTY:
                mEmptyView = view;
                mEmptyViewResId = resId;
                break;
            case ERROR:
                mErrorView = view;
                mErrorViewResId = resId;
                break;
            case NO_NETWORK:
                mNoNetworkView = view;
                mNoNetworkViewResId = resId;
                break;
            case CONTENT:
                mContentView = view;
                mContentViewResId = resId;
                break;
            default:

                break;
        }


    }


    /**
     * 显示不同状态的View
     */
    public void showMultipleStatus(ViewStatus viewStatus) {
        mViewStatus = viewStatus;
        //用到对应布局才inflate
        switch (viewStatus) {
            case LOADING:
                mLoadingView = inflateMultipleView(mLoadingView, mLoadingViewResId);
                break;
            case EMPTY:
                mEmptyView = inflateMultipleView(mEmptyView, mEmptyViewResId);
                break;
            case ERROR:
                mErrorView = inflateMultipleView(mErrorView, mErrorViewResId);
                break;
            case NO_NETWORK:
                mNoNetworkView = inflateMultipleView(mNoNetworkView, mNoNetworkViewResId);
                break;
            case CONTENT:
                mContentView = inflateMultipleView(mContentView, mContentViewResId);
                break;
            default:
                break;
        }
        if (null != mLoadingView) {
            mLoadingView.setVisibility(viewStatus == ViewStatus.LOADING ? View.VISIBLE : View.GONE);
        }
        if (null != mEmptyView) {
            mEmptyView.setVisibility(viewStatus == ViewStatus.EMPTY ? View.VISIBLE : View.GONE);
        }
        if (null != mErrorView) {
            mErrorView.setVisibility(viewStatus == ViewStatus.ERROR ? View.VISIBLE : View.GONE);
        }
        if (null != mNoNetworkView) {
            mNoNetworkView.setVisibility(viewStatus == ViewStatus.NO_NETWORK ? View.VISIBLE : View.GONE);
        }
        if (null != mContentView) {
            mContentView.setVisibility(viewStatus == CONTENT ? View.VISIBLE : View.GONE);
        }
    }


    /**
     * 重试点击监听
     */
    public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener) {
        mOnRetryClickListener = onRetryClickListener;
    }

    /**
     * 显示内容视图
     */
    public void showContentView() {
        showMultipleStatus(CONTENT);
    }

    /**
     * 加载对应视图布局
     */
    private View inflateMultipleView(View view, int resId) {
        if (view == null) {
            if (resId != 0) {
                view = LayoutInflater.from(getContext()).inflate(resId, null);
            }
        }
        if (view != null && view.getParent() == null) {
            addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        return view;
    }


    public interface OnRetryClickListener {
        void onRetryClick(ViewStatus viewStatus, View view);
    }


    public View getEmptyView() {
        return mEmptyView;
    }

    public View getErrorView() {
        return mErrorView;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public View getNoNetworkView() {
        return mNoNetworkView;
    }

    public View getContentView() {
        return mContentView;
    }
}
