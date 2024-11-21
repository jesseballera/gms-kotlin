package com.purplemango.gms.model

import com.purplemango.gms.passwordEncoder
import java.util.UUID

fun User.toViewUser() = ViewUser(
    id = id,
    username = username,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = "********" // Replace with actual password masking
)

fun AddUser.toUser() = User(
    id = UUID.randomUUID().toString(),
    username = username,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = passwordEncoder().encode(password)
)