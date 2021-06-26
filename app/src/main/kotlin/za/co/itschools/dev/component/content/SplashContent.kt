package za.co.itschools.dev.component.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import za.co.itschools.dev.R
import za.co.itschools.dev.component.viewmodel.SplashViewModel
import za.co.itschools.dev.core.component.content.AbstractContent
import za.co.itschools.dev.data.settings.IAuthenticationSetting
import za.co.itschools.dev.databinding.SplashContentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import za.co.itschools.dev.navigation.EnrolmentRouter
import za.co.itschools.dev.navigation.LoginRouter
import za.co.itschools.dev.navigation.extensions.startActivity

class SplashContent(
    private val settings: IAuthenticationSetting
) : AbstractContent<SplashContentBinding>(R.layout.splash_content) {

    private val viewModel by viewModel<SplashViewModel>()

    /**
     * Stub to trigger the loading of data, by default this is only called
     *
     * This is called when the fragment reaches it's [onResume] state
     */
    override fun onFetchDataInitialize() {
        if (settings.isAuthenticated.value) {
            viewModel()
        } else {
            LoginRouter.startActivity(context)
            activity?.finish()
        }
    }

    /**
     * Invoke view model observer to watch for changes, this will be called
     * called in [onViewCreated]
     */
    override fun setUpViewModelObserver() {
        viewModel.model.observe(viewLifecycleOwner) {
            if (it)
                EnrolmentRouter.startActivity(context)
            else LoginRouter.startActivity(context)
            activity?.finish()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {

        }
        viewModel.error.observe(viewLifecycleOwner) {
            val snackBar = Snackbar.make(
                requireView(),
                "${it.message}",
                Snackbar.LENGTH_INDEFINITE
            )

            snackBar.setAction(R.string.action_retry) {
                viewModel()
                snackBar.dismiss()
            }

            snackBar.show()
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
        binding = view?.let { SplashContentBinding.bind(it) }
        return view
    }
}