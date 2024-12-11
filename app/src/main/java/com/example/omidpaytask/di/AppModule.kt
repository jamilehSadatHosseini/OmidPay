package com.example.omidpaytask.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.omidpaytask.MyApplication
import com.example.omidpaytask.data.RepositoryImp
import com.example.omidpaytask.data.local.LocalDataSourceImp
import com.example.omidpaytask.data.local.dao.FavoriteDao
import com.example.omidpaytask.data.local.database.ProductDataBase
import com.example.omidpaytask.data.remote.RemoteDataSource
import com.example.omidpaytask.data.remote.RemoteDataSourceImp
import com.example.omidpaytask.data.remote.api.ApiService
import com.example.omidpaytask.data.remote.api.HeaderInterceptorImpl
import com.example.omidpaytask.domain.Repository
import com.example.omidpaytask.data.local.LocalDataSource
import com.example.omidpaytask.domain.useCases.AddToFavoritesUseCase
import com.example.omidpaytask.domain.useCases.DeleteFromFavoritesUseCase
import com.example.omidpaytask.domain.useCases.GetFavoriteProductsID
import com.example.omidpaytask.domain.useCases.GetProductDetailsUseCase
import com.example.omidpaytask.domain.useCases.GetProductsUseCase
import com.example.omidpaytask.domain.useCases.UseCases
import com.example.omidpaytask.data.util.Constants.Base_Url
import com.example.omidpaytask.data.util.Constants.DataBase_Name
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptorImpl): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(headerInterceptor)
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDataBases(application: Application): ProductDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = ProductDataBase::class.java,
            name = DataBase_Name
        ).fallbackToDestructiveMigration().build()

    }

    @Singleton
    @Provides
    fun provideFavoriteDao(dataBase: ProductDataBase) = dataBase.favoriteDao

    @Provides
    @Singleton
    fun provideLocalDataSource(favoriteDao: FavoriteDao): LocalDataSource =
        LocalDataSourceImp(favoriteDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSourceImp(apiService)

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSourceImp: RemoteDataSourceImp
    ): Repository =
        RepositoryImp(localDataSource, remoteDataSourceImp)

    @Provides
    fun provideUseCases(
        getProducts: GetProductsUseCase,
        getProductDetail: GetProductDetailsUseCase,
        getFavoriteProductsIDs: GetFavoriteProductsID,
        addFavorite: AddToFavoritesUseCase,
        deleteFavorite: DeleteFromFavoritesUseCase
    ): UseCases {
        return UseCases(
            getProducts = getProducts,
            getProductDetail = getProductDetail,
            getFavoriteProductsIDs = getFavoriteProductsIDs,
            addFavorite = addFavorite,
            deleteFavorite = deleteFavorite
        )
    }
}
