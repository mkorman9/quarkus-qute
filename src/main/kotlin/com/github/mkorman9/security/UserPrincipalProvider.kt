package com.github.mkorman9.security

import io.quarkus.security.identity.SecurityIdentity
import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Produces

@RequestScoped
class UserPrincipalProvider {
    @Produces
    fun userPrincipal(securityIdentity: SecurityIdentity?): UserPrincipal? {
        return securityIdentity?.principal as? UserPrincipal
    }
}
