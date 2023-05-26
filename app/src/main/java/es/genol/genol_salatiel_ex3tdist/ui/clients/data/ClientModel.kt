package es.genol.genol_salatiel_ex3tdist.ui.clients.data

const val MIN_USER_CHARACTERS = 3
const val MIN_PASS_CHARACTERS = 4

data class ClientModel(val user: String, val email: String, val password:String, )