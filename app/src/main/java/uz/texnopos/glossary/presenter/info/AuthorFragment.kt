package uz.texnopos.glossary.presenter.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import uz.texnopos.glossary.R
import uz.texnopos.glossary.databinding.FragmentAuthorBinding

class AuthorFragment : Fragment(R.layout.fragment_author) {

    private lateinit var binding: FragmentAuthorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvAuthor.movementMethod = LinkMovementMethod.getInstance()
    }

}