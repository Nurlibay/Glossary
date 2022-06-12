package uz.texnopos.glossary.presenter.glossary.definition

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import uz.texnopos.glossary.R
import uz.texnopos.glossary.data.entity.Glossary
import uz.texnopos.glossary.databinding.FragmentDefinitionBinding
import uz.texnopos.glossary.utils.ResourceState
import uz.texnopos.glossary.utils.onClick
import uz.texnopos.glossary.utils.showMessage

@AndroidEntryPoint
class DefinitionFragment : Fragment(R.layout.fragment_definition) {

    private lateinit var binding: FragmentDefinitionBinding
    private val viewModel: DefinitionViewModel by viewModels()
    private val args: DefinitionFragmentArgs by navArgs()
    private lateinit var glossary: Glossary

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        glossary = Glossary(args.id, args.term, args.russianMean, args.engMean, args.qqMean, args.status)
        binding = FragmentDefinitionBinding.bind(view)
        binding.btnHome.onClick {
            findNavController().popBackStack()
        }
        if (glossary.status == 0) {
            binding.imgStar.setImageResource(R.drawable.ic_star_not_full)
        } else {
            binding.imgStar.setImageResource(R.drawable.ic_star_fully)
        }

        viewModel.getWordMean(glossary.id)
        setupObserver()
        binding.imgStar.onClick {
            viewModel.setFavourite(glossary)
            if (glossary.status == 0) {
                binding.imgStar.setImageResource(R.drawable.ic_star_not_full)
            } else {
                binding.imgStar.setImageResource(R.drawable.ic_star_fully)
            }
        }
    }

    private fun setupObserver() {
        viewModel.definition.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    binding.tvTermin.text = it.data!!.term
                    binding.tvRussianMeanText.text = it.data.russian_mean
                    binding.tvEngMeanText.text = it.data.eng_mean
                    binding.tvQqMeanText.text = it.data.qq_mean
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }
}