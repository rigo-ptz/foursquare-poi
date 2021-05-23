package com.oxygen.poi.core.ui.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  5/23/21
 */
abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity() {

  lateinit var binding: T

  abstract val layoutRes: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, layoutRes)
  }

  override fun onStart() {
    super.onStart()
    binding.lifecycleOwner = this
  }

  override fun onStop() {
    binding.lifecycleOwner = null
    super.onStop()
  }

}