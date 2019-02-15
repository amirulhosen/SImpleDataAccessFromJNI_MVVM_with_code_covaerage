package com.chatapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chatapplication.Constants
import com.chatapplication.R
import com.chatapplication.databinding.ChatItemBinding
import com.chatapplication.model.ChatModel
import com.chatapplication.viewModel.ChatViewModel
import java.util.*


class ChatAdapter(private val mArticles: List<ChatModel>) : RecyclerView.Adapter<ChatAdapter.BindingHolder>() {
    private var lastPosition = -1
    var item = null;
    var item1: ChatViewModel? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.BindingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.chat_item, parent, false);
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {

//        val item = (ChatViewModel(mArticles[position]))
        val item = ChatViewModel().chatModel
        if ((item1!!.mList[position] as ChatModel).chatType == Constants.MESSAGE_RECEIVER.ordinal) {
            holder.binding.findViewById<TextView>(R.id.chat_he).visibility = View.GONE
            holder.binding.findViewById<TextView>(R.id.chat_me).visibility = View.VISIBLE
        } else {
            holder.binding.findViewById<TextView>(R.id.chat_me).visibility = View.GONE
            holder.binding.findViewById<TextView>(R.id.chat_he).visibility = View.VISIBLE
        }

        setAnimation(holder.itemView, position);
        holder.takeItem(item1!!)
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
//            ViewBinding!!.notifyPropertyChanged(com.chatapplication.BR.todoitem)

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

    fun updateData(it: ChatViewModel?) {
        this.item1 = it!!
        if (it.mList.size > 1)
            notifyItemInserted(it.mList.size - 1)
        else
            notifyDataSetChanged()
    }
}
