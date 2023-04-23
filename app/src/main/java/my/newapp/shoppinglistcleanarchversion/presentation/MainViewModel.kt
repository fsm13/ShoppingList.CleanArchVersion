package my.newapp.shoppinglistcleanarchversion.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import my.newapp.shoppinglistcleanarchversion.data.ShopListRepositoryImpl
import my.newapp.shoppinglistcleanarchversion.domain.DeleteShopItemUseCase
import my.newapp.shoppinglistcleanarchversion.domain.EditShopItemUseCase
import my.newapp.shoppinglistcleanarchversion.domain.GetShopListUseCase
import my.newapp.shoppinglistcleanarchversion.domain.ShopItem

class MainViewModel(application:Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}