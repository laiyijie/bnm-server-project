package cn.bangnongmang.server.controller.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.Resource;

public class PingxxUtil {

	public static String getStringFromResource(Resource resource) throws Exception {
		InputStreamReader inReader = new InputStreamReader(resource.getInputStream(), "UTF-8");
		BufferedReader bf = new BufferedReader(inReader);
		StringBuilder sb = new StringBuilder();
		String line;
		do {
			line = bf.readLine();
			if (line != null) {
				if (sb.length() != 0) {
					sb.append("\n");
				}
				sb.append(line);
			}
		} while (line != null);

		return sb.toString();
	}

	public static PublicKey getPubKey(Resource resource) throws Exception {
		String pubKeyString = getStringFromResource(resource);
		pubKeyString = pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
		byte[] keyBytes = Base64.decodeBase64(pubKeyString);

		// generate public key
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(spec);
		return publicKey;
	}

	public static boolean verifyData(String dataString, String signatureString, PublicKey publicKey)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
		byte[] signatureBytes = Base64.decodeBase64(signatureString);
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(dataString.getBytes("UTF-8"));
		return signature.verify(signatureBytes);
	}
}
