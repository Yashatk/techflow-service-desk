package com.app.techflow // Garanta que o package está correto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChamadoViewModel : ViewModel() {

    // O StateFlow agora armazena a lista com a nova estrutura de Chamado
    private val _chamados = MutableStateFlow<List<Chamado>>(emptyList())
    val chamados: StateFlow<List<Chamado>> = _chamados.asStateFlow()

    // Erro de rede ou estado de carregamento
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        // Carrega os chamados assim que o ViewModel for criado
        fetchChamados()
    }

    fun fetchChamados() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // A chamada de API agora preencherá a lista com a nova data class
                val listaDaApi = RetrofitInstance.api.getChamados()
                _chamados.value = listaDaApi
            } catch (e: Exception) {
                // Tratamento de erro mais detalhado para ajudar no debug
                _error.value = "Falha ao carregar os chamados: ${e.javaClass.simpleName} - ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // --- Funções de Ação (Ajustar quando tiver os endpoints POST/PUT/DELETE) ---

    // Exemplo de como a função de mudar status poderia ser (requer um endpoint PUT)
    /*
    fun mudarStatus(chamadoId: Int, novoStatusId: Int) {
        viewModelScope.launch {
            try {
                // Supondo que você tenha um endpoint como:
                // @PUT("chamados/{id}/status")
                // suspend fun updateStatus(@Path("id") id: Int, @Body statusUpdate: StatusUpdateRequest)

                // ... lógica para chamar a API ...

                // Após sucesso, recarregar a lista
                fetchChamados()
            } catch (e: Exception) {
                _error.value = "Falha ao atualizar o status."
            }
        }
    }
    */
}
