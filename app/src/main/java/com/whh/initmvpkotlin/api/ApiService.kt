package com.whh.initmvpkotlin.api

import com.whh.initmvpkotlin.mvp.model.bean.GitUser
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Api 接口
 */

interface ApiService{

    //get请求
    @GET("basil2style")
    fun getGitUser(): Observable<GitUser>


}