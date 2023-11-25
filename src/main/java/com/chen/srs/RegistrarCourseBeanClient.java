package com.chen.srs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import util.BusinessException;

public class RegistrarCourseBeanClient {

	public static void main(String[] args) {

		 final long courseCapacity = 25 ;
		
		try {
			IRegistrarCourseBean iRegistrarCourseBeanClient = lookupRegistrarCourseBean();
			
			Student student =  new Student();
			student.setId("apple");
			student.setEmail("acec@gmail.com");
			student.setAddress("addess-1");
			student.setFirstName("ace");
			student.setLastName("chen");
			student.setLevel("CSE");
			student.setSsn("987654343");
			student.setRegistrationDate(new Date());
			
			char answer = '\0';
			
			for (int i =1 ; i<6 ;i++) {
				System.out.println("To control a short-run/long-run mode, enter s or l ");
				InputStreamReader inputStreamReader = new InputStreamReader(System.in);
				try {
					answer = (char) inputStreamReader.read();
					
					System.out.println("answer : "+answer);
				} catch (IOException e) {
					System.err.println("I/O exception: " + e.toString());
				}
			
				
				if (answer == 's') {
					iRegistrarCourseBeanClient.register(i,courseCapacity, student); // Call short mode
				}
				
				if (answer == 'l') {
					iRegistrarCourseBeanClient.registerWithLongRunning(i,courseCapacity, student); // Call long mode
				}
				
			    
				 answer = '\0';
				System.out.println("-----done----iteration : ----"+i);
			}

		
			
			

	System.out.println("---------------done------------");

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
