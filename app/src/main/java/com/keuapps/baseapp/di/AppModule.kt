package com.keuapps.baseapp.di

import android.content.Context
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.keuapps.baseapp.data.local.NoteDao
import com.keuapps.baseapp.data.local.NoteDatabase
import com.keuapps.baseapp.data.remote.RetrofitAPI
import com.keuapps.baseapp.data.remote.model.Kisiler
import com.keuapps.baseapp.data.remote.repository.BaseRepositoryImpl
import com.keuapps.baseapp.domain.repository.BaseRepository
import com.keuapps.baseapp.ui.home.ContactRecyclerAdapter
import com.keuapps.baseapp.ui.home.HomeFragment
import com.keuapps.baseapp.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase (@ApplicationContext context : Context) = Room.databaseBuilder(
        context, NoteDatabase::class.java,"NoteDB").build()

    @Singleton
    @Provides
    fun injectDao (database: NoteDatabase) = database.noteDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI () : RetrofitAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : NoteDao, api: RetrofitAPI) = BaseRepositoryImpl(api,dao) as BaseRepository

}