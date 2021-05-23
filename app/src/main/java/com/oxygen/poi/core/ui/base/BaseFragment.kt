package com.oxygen.poi.core.ui.base

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.oxygen.poi.R
import com.oxygen.poi.utils.AppUtils
import dagger.android.support.DaggerFragment
import timber.log.Timber

/**
 * @author Yamushev Igor
 * @since  5/23/21
 */
abstract class BaseFragment<T : ViewDataBinding> : DaggerFragment() {

  lateinit var binding: T

  abstract val layoutRes: Int

  private val requestPermissionLauncher =
    registerForActivityResult(
      ActivityResultContracts.RequestPermission()
    ) { isGranted -> onPermissionResult(isGranted) }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    binding.lifecycleOwner = this
  }

  override fun onStop() {
    binding.lifecycleOwner = null
    super.onStop()
  }

  protected open fun onPermissionResult(isGranted: Boolean) { }

  protected fun checkOrRequestPermission(permission: String, doIfGranted: () -> Unit) {
    Timber.d("checkOrRequestPermission($permission)")
    when {
      ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED -> {
        doIfGranted()
      }
      shouldShowRequestPermissionRationale(permission) -> {
        showPermissionRationale(permission)
      }
      else -> {
        requestPermissionLauncher.launch(permission)
      }
    }
  }

  private fun showPermissionRationale(permission: String) {
    Timber.d("showPermissionRationale($permission)")
    if (permission == Manifest.permission.ACCESS_FINE_LOCATION) {
      AlertDialog.Builder(requireContext())
        .setTitle(R.string.dialog_location_title)
        .setMessage(R.string.dialog_location_message)
        .setPositiveButton(R.string.yes) { dialog, _ ->
          AppUtils.showAppSettings(requireContext())
          dialog.dismiss()
        }
        .setNegativeButton(R.string.no, null)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
    }
  }

}