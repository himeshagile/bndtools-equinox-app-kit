/**
 * 
 */

package org.example.ds.service;

import org.example.ds.api.Hello;
import org.osgi.service.component.annotations.Component;

/**
 * @author bhunt
 *
 */
@Component(service = Hello.class)
public class HelloService implements Hello
{

	@Override
	public void sayHello(String name)
	{
		System.out.println("Hello " + name);
	}
}
