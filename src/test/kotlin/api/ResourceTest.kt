package api

import data.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.testng.Assert
import org.testng.annotations.Test
import request.RequestHelper

class ResourceTest : BaseTest() {
    @Test(description = "GET:LIST <RESOURCE>")
    fun getListResourceTest() {
        val url = baseUrl + "unknown"

        val mUserData = gson.fromJson(RequestHelper().getRequest(url), ResourcesListData::class.java)

        val yearsList = mutableListOf<Int>()
        mUserData.data?.forEach { yearsList += it.year }

        Assert.assertEquals(mUserData.page, 1, "Alarm! Страница не первая")
        Assert.assertEquals(mUserData.data?.get(1)?.name, "fuchsia rose", "Alarm! Имя не fuchsia rose")
        Assert.assertEquals(yearsList, yearsList.sorted(), "Alarm! Даты не по порядку")

    }

    @Test(description = "GET: SINGLE <RESOURCE>")
    fun getSingleResourceTest() {
        val url = baseUrl + "unknown/2"

        val mUserData = gson.fromJson(RequestHelper().getRequest(url), SingleResource::class.java)

        Assert.assertEquals(mUserData.data?.name, "fuchsia rose", "Alarm! Имя не fuchsia rose")
    }

    @Test(description = "GET:  SINGLE <RESOURCE> NOT FOUND")
    fun getUserNotFoundTest() {
        val url = baseUrl + "unknown/23"
        val request = Request.Builder().url(url).get().build()
        OkHttpClient().newCall(request).execute().use { response ->
            Assert.assertEquals(response.code, 404, "Alarm! Ошибка не 404")
        }
    }
}