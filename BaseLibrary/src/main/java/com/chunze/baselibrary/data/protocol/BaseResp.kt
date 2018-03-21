package com.chunze.baselibrary.data.protocol

/**
 * Created by Administrator on 2018/2/26.
 */
/*
    响应对象
    @status:响应状态码
    @message:响应文字消息
    @data:具体响应业务对象
 */
data class BaseResp<out T>(val status: Int, val message: String, val data: T)