/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.mulesoft.extension.policies.bankingpolicy.exceptions;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public enum BankingPolicyErrorTypes implements ErrorTypeDefinition<BankingPolicyErrorTypes>{
	INVALID_JWT
}
