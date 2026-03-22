package shop.youandmecreative.app.di

import shop.youandmecreative.app.data.repository.CartRepository
import shop.youandmecreative.app.data.repository.YNMCROnboardingRepo
import shop.youandmecreative.app.data.repository.OrderRepository
import shop.youandmecreative.app.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        YNMCROnboardingRepo(
            ynmcrOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}