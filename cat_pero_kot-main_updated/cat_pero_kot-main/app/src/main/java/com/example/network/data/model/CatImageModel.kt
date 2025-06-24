
package com.example.network.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatImageModel(
    val id: String,
    val url: String,
    val width: Int? = null,
    val height: Int? = null,
    val breeds: List<Breed>? = null
)

@Serializable
data class Breed(
    val id: String,
    val name: String? = null,
    @SerialName("temperament") val temperament: String? = null,
    @SerialName("origin") val origin: String? = null
)
