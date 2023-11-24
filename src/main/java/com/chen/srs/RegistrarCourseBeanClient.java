package com.chen.srs;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import util.BusinessException;

public class RegistrarCourseBeanClient {

	public static void main(String[] args) {

		try {
			IRegistrarCourseBean iRegistrarCourseBeanClient = lookupRegistrarCourseBean();

		iRegistrarCourseBeanClient.register(0, 0, null);
			//iRegistrarCourseBeanClient.registerWithLongRunning(0, 0, null);
			
			
			

		} catch (NamingException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static IRegistrarCourseBean lookupRegistrarCourseBean() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		final Context context = new InitialContext(jndiProperties);
		return (IRegistrarCourseBean) context.lookup("ejb:/srs/RegistrarCourseBean!com.chen.srs.IRegistrarCourseBean");
	}

}
