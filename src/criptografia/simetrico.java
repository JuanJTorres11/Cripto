package criptografia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class simetrico
{
	private final static String PADDING = "AES/ECB/PKCS5Padding";
	private final static String ALGORITMO = "AES";
	
	public static byte[] cifrar (SecretKey llave, String texto)
	{
		byte[] textoCifrado;
		
		try
		{
			Cipher cifrador = Cipher.getInstance(PADDING);
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
	
	public static byte [] descifrar (SecretKey llave, byte[] texto)
	{
		byte[] textoClaro = null;
		
		try
		{
			Cipher cifrador = Cipher.getInstance(PADDING);
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
			System.out.println("Escriba el texto que desea cifrar:");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String texto = in.readLine();
			System.out.println("El texto escrito es: " + texto);
			System.out.print("Texto original: ");
			imprimir (texto.getBytes());
			KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO);
			SecretKey llave = keygen.generateKey();
			long ini = System.nanoTime();
			byte[] cifrado = cifrar(llave, texto);
			long fin = System.nanoTime();
			System.out.print("Tardó " + (fin-ini) + "ns Texto cifrado: ");
			imprimir (cifrado);
			ini = System.nanoTime();
			byte[] descifrado = descifrar (llave, cifrado);
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
