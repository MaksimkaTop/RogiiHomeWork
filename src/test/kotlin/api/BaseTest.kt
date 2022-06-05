package api

import com.google.gson.GsonBuilder
import request.Enviroment

open class BaseTest {
    val gson = GsonBuilder().create()
    val baseUrl = Enviroment().getMainDomain()
}