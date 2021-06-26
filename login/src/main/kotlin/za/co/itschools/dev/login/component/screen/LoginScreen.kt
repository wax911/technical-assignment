package za.co.itschools.dev.login.component.screen

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import za.co.itschools.dev.core.component.screen.AbstractScreen
import za.co.itschools.dev.core.ui.commit
import za.co.itschools.dev.core.ui.model.FragmentItem
import za.co.itschools.dev.login.databinding.LoginScreenBinding
import za.co.itschools.dev.navigation.LoginRouter

class LoginScreen : AbstractScreen<LoginScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(requireBinding().root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setSupportActionBar(requireBinding().bottomAppBar)
        onUpdateUserInterface()
    }

    private fun onUpdateUserInterface() {
        FragmentItem(fragment = LoginRouter.forFragment())
            .commit(requireBinding().contentMain, this)
    }
}