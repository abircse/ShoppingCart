package com.coxtunes.shoopingcart.ui.shoppinglist

import com.coxtunes.shoopingcart.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}