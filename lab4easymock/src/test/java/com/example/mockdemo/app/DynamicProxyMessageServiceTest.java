package com.example.mockdemo.app;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import test.categories.SendMessage;
import test.categories.TestConnection;

import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.IMessageService;
import com.example.mockdemo.messenger.enums.ConnectionStatus;
import com.example.mockdemo.messenger.enums.SendingStatus;

public class DynamicProxyMessageServiceTest {

	public InvocationHandler ih = new IMessageServiceHandler();
	IMessageService messageServiceMock;

	// Arrange
	@Before
	public void arrange() {
		messageServiceMock = (IMessageService) Proxy.newProxyInstance(
				IMessageService.class.getClassLoader(),
				new Class[] { IMessageService.class }, ih);
	}

	@Category(TestConnection.class)
	@Test
	public void testConnection_WhenGivenGoodServer_Return0() {
		// Act
		Messenger messenger = new Messenger(messageServiceMock);
		// Assert
		assertEquals(0, messenger.testConnection("wp.pl"));
	}

	@Category(TestConnection.class)
	@Test
	public void testConnection_WhenGivenBadServer_Return1() {
		Messenger messenger = new Messenger(messageServiceMock);
		assertEquals(1, messenger.testConnection("wp"));
	}

	@Category(SendMessage.class)
	@Test
	public void sendMessage_WhenNulls_Return2Exeption() {
		Messenger messenger = new Messenger(messageServiceMock);
		assertEquals(2, messenger.sendMessage(null, null));
	}
	
	@Category(SendMessage.class)
	@Test
	public void sendMessage_WhenGivenEmptyServerAndMessage_Return2Exeption() {
		Messenger messenger = new Messenger(messageServiceMock);
		assertEquals(2, messenger.sendMessage("", ""));
	}

	@Category(SendMessage.class)
	@SuppressWarnings("unchecked")
	@Test
	public void sendMessage_WhenGivenProperLengthOfParams_Return0Or1() {
		Messenger messenger = new Messenger(messageServiceMock);
		assertThat(messenger.sendMessage("asdf", "asd"),
				anyOf(equalTo(0), equalTo(1)));
	}

	@SuppressWarnings("unchecked")
	@Category(SendMessage.class)
	@Test
	public void sendMessage_WhenGivenServerContainsdotPLandProperLengthOfMessage_Return0() {
		Messenger messenger = new Messenger(messageServiceMock);
		assertThat(messenger.sendMessage("as.pl", "asd"),
				anyOf(equalTo(0), equalTo(1))); // error
	}

	class IMessageServiceHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if ("checkConnection".equals(method.getName())) {
				if (args[0].toString().contains(".pl")) {
					return ConnectionStatus.SUCCESS;
				} else {
					return ConnectionStatus.FAILURE;
				}
			}

			if ("send".equals(method.getName())) {
				if (args[0] == null || args[1] == null) {
					throw new MalformedRecipientException();
				}

				String server = args[0].toString();
				String message = args[1].toString();

				if (server.length() < 4 || message.length() < 3) {
					throw new MalformedRecipientException();
				}
				if (server.contains(".pl")) {
					return SendingStatus.SENT;
				} else {
					return SendingStatus.SENDING_ERROR;
				}
			}

			return null;
		}
	}
}
