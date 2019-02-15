package com.chatapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chatapplication.Constants
import com.chatapplication.model.ChatModel

class ChatViewModel : ViewModel() {
    val mList = ArrayList<ChatModel>()
    var chatModel = ChatModel()
    var mMutableList = MutableLiveData<ArrayList<ChatModel>>()

//    var chatMessage: String?
//        get() = chatModel.chatString
//        set(chatString) {
//            chatModel.chatString = chatString!!
//        }
//
//    var chatType: Int?
//        get() = chatModel.chatType
//        set(subtitle) {
//            chatModel.chatType = subtitle!!
//        }

    fun addRecieverItem(message: String) {
        chatModel  = ChatModel()
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