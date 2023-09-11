package com.example.blixtest.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.blixtest.data.FriendsDao
import com.example.blixtest.data.FriendsRepository
import com.example.blixtest.data.FriendsRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideFriendsRepository(friendsDao: FriendsDao): FriendsRepository =
        FriendsRepositoryImpl(friendsDao)

    @Provides
    fun provideFriendsDao(sharedPreferences: SharedPreferences, gson: Gson): FriendsDao =
        FriendsDao(sharedPreferences, gson)

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("blix_test_shared_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideGson(): Gson = Gson()


}
