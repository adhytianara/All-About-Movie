package bangkit.adhytia.allaboutmovie.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import bangkit.adhytia.allaboutmovie.R
import bangkit.adhytia.allaboutmovie.core.domain.model.Movie
import bangkit.adhytia.allaboutmovie.databinding.ActivityDetailMovieBinding
import bangkit.adhytia.allaboutmovie.favorite.FavoriteActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        populateMovie(movie)
    }

    private fun populateMovie(movie: Movie?) {
        movie?.let {
            title = movie.title

            binding.tvTitle.text = movie.title
            (getString(R.string.release_date) + movie.releaseDate).also {
                binding.tvReleaseDate.text = it
            }
            (getString(R.string.popularity) + movie.popularity).also {
                binding.tvPopularity.text = it
            }
            (getString(R.string.vote_average) + movie.voteAverage).also {
                binding.tvVoteAvg.text = it
            }
            (getString(R.string.vote_count) + movie.voteCount).also {
                binding.tvVoteCount.text = it
            }

            binding.tvOverview.text = movie.overview
            Glide.with(this)
                .load(movie.posterURL)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgPoster)
            Glide.with(this)
                .load(movie.backdropURL)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgBackdrop)

            var statusFavorite = movie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.appbar_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                val favorite = Intent(this@DetailMovieActivity, FavoriteActivity::class.java)
                startActivity(favorite)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}