package za.co.itschools.dev.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ViewFlipper
import za.co.itschools.dev.core.databinding.StateLayoutErrorBinding
import za.co.itschools.dev.core.databinding.StateLayoutLoadingBinding
import za.co.itschools.dev.core.extensions.layoutInflater

/**
 * A state layout that supports Loading and Error states
 * by default by using a [ViewFlipper] as the underlying view
 */
class StateLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewFlipper(context, attrs) {

    val isContentState: Boolean
        get() = displayedChild == CONTENT_VIEW

    private val loadingBinding by lazy(LazyThreadSafetyMode.NONE) {
        StateLayoutLoadingBinding.inflate(layoutInflater())
    }

    private val errorBinding by lazy(LazyThreadSafetyMode.NONE) {
        StateLayoutErrorBinding.inflate(layoutInflater())
    }

    init {
        onInit(context, attrs)
    }

    private fun onInit(context: Context, attrs: AttributeSet?) {
        if (!isInEditMode)
            setupAdditionalViews()

        setInAnimation(context, android.R.anim.fade_in)
        setOutAnimation(context, android.R.anim.fade_out)

        displayedChild = CONTENT_VIEW
    }

    private fun setupAdditionalViews() {
        addView(loadingBinding.root)
        addView(errorBinding.root)
    }

    /**
     * Updates state of this widget based on the state of [isLoading]
     */
    fun onLoadStateChanged(isLoading: Boolean) {
        displayedChild = when {
            isLoading -> LOADING_VIEW
            else -> CONTENT_VIEW
        }
    }

    /**
     * Updates state of this widget when an error is detected
     *
     * @param throwable Error encountered
     * @param retryAction Delegate action for retrying
     */
    fun onErrorStateChanged(throwable: Throwable, retryAction: (View) -> Unit) {
        errorBinding.stateLayoutErrorHeading.text = throwable.javaClass.simpleName
        errorBinding.stateLayoutErrorMessage.text = throwable.message
        errorBinding.stateLayoutErrorRetryAction.setOnClickListener(retryAction)
        displayedChild = ERROR_VIEW
    }

    override fun onDetachedFromWindow() {
        errorBinding.stateLayoutErrorRetryAction.setOnClickListener(null)
        super.onDetachedFromWindow()
    }

    companion object {
        /** First view inflated index which is loading view */
        const val LOADING_VIEW = 0
        /** Second view inflated in this case the error view */
        const val ERROR_VIEW = 1
        /** Third inflated view should be the current view wrapped by this layout */
        const val CONTENT_VIEW = 2
    }
}