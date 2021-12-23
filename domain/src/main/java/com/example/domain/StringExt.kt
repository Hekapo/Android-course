package com.example.domain

fun String.validate(): Boolean {
    return this.trim().isNotEmpty()
}
