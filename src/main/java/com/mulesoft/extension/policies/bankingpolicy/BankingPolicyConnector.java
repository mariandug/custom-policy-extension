/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.mulesoft.extension.policies.bankingpolicy;

import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mulesoft.extension.policies.bankingpolicy.exceptions.BankingPolicyErrorTypes;


@Extension(name = "template-banking-policy")
@Configurations({BankingPolicyConfig.class})
@ErrorTypes(BankingPolicyErrorTypes.class)
public class BankingPolicyConnector {
	private static final Logger LOGGER = LoggerFactory.getLogger(BankingPolicyConnector.class);
	
	public static void main(String[] args) {
		LOGGER.info("BankingPolicyConnector started");
		
		BankingPolicyOperations operations=new BankingPolicyOperations();
		operations.validateJwt(null, null, null, null, null, null, null);
	}
}
