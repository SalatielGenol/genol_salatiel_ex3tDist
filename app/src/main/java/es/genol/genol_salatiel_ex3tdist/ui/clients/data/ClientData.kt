package es.genol.genol_salatiel_ex3tdist.ui.clients.data

class ClientData (private val _clientsList: MutableList<ClientModel> = mutableListOf()) {
    val clientsList get() = _clientsList.toList()

    fun addClient(item: ClientModel){
        _clientsList.add(element = item)
    }
}