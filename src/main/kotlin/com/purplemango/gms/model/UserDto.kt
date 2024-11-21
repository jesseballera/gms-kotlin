package com.purplemango.gms.model

data class ViewUser(val id: String, val username: String, val firstName: String, val lastName: String, val email: String, val password: String)
data class AddUser(val username: String,  val firstName: String, val lastName: String, val email: String, val password: String)
data class UpdateUser(val id: String, val username: String?,  val firstName: String?, val lastName: String?, val email: String?, val password: String?)