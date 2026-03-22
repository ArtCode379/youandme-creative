package shop.youandmecreative.app.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.youandmecreative.app.R
import shop.youandmecreative.app.data.model.Product
import shop.youandmecreative.app.data.model.ProductCategory
import shop.youandmecreative.app.ui.composable.shared.YNMCRContentWrapper
import shop.youandmecreative.app.ui.composable.shared.YNMCREmptyView
import shop.youandmecreative.app.ui.state.DataUiState
import shop.youandmecreative.app.ui.theme.Accent
import shop.youandmecreative.app.ui.theme.Divider
import shop.youandmecreative.app.ui.theme.GradientEnd
import shop.youandmecreative.app.ui.theme.GradientStart
import shop.youandmecreative.app.ui.theme.MutedText
import shop.youandmecreative.app.ui.theme.OnSurface
import shop.youandmecreative.app.ui.theme.Primary
import shop.youandmecreative.app.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private data class InspirationArticle(
    val title: String,
    val imageRes: Int,
)

private val inspirationArticles = listOf(
    InspirationArticle("5 Ways to Style Your Living Room", R.drawable.carousel1),
    InspirationArticle("The Art of Layering Textures", R.drawable.carousel2),
    InspirationArticle("Creating Cozy Corners", R.drawable.carousel3),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        YNMCRContentWrapper(
            dataState = productsState,
            dataPopulated = {
                val products = (productsState as DataUiState.Populated<List<Product>>).data
                HomePopulatedContent(
                    products = products,
                    onNavigateToProductDetails = onNavigateToProductDetails,
                )
            },
            dataEmpty = {
                YNMCREmptyView(
                    primaryText = stringResource(R.string.products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun HomePopulatedContent(
    products: List<Product>,
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    val filteredProducts = if (selectedCategory == null) products
        else products.filter { it.category == selectedCategory }
    val featuredProducts = products.take(4)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Hero Carousel
        item(span = { GridItemSpan(2) }) {
            HeroCarousel(
                products = featuredProducts,
                onProductClick = onNavigateToProductDetails,
            )
        }

        // Category chips
        item(span = { GridItemSpan(2) }) {
            CategoryChips(
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
            )
        }

        // Inspiration section
        item(span = { GridItemSpan(2) }) {
            InspirationSection()
        }

        // Section header
        item(span = { GridItemSpan(2) }) {
            Text(
                text = stringResource(R.string.home_all_products),
                style = MaterialTheme.typography.titleLarge,
                color = OnSurface,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            )
        }

        // Product grid
        items(filteredProducts) { product ->
            ProductCard(
                product = product,
                onClick = { onNavigateToProductDetails(product.id) },
            )
        }

        // Bottom spacing
        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun HeroCarousel(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { products.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)
            val next = (pagerState.currentPage + 1) % products.size
            pagerState.animateScrollToPage(next)
        }
    }

    Column {
        Spacer(modifier = Modifier.height(8.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp)),
        ) { page ->
            val product = products[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onProductClick(product.id) }
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                                startY = 100f,
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = product.title,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "£%.2f".format(product.price),
                        color = Color(0xFFE5C864),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }

        // Dot indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(products.size) { index ->
                val color = if (pagerState.currentPage == index) Primary else Divider
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}

@Composable
private fun CategoryChips(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory?) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { onCategorySelected(null) },
                    label = { Text(stringResource(R.string.category_all), fontSize = 13.sp) },
                    shape = RoundedCornerShape(10.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Primary,
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = OnSurface,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Divider,
                        selectedBorderColor = Primary,
                        enabled = true,
                        selected = selectedCategory == null,
                    ),
                )
            }
            items(ProductCategory.entries) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { onCategorySelected(category) },
                    label = { Text(stringResource(category.titleRes), fontSize = 13.sp) },
                    shape = RoundedCornerShape(10.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Primary,
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = OnSurface,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Divider,
                        selectedBorderColor = Primary,
                        enabled = true,
                        selected = selectedCategory == category,
                    ),
                )
            }
        }
    }
}

@Composable
private fun InspirationSection() {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.home_inspiration),
            style = MaterialTheme.typography.titleLarge,
            color = OnSurface,
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(inspirationArticles) { article ->
                Card(
                    modifier = Modifier
                        .width(260.dp)
                        .height(150.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = article.imageRes),
                            contentDescription = article.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.55f)
                                        ),
                                        startY = 60f,
                                    )
                                )
                        )
                        Text(
                            text = article.title,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(14.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = stringResource(product.category.titleRes).uppercase(),
                    fontSize = 10.sp,
                    color = Accent,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.5.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = OnSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "£%.2f".format(product.price),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Accent,
                )
            }
        }
    }
}
