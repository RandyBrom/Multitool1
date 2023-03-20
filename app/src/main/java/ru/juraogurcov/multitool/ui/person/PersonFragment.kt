package ru.juraogurcov.multitool.ui.person

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import ru.juraogurcov.multitool.databinding.FragmentPersonBinding
import ru.juraogurcov.multitool.viewUtils.addTextChangeListener

class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!
    private val firstNameKey: String = "FIRST-NAME"
    private val secondNameKey: String = "SECOND-NAME"
    private val thirdNameKey: String = "THIRD-NAME"
    private val dateOfBirthKey: String = "BIRTHDATE"
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
        val profileButtonImage: ImageButton = binding.profileButtonImage
            editLiveDataText(personViewModel, accountInfoSharedPref)

        personViewModel.textUserInfo.observe(viewLifecycleOwner) {  //getting updates from view model
           observeLiveDataText(it)
        }

        profileButtonImage.setOnClickListener {

        }
        getImageResult()
        personViewModel.imageProfileId.observe(viewLifecycleOwner){
            profileButtonImage.setImageResource(it)
        }
        return root
    }

    /**
     * Проверка обновлений LiveData
     */
    private fun observeLiveDataText(it: UserInfo){
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
    }
    private fun getImageResult(){
        val qeqeq = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
        urlAvatar,
            {
            response ->
            Log.d("Tag", response)
                val imageUrl = JSONArray(response).getJSONObject(0).getString("url")
                val imageId = JSONArray(response).getJSONObject(0).getString("id")
                Log.d("Tag", imageUrl)

            },
            {

            }
        )
        qeqeq.add(stringRequest)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}