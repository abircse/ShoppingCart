package com.coxtunes.shoopingcart.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    internal lateinit var binding: V

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        onCreated(savedInstanceState)
    }

    protected abstract fun onCreated(instance: Bundle?)

    abstract fun backPressedAction()

    fun showToastMessage(message: String, isPositive: Boolean = false) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}