package za.co.itschools.dev.core.component.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * A helper contract for view model items
 *
 * @property model observable model from a use case output
 * @property isLoading observable loading state from underlying sources
 * @property error observable error state from underlying sources
 *
 * @param T type of your [model]
 */
abstract class AbstractViewModel<T> : ViewModel() {

    abstract val model: LiveData<T>
    abstract val isLoading: LiveData<Boolean>
    abstract val error: LiveData<Throwable>
}