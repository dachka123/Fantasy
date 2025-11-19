package com.example.fantastika.PlayerSelection.DI

import com.apollographql.apollo.ApolloClient
import com.example.fantastika.PlayerSelection.Data.ApolloPlayerClient
import com.example.fantastika.PlayerSelection.Data.TeamSelection.ApolloTeamRepository
import com.example.fantastika.PlayerSelection.Data.TeamSelection.TeamRepository
import com.example.fantastika.PlayerSelection.Domain.GetPlayersUseCase
import com.example.fantastika.PlayerSelection.Domain.PlayerClient
import com.example.fantastika.PlayerSelection.Domain.TeamSelection.LoadTeamUseCase
import com.example.fantastika.PlayerSelection.Domain.TeamSelection.SaveTeamUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerSelectionModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:3000/graphql")
            //.serverUrl("http://localhost:3000/graphql")
            //.serverUrl("http://192.168.1.10:3000/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun providePlayerClient(apolloClient: ApolloClient): PlayerClient{
        return ApolloPlayerClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetPlayersUseCase(playerClient: PlayerClient): GetPlayersUseCase{
        return GetPlayersUseCase(playerClient)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(apolloClient: ApolloClient): TeamRepository {
        return ApolloTeamRepository(apolloClient)
    }

    @Provides
    @Singleton
    fun provideSaveTeamUseCase(repository: TeamRepository): SaveTeamUseCase {
        return SaveTeamUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadTeamUseCase(repository: TeamRepository): LoadTeamUseCase {
        return LoadTeamUseCase(repository)
    }
}