/**
 * 
 */

package org.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

/**
 * @author bhunt
 *
 */
@Component
public class HelloServlet extends HttpServlet
{
	private static final long serialVersionUID = 9103162259259165451L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter out = resp.getWriter();
		out.println("Hello Servlet World");
	}

	@Reference(unbind = "-")
	public void bindHttpService(HttpService httpService) throws ServletException, NamespaceException
	{
		httpService.registerServlet("/hello", this, null, null);
	}
}
