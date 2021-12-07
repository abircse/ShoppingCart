package com.coxtunes.shoopingcart.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coxtunes.shoopingcart.R
import com.coxtunes.shoopingcart.data.db.entities.ShoppingItem
import com.coxtunes.shoopingcart.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.itemView.tvName.text = curShoppingItem.name
        holder.itemView.tvAmount.text = "${curShoppingItem.quantity}"
        holder.itemView.tvPrice.text = "Price: ${curShoppingItem.price}"

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            curShoppingItem.quantity++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if (curShoppingItem.quantity > 1) {
                curShoppingItem.quantity--
                viewModel.upsert(curShoppingItem)
            }
        }

        holder.itemView.ivEdit.setOnClickListener {

        }


    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}