package shop.youandmecreative.app.data.repository

import shop.youandmecreative.app.data.dao.CartItemDao
import shop.youandmecreative.app.data.entity.CartItemEntity
import shop.youandmecreative.app.data.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CartRepository(
    private val cartItemDao: CartItemDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeAll(): Flow<List<CartItemEntity>> {
        return cartItemDao.observeAll()
    }

    suspend fun getAll(): List<CartItemEntity> {
        return cartItemDao.getAll()
    }

    suspend fun deleteById(id: Int) {
        withContext(coroutineDispatcher) {
            cartItemDao.deleteById(id)
        }
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            cartItemDao.deleteAll()
        }
    }

    suspend fun incrementQuantity(productId: Int) {
        withContext(coroutineDispatcher) {
            cartItemDao.incrementQuantity(productId)
        }
    }

    suspend fun incrementProductQuantityOrAdd(product: Product) {
        withContext(coroutineDispatcher) {
            cartItemDao.incrementProductQuantityOrAdd(product)
        }
    }

    suspend fun decrementProductQuantityOrRemove(product: Product) {
        withContext(coroutineDispatcher) {
            cartItemDao.decrementProductQuantityOrRemove(product)
        }
    }
}