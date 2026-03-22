package shop.youandmecreative.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shop.youandmecreative.app.data.repository.YNMCROnboardingRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class YNMCRSplashVM(
    private val onboardingRepository: YNMCROnboardingRepo,
) : ViewModel() {
    val onboardedState: StateFlow<Boolean> =
        onboardingRepository.observeOnboardingState()
            .map { it == true }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = false
            )

}