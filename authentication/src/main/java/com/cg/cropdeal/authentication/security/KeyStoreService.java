package com.cg.cropdeal.authentication.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
public class KeyStoreService {
	
	@Value("${jwtSecretKey}")
	private String secretKey;
	@Value("${keyStore.password}")
	private String keyStorePassword;
	@Value("${keyStore.key.password}")
	private String keyPassword;
	
	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	private void createKeyStore() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		keyStore.load(null, keyStorePassword.toCharArray());
		FileOutputStream fileOutputStream = new FileOutputStream("keystore.jceks");
		keyStore.store(fileOutputStream, keyStorePassword.toCharArray());
	}
	
	public SecretKey loadKeyStore()
		throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, UnrecoverableEntryException {
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream("keystore.jceks");
		} catch (FileNotFoundException exception) {
			System.out.println("Created a new key store");
			createKeyStore();
		}
		
		if (file == null) {
			file = new FileInputStream("keystore.jceks");
		}
		
		keyStore.load(file, keyStorePassword.toCharArray());
		file.close();
		
		// key pwd
		
		KeyStore.ProtectionParameter entryPwd = new KeyStore.PasswordProtection(keyPassword.toCharArray());
		
		KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("jwtSigningKey", entryPwd);
		
		if (keyEntry == null) {
			// create an entry
			SecretKey jwtSigningKey = (SecretKey) getSigningKey();
			
			KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(jwtSigningKey);
			
			keyStore.setEntry("jwtSigningKey", secretKeyEntry, entryPwd);
			
			try (FileOutputStream keyStoreOutputStream = new FileOutputStream("keystore.jceks")) {
				keyStore.store(keyStoreOutputStream, keyStorePassword.toCharArray());
				keyStoreOutputStream.close();
			}
			
			return secretKeyEntry.getSecretKey();
		}
		
		return keyEntry.getSecretKey();
	}
}
