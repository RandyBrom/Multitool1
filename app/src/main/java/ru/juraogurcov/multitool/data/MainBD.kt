package ru.juraogurcov.multitool.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Database([UserInfoData::class, UserImageData::class], version = 1)
abstract class MainBD : RoomDatabase() {
    abstract fun getDao():ru.juraogurcov.multitool.data.Dao
    companion object {
        fun getMainBD(context: Context): MainBD {
            return Room.databaseBuilder(
                context.applicationContext,
                MainBD::class.java,
                "mainBD.db"
            ).build()
        }
    }
}


@Dao
interface Dao {
    @Upsert
    suspend fun replaceUserInfo(userInfoData: UserInfoData)
    @Upsert
    suspend fun replaceUserInfo(userImageData: UserImageData)
    @Query("SELECT * FROM UserInfoData")
    fun getUserInfo(): LiveData<UserInfoData>
}