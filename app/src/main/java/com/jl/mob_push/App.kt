package com.jl.mob_push

import androidx.lifecycle.ProcessLifecycleOwner
import com.zjx.app_common_library.App

class App : App() {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(MobPushLifecycleObserver())
    }
}