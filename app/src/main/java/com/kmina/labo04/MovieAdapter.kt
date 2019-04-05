package com.kmina.labo04

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_element.view.*

class MovieAdapter(var movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_element, p0, false)
        /*
        * Estamos "inflando" cada uno de los elementos en la lista...
        * con p0 le decis cual elemento es su padre
        * y el attachRoot es por si se destruyó el padre seguirá el hijo...
        * */
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size //necesitamos sacarlo del length del array
    //es una inline function y solo se puede hacer así en una sola línea porque estamos devolviendo algo...

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, pos: Int) {
        holder.bind(movies[pos])
        /*
        * La clase movie adpater tiene un ciclo de vida, en bindViewHolder le damos la funcionalidad de lo que queres que haga cada
        * uno de los recyclerview, toma el holder que te pasan y con bind y pos le manda el primer item
        * hacemos la clase y función para enmascararlo como una capa de abstracción...
        * */

    }

    //Creamos la clase viewholder
    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){ //Estás agarrando una vista y se la pasas en el onCreateViewHolder

        //Ahora que necesitamos hacer nuestro OnBindViewHolder y empezamos a crear una función en esta clase
        fun bind(item: Movie) = with(itemView){
            //Acá le dice que lo que tenga una función vaya con el contexto de itemView; Esto es programación funcional...

            //Ponemos nuestra imagen con GLide...
            Glide.with(itemView.context)
                    .load(item.Poster)
                    .placeholder(R.drawable.ic_launcher_background)//El placeholder es de emergencia para poner algo sino carga lo que queremos poner
                    .into(movie_image_cv)

            //Vamos con cada uno de nuestros id's
            movie_title_cv.text = item.Title //una igualación acá sabe que le quiero settear el texto
            movie_plot_cv.text = item.Plot
            movie_rate_cv.text = item.imdbRating
            movie_runtime_cv.text = item.Runtime

        }

    }

    //Crearemos un funcion change list
    fun changeList(movies: List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }

}