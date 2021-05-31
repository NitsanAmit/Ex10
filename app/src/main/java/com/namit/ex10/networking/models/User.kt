package com.namit.ex10.networking.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User() : Serializable {

    @SerializedName("username")
    var username: String? = null

    @SerializedName("pretty_name")
    var prettyName: String? = null

    @SerializedName("image_url")
    var imageUrl: String? = null

    constructor(username: String, prettyName: String, imageUrl: String): this() {
        this.username = username
        this.prettyName = prettyName
        this.imageUrl = imageUrl
    }
}