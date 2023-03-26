package ru.juraogurcov.multitool.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser

class InputStreamVolleyRequest<T>(method: Int, url: String, private val listener: Response.Listener<T>, private val parseResponse: (NetworkResponse?) -> Response<T>) : Request<T> (method, url, null) {
    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return parseResponse.invoke(response)
    }

    override fun deliverResponse(response: T?) {
        listener.onResponse(response)
    }

}