package uk.ac.tees.mad.w9624019.cinimaapp.ui.utils

object Validator {

    fun validateFirsName(fName: String): ValidationResult {
        return ValidationResult(
            (!fName.isNullOrEmpty()/* && fName.length >= 6*/)
        )

    }

    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (!lName.isNullOrEmpty() /*&& lName.length >= 4*/)
        )
    }

    fun validateEmail(emailValue: String): ValidationResult {
        return ValidationResult(
            (!emailValue.isNullOrEmpty() && isValidEmail(emailValue))
        )
    }

    fun validatePassword(passwordValue: String): ValidationResult {
        return ValidationResult(
            (!passwordValue.isNullOrEmpty() /*&& password.length >= 4*/)
        )
    }

    fun validateCheckBox( statusValue: Boolean): ValidationResult {
        return ValidationResult(
            statusValue
        )
    }

}


data class ValidationResult(
    val status: Boolean = false
)

fun isValidEmail(email: String): Boolean {
    val emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"
    return email.matches(emailPattern.toRegex())
}