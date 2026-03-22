package shop.youandmecreative.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shop.youandmecreative.app.data.repository.YNMCROnboardingRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class YNMCROnboardingVM(
    private val onboardingRepository: YNMCROnboardingRepo,
) : ViewModel() {
    private val _onboardingSetState = MutableStateFlow(false)
    val onboardingSetState: StateFlow<Boolean>
        get() = _onboardingSetState.asStateFlow()

    fun setOnboarded() {
        viewModelScope.launch {
            onboardingRepository.setOnboardingState(true)
            _onboardingSetState.update { true }
        }
    }
}