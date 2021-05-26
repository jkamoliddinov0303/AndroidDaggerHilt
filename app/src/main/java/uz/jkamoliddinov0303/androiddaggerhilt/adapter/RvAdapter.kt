package uz.jkamoliddinov0303.androiddaggerhilt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*
import uz.jkamoliddinov0303.androiddaggerhilt.R
import uz.jkamoliddinov0303.androiddaggerhilt.db.User

class RvAdapter(private var userList: List<User>) : RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    private lateinit var listener: OnUserClickListener
    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User) {
            for (i in userList.indices)
                Log.d("TESTRV", "${userList[i].name}")
            itemView.tv_username.text = user.name
            itemView.tv_country.text = user.country
            itemView.setOnClickListener {
                listener.onUserClicked(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(userList[position])
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                listener.onUserClicked(userList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setOnUserClickedListener(listener: OnUserClickListener) {
        this.listener = listener
    }

    interface OnUserClickListener {
        fun onUserClicked(user: User)
    }
}