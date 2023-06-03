package ru.juraogurcov.multitool.ui.person

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.juraogurcov.multitool.R

@Preview
@Composable
fun FirstScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
    ) {
        IconButton(
            onClick = { }, modifier = Modifier
                .height(56.dp)
                .width(56.dp)
                .align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_rename),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .padding(0.dp, 34.dp)
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person_button),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .padding(0.dp, 44.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(
                        topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp
                    )
                )
                .background(
                    color = Color.Transparent, shape = RoundedCornerShape(
                        topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp
                    )
                )

        ) {
            val textStateFirstName = remember {
                mutableStateOf("")
            }
            val textStateSecondName = remember {
                mutableStateOf("")
            }
            val textStateThirdName = remember {
                mutableStateOf("")
            }
            val textStateDate = remember {
                mutableStateOf("")
            }
            TextField(
                value = textStateFirstName.value,
                onValueChange = {
                    textStateFirstName.value = it
                },
                modifier = Modifier
                    .border(
                        width = 0.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    )
                    .background(
                        colorResource(id = R.color.light_gray),
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    ),


            )
            TextField(
                value = textStateSecondName.value, onValueChange = {
                    textStateSecondName.value = it
                },
                Modifier
                    .border(
                        width = 0.dp, color = Color.Transparent
                    )
                    .background(colorResource(id = R.color.light_gray))
            )
            TextField(
                value = textStateThirdName.value, onValueChange = {
                    textStateThirdName.value = it
                },
                Modifier
                    .border(
                        width = 0.dp, color = Color.Transparent
                    )
                    .background(colorResource(id = R.color.light_gray))
            )
            TextField(
                value = textStateDate.value, onValueChange = {
                    textStateDate.value = it
                },
                Modifier
                    .border(
                        width = 0.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                    )
                    .background(
                        colorResource(id = R.color.light_gray),
                        shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                    )
            )
        }
    }
}

/*

fun sksjakj(){

    val personViewModel =
        ViewModelProvider(this).get(PersonViewModel::class.java)
    val accountInfoSharedPref = context?.getSharedPreferences("AccountInfo", Context.MODE_APPEND)  // lifeData file
    val root: View = binding.root
    editLiveDataText(personViewModel, accountInfoSharedPref)
    if(accountInfoSharedPref?.getString(pathImageKey, null) != null){
        val imageBitmap =  BitmapFactory.decodeFile(accountInfoSharedPref.getString(pathImageKey, null))
        binding.profileButtonImage.setImageBitmap(imageBitmap)
    }
    personViewModel.textUserInfoData.observe(viewLifecycleOwner) {  //getting updates from view model
        observeLiveDataText(it)
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
}
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

*/
/**
 * Прослушивание и запись данных из EditText
 *//*

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
}*/
