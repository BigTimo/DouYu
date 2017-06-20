package com.app.douyu.ui.user;

import android.os.Bundle;

import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.base.BasePresenter;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class UserFragment extends BaseFragment {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

}
