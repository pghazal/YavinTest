package com.pghaz.yavintest.di

import android.content.Context
import androidx.room.Room
import com.pghaz.yavintest.database.TicketsDatabase
import com.pghaz.yavintest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TicketsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TicketsDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: TicketsDatabase) = database.ticketsDao()
}