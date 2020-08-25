package br.com.training.android.simplemvvm.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.training.android.simplemvvm.data.api.ApiHelper
import br.com.training.android.simplemvvm.data.mapper.ListMapperImpl
import br.com.training.android.simplemvvm.data.mapper.ApiUserDataMapper
import br.com.training.android.simplemvvm.data.repository.MainRepositoryImpl
import br.com.training.android.simplemvvm.ui.main.viewmodel.MainViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepositoryImpl(apiHelper, ListMapperImpl(ApiUserDataMapper())), context) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}
