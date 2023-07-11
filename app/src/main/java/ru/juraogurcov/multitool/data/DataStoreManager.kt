package ru.juraogurcov.multitool.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Profile_Data")

//class DataStoreManager(val context: Context) {
//    suspend fun saveUserProfileData(userInfoData: UserInfoData) {
//        context.dataStore.edit { pref ->
//            pref[stringPreferencesKey("firstNameUser")] = userInfoData.firstNameUser
//            pref[stringPreferencesKey("secondNameUser")] = userInfoData.secondNameUser
//            pref[stringPreferencesKey("thirdNameUser")] = userInfoData.thirdNameUser
//            pref[stringPreferencesKey("dayOfBirthUser")] = userInfoData.dayOfBirthUser
//        }
//    }

//    fun getUserProfileData() = context.dataStore.data.map { pref ->
//        return@map UserInfoData(
//            pref[stringPreferencesKey("firstNameUser")] ?: "",
//            pref[stringPreferencesKey("secondNameUser")] ?: "",
////            pref[stringPreferencesKey("thirdNameUser")] ?: "",
////            pref[stringPreferencesKey("dayOfBirthUser")] ?: ""
////        )
////    }
//
//    suspend fun saveUserImageData(userImageData: UserImageData) {
//        context.dataStore.edit { pref ->
//            pref[stringPreferencesKey("id")] = userImageData.id
//            pref[stringPreferencesKey("url")] = userImageData.url
//        }
//    }
//
//    fun getUserImageData() = context.dataStore.data.map { pref ->
//        return@map UserImageData(
//            pref[stringPreferencesKey("id")] ?: "",
//            pref[stringPreferencesKey("url")] ?: ""
//        )
//    }
//}