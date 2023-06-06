package com.reylar.composepaging3.di

import android.content.Context
import androidx.room.Room
import com.reylar.composepaging3.BuildConfig
import com.reylar.composepaging3.data.db.local.MovieDao
import com.reylar.composepaging3.data.db.local.MovieDatabase
import com.reylar.composepaging3.data.db.local.RemoteKeysDao
import com.reylar.composepaging3.data.remote.repository.MoviePaginatedRepositoryImpl
import com.reylar.composepaging3.domain.repo.MoviePaginatedRepository
import com.reylar.composepaging3.network.ApiManager
import com.reylar.composepaging3.network.AuthInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder().add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
                .build()
        )

    @Singleton
    @Provides
    fun getRetrofitInstance(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiManager {
        return retrofit.create(ApiManager::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MovieDatabase): MovieDao = moviesDatabase.getMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: MovieDatabase): RemoteKeysDao =
        moviesDatabase.getRemoteKeysDao()

    @Provides
    @Singleton
    fun provideMoviesRequest(
        apiManager: ApiManager,
        movieDb: MovieDatabase,
    ): MoviePaginatedRepository {
        return MoviePaginatedRepositoryImpl(apiManager = apiManager, movieDb = movieDb)
    }


}