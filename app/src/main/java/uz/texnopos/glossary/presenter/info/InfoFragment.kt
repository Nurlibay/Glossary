package uz.texnopos.glossary.presenter.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.texnopos.glossary.R
import uz.texnopos.glossary.databinding.FragmentInfoBinding
import uz.texnopos.glossary.utils.onClick

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var binding: FragmentInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view).apply {
            itemAbbreviation.onClick {
                findNavController().navigate(R.id.action_infoFragment_to_abbreviationFragment)
            }
            itemAuthor.onClick {
                findNavController().navigate(R.id.action_infoFragment_to_authorFragment)
            }
        }
    }
}