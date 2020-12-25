package com.jl.mob_push.ui

import android.view.View
import com.blankj.utilcode.util.ClickUtils
import com.jl.mob_push.MobPushUtils
import com.jl.mob_push.R
import com.jl.mob_push.databinding.ActivityMainBinding
import com.zjx.app_common_library.base.showToast
import com.zjx.app_common_library.base.viewbinding.BaseVbActivity

class MainActivity : BaseVbActivity<ActivityMainBinding>() {
    private val tags = "标签${(0..10).random()}"
    private val alias = "别名${(0..10).random()}"
    override fun initView() {
        mViewBinding.butGetTags.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butSetTags.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butDeleteTags.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butCleanTags.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butGetAlias.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butSetAlias.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butDeleteAlias.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butIsPushStopped.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butRestartPush.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butStopPush.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.butGetDeviceToken.setOnClickListener(mOnDebouncingClickListener)
        mViewBinding.sbShowBadge.setOnCheckedChangeListener { view, isChecked ->
            MobPushUtils.setShowBadge(isChecked)
        }
    }

    private val mOnDebouncingClickListener = object : ClickUtils.OnDebouncingClickListener() {
        override fun onDebouncingClick(v: View?) {
            when (v?.id) {
                R.id.but_get_tags -> {
                    MobPushUtils.getTags()
                }
                R.id.but_set_tags -> {
                    MobPushUtils.addTags(tags)
                }
                R.id.but_delete_tags -> {
                    MobPushUtils.deleteTags(tags)
                }
                R.id.but_clean_tags -> {
                    MobPushUtils.cleanTags()
                }
                R.id.but_get_alias -> {
                    MobPushUtils.getAlias()
                }
                R.id.but_set_alias -> {
                    MobPushUtils.setAlias(alias)
                }
                R.id.but_delete_alias -> {
                    MobPushUtils.deleteAlias()
                }
                R.id.but_is_push_stopped -> {
                    val isPushStopped = MobPushUtils.isPushStopped()
                    showToast(
                        "推送服务${
                            if (isPushStopped) {
                                "已经停止"
                            } else {
                                "还在运行"
                            }
                        }"
                    )
                }
                R.id.but_restart_push -> {
                    MobPushUtils.restartPush()
                }
                R.id.but_stop_push -> {
                    MobPushUtils.stopPush()
                }
                R.id.but_get_device_token -> {
                    MobPushUtils.getDeviceToken {
                        showToast("厂商Token : ${it}")
                    }
                }
            }
        }
    }
}