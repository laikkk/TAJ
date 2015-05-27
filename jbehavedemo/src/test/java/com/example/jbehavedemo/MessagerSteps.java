package com.example.jbehavedemo;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import test.mocksAndresources.MessageServiceSimpleImpl;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.IMessageService;

public class MessagerSteps {
	
	private IMessageService messageService = new MessageServiceSimpleImpl();
	private Messenger messenger;
	private String server;
	private String message;
	
	@Given("a messenger")
	public void messangerSetup(){
		messenger = new Messenger(messageService);
	}
	
	@When("set server to $server")
	public void setServer(String server){
		this.server = server;
	}
	
	@When("set message to $message")
	public void setMessage(String message){
		this.message = message;
	}
	
    @Then("testing connection should return $result")
	public void testingConnectionShouldReturn(int result){
		assertEquals(result, messenger.testConnection(server));
	}
    
    @Then("sending a message should return $result")
  	public void sendingShouldReturn(int result){
  		assertEquals(result, messenger.sendMessage(server, message));
  	}
}
