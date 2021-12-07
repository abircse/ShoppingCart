package com.coxtunes.shoopingcart.ui.shoppinglist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coxtunes.shoopingcart.R
import com.coxtunes.shoopingcart.data.db.ShoppingDatabase
import com.coxtunes.shoopingcart.data.db.entities.ShoppingItem
import com.coxtunes.shoopingcart.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

private const val TAG = "ShoppingActivity"

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    lateinit var db: ShoppingDatabase

    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

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

}
