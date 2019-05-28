package com.whh.initmvpkotlin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.RefWatcher
import com.whh.initmvpkotlin.utils.DisplayManager
import kotlin.properties.Delegates

class MyApplication : Application() {

    private val refWatcher: RefWatcher? = null

    companion object{

        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher?{
            var myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initConfig()
        DisplayManager.init(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)

    }


    /**
     * 初始化log配置
     */
    private fun initConfig(){
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) //隐藏线程信息，默认：显示
            .methodCount(0)        //决定打印多少行（每行代表一个方法）默认：2
            .methodOffset(6)       //(Optional) Hides internal method calls up to offset. Default 5
            .tag("whh")            //log
            .build()
        Logger.addLogAdapter(object: AndroidLogAdapter(formatStrategy){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }

}