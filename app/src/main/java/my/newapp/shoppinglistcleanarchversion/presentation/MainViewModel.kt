package my.newapp.shoppinglistcleanarchversion.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.newapp.shoppinglistcleanarchversion.data.ShopListRepositoryImpl
import my.newapp.shoppinglistcleanarchversion.domain.DeleteShopItemUseCase
import my.newapp.shoppinglistcleanarchversion.domain.EditShopItemUseCase
import my.newapp.shoppinglistcleanarchversion.domain.GetShopListUseCase
import my.newapp.shoppinglistcleanarchversion.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}