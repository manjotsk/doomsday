
/*
 * This Open Source Code is written by Kass, 
 * "https://medium.com/@cryptokass?source=post_header_lockup"
 * this Code have bee used as core model for BlockChain Development.
 * 
 * 
 * Manjot Singh- (Project Developer)
 * */

package rishman;

import java.util.Date;
import java.security.MessageDigest;

import com.google.*;	

public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;

	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash=calculateHash();
    }
	
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256( 
                previousHash +
                Long.toString(timeStamp) +
                data 
                );
        return calculatedhash;
    }
}

 class StringUtil {
	public static String applySha256(String input){		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); 
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
