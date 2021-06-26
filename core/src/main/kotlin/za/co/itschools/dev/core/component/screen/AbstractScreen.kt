package za.co.itschools.dev.core.component.screen

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.KoinScopeComponent

/**
 * Abstraction for activity which enforces view binding
 */
abstract class AbstractScreen<B: ViewBinding> : AppCompatActivity(),
    CoroutineScope by MainScope(), KoinScopeComponent {

    override val scope by lazy { activityScope() }

    protected var binding: B? = null

    protected fun requireBinding(): B =
        requireNotNull(binding) {
            "Binding has not yet been initialized"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        runCatching {
            setupKoinFragmentFactory(scope)
        }.onFailure {
            setupKoinFragmentFactory()
        }
        super.onCreate(savedInstanceState)
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}