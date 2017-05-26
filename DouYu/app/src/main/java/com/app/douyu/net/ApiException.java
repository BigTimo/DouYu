package com.app.douyu.net;


import com.app.douyu.bean.BaseResult;

/**
 * 服务器连接正常，返回与预期不符数据
 *
 * @author Free
 * @version 1.0
 * @since 2017/2/27
 */
public class ApiException extends Exception {

    private BaseResult mBaseResult;


    public ApiException(BaseResult baseResult) {
        mBaseResult = baseResult;
    }

    @Override
    public String getMessage() {
        return "服务器消息，未定义";
    }

    public BaseResult getBaseResult() {
        return mBaseResult;
    }
}
