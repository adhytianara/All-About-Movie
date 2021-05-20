package bangkit.adhytia.allaboutmovie

import android.app.Application
import bangkit.adhytia.allaboutmovie.core.di.databaseModule
import bangkit.adhytia.allaboutmovie.core.di.networkModule
import bangkit.adhytia.allaboutmovie.core.di.repositoryModule
import bangkit.adhytia.allaboutmovie.di.useCaseModule
import bangkit.adhytia.allaboutmovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}