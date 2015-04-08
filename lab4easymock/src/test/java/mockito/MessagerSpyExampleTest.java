package mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import test.mocksAndresources.MessageServiceSimpleImpl;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.enums.ConnectionStatus;

@RunWith(MockitoJUnitRunner.class)
public class MessagerSpyExampleTest {

	// SUT
	private Messenger messenger;

	@Spy
	private MessageServiceSimpleImpl spy = spy(new MessageServiceSimpleImpl());
	private final String WP_PL = "wp.pl";

	// SPY example
	@Test
	public void testConnection_WhenGivenGoodServer_Return0___() {
		messenger = new Messenger(spy);

		when(spy.checkConnection(contains(".pl"))).thenAnswer(
				new Answer<ConnectionStatus>() {
					@Override
					public ConnectionStatus answer(InvocationOnMock invocation)
							throws Throwable {
						if (invocation.getArguments()[0].toString().endsWith(
								".pl")) {
							return ConnectionStatus.FAILURE;
						}

						return ConnectionStatus.FAILURE;
					}
				});
		assertEquals(1, messenger.testConnection(WP_PL));

		verify(spy).checkConnection(WP_PL);

	}

}
