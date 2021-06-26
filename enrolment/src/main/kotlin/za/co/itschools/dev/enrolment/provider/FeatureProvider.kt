package za.co.itschools.dev.enrolment.provider

import android.content.Context
import android.content.Intent
import za.co.itschools.dev.enrolment.component.content.EnrolmentContent
import za.co.itschools.dev.enrolment.component.screen.EnrolmentScreen
import za.co.itschools.dev.navigation.EnrolmentRouter

class FeatureProvider : EnrolmentRouter.Provider {
    override fun activity(context: Context?) = Intent(context, EnrolmentScreen::class.java)

    override fun fragment() = EnrolmentContent::class.java
}