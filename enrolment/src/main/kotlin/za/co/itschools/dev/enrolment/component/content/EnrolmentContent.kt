package za.co.itschools.dev.enrolment.component.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import za.co.itschools.dev.core.component.adapter.AbstractAdapter
import za.co.itschools.dev.core.component.content.AbstractContent
import za.co.itschools.dev.domain.enrolment.entity.Enrolment
import za.co.itschools.dev.enrolment.R
import za.co.itschools.dev.enrolment.component.content.viewmodel.EnrolmentContentViewModel
import za.co.itschools.dev.enrolment.databinding.EnrolmentContentBinding
import za.co.itschools.dev.navigation.EnrolmentRouter
import za.co.itschools.dev.navigation.extensions.startActivity

class EnrolmentContent(
    private val contentAdapter: AbstractAdapter<Enrolment>
) : AbstractContent<EnrolmentContentBinding>(R.layout.enrolment_content) {

    private val viewModel by viewModel<EnrolmentContentViewModel>()

    /**
     * Stub to trigger the loading of data, by default this is only called
     *
     * This is called when the fragment reaches it's [onResume] state
     */
    override fun onFetchDataInitialize() {
        viewModel()
    }

    /**
     * Invoke view model observer to watch for changes, this will be called
     * called in [onViewCreated]
     */
    override fun setUpViewModelObserver() {
        viewModel.model.observe(viewLifecycleOwner) {
            contentAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            requireBinding().stateLayout.onLoadStateChanged(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            requireBinding().stateLayout.onErrorStateChanged(it) {
                onFetchDataInitialize()
            }
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null. This will be called between
     * [.onCreate] and [.onViewCreated].
     *
     * A default View can be returned by calling [.Fragment] in your
     * constructor. Otherwise, this method returns null.
     *
     *
     * It is recommended to **only** inflate the layout in this method and move
     * logic that operates on the returned View to [.onViewCreated].
     *
     *
     * If you return a View from here, you will later be called in
     * [.onDestroyView] when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = view?.let { EnrolmentContentBinding.bind(it) }
        return view
    }

    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().stateLayout.onLoadStateChanged(false)
        with (requireBinding().recycler) {
            setHasFixedSize(true)
            if (adapter == null) {
                contentAdapter.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                adapter = contentAdapter
            }
            if (layoutManager == null)
                layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = true
        }
    }
}