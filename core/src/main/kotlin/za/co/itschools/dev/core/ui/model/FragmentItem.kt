package za.co.itschools.dev.core.ui.model

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Fragment loader holder
 */
data class FragmentItem<T: Fragment>(
    val fragment: Class<out T>,
    val parameter: Bundle? = null,
    val tag: String = fragment.simpleName
) {
    fun tag(): String = tag
}