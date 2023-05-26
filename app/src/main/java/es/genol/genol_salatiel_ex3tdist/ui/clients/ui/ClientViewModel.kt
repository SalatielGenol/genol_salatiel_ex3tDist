package es.genol.genol_salatiel_ex3tdist.ui.clients.ui


import androidx.lifecycle.ViewModel
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.ClientData
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.ClientModel
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.MIN_PASS_CHARACTERS
import es.genol.genol_salatiel_ex3tdist.ui.clients.data.MIN_USER_CHARACTERS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ClientViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(ClientUiState())
    val uiState: StateFlow<ClientUiState> = _uiState.asStateFlow()

    private val _clientsData = ClientData()
    val clientsData get() = _clientsData.clientsList

    private var _isUserTypedValid = false
    val isUserTypedValid get() = _isUserTypedValid

    private var _isEmailTypedValid = false
    val isEmailTypedValid get() = _isEmailTypedValid

    private var _isPassTypedLengthValid = false
    val isPassTypedLengthValid get() = _isPassTypedLengthValid

    private var _isPassTypedValid = false
    val isPassTypedValid get() = _isPassTypedValid

    val isSignInEnabled get() = (uiState.value.userName.isNotBlank()
            && uiState.value.mailAddress.isNotBlank()
            && uiState.value.password.isNotBlank()
            && uiState.value.passwordConfirm.isNotBlank())
            && (!_isUserTypedValid && !_isEmailTypedValid
            && !_isPassTypedLengthValid && !_isPassTypedValid)


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
        validateOppositePassword()
    }

    fun validatePassword(pass: String) {
        _isPassTypedValid = pass.isNotBlank() && pass != uiState.value.password
        _uiState.update {
            it.copy(passwordConfirm = pass)
        }
    }


    /*
    Esta funcion sirve para comprobar el estado del textfield donde se reescribe la contraseña
    desde el textfield donde se introduce por primera vez la contraseña, asi si se hacen coincidir
    desde cualquiera de los dos textfield, se elimina el error de ambos.
     */
    private fun validateOppositePassword(){
        _isPassTypedValid = uiState.value.passwordConfirm.isNotBlank()
                && uiState.value.passwordConfirm != uiState.value.password
    }

    private fun isUserRegistered():Boolean {
        return clientsData.any{
            it.user == uiState.value.userName
                    && it.email == uiState.value.mailAddress
        }

    }

    fun addUser() {
        if (!isUserRegistered()){
            _clientsData.addClient(
                item = ClientModel(
                    user = _uiState.value.userName,
                    email = _uiState.value.mailAddress,
                    password = _uiState.value.passwordConfirm
                )
            )
            _uiState.update {
                it.copy(isUserRegistered = true)
            }
        }else {
            _uiState.update {
                it.copy(isUserRegistered = false)
            }
        }


    }

    fun dialogOkConfirm() {
        _uiState.value = ClientUiState()
        dialogClose()
    }

    fun dialogClose() {
        _uiState.update {
            it.copy(isUserRegistered = null)
        }
    }
}