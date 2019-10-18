package criptografia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class asimetrico
{
	private final static String ALGORITMO = "RSA";
	
	public static byte[] cifrar (Key llave, String algoritmo, String texto)
	{
		byte[] textoCifrado;
		
		try
		{
			Cipher cifrador = Cipher.getInstance(algoritmo);
			byte[] textoClaro = texto.getBytes();
			
			cifrador.init(Cipher.ENCRYPT_MODE, llave);
			textoCifrado = cifrador.doFinal(textoClaro);
			
			return textoCifrado;
		}
		catch (Exception e)
		{
			System.out.println("Exception:" + e.getMessage());
			return null;
		}
	}
	
	public static byte [] descifrar (Key llave, String algoritmo, byte[] texto)
	{
		byte[] textoClaro = null;
		
		try
		{
			Cipher cifrador = Cipher.getInstance(algoritmo);
			cifrador.init(Cipher.DECRYPT_MODE, llave);
			textoClaro = cifrador.doFinal(texto);
		}
		catch (Exception e)
		{
			System.out.println("Exception: " +  e.getMessage());
			return null;
		}
		
		return textoClaro;
	}
	
	public static void imprimir (byte[] contenido)
	{
		for (int i = 0; i < contenido.length; i++)
			System.out.print(contenido[i] + " ");
		System.out.print("\n");
	}
	
	public static void main (String[] args)
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Escriba el texto que desea cifrar:");
			String texto = in.readLine();
			System.out.println("El texto escrito es: " + texto);
			System.out.print("Texto original: ");
			imprimir (texto.getBytes());
			KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITMO);
			keygen.initialize(1024);
			KeyPair keyPair = keygen.generateKeyPair();
			PublicKey publicK = keyPair.getPublic();
			PrivateKey privateK = keyPair.getPrivate();
			long ini = System.nanoTime();
			byte[] cifrado = cifrar(publicK, ALGORITMO, texto);
			long fin = System.nanoTime();
			System.out.print("Tardó " + (fin-ini) + "ns Texto cifrado: ");
			imprimir (cifrado);
			ini = System.nanoTime();
			byte[] descifrado = descifrar (privateK, ALGORITMO, cifrado);
			fin = System.nanoTime();
			System.out.print("Tardó " + (fin-ini) + "ns Texto descifrado: ");
			imprimir(descifrado);
			System.out.println("Texto original: " + new String(descifrado));		
		} 
		catch (Exception e)
		{
			System.out.println("Exception :" + e.getMessage());
			e.printStackTrace();
		}
	}
}
