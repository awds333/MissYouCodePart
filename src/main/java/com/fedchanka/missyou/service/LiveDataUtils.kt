package com.fedchanka.missyou.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <R: Any, T: Any>LiveData<R>.map(transformation: (R) -> T): LiveData<T> =
    Transformations.map(this, transformation)