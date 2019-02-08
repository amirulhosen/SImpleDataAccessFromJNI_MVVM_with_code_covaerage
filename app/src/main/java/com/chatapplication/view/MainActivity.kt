package com.chatapplication.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Base64
import com.chatapplication.R
import com.chatapplication.databinding.ActivityMainBinding
import com.chatapplication.model.ChatModel
import com.chatapplication.viewModel.ChatViewModel


class MainActivity : AppCompatActivity() {
    val chatModel = ChatModel()
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        binding.mRecycleView.layoutManager = layoutManager
        val chatViewModel = ChatViewModel(chatModel)
        binding.viewModel = chatViewModel
        binding.viewModel!!.addsenderItem()
        val adapter = ChatAdapter(binding.viewModel!!.mList)
        binding.mRecycleView.adapter = adapter
        val key1 = String(Base64.decode(getNativeKey1(), Base64.DEFAULT))
        val key2 = String(Base64.decode(getNativeKey2(), Base64.DEFAULT))
        System.out.println(key1 + " and " + key2)
        chatViewModel.mMutableList.observe(this, Observer {
            binding.mRecycleView.smoothScrollToPosition(it!!.size - 1)
            if (it.size == 1)
                adapter.notifyDataSetChanged()
            else
                adapter.notifyItemInserted(it.size - 1)
        })

        binding.sendBtn.setOnClickListener {
            if (!binding.msgBox.text.toString().trim().isEmpty()) {
                binding.viewModel!!.addRecieverItem(binding.msgBox.text.toString())
                binding.msgBox.setText("")
            }
        }
    }

    fun check() {
        System.loadLibrary("keys");
    }

    external fun getNativeKey1(): String
    external fun getNativeKey2(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("keys")
        }
    }
}
