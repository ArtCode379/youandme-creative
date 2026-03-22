package shop.youandmecreative.app.data.model

import androidx.annotation.StringRes
import shop.youandmecreative.app.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    WALL_ART(R.string.category_wall_art),
    CANDLES_FRAGRANCES(R.string.category_candles_fragrances),
    VASES_PLANTERS(R.string.category_vases_planters),
    TEXTILES(R.string.category_textiles),
    DECORATIVE_ACCESSORIES(R.string.category_decorative_accessories),
}
