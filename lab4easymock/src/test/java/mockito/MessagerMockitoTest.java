package mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.IMessageService;
import com.example.mockdemo.messenger.enums.ConnectionStatus;

@RunWith(MockitoJUnitRunner.class)
public class MessagerMockitoTest {
	// SUT
	private Messenger messenger;
	private IMessageService messageServiceMock;
	

	@Before
	public void setUp() {
		messageServiceMock = mock(IMessageService.class);
		messenger = new Messenger(messageServiceMock);
	}

	@Test
	public void testConnection_WhenGivenGoodServer_Return0() {
		// Arrange
		String server = "wp.pl";

		when(messageServiceMock.checkConnection(server)).thenReturn(
				ConnectionStatus.SUCCESS);

		assertEquals(0, messenger.testConnection(server));
		verify(messageServiceMock).checkConnection(server);
	}
}
