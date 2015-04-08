package mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.IMessageService;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.enums.ConnectionStatus;
import com.example.mockdemo.messenger.enums.SendingStatus;

@RunWith(MockitoJUnitRunner.class)
public class MessagerMockitoArgumentMatcherTest {

	// SUT
	private Messenger messenger;
	private IMessageService messageServiceMock;
	
	// Test data
	 private final String WP_PL = "wp.pl";

	@Before
	public void setUp() {
		messageServiceMock = mock(IMessageService.class);
		messenger = new Messenger(messageServiceMock);
	}

	// Argument Matcher example
	@Test
	public void testConnection_WhenGivenGoodServer_Return0() {
		when(messageServiceMock.checkConnection(contains(".pl"))).thenReturn(
				ConnectionStatus.SUCCESS);

		assertEquals(0, messenger.testConnection(WP_PL));

		verify(messageServiceMock).checkConnection(WP_PL);
	}
	
	@Test
	public void sendMessage_WhenGivenServerContainsdotPLandProperLengthOfMessage_Return0()
			throws MalformedRecipientException {
		when(messageServiceMock.send(contains(".pl"), anyString())).thenReturn(
				SendingStatus.SENT);

		assertEquals(0,messenger.sendMessage(WP_PL, "asd"));
		verify(messageServiceMock).send(WP_PL, "asd");
	}
	
}
