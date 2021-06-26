package za.co.itschools.dev.enrolment.component.screen

import android.os.Bundle
import za.co.itschools.dev.core.component.screen.AbstractScreen
import za.co.itschools.dev.core.ui.commit
import za.co.itschools.dev.core.ui.model.FragmentItem
import za.co.itschools.dev.enrolment.databinding.EnrolmentScreenBinding
import za.co.itschools.dev.navigation.EnrolmentRouter

class EnrolmentScreen : AbstractScreen<EnrolmentScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EnrolmentScreenBinding.inflate(layoutInflater)
        setContentView(requireBinding().root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setSupportActionBar(requireBinding().bottomAppBar)
        onUpdateUserInterface()
    }

    private fun onUpdateUserInterface() {
        FragmentItem(fragment = EnrolmentRouter.forFragment())
            .commit(requireBinding().contentMain, this)
    }
}