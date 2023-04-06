package my.newapp.shoppinglistcleanarchversion.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import my.newapp.shoppinglistcleanarchversion.R
import my.newapp.shoppinglistcleanarchversion.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: OnShopItemLongClickListener? = null
    var onShopItemClickListener: OnShopItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw java.lang.RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.onShopItemLongClick(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.onShopItemClick(shopItem)
            true
        }
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) VIEW_TYPE_ENABLED
        else VIEW_TYPE_DISABLED
    }


    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    interface OnShopItemClickListener {
        fun onShopItemClick(shopItem: ShopItem)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        const val MAX_POOL_SIZE = 15
    }

}