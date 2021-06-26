package za.co.itschools.dev.login.provider

import android.content.Context
import android.content.Intent
import za.co.itschools.dev.login.component.content.LoginContent
import za.co.itschools.dev.login.component.screen.LoginScreen
import za.co.itschools.dev.navigation.LoginRouter

class FeatureProvider : LoginRouter.Provider {
    override fun activity(context: Context?) = Intent(context, LoginScreen::class.java)

    override fun fragment() = LoginContent::class.java
}