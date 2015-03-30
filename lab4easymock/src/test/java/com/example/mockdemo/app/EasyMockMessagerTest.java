package com.example.mockdemo.app;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.IMessageService;
import com.example.mockdemo.messenger.enums.ConnectionStatus;
import com.example.mockdemo.messenger.enums.SendingStatus;

public class EasyMockMessagerTest {

	// SUT
	private Messenger messenger;
	private IMessageService messageServiceMock;

	@Before
	public void setUp() {
		messageServiceMock = createMock(IMessageService.class);
		messenger = new Messenger(messageServiceMock);
	}

	@Test
	public void testConnection_WhenGivenGoodServer_Return0() {
		// Arrange
		String server = "wp.pl";

		expect(messageServiceMock.checkConnection(server)).andReturn(
				ConnectionStatus.SUCCESS).anyTimes();
		replay(messageServiceMock);

		assertEquals(0, messenger.testConnection(server));
		verify(messageServiceMock);
	}

	@Test
	public void testConnection_WhenGivenBadServer_Return1() {
		String server = "wp";
		expect(messageServiceMock.checkConnection(server)).andReturn(
				ConnectionStatus.FAILURE).anyTimes();
		replay(messageServiceMock);
		assertEquals(1, messenger.testConnection(server));
		verify(messageServiceMock);
	}

	@Test
	public void sendMessage_WhenNulls_Return2Exeption()
			throws MalformedRecipientException {
		expect(messageServiceMock.send(null, null)).andThrow(
				new MalformedRecipientException()).anyTimes();
		replay(messageServiceMock);
		assertEquals(2, messenger.sendMessage(null, null));
		verify(messageServiceMock);
	}

	@Test
	public void sendMessage_WhenGivenEmptyServerAndMessage_Return2Exeption()
			throws MalformedRecipientException {
		expect(messageServiceMock.send("", "")).andThrow(
				new MalformedRecipientException()).atLeastOnce();
		replay(messageServiceMock);
		assertEquals(2, messenger.sendMessage("", ""));
		verify(messageServiceMock);
	}

	@Test
	public void sendMessage_WhenGivenProperLengthOfParams_Return0Or1()
			throws MalformedRecipientException {
		expect(messageServiceMock.send("asdf", "asd")).andReturn(
				SendingStatus.SENT).atLeastOnce();
		replay(messageServiceMock);
		assertThat(messenger.sendMessage("asdf", "asd"), equalTo(0));
		verify(messageServiceMock);
	}

	@Test
	public void sendMessage_WhenGivenServerContainsdotPLandProperLengthOfMessage_Return0()
			throws MalformedRecipientException {
		expect(messageServiceMock.send("wp.pl", "asd")).andReturn(
				SendingStatus.SENT).atLeastOnce();
		replay(messageServiceMock);
		assertThat(messenger.sendMessage("wp.pl", "asd"), equalTo(0));
		verify(messageServiceMock);
	}
}
