package ru.juraogurcov.multitool.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val personViewModel =
            ViewModelProvider(this).get(PersonViewModel::class.java)

        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val profileButtonImage: ImageButton = binding.profileButtonImage
        val textView: TextView = binding.textPerson
        val renameButton: ImageButton = binding.renameUserButton
        renameButton.setImageResource(R.drawable.ic_rename)
        renameButton.setOnClickListener {

        }
        personViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        personViewModel.imageProfileId.observe(viewLifecycleOwner){
            profileButtonImage.setImageResource(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}