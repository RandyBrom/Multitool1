package ru.juraogurcov.multitool.ui.more

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.databinding.FragmentMoreInfoBinding
class MoreInfoFragment : Fragment() {

    private var _binding: FragmentMoreInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moreInfoViewModel =
            ViewModelProvider(this)[MoreInfoViewModel::class.java]

        _binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.moreText
        moreInfoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = context?.getString(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}