package com.namit.ex10.networking.models

class DataHolder<T>() {
    var data: T? = null

    constructor(data: T) : this() {
        this.data = data
    }
}