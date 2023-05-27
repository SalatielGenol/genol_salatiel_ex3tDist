package es.genol.genol_salatiel_ex3tdist.ui.clients.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class ClientData (private val _clientsList: SnapshotStateList<ClientModel> = mutableStateListOf()) {
    val clientsList get() = _clientsList.toList()

    fun addClient(item: ClientModel){
        _clientsList.add(element = item)
    }

    fun delClient(item: ClientModel){
        _clientsList.remove(element = item)
    }

    fun modifyCheckBox(item: ClientModel){
        _clientsList[clientsList.indexOf(item)] = item.copy(checked = !item.checked)
    }

    fun delChecked() {
        _clientsList.removeAll( clientsList.filter {
            it.checked
        })
    }
}