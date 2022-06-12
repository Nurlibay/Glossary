package uz.texnopos.glossary.presenter.glossary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.glossary.data.entity.Glossary
import uz.texnopos.glossary.databinding.ItemBinding

class GlossaryAdapter : RecyclerView.Adapter<GlossaryAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun populateModel(glossary: Glossary) {
            binding.tvTerm.text = glossary.term
            binding.item.setOnClickListener {
                onItemClick.invoke(glossary)
            }
        }
    }

    var models: MutableList<Glossary> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            models.clear()
            field = value
            notifyDataSetChanged()
        }

    private var onItemClick: (glossary: Glossary) -> Unit = {}
    fun onItemClickListener(onItemClick: (glossary: Glossary) -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    fun filterWordList(s: String, wordList: List<Glossary>) {
        val filteredList: MutableList<Glossary> = mutableListOf()
        for (word in wordList) {
            if (word.term.lowercase().contains(s)) {
                filteredList.add(word)
            }
        }
        models = filteredList
    }
}