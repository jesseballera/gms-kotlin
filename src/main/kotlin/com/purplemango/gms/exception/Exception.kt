package com.purplemango.gms.exception

class UserNotFoundException(private val id: String) : RuntimeException()
class UserDuplicateException(private val username: String) : RuntimeException()