package za.co.itschools.dev.core.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import za.co.itschools.dev.core.component.adapter.holder.CoreViewHolder
import za.co.itschools.dev.core.component.adapter.model.contract.IRecyclerItem
import za.co.itschools.dev.core.extensions.layoutInflater

/**
 * Core implementation for handling complex logic for [List]s and
 * [androidx.recyclerview.widget.RecyclerView.ViewHolder] binding logic
 *
 * @param differCallback Callback for calculating the diff between two non-null items in a list.
 */
abstract class AbstractAdapter<T>(
    differCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, CoreViewHolder>(differCallback) {

    /**
     * Mapper for adapters to converting models to recycler items
     */
    abstract val mapper: (T) -> IRecyclerItem

    /**
     * Should provide the required view holder, this function is a substitute for
     * [androidx.recyclerview.widget.RecyclerView.Adapter.onCreateViewHolder] which now
     * has extended functionality
     */
    abstract fun createDefaultViewHolder(
        parent: ViewGroup,
        @LayoutRes viewType: Int,
        layoutInflater: LayoutInflater
    ): CoreViewHolder

    /**
     * Returns the non-nullable item for this adapter
     *
     * @param position The current position of the adapter
     *
     * @throws IllegalArgumentException when the item is null
     * @throws IndexOutOfBoundsException when the [position] is invalid
     */
    protected fun requireItem(position: Int): T {
        return requireNotNull(getItem(position))
    }

    /**
     * Binds view holder by view type at [position]
     */
    private fun bindViewHolderByType(
        holder: CoreViewHolder,
        position: Int,
        payloads: List<Any> = emptyList()
    ) {
        runCatching {
            if (position != RecyclerView.NO_POSITION) {
                val recyclerItem: IRecyclerItem = mapper(requireItem(position))
                holder.bind(
                    position = position,
                    payloads = payloads,
                    model = recyclerItem,
                )
            }
        }.onFailure { throwable ->
            Timber.w(
                throwable,
                "holder: $holder, position: $position, payloads: [${payloads.joinToString()}]"
            )
        }
    }

    /**
     * Overridden implementation [createDefaultViewHolder] calls to resolve the view holder type
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes viewType: Int
    ): CoreViewHolder {
        val layoutInflater = parent.context.layoutInflater()
        return createDefaultViewHolder(parent, viewType, layoutInflater)
    }

    /**
     * Calls the the recycler view holder to perform view binding,
     * and selection mode decorations are set up
     *
     * @see [CoreViewHolder.bind]
     */
    override fun onBindViewHolder(holder: CoreViewHolder, position: Int) {
        bindViewHolderByType(holder, position)
    }

    /**
     * Calls the the recycler view holder to perform view binding,
     * and selection mode decorations are set up
     *
     * @see [CoreViewHolder.bind]
     */
    override fun onBindViewHolder(
        holder: CoreViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position)
        else
            bindViewHolderByType(holder, position, payloads)
    }

    /**
     * Calls the the recycler view holder impl to perform view recycling
     *
     * @see [CoreViewHolder.onViewRecycled]
     */
    override fun onViewRecycled(holder: CoreViewHolder) {
        holder.onViewRecycled()
    }
}