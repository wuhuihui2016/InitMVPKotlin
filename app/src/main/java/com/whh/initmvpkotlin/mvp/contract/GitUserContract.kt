package com.whh.initmvpkotlin.mvp.contract

import com.whh.initmvpkotlin.base.IBaseView
import com.whh.initmvpkotlin.base.IPresenter
import com.whh.initmvpkotlin.mvp.model.bean.GitUser

interface GitUserContract {

    interface View : IBaseView {

        fun showGitUser(gitUser: GitUser)

        fun showError(errorMsg: String, errorCode: Int)

    }

    interface Presenter : IPresenter<View> {

        fun getGitUserData()
    }
}