package com.oxygen.data.di

import dagger.Module

@Module(includes = [NetworkModule::class, RepositoriesModule::class])
class DataModule