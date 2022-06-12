package uz.texnopos.glossary.presenter.favourite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.texnopos.glossary.R
import uz.texnopos.glossary.databinding.FragmentBookmarkBinding
import uz.texnopos.glossary.presenter.glossary.GlossaryFragmentDirections
import uz.texnopos.glossary.utils.ResourceState
import uz.texnopos.glossary.utils.showMessage

@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_bookmark) {

    private lateinit var binding: FragmentBookmarkBinding
    private val viewModel: FavouriteViewModel by viewModels()
    private val adapter = FavouriteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookmarkBinding.bind(view)
        binding.rvFavourite.adapter = adapter
        binding.rvFavourite.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        viewModel.getFavourites()
        setupObserver()
        adapter.onItemClickListener {
            val action = FavouriteFragmentDirections.actionFavouriteFragmentToDefinitionFragment(it.id, it.term, it.russian_mean, it.eng_mean, it.qq_mean, it.status)
            findNavController().navigate(action)
        }
    }

    private fun setupObserver() {
        viewModel.favorites.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    adapter.models = it.data!!.toMutableList()
                    binding.tvNotFound.isVisible = it.data.isEmpty()
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }
}