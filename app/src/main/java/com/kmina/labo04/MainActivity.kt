package com.kmina.labo04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Adapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

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
}
