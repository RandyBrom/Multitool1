package ru.juraogurcov.multitool.ui.person

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.databinding.FragmentPersonBinding
import ru.juraogurcov.multitool.viewUtils.addTextChangeListener
import ru.juraogurcov.multitool.viewUtils.getBitmapFromUrl
import ru.juraogurcov.multitool.viewUtils.getHTTPSSource
import ru.juraogurcov.multitool.viewUtils.saveImageFromBitmap

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!

    // TODO этот флаг лучше хранить в вью модели, т.к. при повороте экрана или иной смене конфигурации редактирования пользователя пропадают
    private var flagRename = false
    private val firstNameKey: String = "FIRST-NAME"
    private val secondNameKey: String = "SECOND-NAME"
    private val thirdNameKey: String = "THIRD-NAME"
    private val dateOfBirthKey: String = "BIRTHDATE"
    private val pathImageKey: String = "PATHIMAGE"
    private val urlImageKey: String = "URLIMAGE"
    // TODO ключ так нельзя хранить. Для этого используют local.proprties (у тебя есть такой файл в gradleScripts в вкладке project)
    private val keyForApi: String = "live_1xfROQEgx3GT6uCa4fiDj9hQeDOm8r1NJfFsh63Jh736v6gYdZAUaEtFLAz4yCgr"
    private  val urlAvatar: String = "https://api.thecatapi.com/v1/images/search? api_key=$keyForApi"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO вынести переменную к binding (24 строка) и попробовать использовать by viewmodels<название вью модели>()
        // TODO это позволит не передавать её в функции в качестве аргумента (48 строка), а использовать везде, где вздумается
        val personViewModel =
            ViewModelProvider(this).get(PersonViewModel::class.java)
        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val accountInfoSharedPref = context?.getSharedPreferences("AccountInfo", MODE_PRIVATE)  // lifeData file
        val root: View = binding.root
        // TODO в этом callback (onCreateView) нельзя работать с вьюхами экрана. Их использовать можно только начиная с onViewCreated
        val profileButtonImage: ImageButton = binding.profileButtonImage
        editLiveDataText(personViewModel, accountInfoSharedPref)
        if(accountInfoSharedPref?.getString(pathImageKey, null) != null){
            // TODO как раз таки ты пытаешься установить изображение до того, как вью раздулась
            val imageBitmap =  BitmapFactory.decodeFile(accountInfoSharedPref.getString(pathImageKey, null))
            profileButtonImage.setImageBitmap(imageBitmap)
        }
        personViewModel.textUserInfoData.observe(viewLifecycleOwner) {  //getting updates from view model
            // TODO и тут тоже. В общем и целом нужно это дело выность в onViewCreated
            observeLiveDataText(it)
        }
        personViewModel.imageProfileInfo.observe(viewLifecycleOwner){
           observeLiveDataImage(it, accountInfoSharedPref)
            if(accountInfoSharedPref?.getString(pathImageKey, null) != null){
                val imageBitmap =  BitmapFactory.decodeFile(accountInfoSharedPref.getString(pathImageKey, null))
                profileButtonImage.setImageBitmap(imageBitmap)
            }
        }
        // TODO просто уходит запрос в сеть и никак не используется
        profileButtonImage.setOnClickListener {
            val urlImage = getHTTPSSource(urlAvatar, accountInfoSharedPref, context, urlImageKey)

        }

        binding.topPanelRenameButton.setOnClickListener {
            changeUserInfo()
        }

        return root
    }

    /**
     * Проверка обновлений LiveData
     */

    private fun observeLiveDataImage(it: UserImageData, sharedPreferences: SharedPreferences?){
        if(it.url != null) {
            Thread {
                kotlin.run {
                    val bitmapImageAvatar = getBitmapFromUrl(it.url, context)
                    saveImageFromBitmap(context, bitmapImageAvatar, sharedPreferences)
                }
            }.start()
            val imageBitmap =  BitmapFactory.decodeFile(sharedPreferences?.getString(pathImageKey, null))
            binding.profileButtonImage.setImageBitmap(imageBitmap)
        }
    }
    private fun observeLiveDataText(it: UserInfoData){
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

    /**
     * Прослушивание и запись данных из EditText
     */
    private fun editLiveDataText(personViewModel: PersonViewModel , accountInfoSharedPref: SharedPreferences?){
        val firstName = accountInfoSharedPref?.getString(firstNameKey, null)
        val secondName = accountInfoSharedPref?.getString(secondNameKey, null)
        val thirdName = accountInfoSharedPref?.getString(thirdNameKey, null)
        val dayOfBirth = accountInfoSharedPref?.getString(dateOfBirthKey, null)
        personViewModel.setUserInfo(UserInfoData(firstName, secondName, thirdName, dayOfBirth))
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
            flagRename = false
        }else{
            binding.topPanelRenameButton.setImageResource(R.drawable.ic_done)
            binding.firstNameEditText.isEnabled = true
            binding.secondNameEditText.isEnabled = true
            binding.dateOfBirthEditText.isEnabled = true
            binding.thirdNameEditText.isEnabled = true
            binding.profileButtonImage.isClickable = true
            flagRename = true
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}