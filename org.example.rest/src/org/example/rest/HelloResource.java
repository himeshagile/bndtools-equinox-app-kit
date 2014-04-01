/**
 * 
 */

package org.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.osgi.service.component.annotations.Component;

/**
 * @author bhunt
 *
 */
@Path("/hello")
@Component(service = HelloResource.class)
public class HelloResource
{
	@GET
	public String seyHello()
	{
		return "Hello RESTful world";
	}
}
