package com.app.douyu.ui.play;

import android.os.Bundle;

import com.app.douyu.R;
import com.app.douyu.base.BaseActivity;
import com.app.douyu.bean.play.OldLiveVideoInfo;
import com.app.douyu.util.danmu.utils.DanmuProcess;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/27
 */
public class PcLiveVideoActivity extends BaseActivity<PlayPresenter> implements PlayContract.PlayView {
    @Bind(R.id.videoView) JCVideoPlayerStandard mVideoView;
    @Bind(R.id.DanmakuView) DanmakuView mDanmakuView;


    private String mRoom_id;
    private DanmuProcess mDanmuProcess;


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
        mRoom_id = getIntent().getExtras().getString("room_id");
        initDanMu();
    }

    private void initDanMu() {
        mDanmuProcess = new DanmuProcess(this, mDanmakuView, Integer.valueOf(mRoom_id));

        mDanmuProcess.start();
    }

    @Override
    public PlayPresenter getPresenter() {
        return new PlayPresenter();
    }

    @Override
    public void loadData() {
        mPresenter.getRoomInfo(mRoom_id);
    }

    @Override
    public void showPlay(OldLiveVideoInfo liveVideoInfo) {
        mVideoView.setUp(liveVideoInfo.data.live_url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, liveVideoInfo.data.room_name);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mDanmakuView != null && mDanmuProcess != null) {
            mDanmakuView.restart();
            mDanmuProcess.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.pause();
        }
    }

    @Override
    public void onDestroy() {
        JCVideoPlayer.releaseAllVideos();
        mDanmuProcess.finish();
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
        super.onDestroy();

    }
}
