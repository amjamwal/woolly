package woolly.resource;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import woolly.service.FeeService;

/**
 * Created by aman.jamwal on 4/18/15.
 */
@Path("/v1/test")
@Slf4j
@Transactional
@Api(value = "/v1/test", description = "Test Service version 1")
public class TestResource {

  private final FeeService feeService;

  @Inject
  public TestResource(FeeService feeService) {

    this.feeService = feeService;
  }

  @GET
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTest(@QueryParam("id") @NonNull String id) {
    return Response.ok().entity("Hello " + id + "your fees: " + this.feeService.getFee()).build();
  }

}
