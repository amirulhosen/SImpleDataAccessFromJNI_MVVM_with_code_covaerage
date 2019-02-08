package com.chatapplication.viewModel

import android.arch.lifecycle.MutableLiveData
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.chatapplication.Constants
import com.chatapplication.model.ChatModel

class ChatViewModel(private val mChatModel: ChatModel) : BaseObservable() {
    val mList = ArrayList<ChatModel>()
    val chatModel = ChatModel()
    var mMutableList = MutableLiveData<ArrayList<ChatModel>>()

    var chatMessage: String?
        @Bindable
        get() = mChatModel.chatString
        set(chatString) {
            mChatModel.chatString = chatString!!
        }

    var chatType: Int?
        @Bindable
        get() = mChatModel.chatType
        set(subtitle) {
            mChatModel.chatType = subtitle!!
        }

    fun addRecieverItem(message: String) {
        chatModel.chatType = Constants.MESSAGE_RECEIVER.ordinal
        chatModel.chatString = message
        mList.add(chatModel)
        mMutableList.postValue(mList)
    }

    fun addsenderItem() {
        chatModel.chatType = Constants.MESSAGE_SENDER.ordinal
        chatModel.chatString = "Welcome"
        mList.add(chatModel)
        mMutableList.postValue(mList)
    }

}