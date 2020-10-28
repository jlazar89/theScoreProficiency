package com.thescore.proficiencyexercise.utils.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created Date: 10/27/2020
 * Purpose: Extension to inflate layout along with data binding
 */
fun <M : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutRes: Int): M {
    return DataBindingUtil.inflate(
        LayoutInflater.from(context),
        layoutRes,
        this,
        false
    )
}