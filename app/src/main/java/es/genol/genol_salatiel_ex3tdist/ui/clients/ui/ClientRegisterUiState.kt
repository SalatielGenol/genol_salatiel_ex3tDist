package es.genol.genol_salatiel_ex3tdist.ui.clients.ui

data class ClientRegisterUiState(
    val currentClientCount: Int = 0,
    val userName: String = "",
    val mailAddress: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val isUserRegistered: Boolean? = null
)