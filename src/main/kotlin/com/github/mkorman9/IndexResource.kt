package com.github.mkorman9

import com.github.mkorman9.security.UserPrincipal
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import io.quarkus.security.Authenticated
import io.quarkus.security.identity.SecurityIdentity
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType

@Path("/")
@Produces(MediaType.TEXT_HTML)
@Consumes(value = [])
class IndexResource {
    @GET
    fun index(
        @QueryParam("name") @DefaultValue("world") name: String
    ): TemplateInstance {
        return Templates.index(name)
    }

    @GET
    @Path("/admin")
    @Authenticated
    fun admin(
        @Context securityIdentity: SecurityIdentity
    ): TemplateInstance {
        return Templates.admin(securityIdentity.principal as UserPrincipal)
    }

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun index(name: String): TemplateInstance
        
        @JvmStatic
        external fun admin(user: UserPrincipal): TemplateInstance
    }
}
