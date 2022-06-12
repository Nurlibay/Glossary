package uz.texnopos.glossary.presenter.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.texnopos.glossary.data.dao.GlossaryDao
import uz.texnopos.glossary.data.entity.Glossary
import uz.texnopos.glossary.utils.Resource
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val dao: GlossaryDao) : ViewModel() {

    private var _favourites: MutableLiveData<Resource<List<Glossary>>> = MutableLiveData()
    val favorites: LiveData<Resource<List<Glossary>>> get() = _favourites

    fun getFavourites() {
        _favourites.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = dao.getFavourites()
                _favourites.value = Resource.success(result)
            } catch (e: Exception) {
                _favourites.value = Resource.error(e.message.toString())
            }
        }
    }
}