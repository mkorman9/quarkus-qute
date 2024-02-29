package com.github.mkorman9

import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
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

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun index(name: String): TemplateInstance
    }
}
