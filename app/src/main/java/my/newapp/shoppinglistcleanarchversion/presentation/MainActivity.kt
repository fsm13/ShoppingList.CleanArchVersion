package my.newapp.shoppinglistcleanarchversion.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import my.newapp.shoppinglistcleanarchversion.R
import my.newapp.shoppinglistcleanarchversion.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListadapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListadapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rcShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        shopListadapter = ShopListAdapter()
        rcShopList.adapter = shopListadapter
        rcShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        rcShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )

        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rcShopList)
    }

    private fun setupSwipeListener(rcShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListadapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rcShopList)
    }

    private fun setupClickListener() {
        shopListadapter.onShopItemClickListener = object : ShopListAdapter.OnShopItemClickListener {
            override fun onShopItemClick(shopItem: ShopItem) {
                Log.d("123", shopItem.toString())
            }
        }
    }

    private fun setupLongClickListener() {
        shopListadapter.onShopItemLongClickListener =
            object : ShopListAdapter.OnShopItemLongClickListener {
                override fun onShopItemLongClick(shopItem: ShopItem) {
                    viewModel.changeEnableState(shopItem)
                }
            }
    }
}