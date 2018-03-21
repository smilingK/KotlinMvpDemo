package com.chunze.baselibrary.rx

import com.chunze.baselibrary.common.ResultCode.Companion.SUCCESS
import com.chunze.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by Administrator on 2018/2/27.
 */
class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }

}