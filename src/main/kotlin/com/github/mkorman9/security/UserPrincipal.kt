package com.github.mkorman9.security

import java.security.Principal

data class UserPrincipal(
    val username: String
) : Principal {
    override fun getName(): String = username
}
