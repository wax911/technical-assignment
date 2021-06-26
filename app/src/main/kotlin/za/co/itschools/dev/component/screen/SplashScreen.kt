package za.co.itschools.dev.component.screen

import android.os.Bundle
import za.co.itschools.dev.component.content.SplashContent
import za.co.itschools.dev.core.component.screen.AbstractScreen
import za.co.itschools.dev.core.ui.commit
import za.co.itschools.dev.core.ui.model.FragmentItem
import za.co.itschools.dev.databinding.SplashScreenBinding

class SplashScreen : AbstractScreen<SplashScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(requireBinding().root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        onUpdateUserInterface()
    }

    private fun onUpdateUserInterface() {
        FragmentItem(fragment = SplashContent::class.java)
            .commit(requireBinding().splashFrame, this)
    }
}