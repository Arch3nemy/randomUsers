package com.alacrity.randomUsers.di

import com.alacrity.randomUsers.use_cases.*
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindLoadUsersUseCase(impl: LoadUsersUseCaseImpl): LoadUsersUseCase

    @Binds
    fun bindGetUsersForDBUseCase(impl: GetUsersFromDatabaseUseCaseImpl): GetUsersFromDatabaseUseCase

    @Binds
    fun bindRemoveUserFromDBUseCase(impl: RemoveUserFromDatabaseUseCaseImpl): RemoveUserFromDatabaseUseCase

    @Binds
    fun bindSaveUserToDBUseCase(impl: SaveUsersToDatabaseUseCaseImpl): SaveUsersToDatabaseUseCase

}