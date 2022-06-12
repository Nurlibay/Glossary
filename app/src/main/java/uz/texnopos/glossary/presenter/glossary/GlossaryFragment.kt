package uz.texnopos.glossary.presenter.glossary

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.texnopos.glossary.R
import uz.texnopos.glossary.data.entity.Glossary
import uz.texnopos.glossary.databinding.FragmentGlossaryBinding
import uz.texnopos.glossary.utils.ResourceState
import uz.texnopos.glossary.utils.showMessage

@AndroidEntryPoint
class GlossaryFragment : Fragment(R.layout.fragment_glossary) {

    private lateinit var binding: FragmentGlossaryBinding
    private val adapter = GlossaryAdapter()
    private val viewModel: GlossaryViewModel by viewModels()
    private var words: List<Glossary> = emptyList()

    override fun onStart() {
        binding.etSearch.clearFocus()
        binding.etSearch.setText("")
        viewModel.getWord()
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGlossaryBinding.bind(view)
        binding.rvWords.adapter = adapter
        binding.rvWords.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        viewModel.getWord()
        setupObserver()
        setHasOptionsMenu(true)

        binding.etSearch.addTextChangedListener {
            adapter.filterWordList(it.toString(), words)
            binding.tvNotFound.isVisible = adapter.models.isEmpty() && !it.isNullOrEmpty()
        }

        adapter.onItemClickListener {
            val action = GlossaryFragmentDirections.actionGlossaryFragmentToDefinitionFragment(it.id, it.term, it.russian_mean, it.eng_mean, it.qq_mean, it.status)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        viewModel.word.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    words = it.data!!.toMutableList()
                    adapter.models = it.data.toMutableList()
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }
}