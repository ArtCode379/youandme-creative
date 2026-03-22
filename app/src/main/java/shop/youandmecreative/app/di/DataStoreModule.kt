package shop.youandmecreative.app.di

import shop.youandmecreative.app.data.datastore.YNMCROnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { YNMCROnboardingPrefs(androidContext()) }
}