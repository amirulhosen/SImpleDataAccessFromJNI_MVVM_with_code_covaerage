package com.chatapplication.view

import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.viewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        binding.viewModel!!.addsenderItem()
        val adapter = ChatAdapter(binding.viewModel!!.mList as List<ChatModel>)
        adapter.updateData(binding.viewModel)
        binding.mRecycleView.adapter = adapter
        val key1 = String(Base64.decode(getNativeKey1(), Base64.DEFAULT))
        val key2 = String(Base64.decode(getNativeKey2(), Base64.DEFAULT))
        System.out.println(key1 + " and " + key2)
        binding.viewModel!!.mMutableList.observe(this, Observer {
            binding.mRecycleView.smoothScrollToPosition(it!!.size - 1)
            if (it.size == 1)
//                adapter.notifyDataSetChanged()
                adapter.updateData(binding.viewModel)
            else
//                adapter.notifyItemInserted(it.size - 1)
                adapter.updateData(binding.viewModel)
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
