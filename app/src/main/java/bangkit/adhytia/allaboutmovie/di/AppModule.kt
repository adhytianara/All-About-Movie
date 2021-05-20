package bangkit.adhytia.allaboutmovie.di

import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieInteractor
import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieUseCase
import bangkit.adhytia.allaboutmovie.detail.DetailMovieViewModel
import bangkit.adhytia.allaboutmovie.favorite.FavoriteViewModel
import bangkit.adhytia.allaboutmovie.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}