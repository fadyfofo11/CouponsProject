package com.database.web.other;

import com.database.facades.ClientFacade;

public class Session {

	private ClientFacade service;
	private long lastAccessed;

	public Session(ClientFacade service, long lastAccessed) {
		super();
		this.service = service;
		this.lastAccessed = lastAccessed;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public ClientFacade getService() {
		return service;
	}

}
