package mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.IMessageService;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.enums.ConnectionStatus;
import com.example.mockdemo.messenger.enums.SendingStatus;

@RunWith(MockitoJUnitRunner.class)
public class MessagerMockitoArgumentCaptureTest {
	// SUT
	private Messenger messenger;
	private IMessageService messageServiceMock;
	
	private final String WP_PL = "wp.pl";
	private final String FOO = "foo";
	ArgumentCaptor<String> messageServicCaptor = ArgumentCaptor
			.forClass(String.class);
	
	@Before
	public void setUp() {
		messageServiceMock = mock(IMessageService.class);
		messenger = new Messenger(messageServiceMock);
	}
	
	// Argument Capture example
	@Test
	public void testConnection_WhenGivenGoodServer_Return0_() {
		when(messageServiceMock.checkConnection(WP_PL)).thenReturn(
				ConnectionStatus.SUCCESS);

		assertEquals(0, messenger.testConnection(WP_PL));

		verify(messageServiceMock).checkConnection(
				messageServicCaptor.capture());
		assertEquals(messageServicCaptor.getValue(), WP_PL);
	}
	
	@Test
	public void sendMessage_WhenGivenServerContainsdotPLandProperLengthOfMessage_Return0()
			throws MalformedRecipientException {
		when(messageServiceMock.send(WP_PL, FOO)).thenReturn(
				SendingStatus.SENT);

		assertThat(messenger.sendMessage(WP_PL, FOO), equalTo(0));
		
		verify(messageServiceMock).send(messageServicCaptor.capture(), messageServicCaptor.capture());
		
		assertEquals(messageServicCaptor.getAllValues().get(0),WP_PL);
		assertEquals(messageServicCaptor.getAllValues().get(1),FOO);
	}
}
