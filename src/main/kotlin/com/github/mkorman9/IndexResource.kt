package com.github.mkorman9

import com.github.mkorman9.security.UserPrincipal
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import io.quarkus.security.Authenticated
import jakarta.ws.rs.Consumes
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
    fun index(): TemplateInstance {
        return Templates.index()
    }

    @GET
    @Path("/welcome")
    fun welcome(
        @QueryParam("name") name: String
    ): TemplateInstance {
        return Templates.welcome(name)
    }

    @GET
    @Path("/admin")
    @Authenticated
    fun admin(
        @Context userPrincipal: UserPrincipal
    ): TemplateInstance {
        return Templates.admin(userPrincipal)
    }

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun index(): TemplateInstance

        @JvmStatic
        external fun welcome(name: String): TemplateInstance

        @JvmStatic
        external fun admin(user: UserPrincipal): TemplateInstance
    }
}
