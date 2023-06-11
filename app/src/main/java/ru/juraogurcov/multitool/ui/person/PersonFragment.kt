package ru.juraogurcov.multitool.ui.person

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.data.UserImageData
import ru.juraogurcov.multitool.data.UserInfoData
import ru.juraogurcov.multitool.databinding.FragmentPersonBinding
import ru.juraogurcov.multitool.viewUtils.addTextChangeListener
import ru.juraogurcov.multitool.viewUtils.getBitmapFromUrl
import ru.juraogurcov.multitool.viewUtils.getHTTPSSource
import ru.juraogurcov.multitool.viewUtils.saveImageFromBitmap

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!
    private var flagRename = false
    private val firstNameKey: String = "FIRST-NAME"
    private val secondNameKey: String = "SECOND-NAME"
    private val thirdNameKey: String = "THIRD-NAME"
    private val dateOfBirthKey: String = "BIRTHDATE"
    private val pathImageKey: String = "PATHIMAGE"
    private val urlImageKey: String = "URLIMAGE"
    private val keyForApi: String = "live_1xfROQEgx3GT6uCa4fiDj9hQeDOm8r1NJfFsh63Jh736v6gYdZAUaEtFLAz4yCgr"
    private  val urlAvatar: String = "https://api.thecatapi.com/v1/images/search? api_key=$keyForApi"
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
        editLiveDataText(personViewModel, accountInfoSharedPref)
        if(accountInfoSharedPref?.getString(pathImageKey, null) != null){
            val imageBitmap =  BitmapFactory.decodeFile(accountInfoSharedPref.getString(pathImageKey, null))
            binding.profileButtonImage.setImageBitmap(imageBitmap)
        }
        personViewModel.imageProfileInfo.observe(viewLifecycleOwner){
           lifecycleScope.launch{
               observeLiveDataImage(it, accountInfoSharedPref)
           }

        }
        binding.profileButtonImage.setOnClickListener {
            val queue = Volley.newRequestQueue(context)
            val urlImage = getHTTPSSource(urlAvatar, accountInfoSharedPref, queue, urlImageKey)
        }

        binding.topPanelRenameButton.setOnClickListener {
            changeUserInfo()
        }

        return root
    }

    /**
     * Проверка обновлений LiveData
     */

    private suspend fun observeLiveDataImage(it: UserImageData, sharedPreferences: SharedPreferences?){
        if(it.url != null) {
            withContext(Dispatchers.IO){
                val bitmapImageAvatar = getBitmapFromUrl(it.url)
                val imagesDir = context?.filesDir
                saveImageFromBitmap(imagesDir, bitmapImageAvatar, sharedPreferences)
                withContext(Dispatchers.Main) {
                    binding.profileButtonImage.setImageBitmap(bitmapImageAvatar)
                }
            }

        }
    }


    /**
     * Прослушивание и запись данных из EditText
     */
    private fun editLiveDataText(personViewModel: PersonViewModel , accountInfoSharedPref: SharedPreferences?){
        val firstName = accountInfoSharedPref?.getString(firstNameKey, null)
        val secondName = accountInfoSharedPref?.getString(secondNameKey, null)
        val thirdName = accountInfoSharedPref?.getString(thirdNameKey, null)
        val dayOfBirth = accountInfoSharedPref?.getString(dateOfBirthKey, null)
      //  personViewModel.setUserInfo(UserInfoData(firstName, secondName, thirdName, dayOfBirth))
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
    }

    private fun changeUserInfo(){
        if (flagRename){
            binding.topPanelRenameButton.setImageResource(R.drawable.ic_rename)
            binding.firstNameEditText.isEnabled = false
            binding.secondNameEditText.isEnabled = false
            binding.dateOfBirthEditText.isEnabled = false
            binding.thirdNameEditText.isEnabled = false
            binding.profileButtonImage.isClickable = false
            binding.profileButtonImage.isFocusable = false
            flagRename = false
        }else{
            binding.topPanelRenameButton.setImageResource(R.drawable.ic_done)
            binding.firstNameEditText.isEnabled = true
            binding.secondNameEditText.isEnabled = true
            binding.dateOfBirthEditText.isEnabled = true
            binding.thirdNameEditText.isEnabled = true
            binding.profileButtonImage.isClickable = true
            binding.profileButtonImage.isFocusable = true
            flagRename = true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}