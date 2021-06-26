package za.co.itschools.dev.core.ui

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.KoinScopeComponent
import za.co.itschools.dev.core.ui.model.FragmentItem

/**
 * Get given dependency
 *
 * @param qualifier - bean qualifier / optional
 * @param parameters - injection parameters
 */
inline fun <reified T : Any> KoinScopeComponent.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = runCatching {
    scope.get<T>(qualifier, parameters)
}.getOrElse {
    getKoin().get(qualifier, parameters)
}

/**
 * Inject lazily
 *
 * @param qualifier - bean qualifier / optional
 * @param parameters - injection parameters
 */
inline fun <reified T : Any> KoinScopeComponent.inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy(LazyThreadSafetyMode.NONE) { get<T>(qualifier, parameters) }

/**
 * Checks for existing fragment in [FragmentManager], if one exists that is used otherwise
 * a new instance is created.
 *
 * @return tag of the fragment
 *
 * @see androidx.fragment.app.commit
 */
fun FragmentItem<*>.commit(
    @IdRes contentFrame: Int,
    fragmentActivity: FragmentActivity
) : String? {
    val fragmentManager = fragmentActivity.supportFragmentManager

    val fragmentTag = tag()
    val backStack = fragmentManager.findFragmentByTag(fragmentTag)

    fragmentManager.commit {
        backStack?.let {
            replace(contentFrame, it, fragmentTag)
        } ?: replace(contentFrame, fragment, parameter, fragmentTag)
    }

    return fragmentTag
}

/**
 * Checks for existing fragment in [FragmentManager], if one exists that is used otherwise
 * a new instance is created.
 *
 * @return tag of the fragment
 *
 * @see androidx.fragment.app.commit
 */
fun <T: Fragment> FragmentItem<T>.commit(
    contentFrame: View,
    fragmentActivity: FragmentActivity,
) = commit(contentFrame.id, fragmentActivity)
