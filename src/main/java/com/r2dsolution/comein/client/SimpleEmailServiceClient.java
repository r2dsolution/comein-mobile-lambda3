package com.r2dsolution.comein.client;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.r2dsolution.comein.model.EmailRequest;

@Service
public class SimpleEmailServiceClient {
	
	@Value( "${comein.ses.region}" )
	private String region;
	
	@Value( "${comein.ses.sender}" )
	private String sender;
	
	@Autowired
	private AmazonSimpleEmailServiceClientBuilder clientBuilder;
	
	public AmazonSimpleEmailService initClient() {
		AmazonSimpleEmailService client = clientBuilder.withRegion(region).build();
		return client;
	}
	
	public void sendMail(AmazonSimpleEmailService client,EmailRequest request) {
		
		String data = map2json(request.getParams());
		sendMail(client,sender,request.getEmail(),request.getTemplate(),data);
	}
	
	public void sendMail(AmazonSimpleEmailService client,String from,String to,String template,String data) {
		
		try {
			
			

		      SendTemplatedEmailRequest request = new SendTemplatedEmailRequest()
		          .withDestination(
		              new Destination().withToAddresses(to))
		          .withTemplate(template)
		          .withTemplateData(data)		          
		          .withSource(from);

		      client.sendTemplatedEmail(request);
		      System.out.println("Email sent!");
		    } catch (Exception ex) {
		      System.out.println("The email was not sent. Error message: "+ ex.getMessage());
		     
		    }
	}


	protected String map2json(Map<String,String> map) {
		StringBuffer sb = null;//new StringBuffer("{");
		Set<String> names = map.keySet();
		for(String name: names) {
			if (sb==null) {
				sb = new StringBuffer("{");
			} else {
				sb = sb.append(",");
			}
			sb = sb.append("\""+name+"\":\""+map.get(name)+"\"");
			
		}
		if (sb!=null) {
			sb.append("}");
		}else {
			sb = new StringBuffer("");
		}
		return sb.toString();
	}
}
