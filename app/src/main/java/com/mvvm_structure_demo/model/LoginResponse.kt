package com.mvvm_structure_demo.model


data class LoginResponse(
    val status: String,
    val message: String,
    val data: DataLoginResponse)

data class DataLoginResponse(
    val access_token: String,
    val token_type: String,
    val expires_at: String,
    val user: UserLoginResponse)

data class UserLoginResponse(
    val id: Int,
    val name: String,
    val email: String,
    val phone_number: String,
    val type: String,
    val cyclist_type: String,
    val cyclist_bike_type_id: String,
    val home_post_code: String,
    val work_post_code: String,
    val is_verified: Int,
    val otp: String,
    val subject: String)
