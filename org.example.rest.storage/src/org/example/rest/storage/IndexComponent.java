
package org.example.rest.storage;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipselabs.emongo.MongoDatabaseProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component(service = IndexComponent.class)
@Path("/storage/index/{collection}")
@Produces("application/json")
@Consumes("application/json")
public class IndexComponent
{
	private MongoDatabaseProvider mongoDatabaseProvider;

	@Activate
	public void activate()
	{
		System.out.println("IndexComponent is active");
	}

	@POST
	public Response addIndex(@PathParam("collection") String collectionName, String json)
	{
		DBCollection collection = getCollection(collectionName);
		DBObject index = (DBObject) JSON.parse(json);
		collection.ensureIndex(index);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{indexName}")
	public Response deleteIndex(@PathParam("collection") String collectionName, @PathParam("indexName") String keyName)
	{
		DBCollection collection = getCollection(collectionName);
		DBObject index = new BasicDBObject();
		index.put(keyName, 1);
		collection.dropIndex(index);
		return Response.ok().build();
	}

	@GET
	public Response getIndex(@Context UriInfo uriInfo, @PathParam("collection") String collectionName)
	{
		DBCollection collection = getCollection(collectionName);

		List<DBObject> indicies = collection.getIndexInfo();

		StringBuilder data = new StringBuilder("[");
		boolean firstOne = true;

		for (DBObject index : indicies)
		{
			if (firstOne)
				firstOne = false;
			else
				data.append(',');

			data.append(index.toString());
		}

		data.append("]");
		return Response.ok(data.toString(), MediaType.APPLICATION_JSON).build();
	}

	@Reference(unbind = "-")
	public void bindMongoDatabaseProvider(MongoDatabaseProvider mongoDatabaseProvider)
	{
		this.mongoDatabaseProvider = mongoDatabaseProvider;
	}

	private DBCollection getCollection(String collectionName)
	{
		return mongoDatabaseProvider.getDB().getCollection(collectionName);
	}
}
