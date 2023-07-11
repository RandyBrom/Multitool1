package ru.juraogurcov.multitool.viewUtils

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import ru.juraogurcov.multitool.ui.person.PersonViewModel
import ru.juraogurcov.multitool.data.UserImageData
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

private val idImageKey: String = "IDIMAGE"
fun getHTTPSSource(
    urlImageResorse: String,
    sharedPreferences: SharedPreferences?,
    queue: RequestQueue,
    urlImageKey: String
): String {
    var urlImage = ""
    val personViewModel = PersonViewModel()
    val stringRequest = StringRequest(
        Request.Method.GET,
        urlImageResorse,
        { response ->
            urlImage = JSONArray(response).getJSONObject(0).getString("url")
            sharedPreferences?.edit()
                ?.putString(urlImageKey, JSONArray(response).getJSONObject(0).getString("url"))
                ?.apply()
            sharedPreferences?.edit()
                ?.putString(idImageKey, JSONArray(response).getJSONObject(0).getString("id"))
                ?.apply()
            Log.d("Tag", urlImage)
            personViewModel.setUserAvatarInfo(UserImageData("", urlImage))
        },
        {
            Log.d("Tag", "No Result")
        }
    )
    queue.add(stringRequest)
    queue.start()
    return urlImage
}

fun getBitmapFromUrl(uRL: String): Bitmap? {
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
    }
    return null
}

fun saveImageFromBitmap(
    imagesDir: File?,
    imageBitmap: Bitmap?,
    sharedPreferences: SharedPreferences?
) {
    val filename = "ic_avatar.jpg"
    var fos: OutputStream? = null
    val image = File(imagesDir, filename)
    sharedPreferences?.edit()?.putString("PATHIMAGE", image.path)?.apply()
    fos = FileOutputStream(image)
    fos.use {
        imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
}

