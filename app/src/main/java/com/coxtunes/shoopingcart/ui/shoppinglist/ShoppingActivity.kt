package com.coxtunes.shoopingcart.ui.shoppinglist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.coxtunes.shoopingcart.R
import com.coxtunes.shoopingcart.base.BaseActivity
import com.coxtunes.shoopingcart.data.db.ShoppingDatabase
import com.coxtunes.shoopingcart.data.db.entities.ShoppingItem
import com.coxtunes.shoopingcart.databinding.ActivityShoppingBinding
import com.coxtunes.shoopingcart.other.ShoppingItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shopping.*

private const val TAG = "ShoppingActivity"

@AndroidEntryPoint
class ShoppingActivity : BaseActivity<ActivityShoppingBinding>() {


    private val viewModel: ShoppingViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.activity_shopping

    override fun onCreated(instance: Bundle?) {

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()

            if (it.isEmpty()) {
                placeholder.text = "Empty Shopping Cart"
            }

        })

        fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                        placeholder.text = ""

                    }
                }).show()
        }

        viewModel.getAllShoppingItems().observe(this, Observer {
            if (it.isEmpty()) {
                placeholder.text = "Empty Shopping Cart"
            }
            // Calculate Subtotal
            totaltxt.text = "Total: ${it.map { it.quantity * it.price }.sum()}"
            Log.d(TAG, "Total list of items: ${it}")
        })
    }

    override fun backPressedAction() {
        onBackPressed()
    }

}
