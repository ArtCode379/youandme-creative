package shop.youandmecreative.app.data.repository

import shop.youandmecreative.app.data.dao.OrderDao
import shop.youandmecreative.app.data.entity.OrderEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OrderRepository(
    private val orderDao: OrderDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun save(orderEntity: OrderEntity): Long {
        return withContext(coroutineDispatcher) {
            orderDao.save(orderEntity)
        }
    }

    fun observeAll(): Flow<List<OrderEntity>> {
        return orderDao.observeAll()
    }


    suspend fun deleteByNumber(orderNumber: String) {
        withContext(coroutineDispatcher) {
            orderDao.deleteByNumber(orderNumber)
        }
    }
}