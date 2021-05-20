package bangkit.adhytia.allaboutmovie.favorite.di

import bangkit.adhytia.allaboutmovie.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}