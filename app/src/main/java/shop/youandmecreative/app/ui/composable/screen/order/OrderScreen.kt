package shop.youandmecreative.app.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.youandmecreative.app.R
import shop.youandmecreative.app.data.entity.OrderEntity
import shop.youandmecreative.app.ui.composable.shared.YNMCRContentWrapper
import shop.youandmecreative.app.ui.composable.shared.YNMCREmptyView
import shop.youandmecreative.app.ui.state.DataUiState
import shop.youandmecreative.app.ui.theme.Accent
import shop.youandmecreative.app.ui.theme.Divider
import shop.youandmecreative.app.ui.theme.MutedText
import shop.youandmecreative.app.ui.theme.OnSurface
import shop.youandmecreative.app.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    YNMCRContentWrapper(
        dataState = ordersState,
        modifier = modifier,

        dataPopulated = {
            val orders = (ordersState as DataUiState.Populated<List<OrderEntity>>).data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 16.dp),
            ) {
                items(orders, key = { it.orderNumber }) { order ->
                    OrderCard(order = order)
                }
            }
        },

        dataEmpty = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Receipt,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Divider,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.orders_state_empty_primary_text),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = OnSurface,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.orders_state_empty_secondary_text),
                    fontSize = 14.sp,
                    color = MutedText,
                )
            }
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_number, order.orderNumber),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = OnSurface,
                )

                // Status chip
                Card(
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(containerColor = Accent.copy(alpha = 0.15f)),
                ) {
                    Text(
                        text = stringResource(R.string.order_status_reserved),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Accent,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.timestamp.format(formatter),
                fontSize = 13.sp,
                color = MutedText,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.description,
                fontSize = 13.sp,
                color = MutedText,
                lineHeight = 18.sp,
                maxLines = 3,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.order_customer, order.customerFirstName, order.customerLastName),
                    fontSize = 13.sp,
                    color = MutedText,
                )
                Text(
                    text = "£%.2f".format(order.price),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Accent,
                )
            }
        }
    }
}
