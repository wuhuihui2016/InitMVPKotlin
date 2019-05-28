package com.whh.initmvpkotlin.ui

import android.widget.Toast
import com.hazz.kotlinmvp.net.exception.ErrorStatus
import com.whh.initmvpkotlin.R
import com.whh.initmvpkotlin.base.BaseActivity
import com.whh.initmvpkotlin.mvp.contract.GitUserContract
import com.whh.initmvpkotlin.mvp.model.bean.GitUser
import com.whh.initmvpkotlin.mvp.presenter.GitUserPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), GitUserContract.View {

    private val presenter by lazy { GitUserPresenter() }

    override fun initData() {
        presenter.attachView(this)
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() {
        loadData.setOnClickListener(){
            presenter.getGitUserData()
        }
    }

    override fun showGitUser(gitUser: GitUser) {
        showData.text = (gitUser.toString())
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            Toast.makeText(this, "无网络", Toast.LENGTH_LONG)
        } else Toast.makeText(this, "加载失败", Toast.LENGTH_LONG)
    }

    override fun showLoading() {
        Toast.makeText(this, "数据加载中...", Toast.LENGTH_LONG)
    }

    override fun dismissLoading() {
        Toast.makeText(this, "数据完毕", Toast.LENGTH_LONG)
    }
}
