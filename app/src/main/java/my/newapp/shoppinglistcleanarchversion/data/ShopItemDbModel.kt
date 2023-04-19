package my.newapp.shoppinglistcleanarchversion.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import my.newapp.shoppinglistcleanarchversion.domain.ShopItem

@Entity (tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)