package com.chatapplication.model

import android.R.attr.password
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR


class ChatModel: BaseObservable() {
    @Bindable
    var chatString: String? = null
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.chatString)
        }

    @Bindable
    var chatType: Int? = null
        get() = field
        set(value) {
            field = value
        }
}