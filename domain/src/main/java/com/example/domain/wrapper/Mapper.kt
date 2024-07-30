package com.example.domain.wrapper

interface Mapper<F,T> {
    fun mapFrom(from:F):T
}