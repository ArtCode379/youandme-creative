package shop.youandmecreative.app.di

import androidx.room.Room
import shop.youandmecreative.app.data.database.YNMCRDatabase
import org.koin.dsl.module

private const val DB_NAME = "ynmcr_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = YNMCRDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<YNMCRDatabase>().cartItemDao() }

    single { get<YNMCRDatabase>().orderDao() }
}