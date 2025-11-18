package com.example.fantastika.LoginRegister.Di

import com.apollographql.apollo.ApolloClient
import com.example.fantastika.LoginRegister.Data.AuthRepository
import com.example.fantastika.LoginRegister.Data.AuthRepositoryImpl
import com.example.fantastika.LoginRegister.Domain.LoginUseCase
import com.example.fantastika.LoginRegister.Domain.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

// Example: Data Module
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apolloClient: ApolloClient): AuthRepository {
        return AuthRepositoryImpl(apolloClient)
    }
}

// Example: Domain Module
@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }
}