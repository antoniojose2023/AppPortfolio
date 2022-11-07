package br.com.antoniojoseuchoa.appportfolio.domain

data class RepositoryElement (
    val id: Long,
    val nodeID: String,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val html_url: String,
    val description: String? = null,
    val fork: Boolean,
    val url: String,
): java.io.Serializable