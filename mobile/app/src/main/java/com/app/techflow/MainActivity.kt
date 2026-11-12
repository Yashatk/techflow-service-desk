package com.app.techflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.techflow.ui.theme.TechflowTheme
import com.app.techflow.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    // A instância do ViewModel é criada aqui e sobrevive a mudanças de configuração.
    private val chamadoViewModel: ChamadoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechflowTheme {
                // Scaffold agora está configurado corretamente
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Passe o ViewModel para o seu Composable principal
                    GerenciadorDeChamados(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = chamadoViewModel // Passando o ViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun GerenciadorDeChamados(modifier: Modifier = Modifier, viewModel: ChamadoViewModel) {
    // 1. Coletar os estados do ViewModel
    val listaDeChamados by viewModel.chamados.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var showEditDialog by remember { mutableStateOf(false) }
    var chamadoParaEditar by remember { mutableStateOf<Chamado?>(null) }

    if (showEditDialog) {
        chamadoParaEditar?.let { chamadoOriginal ->
            EditarChamadoDialog(
                chamado = chamadoOriginal,
                onDismiss = { showEditDialog = false },
                onSave = { novoTitulo, novaDescricao, novaPrioridade ->
                    // 2. Notificar o ViewModel sobre a edição
                    // viewModel.editarChamado(chamadoOriginal, novoTitulo, novaDescricao, novaPrioridade)
                    showEditDialog = false
                }
            )
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.techflow_logo),
            contentDescription = "Logo da empresa",
            alpha = 0.1f,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
                .size(150.dp)
        )

        // 3. Lógica de UI para Loading e Erro
        if (isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Erro: $error",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            // A UI principal só é exibida se não houver loading ou erro
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                item {
                    AdicionarChamado(
                        // 4. Conectando o "Salvar" ao ViewModel
                        aoSalvarChamado = { titulo, descricao, prioridade ->
                            // viewModel.adicionarChamado(titulo, descricao, prioridade)
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // A lógica de filtragem foi simplificada aqui
                val chamadosAbertos = listaDeChamados.filter { it.statusId == 1 } // Supondo que 1=ABERTO
                val chamadosEmProgresso = listaDeChamados.filter { it.statusId == 2 } // Supondo que 2=EM PROGRESSO
                val chamadosFechados = listaDeChamados.filter { it.statusId == 3 } // Supondo que 3=FECHADO

                item { Titulo(texto = "Chamados Abertos") }
                if (chamadosAbertos.isEmpty()) item { EmptyState() }
                items(chamadosAbertos, key = { it.id }) { chamado ->
                    ItemChamado(
                        chamado = chamado,
                        aoMudarStatus = { /* viewModel.mudarStatus(chamado.id, 2) */ },
                        aoRemoverChamado = { /* viewModel.removerChamado(chamado) */ },
                        aoEditarChamado = {
                            chamadoParaEditar = chamado
                            showEditDialog = true
                        }
                    )
                }

                item { Titulo(texto = "Em Progresso") }
                if (chamadosEmProgresso.isEmpty()) item { EmptyState() }
                items(chamadosEmProgresso, key = { it.id }) { chamado ->
                    ItemChamado(
                        chamado = chamado,
                        aoMudarStatus = { /* viewModel.mudarStatus(chamado.id, 3) */ },
                        aoRemoverChamado = { /* viewModel.removerChamado(chamado) */ },
                        aoEditarChamado = {
                            chamadoParaEditar = chamado
                            showEditDialog = true
                        }
                    )
                }

                item { Titulo(texto = "Chamados Fechados") }
                if (chamadosFechados.isEmpty()) item { EmptyState() }
                items(chamadosFechados, key = { it.id }) { chamado ->
                    ItemChamado(
                        chamado = chamado,
                        aoMudarStatus = { /* viewModel.mudarStatus(chamado.id, 1) */ },
                        aoRemoverChamado = { /* viewModel.removerChamado(chamado) */ },
                        aoEditarChamado = {
                            chamadoParaEditar = chamado
                            showEditDialog = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    Text(
        text = "Nenhum chamado encontrado",
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        style = Typography.bodyMedium
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarChamado(
    aoSalvarChamado: (titulo: String, descricao: String, prioridade: PrioridadeChamado) -> Unit,
    modifier: Modifier = Modifier
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var descricao by rememberSaveable { mutableStateOf("") }
    var prioridade by rememberSaveable { mutableStateOf(PrioridadeChamado.MEDIA) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(horizontal = 16.dp)) {
        // Campos de texto descomentados
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título do chamado") },
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição do chamado") },
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = prioridade.name,
                onValueChange = {},
                label = { Text("Prioridade") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                // Filtra a opção TODOS para não aparecer ao criar um chamado
                PrioridadeChamado.values().filter { it != PrioridadeChamado.TODOS }.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            prioridade = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        // Botão de Salvar descomentado
        Button(
            shape = RoundedCornerShape(16.dp),
            onClick = {
                if (titulo.isNotBlank()) {
                    aoSalvarChamado(titulo, descricao, prioridade)
                    // Limpa os campos após salvar
                    titulo = ""
                    descricao = ""
                    prioridade = PrioridadeChamado.MEDIA
                }
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "Salvar chamado",
                style = Typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

// Dialog para edição (exemplo)
@Composable
fun EditarChamadoDialog(
    chamado: Chamado,
    onDismiss: () -> Unit,
    onSave: (titulo: String, descricao: String, prioridade: PrioridadeChamado) -> Unit
) {
    var titulo by remember { mutableStateOf(chamado.subject) }
    var descricao by remember { mutableStateOf(chamado.description) }
    // A lógica para converter a prioridade de String/ID para Enum deve ser adicionada aqui
    var prioridade by remember { mutableStateOf(PrioridadeChamado.ALTA) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Chamado") },
        text = {
            Column {
                TextField(value = titulo, onValueChange = { titulo = it }, label = { Text("Título") })
                Spacer(Modifier.height(8.dp))
                TextField(value = descricao, onValueChange = { descricao = it }, label = { Text("Descrição") })
            }
        },
        confirmButton = {
            Button(onClick = { onSave(titulo, descricao, prioridade) }) { Text("Salvar") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

@Composable
fun Titulo(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto, style = Typography.headlineMedium, modifier = modifier.padding(
            top = 24.dp, bottom = 8.dp, start = 16.dp, end = 16.dp
        ),
        color = MaterialTheme.colorScheme.onSurface
    )
}

// ItemChamado agora usa os campos da API (`subject`, `description`, etc.)
@Composable
fun ItemChamado(
    chamado: Chamado,
    aoMudarStatus: () -> Unit,
    aoRemoverChamado: () -> Unit,
    aoEditarChamado: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "#${chamado.id} - ${chamado.subject}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = chamado.priority.uppercase(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .background(
                            color = prioridadeColor(chamado.priority),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (chamado.description.isNotBlank()) {
                Text(
                    text = chamado.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Aberto por: ${chamado.user}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Row {
                    IconButton(onClick = aoEditarChamado) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = aoRemoverChamado) {
                        Icon(Icons.Default.Delete, contentDescription = "Remover", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

// Função auxiliar para dar cor à tag de prioridade
@Composable
fun prioridadeColor(priority: String): Color {
    return when (priority.uppercase()) {
        "URGENTE" -> Color(0xFFD32F2F)
        "ALTA" -> Color(0xFFF57C00)
        "MEDIA" -> Color(0xFF1976D2)
        "BAIXA" -> Color(0xFF388E3C)
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun ItemChamadoPreview() {
    TechflowTheme {
        val chamado = Chamado(
            id = 101,
            subject = "Problema na API de login",
            description = "A API de autenticação está retornando erro 500 inesperadamente quando o usuário tenta logar com credenciais válidas.",
            user = "André Takamura",
            priority = "Urgente",
            statusId = 1, categoryId = 1, priorityId = 1, userId = 1
        )
        ItemChamado(chamado = chamado, aoMudarStatus = {}, aoRemoverChamado = {}, aoEditarChamado = {})
    }
}
