package com.cg.cropdeal.cloudconfig;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

@RestController
public class RetrieveKey {
	@GetMapping("/mykey")
	void getKey()
		throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
		FileInputStream inputStream = new FileInputStream("cloudconfig/cropdeal_keystore.jceks");
		String keyStorePassword = "cropdeal";
		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		keyStore.load(inputStream, keyStorePassword.toCharArray());
		
		SecretKey secretKey = (SecretKey) keyStore.getKey("configserverkey", "configserverkey".toCharArray());
		byte[] rawData = secretKey.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(rawData);
		System.out.println(secretKey.getEncoded());
	}
	
}
