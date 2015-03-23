package com.example.mockdemo.app;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import test.categories.SendMessage;
import test.categories.TestConnection;
import test.mocksAndresources.MessageServiceSimpleImpl;

import com.example.mockdemo.messenger.IMessageService;

public class DummyTest {
	IMessageService messageServiceMock;

	// Arrange
	@Before
	public void arrange() {
		messageServiceMock = new MessageServiceSimpleImpl();
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
				anyOf(equalTo(0), equalTo(1)));
	}
}