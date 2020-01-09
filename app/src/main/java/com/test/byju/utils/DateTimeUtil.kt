package com.test.byju.utils

fun String.formatToDateStr(): String? {
    this.let {
        return it.split("T")[0]
    }
}