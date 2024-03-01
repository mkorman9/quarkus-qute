package com.github.mkorman9.security

import io.quarkus.security.identity.AuthenticationRequestContext
import io.quarkus.security.identity.IdentityProvider
import io.quarkus.security.identity.SecurityIdentity
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest
import io.quarkus.security.runtime.QuarkusSecurityIdentity
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

private const val PASSWORD = "admin"

@ApplicationScoped
class HttpIdentityProvider : IdentityProvider<UsernamePasswordAuthenticationRequest> {
    override fun authenticate(
        request: UsernamePasswordAuthenticationRequest,
        context: AuthenticationRequestContext
    ): Uni<SecurityIdentity> {
        return if (request.password.password.contentEquals(PASSWORD.toCharArray())) {
            return Uni.createFrom().item(
                QuarkusSecurityIdentity.builder()
                    .setPrincipal(UserPrincipal(request.username))
                    .build()
            )
        } else {
            Uni.createFrom().nullItem()
        }
    }

    override fun getRequestType(): Class<UsernamePasswordAuthenticationRequest> =
        UsernamePasswordAuthenticationRequest::class.java
}
