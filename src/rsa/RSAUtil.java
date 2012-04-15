package rsa;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.naming.ConfigurationException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.security.rsa.RSAKeyPairGenerator;

public class RSAUtil {

	
	 private static String RSAKeyStore = "F:/RSAKey.txt";   
	    /**  
	     * * ������Կ�� *  
	     *   
	     * @return KeyPair *  
	     * @throws EncryptException  
	     */  
	 public static KeyPair generateKeyPair() throws Exception {   
	        try {   
	            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",   
	                    new org.bouncycastle.jce.provider.BouncyCastleProvider());   
	            final int KEY_SIZE = 1024;// ûʲô��˵���ˣ����ֵ��ϵ������ܵĴ�С�����Ը��ģ����ǲ�Ҫ̫�󣬷���Ч�ʻ��   
	            keyPairGen.initialize(KEY_SIZE, new SecureRandom());   
	            KeyPair keyPair = keyPairGen.generateKeyPair();   
	            System.out.println("===============================");
	            System.out.println(keyPair.getPrivate());  
	            System.out.println("===============================");
	            System.out.println(keyPair.getPublic());   
	            System.out.println("===============================");
	            saveKeyPair(keyPair);   
	            return keyPair;   
	        } catch (Exception e) {   
	            throw new Exception(e.getMessage());   
	        }   
	    }   

	 public static KeyPair getKeyPair() throws Exception {   
	        FileInputStream fis = new FileInputStream(RSAKeyStore);   
	        ObjectInputStream oos = new ObjectInputStream(fis);   
	        KeyPair kp = (KeyPair) oos.readObject();   
	        oos.close();   
	        fis.close();   
	        return kp;   
	    }   
	  
	    public static void saveKeyPair(KeyPair kp) throws Exception {   
	  
	        FileOutputStream fos = new FileOutputStream(RSAKeyStore);   
	        ObjectOutputStream oos = new ObjectOutputStream(fos);   
	        // ������Կ   
	        oos.writeObject(kp);   
	        oos.close();   
	        fos.close();   
	    }   

	    /**  
	     * * ���ɹ�Կ *  
	     *   
	     * @param modulus *  
	     * @param publicExponent *  
	     * @return RSAPublicKey *  
	     * @throws Exception  
	     */  
	    public static RSAPublicKey generateRSAPublicKey(byte[] modulus,   
	            byte[] publicExponent) throws Exception {   
	        KeyFactory keyFac = null;   
	        try {   
	            keyFac = KeyFactory.getInstance("RSA",   
	                    new org.bouncycastle.jce.provider.BouncyCastleProvider());   
	        } catch (NoSuchAlgorithmException ex) {   
	            throw new Exception(ex.getMessage());   
	        }   
	  
	        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(   
	                modulus), new BigInteger(publicExponent));   
	        try {   
	            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);   
	        } catch (InvalidKeySpecException ex) {   
	            throw new Exception(ex.getMessage());   
	        }   
	    }   
	  
	    /**  
	     * * ����˽Կ *  
	     *   
	     * @param modulus *  
	     * @param privateExponent *  
	     * @return RSAPrivateKey *  
	     * @throws Exception  
	     */  
	    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,   
	            byte[] privateExponent) throws Exception {   
	        KeyFactory keyFac = null;   
	        try {   
	            keyFac = KeyFactory.getInstance("RSA",   
	                    new org.bouncycastle.jce.provider.BouncyCastleProvider());   
	        } catch (NoSuchAlgorithmException ex) {   
	            throw new Exception(ex.getMessage());   
	        }   
	  
	        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(   
	                modulus), new BigInteger(privateExponent));   
	        try {   
	            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);   
	        } catch (InvalidKeySpecException ex) {   
	            throw new Exception(ex.getMessage());   
	        }   
	    }   

	    /**  
	     * * ���� *  
	     *   
	     * @param key  
	     *            ���ܵ���Կ *  
	     * @param data  
	     *            �����ܵ��������� *  
	     * @return ���ܺ������ *  
	     * @throws Exception  
	     */  
	    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {   
	        try {   
	            Cipher cipher = Cipher.getInstance("RSA",   
	                    new org.bouncycastle.jce.provider.BouncyCastleProvider());   
	            cipher.init(Cipher.ENCRYPT_MODE, pk);   
	            int blockSize = cipher.getBlockSize();// ��ü��ܿ��С���磺����ǰ����Ϊ128��byte����key_size=1024   
	            // ���ܿ��СΪ127   
	            // byte,���ܺ�Ϊ128��byte;��˹���2�����ܿ飬��һ��127   
	            // byte�ڶ���Ϊ1��byte   
	            int outputSize = cipher.getOutputSize(data.length);// ��ü��ܿ���ܺ���С   
	            int leavedSize = data.length % blockSize;   
	            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1  
	                    : data.length / blockSize;   
	            byte[] raw = new byte[outputSize * blocksSize];   
	            int i = 0;   
	            while (data.length - i * blockSize > 0) {   
	                if (data.length - i * blockSize > blockSize)   
	                    cipher.doFinal(data, i * blockSize, blockSize, raw, i   
	                            * outputSize);   
	                else  
	                    cipher.doFinal(data, i * blockSize, data.length - i   
	                            * blockSize, raw, i * outputSize);   
	                // ������doUpdate���������ã��鿴Դ�������ÿ��doUpdate��û��ʲôʵ�ʶ������˰�byte[]�ŵ�   
	                // ByteArrayOutputStream�У������doFinal��ʱ��Ž����е�byte[]���м��ܣ����ǵ��˴�ʱ���ܿ��С�ܿ����Ѿ�������   
	                // OutputSize����ֻ����dofinal������   
	  
	                i++;   
	            }   
	            return raw;   
	        } catch (Exception e) {   
	            throw new Exception(e.getMessage());   
	        }   
	    }   
	  
	    /**  
	     * * ���� *  
	     *   
	     * @param key  
	     *            ���ܵ���Կ *  
	     * @param raw  
	     *            �Ѿ����ܵ����� *  
	     * @return ���ܺ������ *  
	     * @throws Exception  
	     */  
	    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {   
	        try {   
	            Cipher cipher = Cipher.getInstance("RSA",   
	                    new org.bouncycastle.jce.provider.BouncyCastleProvider());   
	            cipher.init(cipher.DECRYPT_MODE, pk);   
	            int blockSize = cipher.getBlockSize();   
	            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);   
	            int j = 0;   
	  
	            while (raw.length - j * blockSize > 0) {   
	                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));   
	                j++;   
	            }   
	            return bout.toByteArray();   
	        } catch (Exception e) {   
	            throw new Exception(e.getMessage());   
	        }   
	    } 
	    public static  Key loadKey(String value, int type)throws ConfigurationException, NoSuchAlgorithmException,    
        InvalidKeySpecException {    
		    //PropertiesConfiguration config = new PropertiesConfiguration(filename);    
		    KeyFactory keyFactory = KeyFactory.getInstance("RSA",    
		            new BouncyCastleProvider());    
		
		    if (type == 0) {    
		        // privateKey    
		        String privateKeyValue = value ;
		        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(    
		                toBytes(privateKeyValue));    
		        PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);    
		        return privateKey;    
		
		    } else {    
		        // publicKey    
		        String privateKeyValue = value ;  
		        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(    
		                toBytes(privateKeyValue));    
		        PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);    
		        return publicKey;    
		    }    
		}    
	    public static final byte[] toBytes(String s) {    
	        byte[] bytes;    
	        bytes = new byte[s.length() / 2];    
	        for (int i = 0; i < bytes.length; i++) {    
	            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),    
	                    16);    
	        }    
	        return bytes;    
	    }    

	    public static String toHexString(byte[] b) {    
	        StringBuilder sb = new StringBuilder(b.length * 2);    
	        for (int i = 0; i < b.length; i++) {    
	            sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);    
	            sb.append(HEXCHAR[b[i] & 0x0f]);    
	        }    
	        return sb.toString();    
	    }    
	    private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',    
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };    
   

	    /**  
	     * * *  
	     *   
	     * @param args *  
	     * @throws Exception  
	     */  
	    public static void main(String[] args) throws Exception {   
	        
	    	//������Կ�ļ�
	    	//RSAPublicKey rsap = (RSAPublicKey) RSAUtil.generateKeyPair().getPublic();   
	        
	       /* 
	        String test = "123456";   
	        byte[] en_test = encrypt(getKeyPair().getPublic(), test.getBytes());  
	        System.out.println("���ܺ�"+new String(en_test));
	        byte[] de_test = decrypt(getKeyPair().getPrivate(), en_test);   
	        System.out.println(new String(de_test)); */
	    	
	    	
	    	KeyFactory keyFactory = KeyFactory.getInstance("RSA",    
		            new BouncyCastleProvider());    
	    	String pub_modulus="801AE554D266A9D1EE8B31B6F010E6A7BAF7506E6A5198159F1AAC45CAD7BE1738352C38A8DDDC44228315A7DA572CADC1689D37345B1713C1A5D2042449807F75184D7DCD2010DAC70C1DE0F9D61F8E2E61491B5F6924B600B64132BC18C7212DEDD3651064E71E79A3D98D94E02C5D46767A47B362A84A31AE9D9AB8E793A9";
	    	String pub_exponent="DEE3";
	    	//String pri_modulus="801AE554D266A9D1EE8B31B6F010E6A7BAF7506E6A5198159F1AAC45CAD7BE1738352C38A8DDDC44228315A7DA572CADC1689D37345B1713C1A5D2042449807F75184D7DCD2010DAC70C1DE0F9D61F8E2E61491B5F6924B600B64132BC18C7212DEDD3651064E71E79A3D98D94E02C5D46767A47B362A84A31AE9D9AB8E793A9";
	    	String pri_exponent="C74294139C04AF38A32FC5CC27720F4049CED24547B28192520E9D1428A97FEF6D1ED8C29FD6BE0EFB9EE17321E9ED6C6E5F205B1B97CD806600EC6F165BA2878D62110B8A4893F084242CCCB7A22079FEFBC86815DC82D7BE455AA11646FD0C913CF9960E45AE0C2AF82D36795E60750467089A93111C85521E6C156183DEF";
	    	
	    	//RSAPublicKey pubk=RSAUtil.generateRSAPublicKey(pub_modulus.getBytes(),pub_exponent.getBytes());
	    	X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(    
	                toBytes(pub_modulus));    
	        PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);  
	    	
	    	
	    	//RSAPrivateKey prik = RSAUtil.generateRSAPrivateKey(pub_modulus.getBytes(),pri_exponent.getBytes());
	    	PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(    
	                toBytes(pri_exponent));    
	        PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8); 
	    	
	    	String str="123456";
	    	System.out.println("����ǰ��"+str);
	    	byte[] en_test = RSAUtil.encrypt(publicKey, str.getBytes());
	    	//System.out.println(new String(en_test));
	    	byte[] de_test = RSAUtil.decrypt(privateKey, en_test); 
	    	System.out.println("���ܺ�"+toHexString(en_test));
	    	System.out.println("���ܺ�"+toHexString(de_test));

	    }   


}
