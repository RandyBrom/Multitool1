package ru.juraogurcov.multitool.ui.person

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.juraogurcov.multitool.databinding.FragmentPersonBinding
import ru.juraogurcov.multitool.ui.more.UserInfo
import ru.juraogurcov.multitool.viewUtils.addTextChangeListener

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!
    private val firstNameKey: String = "FIRST-NAME"
    private val secondNameKey: String = "SECOND-NAME"
    private val thirdNameKey: String = "THIRD-NAME"
    private val dateOfBirthKey: String = "BIRTHDATE"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val personViewModel =
            ViewModelProvider(this).get(PersonViewModel::class.java)
        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val accountInfoSharedPref = context?.getSharedPreferences("AccountInfo", MODE_PRIVATE)  // lifeData file
        val root: View = binding.root
        val profileButtonImage: ImageButton = binding.profileButtonImage
        val firstName = accountInfoSharedPref?.getString(firstNameKey, null)
        val secondName = accountInfoSharedPref?.getString(secondNameKey, null)
        val thirdName = accountInfoSharedPref?.getString(thirdNameKey, null)
        val dayOfBirth = accountInfoSharedPref?.getString(dateOfBirthKey, null)

        personViewModel.setUserInfo(UserInfo(firstName, secondName, thirdName, dayOfBirth))
        binding.firstNameEditText.addTextChangeListener {
            accountInfoSharedPref?.edit()?.putString(firstNameKey, it.toString() )?.apply()
        }
        binding.secondNameEditText.addTextChangeListener {
            accountInfoSharedPref?.edit()?.putString(secondNameKey, it.toString())?.apply()
        }
        binding.thirdNameEditText.addTextChangeListener {
            accountInfoSharedPref?.edit()?.putString(thirdNameKey, it.toString())?.apply()
        }
        binding.dateOfBirthEditText.addTextChangeListener{
            accountInfoSharedPref?.edit()?.putString(dateOfBirthKey, it.toString())?.apply()
        }
        personViewModel.textUserInfo.observe(viewLifecycleOwner) {  //getting updates from view model
            if(it.firstNameUser != null) {
                binding.firstNameEditText.setText(it.firstNameUser)
            }
            if(it.secondNameUser != null) {
                binding.secondNameEditText.setText(it.secondNameUser)
            }
            if(it.thirdNameUser != null) {
                binding.thirdNameEditText.setText(it.thirdNameUser)
            }
            if(it.dayOfBirthUser != null) {
                binding.dateOfBirthEditText.setText(it.dayOfBirthUser)
            }
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