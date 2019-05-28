package com.whh.initmvpkotlin.base

interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()
}