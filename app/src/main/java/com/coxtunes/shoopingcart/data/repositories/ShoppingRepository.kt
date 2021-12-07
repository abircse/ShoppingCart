package com.coxtunes.shoopingcart.data.repositories

import com.coxtunes.shoopingcart.data.db.ShoppingDatabase
import com.coxtunes.shoopingcart.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}