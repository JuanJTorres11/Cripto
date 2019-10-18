package criptografia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class Digest
{
	public static byte[] getDigest(String algoritmo, byte[] buffer)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance(algoritmo);
			digest.update(buffer);
			return digest.digest();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void imprimirHexa(byte[] byteArray)
	{
		String out = "";
		for (int i = 0; i < byteArray.length; i++)
		{
			if ((byteArray[i] & 0xff) <= 0xf)
				out += "0";
			out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
		}
		System.out.println(out);
	}
	
	public static void main (String args[])
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Escriba el texto al que le desea calcular su digest:");
			String texto = in.readLine();
			System.out.println("El texto escrito es: " + texto);
			System.out.print("Digest MD5 obtenido: ");
			imprimirHexa (getDigest("MD5", texto.getBytes()));
			System.out.print("Digest SHA-1 obtenido: ");
			imprimirHexa (getDigest("SHA1", texto.getBytes()));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
