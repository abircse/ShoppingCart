package com.coxtunes.shoopingcart.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_quantity")
    var quantity: Int,
    @ColumnInfo(name = "item_price")
    var price: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}