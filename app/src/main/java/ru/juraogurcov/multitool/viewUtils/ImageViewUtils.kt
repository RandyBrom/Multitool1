package ru.juraogurcov.multitool.viewUtils

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import ru.juraogurcov.multitool.ui.person.PersonViewModel
import ru.juraogurcov.multitool.ui.person.UserImageData
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

private val idImageKey: String = "IDIMAGE"

// TODO тут пока что контекст оставь. Потом придумаем как от него избавиться. Остальное вроде норм
fun getHTTPSSource(urlImageResorse: String, sharedPreferences: SharedPreferences?, context: Context?, urlImageKey: String): String{
    var urlImage = ""
    val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET,
            urlImageResorse,
            { response ->

                urlImage = JSONArray(response).getJSONObject(0).getString("url")
                sharedPreferences?.edit()?.putString(urlImageKey, JSONArray(response).getJSONObject(0).getString("url"))?.apply()
                sharedPreferences?.edit()?.putString(idImageKey, JSONArray(response).getJSONObject(0).getString("id"))?.apply()
                Log.d("Tag", urlImage)
                // TODO кроме этого статистический метод для записи значений не лучшее решение. Всё таки нужно переделывать будет
                // TODO на callBack. Если захочешь сам пеоеделать, то пример в файле EditTextUtils, функция addTextChangeListener.
                // TODO onChange - это и есть callBack
                PersonViewModel.setUserAvatarInfo(UserImageData(null, urlImage, null, null))
                Toast.makeText(context, "URL Got", Toast.LENGTH_SHORT).show()
            },
            {
            Log.d("Tag", "No Result")
            }
        )
        queue.add(stringRequest)
        queue.start()
    return urlImage
}

// TODO опять же контекст передавать. Тупо для того, что бы показать тост
fun getBitmapFromUrl(uRL: String, context: Context?): Bitmap? {
    val url = URL(uRL)
    val connection: HttpURLConnection?
    try {
        connection = url.openConnection() as HttpURLConnection
        connection.connect()
        val inputStream: InputStream = connection.inputStream
        val bufferedInputStream = BufferedInputStream(inputStream)
        return BitmapFactory.decodeStream(bufferedInputStream)
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
    }
    return null
}

// TODO передавать контекст не рекоммендуюется. И сама суть метода не будет работать на версии андроида выше 10
// TODO он тупо не сможет достать картинку, потому что там выдаётся временный путь какой то и по нему нельзя из загрузок получить картинку
fun saveImageFromBitmap(context: Context?, imageBitmap: Bitmap?, sharedPreferences: SharedPreferences?){
    val filename = "ic_avatar.jpg"
    var fos: OutputStream? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        context?.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            // TODO полный путь нет смысла сохранять, так как директория может изменится. Имя файла тебе известно заранее (69 строка)
            // TODO так что можно ничего этого не сохранять
            sharedPreferences?.edit()?.putString("PATHIMAGE", image.path)?.apply()
            fos = FileOutputStream(image)
        }
        fos?.use {
           imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
}

