package shop.youandmecreative.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shop.youandmecreative.app.data.dao.CartItemDao
import shop.youandmecreative.app.data.dao.OrderDao
import shop.youandmecreative.app.data.database.converter.Converters
import shop.youandmecreative.app.data.entity.CartItemEntity
import shop.youandmecreative.app.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class YNMCRDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}