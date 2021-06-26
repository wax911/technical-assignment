package za.co.itschools.dev.core.component.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import za.co.itschools.dev.core.component.adapter.holder.contract.ICoreViewHolder
import za.co.itschools.dev.core.component.adapter.model.contract.IRecyclerItem

/**
 * General purpose recycler view holder to simplify construction of views
 *
 * @see ICoreViewHolder
 * @since 1.3.0
 */
open class CoreViewHolder(
    binding: ViewBinding
) : ICoreViewHolder, RecyclerView.ViewHolder(binding.root) {

    private var recyclerItem: IRecyclerItem? = null

    /**
     * Load images, text, buttons, etc. in this method from the given parameter
     */
    override fun bind(position: Int, payloads: List<Any>, model: IRecyclerItem) {
        recyclerItem = model
        model.bind(itemView, position, payloads)
    }

    /**
     * Clear or unbind any references the views might be using, e.g. image loading
     * libraries, data binding, callbacks e.t.c
     */
    override fun onViewRecycled() {
        recyclerItem?.unbind(itemView)
        recyclerItem = null
    }
}