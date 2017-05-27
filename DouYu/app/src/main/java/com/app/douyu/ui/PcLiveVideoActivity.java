package com.app.douyu.ui;

import android.os.Bundle;

import com.app.douyu.R;
import com.app.douyu.base.BaseActivity;
import com.app.douyu.base.BasePresenter;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/27
 */
public class PcLiveVideoActivity extends BaseActivity {
    @Bind(R.id.videoView) JCVideoPlayerStandard mVideoView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pclive;
    }

    @Override
    protected boolean setUseLayoutIdCustom() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {


    }


    @Override
    public void loadData() {
                String url = "http://14.215.100.54/hdl3.douyucdn.cn/live/35651rTiAVLKgGOK.flv?wsAuth=7f53f503e62690741fe5d8bf19c81343&token=cpn-pcclient-395184-35651-f4e47d005d2ba9621445421ac9274a87&logo=0&expire=0&wshc_tag=0&wsts_tag=592933aa&wsid_tag=77824643&wsiphost=ipdbm";
//        String url = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
        mVideoView.setUp(url,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
