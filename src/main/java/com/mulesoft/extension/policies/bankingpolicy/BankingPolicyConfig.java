/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.mulesoft.extension.policies.bankingpolicy;

import static org.mule.runtime.core.api.config.MuleProperties.OBJECT_SECURITY_MANAGER;

import javax.inject.Inject;
import javax.inject.Named;

import org.mule.runtime.api.lifecycle.Disposable;
import org.mule.runtime.api.lifecycle.Initialisable;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.core.api.security.SecurityManager;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import com.mulesoft.mule.runtime.gw.api.key.ApiKey;
import com.mulesoft.mule.runtime.gw.api.security.GatewaySecurityProvider;
import com.mulesoft.mule.runtime.gw.api.service.ContractService;

@Configuration(name = "config")
@Operations(BankingPolicyOperations.class)
public class BankingPolicyConfig implements Initialisable, Disposable {
	public static final String TRACKER_DESCRIPTION = "BANKING_POLICY_EXTENSION";

	public static final String BANKING_POLICY_SECURITY_PROVIDER = "banking-policy-security-provider";

	@Inject
	@Named(OBJECT_SECURITY_MANAGER)
	private SecurityManager securityManager;

	@Parameter
	@Optional(defaultValue = "${apiId}")
	private Long apiId;

	@Inject
	private ContractService contractService;

	@Override
	public void dispose() {
		contractService.untrack(apiKey(), TRACKER_DESCRIPTION);

	}

	@Override
	public void initialise() throws InitialisationException {
		contractService.track(apiKey(), TRACKER_DESCRIPTION);

		if (!isProviderPresent(BANKING_POLICY_SECURITY_PROVIDER)) {
			GatewaySecurityProvider provider = new GatewaySecurityProvider(BANKING_POLICY_SECURITY_PROVIDER);
			securityManager.addProvider(provider);

			provider.initialise();
		}

	}

	public ApiKey apiKey() {
		return new ApiKey(apiId);
	}

	private boolean isProviderPresent(String providerName) {
		return securityManager.getProviders().stream().anyMatch(provider -> provider.getName().equals(providerName));
	}

}
