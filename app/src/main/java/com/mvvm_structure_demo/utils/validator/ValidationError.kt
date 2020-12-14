package com.mvvm_structure_demo.utils.validator


/**
 * An ENUM class which declares different types of validation error constants
 * , which needs to be passed in ValidationError model from the presenter to the activity.
 */
enum class ValidationError {
    EMAIL,
    FIRST_NAME,
    TERMS,
    LAST_NAME,
    DOB,
    GENDER,
    PASSWORD,
    CONFIRM_PASSWORD,
    PHONE,
    OTP,
    DATA,
    USERNAME,
    DESC
}