package lz77;

import java.util.StringTokenizer;

public class Lz77 {

	public static String compress(String plainText) {
		String compressedText = "";
		int d = 0, l = 0;
		char c = 0;
		for (int i = 0; i < plainText.length(); i++) {
			int match = plainText.substring(0, i).lastIndexOf(plainText.charAt(i));
			if (match != -1) {
				d = match;
				while (i < plainText.length() - 2 && plainText.substring(0, i).lastIndexOf(plainText.charAt(i)) != -1) {
					l++;
					i++;
				}
				c = plainText.charAt(i);
			} else {
				d = 0;
				l = 0;
				c = plainText.charAt(i);
			}
			compressedText += (d + "," + l + "," + c + ",");
			l = 0;
		}
		return compressedText;
	}

	public static String decompress(String compressedText) {
		String plainText = "";
		int d = 0, l = 0;
		String c = "";
		StringTokenizer st1 = new StringTokenizer(compressedText, ",");
		while (st1.hasMoreTokens()) {
			d = Integer.parseInt(st1.nextToken());
			l = Integer.parseInt(st1.nextToken());
			c = st1.nextToken();
			if(l!=0) {
				while (l-- != 0) {
					plainText+=plainText.charAt(d++);
				}
			}
			plainText+=c;
		}
		return plainText;
	}

	public static void main(String[] args) {
		String s1 = "aaabbbc";
		String s2 = "0,0,a,0,2,b,3,1,b,0,0,c,";
		System.out.println(compress(s1));
		System.out.println(decompress(s2));
		}
}
