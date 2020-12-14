package com.mvvm_structure_demo.network


import com.mvvm_structure_demo.model.LoginResponse
import com.mvvm_structure_demo.utils.SIGNUP
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.FormUrlEncoded


/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {



    @FormUrlEncoded
    @POST(SIGNUP)
    fun signUp(
        @Field("name") name: String,
        @Field("phone_number") phone_number: String,
        @Field("email") email: String
    ): Observable<LoginResponse>


  /*@GET(GET_SHOP_DETAILS)
    fun getShopDetails(@QueryMap params: HashMap<String, String>): Observable<ShopDetailsResponse>*/
}
