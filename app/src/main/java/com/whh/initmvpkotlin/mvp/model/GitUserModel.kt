package com.whh.initmvpkotlin.mvp.model

import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.whh.initmvpkotlin.mvp.model.bean.GitUser
import com.whh.initmvpkotlin.net.RetrofitManager
import io.reactivex.Observable

class GitUserModel {

    fun getGitUserData(): Observable<GitUser> {

        return RetrofitManager.service.getGitUser().compose(SchedulerUtils.ioToMain())
    }
}