package com.fantasyjutsu.api




import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @FormUrlEncoded
    @POST("users/signup")
    fun signUp(): Observable<Response<ResponseBody?>?>?

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Observable<Response<ResponseBody?>?>?

    @GET("match")
    fun getMatches(@Query("matchId") matchId: String): Observable<Response<ResponseBody?>?>?

}