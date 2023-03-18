package ru.juraogurcov.multitool.ui.person

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.databinding.FragmentPersonBinding
import ru.juraogurcov.multitool.ui.more.UserInfo

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!
    private val accountInfo = context?.getSharedPreferences("AccountInfo", MODE_PRIVATE)

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
            binding.editTextPerson.setText(it.nameUser)
        }
        personViewModel.imageProfileId.observe(viewLifecycleOwner){
            profileButtonImage.setImageResource(it)
        }
        val name = accountInfo?.getString("Name", null)
        personViewModel.setUserInfo(UserInfo(name))
        binding.editTextPerson.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                accountInfo?.edit()?.putString("Name", s.toString())?.apply()

            }

            override fun afterTextChanged(s: Editable?) {


            }

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}