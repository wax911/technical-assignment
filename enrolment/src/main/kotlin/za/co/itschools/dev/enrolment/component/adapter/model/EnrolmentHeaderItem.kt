package za.co.itschools.dev.enrolment.component.adapter.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import za.co.itschools.dev.core.component.adapter.holder.CoreViewHolder
import za.co.itschools.dev.core.component.adapter.model.RecyclerItem
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import za.co.itschools.dev.enrolment.databinding.EnrolmentHeaderBinding

internal class EnrolmentHeaderItem(
    private val entity: Enrolment.Header
) : RecyclerItem<EnrolmentHeaderBinding>() {

    /**
     * Called when the [view] needs to be setup, this could be to set click listeners,
     * assign text, load images, e.t.c
     *
     * @param view view that was inflated
     * @param position current position
     * @param payloads optional payloads which maybe empty
     */
    override fun bind(view: View, position: Int, payloads: List<Any>) {
        binding = EnrolmentHeaderBinding.bind(view)
        requireBinding().enrolmentHeaderText.text = entity.grade
    }

    /**
     * Called when the view needs to be recycled for reuse, clear any held references
     * to objects, stop any asynchronous work, e.t.c
     */
    override fun unbind(view: View) {
        super.unbind(view)
        // Nothing to unbind or clear here
    }

    companion object {
        fun createViewHolder(
            layoutInflater: LayoutInflater,
            viewGroup: ViewGroup
        ) = EnrolmentHeaderBinding.inflate(
            layoutInflater, viewGroup, false
        ).let { CoreViewHolder(it) }
    }
}