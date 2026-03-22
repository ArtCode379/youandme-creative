package shop.youandmecreative.app.ui.composable.screen.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shop.youandmecreative.app.R
import shop.youandmecreative.app.data.model.Product
import shop.youandmecreative.app.ui.composable.shared.YNMCRContentWrapper
import shop.youandmecreative.app.ui.composable.shared.YNMCREmptyView
import shop.youandmecreative.app.ui.state.DataUiState
import shop.youandmecreative.app.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier) {

        YNMCRContentWrapper(
            dataState = productState,

            dataPopulated = {

            },

            dataEmpty = {
                YNMCREmptyView(
                    primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}