package uz.texnopos.glossary.presenter.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.texnopos.glossary.R
import uz.texnopos.glossary.databinding.FragmentAbbreviationBinding

class AbbreviationFragment : Fragment(R.layout.fragment_abbreviation) {

    private lateinit var binding: FragmentAbbreviationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAbbreviationBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}