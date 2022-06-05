package api

import data.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.testng.Assert
import org.testng.annotations.Test
import request.RequestHelper
import utils.TimeCompareHelper


class UserTest : BaseTest() {
    @Test(description = "GET: LIST USERS")
    fun getListUsersTest() {
        val url = baseUrl + "users?page=2"

        val userData = gson.fromJson(RequestHelper().getRequest(url), UserListData::class.java)

        userData.data?.forEach {
            it.avatar?.contains(it.id.toString())
                ?.let { it1 ->
                    Assert.assertTrue(
                        it1,
                        "Alarm! Номер в аватаре не совпадает с ID пользователя"
                    )
                }
        }

        userData.data?.forEach {
            it.email?.contains(("@reqres.in"))
                ?.let { it1 ->
                    Assert.assertTrue(
                        it1,
                        "Alarm! email пользователя не заказнчивается на: @reqres.in"
                    )
                }
        }
    }

    @Test(description = "GET: SINGLE USER")
    fun getSingleUserTest() {
        val url = baseUrl + "users/2"

        val userData = gson.fromJson(RequestHelper().getRequest(url), SingleUserData::class.java)

        Assert.assertEquals(userData.data?.email, "janet.weaver@reqres.in")
        userData.data?.avatar?.contains(userData.data?.id.toString())
            ?.let { Assert.assertTrue(it, "Alarm! Номер в аватаре не совпадает с ID пользователя") }

    }

    @Test(description = "GET:  SINGLE USER NOT FOUND")
    fun getUserNotFoundTest() {
        val url = baseUrl + "users/23"
        val request = Request.Builder().url(url).get().build()
        OkHttpClient().newCall(request).execute().use { response ->
            Assert.assertEquals(response.code, 404, "Alarm! Ошибка не 404")
        }
    }

    @Test(description = "PATCH: UPDATE")
    fun patchUserTest() {
        val url = baseUrl + "users/2"
        var userData = UserJobData()

        val formBody: RequestBody = FormBody.Builder()
            .add("name", userData.name)
            .add("job", userData.job)
            .build()

        userData = gson.fromJson(RequestHelper().patchRequest(url, formBody), UserJobData::class.java)

        Assert.assertEquals(userData.name, "morpheus")
        Assert.assertEquals(userData.job, "zion resident")
        userData.updatedAt?.let { TimeCompareHelper().localToResponseAssert(it) }

    }

    @Test(description = "PUT: UPDATE")
    fun putUserTest() {
        val url = baseUrl + "users/2"
        var userData = UserJobData()

        val formBody: RequestBody = FormBody.Builder()
            .add("name", userData.name)
            .add("job", userData.job)
            .build()

        userData = gson.fromJson(RequestHelper().putRequest(url, formBody), UserJobData::class.java)

        Assert.assertEquals(userData.name, "morpheus")
        Assert.assertEquals(userData.job, "zion resident")
        userData.updatedAt?.let { TimeCompareHelper().localToResponseAssert(it) }

    }

    @Test(description = "DELETE: DELETE")
    fun deleteUserTest() {
        val url = baseUrl + "users/2"
        val request = Request.Builder().url(url).delete().build()
        OkHttpClient().newCall(request).execute().use { response ->
            Assert.assertEquals(response.code, 204, "Alarm! Ошибка не 204")
        }
    }
}

