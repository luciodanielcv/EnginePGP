package org.jdamico.bc.openpgp.tests;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.jdamico.bc.openpgp.utils.PgpHelper;
import org.jdamico.bc.openpgp.utils.RSAKeyPairGenerator;
import org.junit.Test;


public class TestBCOpenPGP {

	private boolean isArmored = false;
	//private String id = "damico";
	private String passwd = "This is GBimbo PGP keys. Integrations with Kronos cloud. Keep it safe!";
	private boolean integrityCheck = true;


	private String pubKeyFile = "/home/ldcv/eclipse-workspace/OpenPgp-BounceCastle-Example/files/kronosCloud300Public.asc";
	private String privKeyFile = "/home/ldcv/eclipse-workspace/OpenPgp-BounceCastle-Example/files/kronosCloud300SECRET.asc";

	private String plainTextFile = "/home/ldcv/eclipse-workspace/OpenPgp-BounceCastle-Example/files/text.txt"; //create a text file to be encripted, before run the tests
	private String cipherTextFile = "/home/ldcv/eclipse-workspace/OpenPgp-BounceCastle-Example/files/medical_leaves.csv.pgp";
	private String decPlainTextFile = "/home/ldcv/eclipse-workspace/OpenPgp-BounceCastle-Example/files/dec-text.txt";
	//private String signatureFile = "/tmp/signature.txt";

	@Test
	public void genKeyPair() throws InvalidKeyException, NoSuchProviderException, SignatureException, IOException, PGPException, NoSuchAlgorithmException {

//		RSAKeyPairGenerator rkpg = new RSAKeyPairGenerator();
//
//		Security.addProvider(new BouncyCastleProvider());
//
//		KeyPairGenerator    kpg = KeyPairGenerator.getInstance("RSA", "BC");
//
//		kpg.initialize(1024);
//
//		KeyPair                    kp = kpg.generateKeyPair();
//
//		FileOutputStream    out1 = new FileOutputStream(privKeyFile);
//		FileOutputStream    out2 = new FileOutputStream(pubKeyFile);
//
//		rkpg.exportKeyPair(out1, out2, kp.getPublic(), kp.getPrivate(), id, passwd.toCharArray(), isArmored);


	}

	@Test
	public void encrypt() throws NoSuchProviderException, IOException, PGPException{
		FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
		FileOutputStream cipheredFileIs = new FileOutputStream(cipherTextFile);
		PgpHelper.getInstance().encryptFile(cipheredFileIs, plainTextFile, PgpHelper.getInstance().readPublicKey(pubKeyIs), isArmored, integrityCheck);
		cipheredFileIs.close();
		pubKeyIs.close();
	}

	@Test
	public void decrypt( String cipheredFile, String privateKey, String outputFile, String passphrase) throws Exception{

		FileInputStream cipheredFileIs = new FileInputStream(cipheredFile);
		FileInputStream privKeyIn = new FileInputStream(privateKey);
		FileOutputStream plainTextFileIs = new FileOutputStream(outputFile);
		PgpHelper.getInstance().decryptFile(cipheredFileIs, plainTextFileIs, privKeyIn, passphrase.toCharArray());
		cipheredFileIs.close();
		plainTextFileIs.close();
		privKeyIn.close();

//		FileInputStream cipheredFileIs = new FileInputStream(cipherTextFile);
//		FileInputStream privKeyIn = new FileInputStream(privKeyFile);
//		FileOutputStream plainTextFileIs = new FileOutputStream(decPlainTextFile);
//		PgpHelper.getInstance().decryptFile(cipheredFileIs, plainTextFileIs, privKeyIn, passwd.toCharArray());
//		cipheredFileIs.close();
//		plainTextFileIs.close();
//		privKeyIn.close();

		
	}

	@Test
	public void signAndVerify() throws Exception{
		FileInputStream privKeyIn = new FileInputStream(privKeyFile);
		FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
		FileInputStream plainTextInput = new FileInputStream(plainTextFile);
		//FileOutputStream signatureOut = new FileOutputStream(signatureFile);
				
		byte[] bIn = PgpHelper.getInstance().inputStreamToByteArray(plainTextInput);
		//byte[] sig = PgpHelper.getInstance().createSignature(plainTextFile, privKeyIn, signatureOut, passwd.toCharArray(), true);
		//PgpHelper.getInstance().verifySignature(plainTextFile, sig, pubKeyIs);
	}

}
