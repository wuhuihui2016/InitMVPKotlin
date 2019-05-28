package com.whh.initmvpkotlin.mvp.presenter

import com.whh.initmvpkotlin.base.BasePresenter
import com.whh.initmvpkotlin.mvp.contract.GitUserContract
import com.whh.initmvpkotlin.mvp.model.GitUserModel
import com.whh.initmvpkotlin.net.exception.ExceptionHandle

class GitUserPresenter : BasePresenter<GitUserContract.View>(), GitUserContract.Presenter {
    override fun getGitUserData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = gitUserModel.getGitUserData()
            .subscribe({gitUser ->
                mRootView?.apply {
                    dismissLoading()
                    showGitUser(gitUser)
                }
            }, {t ->
                mRootView?.apply {
                    showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                }
            })

        addSubscription(disposable)

    }

    private val gitUserModel: GitUserModel by lazy {
        GitUserModel()
    }

}