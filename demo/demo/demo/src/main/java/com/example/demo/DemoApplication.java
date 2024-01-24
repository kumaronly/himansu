package com.example.demo;

import java.io.IOException;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;


@SpringBootApplication
public class DemoApplication {
	
	private static final String NEW_LINE = "\n";
	private static String webhook_channel1 = "https://hooks.slack.com/services/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";
	private static String webhook_channel2 = "https://hooks.slack.com/services/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";
	private static String channel1 = "slack-automation-poc";
	private static String channel2 = "slack-automation-poc-2";
	
	
	
	public static void sendMessageToSlack(String message, String channel, String webhookURL) {
		  
	      StringBuilder messageBuider = new StringBuilder();
	      messageBuider.append(message + NEW_LINE);
	      process(messageBuider.toString(),channel,webhookURL);
	   }
	
	private static void process(String message, String channel, String webhookURL) {
		  
	      Payload payload = Payload.builder()
	        .attachments(Collections.singletonList(Attachment.builder()
	        .channelName(channel)
	        .build()))
	        .text(message).build();
	    try {
	        WebhookResponse webhookResponse = Slack.getInstance().send(webhookURL, payload);
	     if (webhookResponse.getCode() == 200)
	    	 System.out.println("Success send slack !");
	     
	     System.out.println(webhookResponse.getMessage());
	    } catch (IOException e) {
	    	System.out.println("Unexpected Error! WebHook:" + webhookURL);
	  
	    }
	   }
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Application Running!!!");
		sendMessageToSlack("Build Failure. Unexpected Error at line 10 !!!",channel1,webhook_channel1);
		sendMessageToSlack("Build Failure. Unexpected Error at line 10 !!!",channel2,webhook_channel2);
		
		
	}

}
