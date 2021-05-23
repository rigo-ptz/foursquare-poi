package com.oxygen.poi.core.ui.base

/**
 * @author Yamushev Igor
 * @since  4/21/21
 */
sealed class UiViewState<out T> {

  object Idle : UiViewState<Nothing>()
  object Loading : UiViewState<Nothing>()
  data class Result<out T>(val data: T) : UiViewState<T>()
  data class Error(val message: String) : UiViewState<Nothing>()

  companion object {
    fun <T> result(data: T): UiViewState<T> = Result(data)
    fun <T> idle(): UiViewState<T> = Idle
    fun <T> loading(): UiViewState<T> = Loading
    fun <T> error(message: String): UiViewState<T> = Error(message)
  }

}