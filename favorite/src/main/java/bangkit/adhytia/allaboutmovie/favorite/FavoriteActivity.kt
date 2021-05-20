package bangkit.adhytia.allaboutmovie.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.adhytia.allaboutmovie.R
import bangkit.adhytia.allaboutmovie.core.ui.FavoriteMovieAdapter
import bangkit.adhytia.allaboutmovie.detail.DetailMovieActivity
import bangkit.adhytia.allaboutmovie.favorite.databinding.ActivityFavoriteBinding
import bangkit.adhytia.allaboutmovie.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val favoriteMovieAdapter = FavoriteMovieAdapter()
        favoriteMovieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this, { dataMovie ->
            favoriteMovieAdapter.setData(dataMovie)
        })

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteMovieAdapter
        }

        title = getString(R.string.my_favorite)
    }
}