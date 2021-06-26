package za.co.itschools.dev.enrolment.initializer

import android.content.Context
import za.co.itschools.dev.core.initializer.contract.AbstractFeatureInitializer
import za.co.itschools.dev.core.koin.helper.FeatureModuleHelper.Companion.loadModules
import za.co.itschools.dev.enrolment.koin.featureModules

class FeatureInitializer : AbstractFeatureInitializer<Unit>() {
    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {
        featureModules.loadModules()
    }
}