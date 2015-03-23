package test.mocksAndresources;

import java.util.Random;

import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.IMessageService;
import com.example.mockdemo.messenger.enums.ConnectionStatus;
import com.example.mockdemo.messenger.enums.SendingStatus;

public class MessageServiceSimpleImpl implements IMessageService {

	private Random random = new Random();

	public ConnectionStatus checkConnection(String server) {
		if (server.endsWith(".pl")) {
			return ConnectionStatus.SUCCESS;
		}

		return ConnectionStatus.FAILURE;
	}

	public SendingStatus send(String server, String message)
			throws MalformedRecipientException {

		if (message == null || message.length() < 3)
			throw new MalformedRecipientException();

		if (server == null || server.length() < 4)
			throw new MalformedRecipientException();

		if (checkConnection(server) == ConnectionStatus.FAILURE) {
			return SendingStatus.SENDING_ERROR;
		}

		if (random.nextBoolean()) {
			return SendingStatus.SENT;
		}

		return SendingStatus.SENDING_ERROR;
	}
}