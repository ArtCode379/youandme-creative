package shop.youandmecreative.app.ui.state

import androidx.annotation.DrawableRes

data class CartItemUiState(
    val productId: Int,
    val productTitle: String,
    val productPrice: Double,
    val quantity: Int,
    @field:DrawableRes val productImageRes: Int? = null,
)