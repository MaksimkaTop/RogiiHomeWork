package api

import data.RegistrationResponseData
import data.UserRegistrationData
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.testng.Assert
import org.testng.annotations.Test
import request.RequestHelper


class RegisterTest : BaseTest() {

    @Test(description = "POST: REGISTER - SUCCESSFUL")
    fun registerSuccessTest() {
        val url = baseUrl + "register"

        val formBody = FormBody.Builder()
            .add("email", UserRegistrationData().email)
            .add("password", UserRegistrationData().password)
            .build()

        val data = gson.fromJson(RequestHelper().postRequest(url, formBody), RegistrationResponseData::class.java)

        Assert.assertEquals(data.token, "QpwL5tke4Pnpja7X4")
        Assert.assertEquals(data.id, 4)

    }

    @Test(description = "POST: REGISTER - UNSUCCESSFUL")
    fun registerUnSuccessTest() {
        val url = baseUrl + "register"
        val registrationData = UserRegistrationData()

        registrationData.fillUnSuccessData()

        val formBody: RequestBody = FormBody.Builder()
            .add("email", registrationData.email)
            .build()

        val request = Request.Builder().url(url).post(formBody).build()
        OkHttpClient().newCall(request).execute().use { response ->
            Assert.assertEquals(response.code, 400, "Alarm! Ошибка не 400")
            response.body?.string()?.contains("Missing password")
                ?.let { Assert.assertTrue(it, "Alarm! Неверный текст ошибки") }
        }
    }
}