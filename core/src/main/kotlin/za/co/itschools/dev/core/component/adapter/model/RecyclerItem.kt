package za.co.itschools.dev.core.component.adapter.model

import android.view.View
import androidx.viewbinding.ViewBinding
import za.co.itschools.dev.core.component.adapter.model.contract.IRecyclerItem

/**
 * Recycler item abstract to support view binding
 *
 * @see IRecyclerItem
 */
abstract class RecyclerItem<B : ViewBinding> : IRecyclerItem {

    protected var binding: B? = null

    @Throws(IllegalArgumentException::class)
    fun requireBinding(): B = requireNotNull(binding)

    /**
     * Called when the view needs to be recycled for reuse, clear any held references
     * to objects, stop any asynchronous work, e.t.c
     */
    override fun unbind(view: View) {
        binding = null
    }
}