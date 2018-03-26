package io.github.hugoangeles0810.daggerdidemo.presentation.main

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.github.hugoangeles0810.daggerdidemo.R
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import io.github.hugoangeles0810.daggerdidemo.presentation.common.inflate
import io.github.hugoangeles0810.daggerdidemo.presentation.common.load

class RVMoviesAdapter : RecyclerView.Adapter<RVMoviesAdapter.ViewHolder>() {

    var data: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = parent.inflate(R.layout.item_movie)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(movie: Movie) {
            with(movie) {
                val imageView = itemView as AppCompatImageView
                imageView.load(movie.posterUrl)
            }
        }
    }
}