package shop.youandmecreative.app.ui.composable.screen.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shop.youandmecreative.app.R
import shop.youandmecreative.app.ui.composable.shared.YNMCRContentWrapper
import shop.youandmecreative.app.ui.composable.shared.YNMCREmptyView
import shop.youandmecreative.app.ui.state.CartItemUiState
import shop.youandmecreative.app.ui.state.DataUiState
import shop.youandmecreative.app.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    val onPlusItemClick = { itemId: Int ->
        viewModel.incrementProductInCart(itemId)
    }

    val onMinusItemClick = { itemId: Int ->
        viewModel.decrementItemInCart(itemId)
    }

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = onPlusItemClick,
        onMinusItemClick = onMinusItemClick,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(modifier = modifier) {

        YNMCRContentWrapper(
            dataState = cartItemsState,

            dataPopulated = {

            },

            dataEmpty = {
                YNMCREmptyView(
                    primaryText = stringResource(R.string.cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}