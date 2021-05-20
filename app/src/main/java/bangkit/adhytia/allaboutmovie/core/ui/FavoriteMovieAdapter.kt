package bangkit.adhytia.allaboutmovie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bangkit.adhytia.allaboutmovie.R
import bangkit.adhytia.allaboutmovie.core.data.source.local.entity.MovieEntity
import bangkit.adhytia.allaboutmovie.databinding.ItemRowFavoriteBinding
import bangkit.adhytia.allaboutmovie.databinding.ItemRowHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieEntity>()
    var onItemClick: ((MovieEntity) -> Unit)? = null

    fun setData(newListData: List<MovieEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_favorite, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowFavoriteBinding.bind(itemView)
        fun bind(data: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.posterURL)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
                tvTitle.text = data.title
                tvOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}