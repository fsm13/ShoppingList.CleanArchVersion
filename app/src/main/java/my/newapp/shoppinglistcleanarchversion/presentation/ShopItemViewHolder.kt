package my.newapp.shoppinglistcleanarchversion.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.newapp.shoppinglistcleanarchversion.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvCount = view.findViewById<TextView>(R.id.tv_count)
}