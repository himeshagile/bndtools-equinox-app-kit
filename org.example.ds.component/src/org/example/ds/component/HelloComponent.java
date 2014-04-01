/**
 * 
 */

package org.example.ds.component;

import org.example.ds.api.Hello;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author bhunt
 *
 */
@Component
public class HelloComponent
{
	private Hello helloService;

	@Activate
	public void activate()
	{
		helloService.sayHello("world");
	}

	@Reference(unbind = "-")
	public void bindHelloService(Hello helloService)
	{
		this.helloService = helloService;
	}
}
