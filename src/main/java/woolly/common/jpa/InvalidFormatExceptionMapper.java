package woolly.common.jpa;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {
  @Override
  public Response toResponse(InvalidFormatException exception) {
    String message = "Invalid entry passed of type : " + exception.getTargetType().getSimpleName()
                     + ". Location: " + exception.getLocation();
    return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON_TYPE)
        .entity(new GenericEntity<List<String>>(Arrays.asList(message)) {
        }).build();
  }
}
