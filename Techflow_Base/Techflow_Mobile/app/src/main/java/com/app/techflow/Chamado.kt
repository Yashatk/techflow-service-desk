package com.app.techflow



import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class StatusChamado : Parcelable {
    ABERTO,
    EM_PROGRESSO,
    FECHADO
}

@Parcelize
enum class PrioridadeChamado : Parcelable {
    TODOS,
    URGENTE,
    ALTA,
    MEDIA,
    BAIXA
}

@Parcelize
data class Chamado(
    val id: Int, // A API geralmente fornece um ID
    val statusId: Int, // A API geralmente fornece um ID para o status
    val categoryId: Int,
    val priorityId: Int,
    val priority: String,
    var subject: String,
    val description: String,
    val userId: Int,
    val user: String
) : Parcelable