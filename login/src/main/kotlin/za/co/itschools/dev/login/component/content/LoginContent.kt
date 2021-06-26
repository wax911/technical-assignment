package za.co.itschools.dev.login.component.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel
import za.co.itschools.dev.core.component.content.AbstractContent
import za.co.itschools.dev.domain.login.model.LoginParam
import za.co.itschools.dev.login.R
import za.co.itschools.dev.login.component.content.controller.LoginContentController
import za.co.itschools.dev.login.component.content.viewmodel.LoginContentViewModel
import za.co.itschools.dev.login.databinding.LoginContentBinding
import za.co.itschools.dev.navigation.EnrolmentRouter
import za.co.itschools.dev.navigation.extensions.startActivity

class LoginContent : AbstractContent<LoginContentBinding>(R.layout.login_content) {

    private val resetStateOnBackPressed =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!requireBinding().stateLayout.isContentState)
                    requireBinding().stateLayout.onLoadStateChanged(false)
                else activity?.finish()
            }
        }

    private val viewModel by viewModel<LoginContentViewModel>()

    private val controller = LoginContentController()

    private fun loginUser() {
        if (controller.areInputsValid(requireBinding())) {
            viewModel(
                LoginParam(
                    username = requireNotNull(viewModel.usernameLiveData.value),
                    password = requireNotNull(viewModel.passwordLiveData.value)
                )
            )
        }
    }

    /**
     * Stub to trigger the loading of data, by default this is only called
     *
     * This is called when the fragment reaches it's [onResume] state
     */
    override fun onFetchDataInitialize() {
        // Nothing to preload
    }

    /**
     * Invoke view model observer to watch for changes, this will be called
     * called in [onViewCreated]
     */
    override fun setUpViewModelObserver() {
        viewModel.model.observe(viewLifecycleOwner) {
            if (it) {
                EnrolmentRouter.startActivity(context)
                activity?.finish()
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            requireBinding().stateLayout.onLoadStateChanged(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            requireBinding().stateLayout.onErrorStateChanged(it) {
                loginUser()
            }
        }
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * [.onAttach] and before
     * [.onCreateView].
     *
     *
     * Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, add a [androidx.lifecycle.LifecycleObserver] on the
     * activity's Lifecycle, removing it when it receives the
     * [Lifecycle.State.CREATED] callback.
     *
     *
     * Any restored child fragments will be created before the base
     * `Fragment.onCreate` method returns.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this, resetStateOnBackPressed
        )
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
        binding = view?.let { LoginContentBinding.bind(it) }
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
        requireBinding().inputUserName.doAfterTextChanged { editable ->
            if (editable != null)
                viewModel.usernameLiveData.value = editable.toString()
        }
        requireBinding().inputPassword.doAfterTextChanged { editable ->
            if (editable != null)
                viewModel.passwordLiveData.value = editable.toString()
        }
        requireBinding().loginButton.setOnClickListener {
            loginUser()
        }
    }

    override fun onDestroy() {
        binding?.loginButton?.setOnClickListener(null)
        super.onDestroy()
    }
}