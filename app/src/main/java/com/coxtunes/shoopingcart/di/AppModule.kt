package com.coxtunes.shoopingcart.di

import android.content.Context
import androidx.room.Room
import com.coxtunes.shoopingcart.data.db.ShoppingDao
import com.coxtunes.shoopingcart.data.db.ShoppingDatabase
import com.coxtunes.shoopingcart.data.repositories.ShoppingInterfaces
import com.coxtunes.shoopingcart.data.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: Nayeem Shiddiki Abir
 * @Date: 09-Dec-21
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingDatabase(@ApplicationContext appContext: Context): ShoppingDatabase {
        return Room.databaseBuilder(
            appContext,
            ShoppingDatabase::class.java,
            "RssReader"
        ).build()
    }

    @Provides
    fun provideShoppingDao(shoppingDatabase: ShoppingDatabase): ShoppingDao {
        return shoppingDatabase.getShoppingDao()
    }

    @Provides
    fun provideShoppingRepository(db: ShoppingDatabase): ShoppingInterfaces = ShoppingRepository(db)



}