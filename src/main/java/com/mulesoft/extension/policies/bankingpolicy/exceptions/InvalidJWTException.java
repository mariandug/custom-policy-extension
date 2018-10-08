/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.mulesoft.extension.policies.bankingpolicy.exceptions;

import static com.mulesoft.extension.policies.bankingpolicy.exceptions.BankingPolicyErrorTypes.INVALID_JWT;

import org.mule.runtime.extension.api.exception.ModuleException;

public class InvalidJWTException extends ModuleException {

	private static final long serialVersionUID = 4293937020863079653L;

	public InvalidJWTException(Throwable cause) {
		super("Invalid JWT", INVALID_JWT, cause);
	}

	public InvalidJWTException() {
		super("Invalid JWT", INVALID_JWT);
	}

}
