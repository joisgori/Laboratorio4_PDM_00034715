package com.kmina.labo04

import android.net.Uri
import java.io.IOException
import java.lang.reflect.MalformedParameterizedTypeException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val MOVIES_API_BASEURL = "http://www.omdapi.com/"
    val TOKEN_API = "8be834d"

    fun buildtSearchURL(movieName: String) : URL {
        val buildUri = Uri.parse(MOVIES_API_BASEURL)
                .buildUpon()
                .appendQueryParameter("apiKey", TOKEN_API)
                .appendQueryParameter("t", movieName)
                .build()

        return try {
            URL(buildUri.toString())
        }catch (e: MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL):String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput){
                scanner.next()
            }else{
                ""
            }
        }finally {
            urlConnection.disconnect()
        }
    }

}