package shop.youandmecreative.app.data.repository

import shop.youandmecreative.app.R
import shop.youandmecreative.app.data.model.Product
import shop.youandmecreative.app.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Abstract Horizon Canvas",
            description = "Hand-painted abstract canvas featuring soft earth tones and gold leaf accents. Perfect statement piece for living rooms.",
            category = ProductCategory.WALL_ART,
            price = 89.99,
            imageRes = R.drawable.product1,
        ),
        Product(
            id = 2,
            title = "Lavender Dreams Candle Set",
            description = "Set of 3 soy wax candles with natural lavender, vanilla, and eucalyptus essential oils in ceramic holders.",
            category = ProductCategory.CANDLES_FRAGRANCES,
            price = 34.99,
            imageRes = R.drawable.product2,
        ),
        Product(
            id = 3,
            title = "Nordic Ceramic Vase",
            description = "Minimalist matte ceramic vase in cream white. Elegant curves inspired by Scandinavian design.",
            category = ProductCategory.VASES_PLANTERS,
            price = 42.50,
            imageRes = R.drawable.product3,
        ),
        Product(
            id = 4,
            title = "Woven Linen Throw Pillow",
            description = "Handwoven linen cushion cover with geometric pattern in terracotta and ivory. Insert included.",
            category = ProductCategory.TEXTILES,
            price = 28.99,
            imageRes = R.drawable.product4,
        ),
        Product(
            id = 5,
            title = "Botanical Print Set",
            description = "Collection of 3 watercolor botanical prints on premium archival paper. Framing not included.",
            category = ProductCategory.WALL_ART,
            price = 55.00,
            imageRes = R.drawable.product5,
        ),
        Product(
            id = 6,
            title = "Amber Noir Reed Diffuser",
            description = "Luxury reed diffuser with amber, black pepper, and sandalwood notes. Lasts up to 8 weeks.",
            category = ProductCategory.CANDLES_FRAGRANCES,
            price = 29.99,
            imageRes = R.drawable.product6,
        ),
        Product(
            id = 7,
            title = "Terrazzo Plant Pot",
            description = "Handmade terrazzo-style planter with drainage hole. Suitable for small to medium houseplants.",
            category = ProductCategory.VASES_PLANTERS,
            price = 36.00,
            imageRes = R.drawable.product7,
        ),
        Product(
            id = 8,
            title = "Moroccan Woven Basket",
            description = "Handwoven seagrass basket with leather handles. Perfect for storage or as a decorative piece.",
            category = ProductCategory.DECORATIVE_ACCESSORIES,
            price = 45.00,
            imageRes = R.drawable.product8,
        ),
        Product(
            id = 9,
            title = "Velvet Table Runner",
            description = "Rich velvet table runner in deep emerald green with gold-stitched edges. 72 inches long.",
            category = ProductCategory.TEXTILES,
            price = 38.50,
            imageRes = R.drawable.product9,
        ),
        Product(
            id = 10,
            title = "Geometric Brass Wall Clock",
            description = "Modern wall clock with brass geometric frame and silent quartz movement. 14-inch diameter.",
            category = ProductCategory.DECORATIVE_ACCESSORIES,
            price = 62.00,
            imageRes = R.drawable.product10,
        ),
        Product(
            id = 11,
            title = "Sage & Cedar Wax Melts",
            description = "Pack of 12 hand-poured soy wax melts with sage, cedar, and a hint of citrus.",
            category = ProductCategory.CANDLES_FRAGRANCES,
            price = 18.99,
            imageRes = R.drawable.product11,
        ),
        Product(
            id = 12,
            title = "Handblown Glass Orb Set",
            description = "Set of 3 handblown glass decorative orbs in graduated sizes. Iridescent blue-gold finish.",
            category = ProductCategory.DECORATIVE_ACCESSORIES,
            price = 49.99,
            imageRes = R.drawable.product12,
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}
