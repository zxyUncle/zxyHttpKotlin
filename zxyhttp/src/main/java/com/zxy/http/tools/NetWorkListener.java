package com.zxy.http.tools;

/**
 * Created by zxy on 2021/2/27 0027 18:20
 * ******************************************
 * * 网路请求的回调
 * ******************************************
 */
public interface NetWorkListener<T> {

    public void onSucc(T bean);//成功  网路请求成功，但是并不是code==200

    public void onNetError(Throwable throwable);//失败  无网路，或者网路请求不通（404,500等）

    public void onFail(T bean);//失败  无网路，或者网路请求不通（404,500等）
}
