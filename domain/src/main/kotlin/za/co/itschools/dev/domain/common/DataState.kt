package za.co.itschools.dev.domain.common

import kotlinx.coroutines.flow.Flow

data class DataState<T>(
    val data: Flow<T>,
    val isLoading: Flow<Boolean>,
    val error: Flow<Throwable>
)