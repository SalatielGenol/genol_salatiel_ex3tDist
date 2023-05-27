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
    private var _uiRegisterState = MutableStateFlow(ClientRegisterUiState())
    val uiRegisterState: StateFlow<ClientRegisterUiState> = _uiRegisterState.asStateFlow()

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

    val isSignInEnabled get() = (uiRegisterState.value.userName.isNotBlank()
            && uiRegisterState.value.mailAddress.isNotBlank()
            && uiRegisterState.value.password.isNotBlank()
            && uiRegisterState.value.passwordConfirm.isNotBlank())
            && (!_isUserTypedValid && !_isEmailTypedValid
            && !_isPassTypedLengthValid && !_isPassTypedValid)


    fun validateUserName(userName: String) {
        _isUserTypedValid = userName.isNotBlank() && userName.length <= MIN_USER_CHARACTERS
        _uiRegisterState.update {
            it.copy(userName = userName)
        }
    }

    fun validateEmail(email: String) {
        _isEmailTypedValid =
            email.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _uiRegisterState.update {
            it.copy(mailAddress = email)
        }
    }

    fun validatePassLength(pass: String) {
        _isPassTypedLengthValid = pass.isNotBlank() && pass.length <= MIN_PASS_CHARACTERS
        _uiRegisterState.update {
            it.copy(password = pass)
        }
        validateOppositePassword()
    }

    fun validatePassword(pass: String) {
        _isPassTypedValid = pass.isNotBlank() && pass != uiRegisterState.value.password
        _uiRegisterState.update {
            it.copy(passwordConfirm = pass)
        }
    }


    /*
    Esta funcion sirve para comprobar el estado del textfield donde se reescribe la contraseña
    desde el textfield donde se introduce por primera vez la contraseña, asi si se hacen coincidir
    desde cualquiera de los dos textfield, se elimina el error de ambos.
     */
    private fun validateOppositePassword(){
        _isPassTypedValid = uiRegisterState.value.passwordConfirm.isNotBlank()
                && uiRegisterState.value.passwordConfirm != uiRegisterState.value.password
    }

    private fun isUserRegistered():Boolean {
        return clientsData.any{
            it.user == uiRegisterState.value.userName
                    && it.email == uiRegisterState.value.mailAddress
        }

    }

    fun addClient() {
        if (!isUserRegistered()){
            _clientsData.addClient(
                item = ClientModel(
                    user = _uiRegisterState.value.userName,
                    email = _uiRegisterState.value.mailAddress,
                    password = _uiRegisterState.value.passwordConfirm
                )
            )
            _uiRegisterState.update {
                it.copy(isUserRegistered = true)
            }
        }else {
            _uiRegisterState.update {
                it.copy(isUserRegistered = false)
            }
        }


    }

    fun dialogOkConfirm() {
        _uiRegisterState.value = ClientRegisterUiState()
        dialogClose()
    }

    fun dialogClose() {
        _uiRegisterState.update {
            it.copy(isUserRegistered = null)
        }
    }

    /*
    Funciones para la lista de gestion de clientes
     */

    fun delClient(element: ClientModel){
        _clientsData.delClient(item = element)
    }

    fun checkItem(client: ClientModel) {
        _clientsData.modifyCheckBox(client)
    }

    fun delClientsRowChecked(){
        _clientsData.delChecked()
    }
}