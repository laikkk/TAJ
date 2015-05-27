package com.example.mockdemo.messenger;

import com.example.mockdemo.messenger.enums.ConnectionStatus;
import com.example.mockdemo.messenger.enums.SendingStatus;

public interface IMessageService {
		
	ConnectionStatus checkConnection(String server);
	
	SendingStatus send(String server, String message) throws MalformedRecipientException;

}
