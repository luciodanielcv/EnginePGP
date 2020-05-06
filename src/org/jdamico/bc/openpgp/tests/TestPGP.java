package org.jdamico.bc.openpgp.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPGP {

	public static void main(String[] args) throws Exception {
		
		TestBCOpenPGP pgp = new TestBCOpenPGP();
		
		InputStream is = null;
		Properties prop = new Properties();
		try {
		    is = new FileInputStream(args[0]);
		} catch (FileNotFoundException ex) {
		}
		try {
		    prop.load(is);
		} catch (IOException ex) {
		}
		
		String cipheredFile = prop.getProperty("pgp.dxc.ciphered.file");
		String privateKey = prop.getProperty("pgp.dxc.private.key");
		String outputFile = prop.getProperty("pgp.dxc.output.file");
		String passphrase = prop.getProperty("pgp.dxc.passpharse");
		
		
		pgp.decrypt( cipheredFile, privateKey, outputFile, passphrase );
		

	}

}
