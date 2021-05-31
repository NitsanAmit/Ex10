package com.namit.ex10.networking.models

import com.google.gson.annotations.SerializedName

class SetPrettyNameRequest {

    @SerializedName("pretty_name")
    val prettyName: String?

    constructor(prettyName: String?) {
        this.prettyName = prettyName
    }
}