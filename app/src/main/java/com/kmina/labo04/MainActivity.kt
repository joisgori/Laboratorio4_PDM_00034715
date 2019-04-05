package com.kmina.labo04

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter //para qué es lateinit
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var movieList: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun initRecyclerView () {
        viewManager = LinearLayoutManager(this) //Le pasamos el contexto;
        movieAdapter = MovieAdapter(movieList) //Inicalizamos el movieAdapter

        movie_list_rv.apply {
            //Al recyclerview le pedimos que tenga un tamaño fijo, que tenga un layoutmanager que definí arriba
            //Y va faltando na más el adapter.
            setHasFixedSize(true)
            layoutManager = viewManager

            //Ahora sí, va el adpater
            adapter = movieAdapter
        }
    }

    //Función AddMovieToList

    fun addMovieToList(movie: Movie){
        movieList.add(movie)
        movieAdapter.changeList(movieList)
        Log.d("Number", movieList.size.toString())
    }

    private inner class FetchMovie : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String): String {
            if (params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieURL = NetworkUtils().buildtSearchURL(movieName)

            return try {
                NetworkUtils().getResponseFromHttpUrl(movieURL)
            } catch (e: IOException){
                ""
            }
        }

        override fun onPostExecute(movieInfo: String) {

            super.onPostExecute(movieInfo)

            if (!movieInfo.isEmpty()) {
                val movieJson = JSONObject(movieInfo)
                if (movieJson.getString("Response") == "True") {
                    val movie = Gson().fromJson<Movie>(movieInfo, Movie::class.java)
                    addMovieToList(movie)
                }else{
                    Snackbar.make(main_ll, "No existe la película en la base", Snackbar.LENGTH_SHORT).show()
                }
            }

        }
    }
}
