package shop.youandmecreative.app

import android.app.Application
import shop.youandmecreative.app.di.dataModule
import shop.youandmecreative.app.di.dispatcherModule
import shop.youandmecreative.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class YNMCRApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@YNMCRApp)
            modules(appModules)
        }
    }
}