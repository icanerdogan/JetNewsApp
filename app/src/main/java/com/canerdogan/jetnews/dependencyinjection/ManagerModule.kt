package com.canerdogan.jetnews.dependencyinjection

import android.app.Application
import com.canerdogan.jetnews.data.manager.LocalUserManagerImpl
import com.canerdogan.jetnews.domain.manager.LocalUserManager
import com.canerdogan.jetnews.domain.usecases.manager.AppEntryUseCase
import com.canerdogan.jetnews.domain.usecases.manager.ReadAppEntry
import com.canerdogan.jetnews.domain.usecases.manager.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}