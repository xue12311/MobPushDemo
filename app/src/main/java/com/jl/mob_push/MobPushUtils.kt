package com.jl.mob_push

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import com.mob.pushsdk.*

object MobPushUtils {
    /**
     * 推送监听接口
     * @param mMobPushReceiver MobPushReceiver
     */
    fun addPushReceiver(mMobPushReceiver: MobPushReceiver) {
        MobPush.addPushReceiver(mMobPushReceiver)
    }

    /**
     * 移除推送监听接口
     * @param mMobPushReceiver MobPushReceiver
     */
    fun removePushReceiver(mMobPushReceiver: MobPushReceiver) {
        MobPush.removePushReceiver(mMobPushReceiver)
    }

    /**
     * 获取注册id，同一台设备，同个appkey对应一个注册id
     */
    fun getRegistrationId(onCallback: ((String) -> Unit)? = null) {
        MobPush.getRegistrationId { rid ->
            onCallback?.invoke(rid)
        }
    }

    /**
     * 设置别名
     * 别名是唯一的，多次调用，以最后一次设置为准，会进行覆盖；与注册Id是一一对应的
     * @param strAlias String 设置别名
     */
    fun setAlias(strAlias: String) {
        //参数不可使用中文或者特殊字符
        MobPush.setAlias(strAlias)
    }

    /**
     * 删除别名
     */
    fun deleteAlias() {
        MobPush.deleteAlias()
    }

    /**
     * 获取别名
     */
    fun getAlias() {
        MobPush.getAlias()
    }

    /**
     * 添加标签
     * 标签可以添加多个，每次调用都会在原来的基础上进行追加
     * @param tags 标签
     */
    fun addTags(vararg tags: String) {
        MobPush.addTags(tags)
    }

    /**
     * 删除标签
     * @param tags Array<out String>
     */
    fun deleteTags(vararg tags: String) {
        MobPush.deleteTags(tags)
    }

    /**
     * 获取标签
     */
    fun getTags() {
        MobPush.getTags()
    }

    /**
     * 清空标签
     */
    fun cleanTags() {
        MobPush.cleanTags()
    }

    /**
     * 设置通知静音时段 (24小时)
     * @param startHour Int 开始 小时
     * @param startMinute Int 开始 分钟
     * @param endHour Int 结束 小时
     * @param endMinute Int 结束 分钟
     */
    fun setSilenceTime(startHour: Int, startMinute: Int, endHour: Int, endMinute: Int) {
        MobPush.setSilenceTime(startHour, startMinute, endHour, endMinute)
    }

    /**
     * 添加本地通知。
     * 不通过服务器推送，客户端主动发送通知
     * @param localNotification MobPushLocalNotification
     */
    fun addLocalNotification(localNotification: MobPushLocalNotification): Boolean =
        MobPush.addLocalNotification(localNotification)

    /**
     * 设置通知图标
     * 通知默认使用应用图标，调用此方法来修改通知图标。
     * @param icon Int
     */
    fun setNotifyIcon(@DrawableRes icon: Int) {
        MobPush.setNotifyIcon(icon)
    }

    /**
     * 设置应用在前台时接收到推送不显示通知。
     * 默认是应用在前台是接收到推送则显示通知。
     * true : 应用在前台时接收到推送不显示通知；
     * false : 应用在前台时接收到推送显示通知。
     */
    fun setAppForegroundHiddenNotification(isHideNotification: Boolean = false) {
        MobPush.setAppForegroundHiddenNotification(isHideNotification)
    }

    /**
     * 设置自定义通知，根据需要需要定制通知如何显示。
     * 通过定义一个继承自MobPushTailorNotification来定制自己需要的通知。
     * @param mClass Class<T>
     */
    fun <T : MobPushTailorNotification> setTailorNotification(mClass: Class<T>) {
        MobPush.setTailorNotification(mClass)
    }

    /**
     * 移除自定义通知
     */
    @Deprecated("removeTailorNotification is Deprecated")
    fun removeTailorNotification() {
        MobPush.removeTailorNotification()
    }

    /**
     * 移除本地通知
     * @param mNotificationId Int
     */
    fun removeLocalNotification(mNotificationId: Int): Boolean =
        MobPush.removeLocalNotification(mNotificationId)

    /**
     * 清空本地通知
     */
    fun clearLocalNotifications(): Boolean =
        MobPush.clearLocalNotifications()

    /**
     * 判断是否停止推送
     * @return Boolean
     */
    fun isPushStopped(): Boolean = MobPush.isPushStopped()

    /**
     * 	重新打开推送服务
     * 	推送服务停止后，重新启动推送服务，继续接收推送
     */
    fun restartPush() {
        MobPush.restartPush()
    }

