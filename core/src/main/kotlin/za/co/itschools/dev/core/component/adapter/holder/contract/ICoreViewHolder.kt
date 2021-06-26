package za.co.itschools.dev.core.component.adapter.holder.contract

import za.co.itschools.dev.core.component.adapter.model.contract.IRecyclerItem

/**
 * General purpose recycler view holder to simplify construction of views
 */
interface ICoreViewHolder {

    /**
     * Load images, text, buttons, etc. in this method from the given parameter
     */
    fun bind(
        position: Int,
        payloads: List<Any>,
        model: IRecyclerItem
    )

    /**
     * Clear or unbind any references the views might be using, e.g. image loading
     * libraries, data binding, callbacks e.t.c
     */
    fun onViewRecycled()
}