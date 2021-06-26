package za.co.itschools.dev.enrolment.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import za.co.itschools.dev.core.component.adapter.AbstractAdapter
import za.co.itschools.dev.core.component.adapter.holder.CoreViewHolder
import za.co.itschools.dev.core.component.adapter.model.contract.IRecyclerItem
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import za.co.itschools.dev.enrolment.R
import za.co.itschools.dev.enrolment.component.adapter.model.EnrolmentHeaderItem
import za.co.itschools.dev.enrolment.component.adapter.model.EnrolmentItem

class EnrolmentAdapter(
    override val mapper: (Enrolment) -> IRecyclerItem = { entity ->
        when (entity) {
            is Enrolment.Header -> EnrolmentHeaderItem(entity)
            is Enrolment.Item -> EnrolmentItem(entity)
        }
    }
) : AbstractAdapter<Enrolment>(DIFF_UTIL) {

    /**
     * Return the view type of the item at `position` for the purposes
     * of view recycling.
     *
     *
     * The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * `position`. Type codes need not be contiguous.
     */
    override fun getItemViewType(position: Int): Int {
        return when (requireItem(position)) {
            is Enrolment.Header -> R.layout.enrolment_header
            is Enrolment.Item -> R.layout.enrolment_item
        }
    }

    /**
     * Should provide the required view holder, this function is a substitute for
     * [androidx.recyclerview.widget.RecyclerView.Adapter.onCreateViewHolder] which now
     * has extended functionality
     */
    override fun createDefaultViewHolder(
        parent: ViewGroup,
        viewType: Int,
        layoutInflater: LayoutInflater
    ): CoreViewHolder {
        return when (viewType) {
            R.layout.enrolment_header ->
                EnrolmentHeaderItem.createViewHolder(layoutInflater, parent)
            else ->
                EnrolmentItem.createViewHolder(layoutInflater, parent)
        }
    }

    private companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Enrolment>() {
            /**
             * Called to check whether two objects represent the same item.
             *
             * For example, if your items have unique ids, this method should check their id equality.
             *
             * Note: `null` items in the list are assumed to be the same as another `null`
             * item and are assumed to not be the same as a non-`null` item. This callback will
             * not be invoked for either of those cases.
             *
             * @param oldItem The item in the old list.
             * @param newItem The item in the new list.
             * @return True if the two items represent the same object or false if they are different.
             * @see Callback.areItemsTheSame
             */
            override fun areItemsTheSame(oldItem: Enrolment, newItem: Enrolment): Boolean {
                return oldItem == newItem
            }

            /**
             * Called to check whether two items have the same data.
             *
             * This information is used to detect if the contents of an item have changed.
             *
             * This method to check equality instead of [Object.equals] so that you can
             * change its behavior depending on your UI.
             *
             * For example, if you are using DiffUtil with a
             * [RecyclerView.Adapter], you should
             * return whether the items' visual representations are the same.
             *
             *
             * This method is called only if [.areItemsTheSame] returns `true` for
             * these items.
             *
             *
             * Note: Two `null` items are assumed to represent the same contents. This callback
             * will not be invoked for this case.
             *
             * @param oldItem The item in the old list.
             * @param newItem The item in the new list.
             * @return True if the contents of the items are the same or false if they are different.
             * @see Callback.areContentsTheSame
             */
            override fun areContentsTheSame(oldItem: Enrolment, newItem: Enrolment): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            /**
             * When [.areItemsTheSame] returns `true` for two items and
             * [.areContentsTheSame] returns false for them, this method is called to
             * get a payload about the change.
             *
             * For example, if you are using DiffUtil with [RecyclerView], you can return the
             * particular field that changed in the item and your
             * [ItemAnimator][RecyclerView.ItemAnimator] can use that
             * information to run the correct animation.
             *
             * Default implementation returns `null`.
             *
             * @see Callback.getChangePayload
             */
            override fun getChangePayload(oldItem: Enrolment, newItem: Enrolment): Any? {
                return newItem
            }
        }
    }
}