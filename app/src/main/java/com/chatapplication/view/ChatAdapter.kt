package com.chatapplication.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import com.chatapplication.Constants
import com.chatapplication.R
import com.chatapplication.databinding.ChatItemBinding
import com.chatapplication.model.ChatModel
import com.chatapplication.viewModel.ChatViewModel
import java.util.*


class ChatAdapter(private val mArticles: List<ChatModel>) : RecyclerView.Adapter<ChatAdapter.BindingHolder>() {
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.BindingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.chat_item, parent, false);
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {

        val item = (ChatViewModel(mArticles[position]))
        if (item.chatType == Constants.MESSAGE_RECEIVER.ordinal)
            holder.binding.findViewById<TextView>(R.id.chat_he).visibility = View.GONE
        else
            holder.binding.findViewById<TextView>(R.id.chat_me).visibility = View.GONE

        setAnimation(holder.itemView, position);
        holder.takeItem(item)
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    class BindingHolder(val binding: View) : RecyclerView.ViewHolder(binding) {
        var ViewBinding: ViewDataBinding?
            get() = DataBindingUtil.getBinding(itemView)

        init {
            ViewBinding = DataBindingUtil.bind<ChatItemBinding>(itemView)
        }
        fun takeItem(item: ChatViewModel) {
            ViewBinding!!.setVariable(com.chatapplication.BR.todoitem, item)
            ViewBinding!!.executePendingBindings()

        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val anim = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.duration = Random().nextInt(501).toLong()
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
    }
}
