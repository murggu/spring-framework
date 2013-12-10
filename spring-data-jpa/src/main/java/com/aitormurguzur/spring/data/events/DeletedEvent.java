package com.aitormurguzur.spring.data.events;

public class DeletedEvent {
	
	protected boolean entityFound = true;

	public boolean isEntityFound() {
		return entityFound;
	}
}
