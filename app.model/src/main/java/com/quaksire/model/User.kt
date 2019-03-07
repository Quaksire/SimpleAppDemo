package com.quaksire.model

/**
 * Created by Julio.
 */
data class User (
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val company: Company,
    val website: String)