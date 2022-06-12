package uz.texnopos.glossary.presenter.glossary

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
class GlossaryViewModel @Inject constructor(private val dao: GlossaryDao) : ViewModel() {

    private var _word: MutableLiveData<Resource<List<Glossary>>> = MutableLiveData()
    val word: LiveData<Resource<List<Glossary>>> get() = _word

    fun getWord() {
        _word.value = Resource.loading()
        viewModelScope.launch {
            try {
                val result = dao.readAllData()
                _word.value = Resource.success(result)
            } catch (e: Exception) {
                _word.value = Resource.error(e.message.toString())
            }
        }
    }
}