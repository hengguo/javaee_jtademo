package demo.velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class HelloVelocity {
	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

		ve.init();

		Template t = ve.getTemplate("velocity/hellovelocity.vm", "UTF-8");
		VelocityContext ctx = new VelocityContext();

		Member m1=new Member();
	       m1.setEmail("gaoxiang1@tom.com");
	       m1.setFirstname("gao1");
	       m1.setLastname("xiang1");
	       
	       Member m2=new Member();
	       m2.setEmail("gaoxiang2@tom.com");
	       m2.setFirstname("gao2");
	       m2.setLastname("xiang2");
	       
	       Member m3=new Member();
	       m3.setEmail("gaoxiang3@tom.com");
	       m3.setFirstname("gao3");
	       m3.setLastname("xiang3");
	       List members=new ArrayList();
	        members.add(m1);
	        members.add(m2);
	        members.add(m3);
	        
		ctx.put("members", members);
		StringWriter sw = new StringWriter();

		t.merge(ctx, sw);

		System.out.println(sw.toString());
	}
}

class Member {
	private String email,firstname,lastname;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}