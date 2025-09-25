package org.bank.service;

public class CustomerInvalidDataException extends RuntimeException {

	private String exceptionmsg;
	public CustomerInvalidDataException() {
	}

	public CustomerInvalidDataException(String exceptionmsg) {
		this.exceptionmsg = exceptionmsg;
	}

	@Override
	public String toString() {
		return "CustomerInvalidDataException [exceptionmsg=" + exceptionmsg + "]";
	}

}
