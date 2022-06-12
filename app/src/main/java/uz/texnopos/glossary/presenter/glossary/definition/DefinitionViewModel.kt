package uz.texnopos.glossary.presenter.glossary.definition

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
class DefinitionViewModel @Inject constructor(private val dao: GlossaryDao) : ViewModel() {

    private var _definition: MutableLiveData<Resource<Glossary>> = MutableLiveData()
    val definition: LiveData<Resource<Glossary>> get() = _definition

    fun getWordMean(id: Int) {
        _definition.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = dao.getWordMeans(id)
                _definition.value = Resource.success(result)
            } catch (e: Exception) {
                _definition.value = Resource.error(e.message.toString())
            }
        }
    }

    fun setFavourite(glossary: Glossary) {
        viewModelScope.launch {
            glossary.status = 1 - glossary.status
            dao.update(glossary)
        }
    }
}