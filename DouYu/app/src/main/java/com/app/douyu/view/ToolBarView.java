package com.app.douyu.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.douyu.R;


/**
 * @author Free
 * @version 1.0
 * @since 2016/12/23
 */
public class ToolBarView extends LinearLayout {

    private TextView mTvTitle;
    private TextView mMTvBack;
    private TextView mTvRight;

    public static final int TEXT_LEFT = 0;
    public static final int TEXT_TITLE = 1;
    public static final int TEXT_RIGHT = 2;


    public static final int MODEL_TITLE_GRAVITY_CENTER = 10;
    public static final int MODEL_TITLE_GRAVITY_LEFT = 11;


    public ToolBarView(Context context) {
        super(context);
        initView(context, null);
    }


    public ToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ToolBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_toolbar, this, true);
//        View inflate = inflate(context, R.layout.view_toolbar, this);
        mMTvBack = (TextView) inflate.findViewById(R.id.tv_bar_back);
        mTvTitle = (TextView) inflate.findViewById(R.id.tv_bar_title);
        mTvRight = (TextView) inflate.findViewById(R.id.tv_bar_right);

        setBackgroundColor(Color.WHITE);
        setOrientation(LinearLayout.HORIZONTAL);
        showStyle(context, attrs);

    }

    private void showStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.ToolBarView);
            String leftText = style.getString(R.styleable.ToolBarView_textLeft);
            String titleText = style.getString(R.styleable.ToolBarView_textTitle);
            String rightText = style.getString(R.styleable.ToolBarView_textRight);


            int leftColor = style.getColor(R.styleable.ToolBarView_textColorLeft, Color.parseColor("#333333"));
            int titleColor = style.getColor(R.styleable.ToolBarView_textColorTitle, Color.parseColor("#333333"));
            int rightColor = style.getColor(R.styleable.ToolBarView_textColorRight, Color.parseColor("#333333"));

            float leftTextSize = style.getDimension(R.styleable.ToolBarView_textSizeLeft, 16);
            float titleTextSize = style.getDimension(R.styleable.ToolBarView_textSizeTitle, 16);
            float rightTextSize = style.getDimension(R.styleable.ToolBarView_textSizeRight, 16);

            setStyle(mMTvBack, leftText, leftColor, leftTextSize);
            setStyle(mTvTitle, titleText, titleColor, titleTextSize);
            setStyle(mTvRight, rightText, rightColor, rightTextSize);

            style.recycle();
        }
    }

    private void setStyle(TextView textView, String text, int color, float size) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
        textView.setTextColor(color);
        textView.setTextSize(size);
    }


    /**
     * 返回按钮,默认图片为R.mipmap.back
     *
     * @param activity 当前activity
     */
    public void setBackPressed(final Activity activity) {
        setBackPressed(activity, R.drawable.back);
    }

    /**
     * 返回按钮
     *
     * @param activity 当前activity
     * @param resId    返回按钮图片
     */
    public void setBackPressed(final Activity activity, int resId) {
        setBackImage(resId);
        mMTvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }


    /**
     * 设置返回图片
     *
     * @param resId
     */
    public void setBackImage(int resId) {
        setDrawable(TEXT_LEFT, resId);
    }


    /**
     * 设置中间标题,默认居中
     *
     * @param text
     */
    public void setTitleText(String text) {
        setTitleText(text, MODEL_TITLE_GRAVITY_CENTER);
    }

    /**
     * 设置中间标题
     *
     * @param text
     */
    public void setTitleText(String text, int gravityType) {
        setText(TEXT_TITLE, text);
        setTitleGravity(gravityType);
    }

    /**
     * 设置右边标题
     *
     * @param text
     */
    public void setRightText(String text) {
        setText(TEXT_RIGHT, text);
    }

    /**
     * 设置title显示格式,居中(默认)/左边对齐
     *
     * @param gravityType
     */
    public void setTitleGravity(int gravityType) {
        switch (gravityType) {
            case MODEL_TITLE_GRAVITY_CENTER:
                mTvTitle.setGravity(Gravity.CENTER);
                break;
            case MODEL_TITLE_GRAVITY_LEFT:
                mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
                break;
        }
    }

    /**
     * 设置文字
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     * @param text
     */
    public void setText(int textType, String text) {
        switch (textType) {
            case TEXT_LEFT:
                mMTvBack.setText(text);
                break;
            case TEXT_TITLE:
                mTvTitle.setText(text);
                break;
            case TEXT_RIGHT:
                mTvRight.setText(text);
                break;
        }

    }

    /**
     * 设置字体大小
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     * @param size
     */
    public void setTextSize(int textType, float size) {
        switch (textType) {
            case TEXT_LEFT:
                mMTvBack.setTextSize(size);
                break;
            case TEXT_TITLE:
                mTvTitle.setTextSize(size);
                break;
            case TEXT_RIGHT:
                mTvRight.setTextSize(size);
                break;
        }
    }

    /**
     * 设置字体颜色
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     * @param color    颜色id
     */
    public void setTextColor(int textType, int color) {
        switch (textType) {
            case TEXT_LEFT:
                mMTvBack.setTextColor(color);
                break;
            case TEXT_TITLE:
                mTvTitle.setTextColor(color);
                break;
            case TEXT_RIGHT:
                mTvRight.setTextColor(color);
                break;
        }
    }


    /**
     * 设置标题图片
     * 左边标题默认在左边，中间标题默认在左边，右边标题默认在右边
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     * @param resId    图片资源id
     */
    public void setDrawable(int textType, int resId) {
        switch (textType) {
//            case TEXT_LEFT:
//                TextViewUtil.setDrawable(mMTvBack, resId, TextViewUtil.ORIENTATION_LEFT);
//                break;
//            case TEXT_TITLE:
//                TextViewUtil.setDrawable(mTvTitle, resId, TextViewUtil.ORIENTATION_LEFT);
//                break;
//            case TEXT_RIGHT:
//                TextViewUtil.setDrawable(mTvRight, resId, TextViewUtil.ORIENTATION_RIGHT);
//                break;
        }
    }


    /**
     * 左title点击事件
     *
     * @param listener
     */
    @SuppressWarnings("unused")
    public void setOnLeftClickListener(final OnBarLeftClickListener listener) {
        mMTvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick(v);
            }
        });


    }

    /**
     * 右title点击事件
     *
     * @param listener
     */
    public void setOnRightClickListener(final OnBarRightClickListener listener) {
        mTvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick(v);
            }
        });


    }

    public interface OnBarLeftClickListener {
        void onLeftClick(View v);
    }

    public interface OnBarRightClickListener {
        void onRightClick(View v);
    }

}
