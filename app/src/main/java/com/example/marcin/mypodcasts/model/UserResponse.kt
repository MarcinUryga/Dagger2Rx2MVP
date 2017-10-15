package com.example.marcin.mypodcasts.model

/**
 * Created by MARCIN on 2017-10-15.
 */
data class UserResponse(
        var username: String,
        var objectId: String,
        var email: String,
        var firstName: String,
        var lastName: String,
        var sessionToken: String
) {
    override fun toString(): String {
        return "UserResponse(" +
                "username='$username', " +
                "objectId='$objectId', " +
                "email='$email', " +
                "firstName='$firstName', " +
                "lastName='$lastName', " +
                "sessionToken='$sessionToken'" +
                ")"
    }

}
