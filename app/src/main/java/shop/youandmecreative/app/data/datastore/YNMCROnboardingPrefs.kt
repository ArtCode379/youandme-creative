package shop.youandmecreative.app.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val YNMCR_PREFS_NAME = "ynmcr_prefs"

val Context.ynmcrOnboardingStore by preferencesDataStore(name = YNMCR_PREFS_NAME)

class YNMCROnboardingPrefs(
    private val context: Context
) {
    val onboardedStateFlow: Flow<Boolean?> = context.ynmcrOnboardingStore.data.map { prefs ->
        prefs[ONBOARDED_STATE_KEY]
    }

    suspend fun setOnboardedState(state: Boolean) {
        context.ynmcrOnboardingStore.edit { prefs ->
            prefs[ONBOARDED_STATE_KEY] = state
        }
    }

    companion object {
        private val ONBOARDED_STATE_KEY = booleanPreferencesKey("onboardedState")
    }
}