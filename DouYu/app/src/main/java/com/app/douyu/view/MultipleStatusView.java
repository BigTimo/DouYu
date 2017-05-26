package com.app.douyu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.douyu.R;

import static com.app.douyu.view.MultipleStatusView.ViewStatus.CONTENT;


/**
 * 对不同状态切换相应的显示
 */
public class MultipleStatusView extends RelativeLayout {

    private LayoutParams mParams;

    public enum ViewStatus {
        CONTENT,
        LOADING,
        EMPTY,
        ERROR,
        NO_NETWORK
    }


    public static final int DEFAULT_RES = -1;


    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;
    private View mNoNetworkView;
    private View mContentView;


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
        //
        //        final TypedArray a =
        //            context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
        //
        //        mEmptyViewResId =
        //            a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view);
        //        mErrorViewResId =
        //            a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view);
        //        mLoadingViewResId =
        //            a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view);
        //        mNoNetworkViewResId = a.getResourceId(R.styleable.MultipleStatusView_noNetworkView,
        //            R.layout.no_network_view);
        //        mContentViewResId = a.getResourceId(R.styleable.MultipleStatusView_contentView,
        //            NULL_RESOURCE_ID);
        //        a.recycle();
    }


    /**
     * 获取当前状态
     */
    public ViewStatus getViewStatus() {
        return mViewStatus;
    }

    public void setViewStatus(ViewStatus viewStatus) {
        mViewStatus = viewStatus;
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener) {
        this.mOnRetryClickListener = onRetryClickListener;
    }


    public void setContentViewResId(int contentViewResId) {
        mContentView = addMultipleView(mContentView, CONTENT, contentViewResId);
        mContentView.setVisibility(GONE);
    }

    /**
     * 显示内容视图
     */
    public void showContentView() {
        showViewByStatus(CONTENT);
    }


    public void showMultipleView(ViewStatus status, int viewResId) {

        switch (status) {
            case EMPTY:
                mEmptyView = addMultipleView(mEmptyView, status, viewResId == DEFAULT_RES ? R.layout.multiple_empty : viewResId);
                break;
            case ERROR:
                mErrorView = addMultipleView(mErrorView, status, viewResId == DEFAULT_RES ? R.layout.multiple_error : viewResId);
                break;
            case LOADING:
                mLoadingView = addMultipleView(mLoadingView, status, viewResId == DEFAULT_RES ? R.layout.multiple_loading : viewResId);
                break;
            case NO_NETWORK:
                mNoNetworkView = addMultipleView(mNoNetworkView, status, viewResId == DEFAULT_RES ? R.layout.multiple_no_network : viewResId);
                break;
            default:

                break;
        }

    }


    private View addMultipleView(View statusView, final ViewStatus status, int resId) {
        if (statusView == null) {
            statusView = LayoutInflater.from(getContext()).inflate(resId, null);
            addView(statusView, -1, -1);
        }
        final View finalStatusView = statusView;
        View retryView = statusView.findViewById(R.id.retry_view);
        if (retryView != null) {
            retryView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnRetryClickListener != null) {
                        mOnRetryClickListener.onRetryClick(status, finalStatusView);
                    }
                }
            });
        }
        if (status != CONTENT) {
            showViewByStatus(status);
        }
        return statusView;
    }

    private void showViewByStatus(ViewStatus viewStatus) {
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
        mViewStatus = viewStatus;
    }

    public interface OnRetryClickListener {
        void onRetryClick(ViewStatus viewStatus, View view);
    }

}