    /**
     * 停止推送服务，不继续接收推送
     * 停止后将不会接收到推送，仅可通过restartPush重新打开
     */
    fun stopPush() {
        MobPush.stopPush()
    }

    /**
     * 设置点击通知是否启动默认页。默认为启动。
     */
    fun setClickNotificationToLaunchMainActivity(isClick: Boolean = true) {
        MobPush.setClickNotificationToLaunchMainActivity(isClick)
    }

    /**
     * 设置是否显示角标，用于接收通知时显示角标数量。
     * @param isShow Boolean
     */
    fun setShowBadge(isShow: Boolean = true) {
        MobPush.setShowBadge(isShow)
    }

    /**
     * 获取厂商token
     */
    fun getDeviceToken(onCallback: ((String?) -> Unit)? = null) {
        MobPush.getDeviceToken(object : MobPushCallback<String> {
            override fun onCallback(mToken: String?) {
                onCallback?.invoke(mToken)
            }
        })
    }

    /**
     * 统计厂商统计点击数
     */
    fun notificationClickAck(intent: Intent) {
        MobPush.notificationClickAck(intent)
    }

    /**
     * 绑定手机号，用于短信补量时发送短信
     * @param strPhoneNum String 手机号
     * @param onCallback Function1<Boolean?, Unit>? true 绑定成功 false 绑定失败
     */
    fun bindPhoneNum(strPhoneNum: String, onCallback: ((Boolean?) -> Unit)? = null) {
        MobPush.bindPhoneNum(strPhoneNum, object : MobPushCallback<Boolean> {
            override fun onCallback(mBoolean: Boolean?) {
                //true 绑定成功 false 绑定失败
                onCallback?.invoke(mBoolean)
            }
        })
    }

    /**
     * 绑定手机号，用于短信补量时发送短信
     * @param strPhoneNum String
     * @param mMobPushCallback MobPushCallback<Boolean>
     */
    fun bindPhoneNum(strPhoneNum: String, mMobPushCallback: MobPushCallback<Boolean>) {
        MobPush.bindPhoneNum(strPhoneNum, mMobPushCallback)
    }

    /**
     * 推送监听接口
     * @param onCustomMessageReceive 接收到自定义消息（透传消息）
     * @param onNotifyMessageReceive 接收到通知消息
     * @param onNotifyMessageOpenedReceive 通知被点击事件
     * @param onTagsCallback 标签操作回调
     * @param onAliasCallback 别名操作回调
     */
    fun getMobPushReceiver(
        onCustomMessageReceive: ((Context, MobPushCustomMessage) -> Unit)? = null,
        onNotifyMessageReceive: ((Context, MobPushNotifyMessage) -> Unit)? = null,
        onNotifyMessageOpenedReceive: ((Context, MobPushNotifyMessage) -> Unit)? = null,
        onTagsCallback: ((Context, Array<out String>, Int, Int) -> Unit)? = null,
        onAliasCallback: ((Context, String, Int, Int) -> Unit)? = null
    ): MobPushReceiver =
        object : MobPushReceiver {
            //接收到自定义消息（透传消息）
            override fun onCustomMessageReceive(context: Context?, message: MobPushCustomMessage?) {
                if (context != null && message != null) {
                    onCustomMessageReceive?.invoke(context, message)
                }
            }

            //接收到通知消息
            override fun onNotifyMessageReceive(context: Context?, message: MobPushNotifyMessage?) {
                if (context != null && message != null) {
                    onNotifyMessageReceive?.invoke(context, message)
                }
            }

            //通知被点击事件
            override fun onNotifyMessageOpenedReceive(
                context: Context?,
                message: MobPushNotifyMessage?
            ) {
                if (context != null && message != null) {
                    onNotifyMessageOpenedReceive?.invoke(context, message)
                }
            }

            //标签操作回调
            override fun onTagsCallback(
                context: Context?,
                //标签
                tags: Array<out String>?,
                // operation == 0 , 获取标签
                // operation == 1 , 设置标签
                // operation == 2 , 删除标签
                operation: Int,
                // errorCode == 0 , 操作成功
                // errorCode != 0 ,操作失败
                errorCode: Int
            ) {
                if (context != null) {
                    onTagsCallback?.invoke(context, tags ?: arrayOf(), operation, errorCode)
                }
            }

            //别名操作回调
            override fun onAliasCallback(
                context: Context?,
                // 别名
                alias: String?,
                // operation == 0 , 获取别名
                // operation == 1 , 设置别名
                // operation == 2 , 删除别名
                operation: Int,
                // errorCode == 0 , 操作成功
                // errorCode != 0 ,操作失败
                errorCode: Int
            ) {
                if (context != null) {
                    onAliasCallback?.invoke(context, alias?:"", operation, errorCode)
                }
            }
        }
}