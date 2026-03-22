package shop.youandmecreative.app.di

import shop.youandmecreative.app.ui.viewmodel.AppViewModel
import shop.youandmecreative.app.ui.viewmodel.CartViewModel
import shop.youandmecreative.app.ui.viewmodel.CheckoutViewModel
import shop.youandmecreative.app.ui.viewmodel.YNMCROnboardingVM
import shop.youandmecreative.app.ui.viewmodel.OrderViewModel
import shop.youandmecreative.app.ui.viewmodel.ProductDetailsViewModel
import shop.youandmecreative.app.ui.viewmodel.ProductViewModel
import shop.youandmecreative.app.ui.viewmodel.YNMCRSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        YNMCRSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        YNMCROnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}