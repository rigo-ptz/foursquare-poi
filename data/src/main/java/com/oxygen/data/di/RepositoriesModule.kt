package com.oxygen.data.di

import com.oxygen.data.venues.repo.VenuesRepositoryImpl
import com.oxygen.domain.venues.repo.VenuesRepository
import dagger.Binds
import dagger.Module

/**
 * @author Yamushev Igor
 * @since  4/19/21
 */
@Module
abstract class RepositoriesModule {

  @Binds
  abstract fun bindStationsRepo(repo: VenuesRepositoryImpl): VenuesRepository

}