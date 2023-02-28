package com.alacrity.randomUsers.di

import com.alacrity.randomUsers.repository.Repository
import com.alacrity.randomUsers.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl): Repository

}