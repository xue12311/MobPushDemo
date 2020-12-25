package com.jl.mob_push

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils

class MobPushLifecycleObserver : LifecycleObserver {
    private val mMobPushReceiver = MobPushUtils.getMobPushReceiver(
        //接收到自定义消息（透传消息）
        { context, mCustomMessage ->
            ToastUtils.showShort("收到自定义消息（透传消息）: ${mCustomMessage.content}")
        },
        //接收到通知消息
        { context, mNotifyMessage ->
            ToastUtils.showShort("收到推送通知 : ${mNotifyMessage.content}")
        },
        //通知被点击事件
        { context, mNotifyMessage ->
            ToastUtils.showShort("推送通知被点击 : ${mNotifyMessage.content}")
        },
        //标签操作回调
        // tags : 标签(多个)
        // operation  0:获取标签 1 : 设置标签 2 : 删除标签
        // errorCode == 0 , 操作成功 , errorCode != 0 ,操作失败
        { context, tags, operation, errorCode ->
            // 0:获取标签 1 : 设置标签 2 : 删除标签
            var str_tags = ""
            tags.forEach {
                if (StringUtils.isTrimEmpty(str_tags)) {
                    str_tags = it
                } else {
                    str_tags = "${str_tags},${it}"
                }
            }
            when (operation) {
                0 -> {
                    val str = StringBuilder("获取标签")
                    if (errorCode == 0) {
                        str.append("成功")
                    } else {
                        str.append("失败")
                    }
                    str.append(" , 标签 : ${str_tags}")
                    ToastUtils.showShort(str.toString())
                }
                1 -> {
                    val str = StringBuilder("设置标签")
                    if (errorCode == 0) {
                        str.append("成功")
                    } else {
                        str.append("失败")
                    }
                    str.append(" , 标签 : ${str_tags}")
                    ToastUtils.showShort(str.toString())
                }
                2 -> {
                    val str = StringBuilder("删除标签")
                    if (errorCode == 0) {
                        str.append("成功")
                    } else {
                        str.append("失败")
                    }
                    str.append(" , 标签 : ${str_tags}")
                    ToastUtils.showShort(str.toString())
                }
            }
        },
        //别名操作回调
        // alias 别名(一个)
        // operation  0:获取别名 1 : 设置别名 2 : 删除别名
        // errorCode == 0 , 操作成功 , errorCode != 0 ,操作失败
        { context, alias, operation, errorCode ->
            // 0:获取别名 1 : 设置别名 2 : 删除别名
            when (operation) {
                0 -> {
                    val str = StringBuilder("获取别名")
                    if (errorCode == 0) {
                        str.append("成功")
                    } else {
                        str.append("失败")
                    }
                    str.append(" , 别名 : ${alias}")
                    ToastUtils.showShort(str.toString())
                }
                1 -> {
                    val str = StringBuilder("设置别名")
                    if (errorCode == 0) {
                        str.append("成功")
                    } else {
                        str.append("失败")
                    }
                    str.append(" , 别名 : ${alias}")
                    ToastUtils.showShort(str.toString())
                }
                2 -> {
                    val str = StringBuilder("删除别名")
                    if (errorCode == 0) {
                        str.append("成功")
                    } else {
                        str.append("失败")
                    }
                    str.append(" , 别名 : ${alias}")
                    ToastUtils.showShort(str.toString())
                }
            }
        },
    )

    /**
     * ON_CREATE 在应用程序的整个生命周期中只会被调用一次
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        MobPushUtils.addPushReceiver(mMobPushReceiver)
    }

    /**
     * 应用程序出现到前台时调用
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    /**
     * 应用程序出现到前台时调用
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    /**
     * 应用程序退出到后台时调用
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    /**
     * 应用程序退出到后台时调用
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
    }

    /**
     * 永远不会被调用到，系统不会分发调用ON_DESTROY事件
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        MobPushUtils.removePushReceiver(mMobPushReceiver)
    }
}