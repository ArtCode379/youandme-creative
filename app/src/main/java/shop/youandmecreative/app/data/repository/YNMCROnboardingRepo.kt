package shop.youandmecreative.app.data.repository

import shop.youandmecreative.app.data.datastore.YNMCROnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class YNMCROnboardingRepo(
    private val ynmcrOnboardingStoreManager: YNMCROnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ynmcrOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ynmcrOnboardingStoreManager.setOnboardedState(state)
        }
    }
}