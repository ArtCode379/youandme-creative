package shop.youandmecreative.app.ui.composable.screen.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.youandmecreative.app.R
import shop.youandmecreative.app.data.model.Product
import shop.youandmecreative.app.ui.composable.shared.YNMCRContentWrapper
import shop.youandmecreative.app.ui.composable.shared.YNMCREmptyView
import shop.youandmecreative.app.ui.state.DataUiState
import shop.youandmecreative.app.ui.theme.Accent
import shop.youandmecreative.app.ui.theme.MutedText
import shop.youandmecreative.app.ui.theme.OnSurface
import shop.youandmecreative.app.ui.theme.Primary
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
                val product = (productState as DataUiState.Populated<Product>).data
                ProductDetailsPopulated(
                    product = product,
                    onAddToCart = onAddToCart,
                )
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

@Composable
private fun ProductDetailsPopulated(
    product: Product,
    onAddToCart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero image
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Category chip
            Text(
                text = stringResource(product.category.titleRes).uppercase(),
                fontSize = 11.sp,
                color = Accent,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Product name
            Text(
                text = product.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = OnSurface,
                letterSpacing = 0.5.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Price
            Text(
                text = "£%.2f".format(product.price),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Accent,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Description
            Text(
                text = product.description,
                fontSize = 14.sp,
                color = MutedText,
                lineHeight = 22.sp,
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Add to Cart button
            Button(
                onClick = onAddToCart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
            ) {
                Text(
                    text = stringResource(R.string.button_add_to_cart_label),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
