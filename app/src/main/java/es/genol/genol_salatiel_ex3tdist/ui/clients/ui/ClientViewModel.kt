package es.genol.genol_salatiel_ex3tdist.ui.clients.ui


import androidx.lifecycle.ViewModel
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.ClientData
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.MIN_PASS_CHARACTERS
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.MIN_USER_CHARACTERS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ClientViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(ClientUiState())
    val uiState: StateFlow<ClientUiState> = _uiState.asStateFlow()

    private var _clientsList = mutableListOf<ClientData>()
    val clientList get() = _clientsList.toList()

    /*
    No he conseguido meter estos 3 datos en la clase ClientUiState,
    internamente si que se modificaba el dato al hacerlo con un constructor
    pero luego no me modificaba la vista, no entiendo por que puede ser.
     */

    private var _isUserTypedValid = false
    val isUserTypedValid get() = _isUserTypedValid

    private var _isEmailTypedValid = false
    val isEmailTypedValid get() = _isEmailTypedValid

    private var _isPassTypedLengthValid = false
    val isPassTypedLengthValid get() = _isPassTypedLengthValid

    private var _isPassTypedValid = false
    val isPassTypedValid get() = _isPassTypedValid

    /*
    No he conseguido hacer funcionar de ninguna manera que se habilite el boton a partir
    de las validaciones, el && me da por valida la primera condicion y anula el resto.
    */
    private var _isSignInEnabled = true
    val isSignInEnabled get() = _isSignInEnabled

    private var _isUserRegistered = false
    val isUserRegistered get() = _isUserRegistered

    fun validateUserName(userName: String) {
        _isUserTypedValid = userName.isNotBlank() && userName.length <= MIN_USER_CHARACTERS
        _uiState.update {
            it.copy(userName = userName)
        }
    }

    fun validateEmail(email: String) {
        _isEmailTypedValid =
            email.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _uiState.update {
            it.copy(mailAddress = email)
        }
    }

    fun validatePassLength(pass: String) {
        _isPassTypedLengthValid = pass.isNotBlank() && pass.length <= MIN_PASS_CHARACTERS
        _uiState.update {
            it.copy(password = pass)
        }
    }

    fun validatePassword(pass: String) {
        _isPassTypedValid = pass.isNotBlank() && pass != uiState.value.password
        _uiState.update {
            it.copy(passwordConfirm = pass)
        }
    }

    fun countUp() {
        _uiState.update {
            it.copy(currentClientCount = _uiState.value.currentClientCount )
        }
    }

    fun signIn() {
        _clientsList.add(
            element = ClientData(
                user = _uiState.value.userName,
                email = _uiState.value.mailAddress,
                password = _uiState.value.passwordConfirm
            )
        )
        _uiState.value = ClientUiState()
        _isSignInEnabled = false
        _isUserRegistered = true
    }

    fun confirm(){
        _isUserRegistered = false
    }
}