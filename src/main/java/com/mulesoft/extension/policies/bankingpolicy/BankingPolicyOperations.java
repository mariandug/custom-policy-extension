/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.mulesoft.extension.policies.bankingpolicy;

import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mulesoft.extension.policies.bankingpolicy.exceptions.InvalidJWTException;

public class BankingPolicyOperations {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankingPolicyOperations.class);

	@Summary("Check if API has a contract with the specified client and the secret matches")
	// @OutputResolver(output = ClientIdEnforcementMetadataResolver.class)
	// @Throws(ClientIdEnforcementErrorTypeProvider.class)
	public void validateJwt(@Config BankingPolicyConfig config, @Config String token,
			@Config String encryptionAlghorithm, @Config String encryptionKey, @Config String[] jwks,
			@Config String signatureAlghorithm, @Config String issuer) {
		LOGGER.info("encryptionAlghotitm=" + encryptionAlghorithm);
		LOGGER.info("jwks=" + jwks);
		LOGGER.info("signatureAlghoritm=" + signatureAlghorithm);
		LOGGER.info("issuer=" + issuer);

		// for development tests
		token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
		encryptionKey = "secret";
		issuer = "auth0";
		try {
			Algorithm algorithm = getAlgorithm(encryptionAlghorithm, encryptionKey);

			JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build(); // Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);
			LOGGER.info("JWT token verified");
			LOGGER.info("alg=" + jwt.getAlgorithm());
			LOGGER.info("typ=" + jwt.getType());
			LOGGER.info("iss=" + jwt.getIssuer());
			LOGGER.info("exp=" + jwt.getExpiresAt());
			LOGGER.info("nbf=" + jwt.getNotBefore());
			LOGGER.info("iat=" + jwt.getIssuedAt());
		} catch (JWTVerificationException exception) {
			LOGGER.warn("exception=" + exception);
			throw new InvalidJWTException(exception);
		}
	}

	private Algorithm getAlgorithm(String encryptionAlghorithm, String encryptionKey) {
		Algorithm retVal = null;
		
		if (encryptionAlghorithm != null) {
			switch (encryptionAlghorithm) {
			case "HMAC256":
				retVal = Algorithm.HMAC256(encryptionKey);
				break;
			default:
				retVal = Algorithm.HMAC256(encryptionKey);
			}
		} else {
			retVal = Algorithm.HMAC256(encryptionKey);
		}

		return retVal;
	}

}
