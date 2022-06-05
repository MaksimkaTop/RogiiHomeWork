package request

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class RequestHelper {
    fun getRequest(url: String): String? {

        val request = Request.Builder().url(url).get().build()

        return OkHttpClient().newCall(request).execute().body?.string()
    }

    fun postRequest(url: String, body: RequestBody): String? {

        val request = Request.Builder().url(url).post(body).build()

        return OkHttpClient().newCall(request).execute().body?.string()
    }

    fun putRequest(url: String, body: RequestBody): String? {

        val request = Request.Builder().url(url).put(body).build()

        return OkHttpClient().newCall(request).execute().body?.string()
    }

    fun patchRequest(url: String, body: RequestBody): String? {

        val request = Request.Builder().url(url).patch(body).build()

        return OkHttpClient().newCall(request).execute().body?.string()
    }
}