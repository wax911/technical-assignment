package za.co.itschools.dev.login.component.content.controller

import za.co.itschools.dev.login.databinding.LoginContentBinding

class LoginContentController {

    /**
     * Checks for valid inputs and return True if all conditions are met,
     * in addition applies error on [binding] layouts for each invalid case
     */
    fun areInputsValid(binding: LoginContentBinding): Boolean {
        if (binding.inputUserName.text.isNullOrBlank()) {
            binding.inputUserNameLayout.error = "Field is required"
            return false
        } else
            binding.inputUserNameLayout.error = null

        if (binding.inputPassword.text.isNullOrBlank()) {
            binding.inputPasswordLayout.error = "Field is required"
            return false
        } else
            binding.inputPasswordLayout.error = null

        return true
    }
}