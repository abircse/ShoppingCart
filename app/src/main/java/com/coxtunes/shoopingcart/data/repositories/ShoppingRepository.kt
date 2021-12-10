package com.coxtunes.shoopingcart.data.repositories

import com.coxtunes.shoopingcart.data.db.ShoppingDatabase
import com.coxtunes.shoopingcart.data.db.entities.ShoppingItem
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val db: ShoppingDatabase
): ShoppingInterfaces {

    override fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    override fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    override fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}