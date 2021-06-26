package za.co.itschools.dev.core.component.adapter.model.contract

import android.view.View

/**
 * Contract for recycler item
 */
interface IRecyclerItem {

    /**
     * Called when the [view] needs to be setup, this could be to set click listeners,
     * assign text, load images, e.t.c
     *
     * @param view view that was inflated
     * @param position current position
     * @param payloads optional payloads which maybe empty
     */
    fun bind(
        view: View,
        position: Int,
        payloads: List<Any>,
    )

    /**
     * Called when the view needs to be recycled for reuse, clear any held references
     * to objects, stop any asynchronous work, e.t.c
     */
    fun unbind(view: View)
}