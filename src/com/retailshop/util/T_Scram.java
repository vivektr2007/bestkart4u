package com.retailshop.util;

import java.io.IOException;

//import org.apache.log4j.Logger;

public class T_Scram {
   public static void main(String args[]) {
        try {
            String result = null;
            StringBuffer encstr = new StringBuffer();

            if (args.length != 1)
                return;

            if (args[0].equalsIgnoreCase("E")) {
                System.out.println("\nPlease enter a string to encrypt:");
                int x = 0;

                while ((x = System.in.read()) != 13 && x != 10)
                    encstr.append((char) x);

                
                System.out.println("\nPlease enter the length you desire for the encrypted string:\n** Notes: Length must be at least 3 char's longer than entered string (max=90)."
                                + "\n**        If you want the default length of 40, just press ENTER.");

                StringBuffer enclen = new StringBuffer();
                System.in.skip(1);

                while ((x = System.in.read()) != 13 && x != 10)
                    enclen.append((char) x);

                if (enclen.length() == 0)
                    enclen.append(40);
                System.out.println(((result = encrypt(encstr.toString(), Integer.parseInt(enclen.toString()))) != null)
                        ? "\nEncrypted string is printed below:\n" + result
                        : "\nYou have not passed valid parameters to encrypt.");
            }

            else if (args[0].equalsIgnoreCase("D")) {
            	
                System.out.println("\nPlease enter a string to decrypt:");
                int x = 0;

                while ((x = System.in.read()) != 13 && x != 10)
                    encstr.append((char) x);

                result = decrypt(encstr.toString());
                
                System.out.println(((result = decrypt(encstr.toString())) != null) ? "\nDecrypted string is printed below:\n"
                        + result : "\nYou have not passed a valid string to decrypt.");
            }
        } catch (IOException ioExcetpion) {
            //logger.error("There was an IO problem while executing this class : ", ioExcetpion);
        } catch (NumberFormatException exception) {
            //logger.error("You must enter an integer of 90 or less for the length of the encrypted string.", exception);
        }
    }

    public static String encrypt(String encstr) {
        return encrypt(encstr, 40);
    }

    public static String encrypt(String encstr, int enclen) {

        if (encstr == null)
            return null;

        int len = encstr.length();

        if (len < 1)
            return null;

        if (enclen > 90 || enclen < len + 3)
            return null;

        enclen -= 3;
        StringBuffer lev1scram = new StringBuffer();

        for (int i = 0, j = 10; i < len; i++, j += 2 * i + 3) {
            int shift = (int) ((i * i + j / 2) % 95);
            int val = (int) encstr.charAt(i) + shift;
            val = (val > 125) ? val - 95 : val;
            val = (val >= 92) ? ++val : val;
            lev1scram.append((char) val);
        }

        lev1scram = lev1scram.reverse();
        int half = len / 2;
        String part1 = lev1scram.substring(0, half);
        String part2 = lev1scram.substring(half, len);
        int pad = enclen - len;
        int frt = pad / 3;
        int mid = 2 * frt;
        int seed1 = (int) lev1scram.charAt(0);
        seed1 *= len + 13;
        int seed2 = (int) lev1scram.charAt(len / 2);
        StringBuffer lev2scram = new StringBuffer();
        int temp = 0;

        for (int i = 1, j = 50; i <= frt; i++, j--)
            lev2scram.append((char) (((temp = (int) ((i * seed1 + j * j + seed2) % 93 + 33)) >= 92) ? ++temp : temp));

        lev2scram.append(part2);

        for (int i = ++frt, j = 50; i <= mid; i++, j--)
            lev2scram.append((char) (((temp = (int) ((i * seed1 + j * j + seed2) % 93 + 33)) >= 92) ? ++temp : temp));

        lev2scram.append(part1);

        for (int i = ++mid, j = 50; i <= pad; i++, j--)
            lev2scram.append((char) (((temp = (int) ((i * seed1 + j * j + seed2) % 93 + 33)) >= 92) ? ++temp : temp));

        int lendig1 = ((temp = len / 10 + 33 + (((int) lev2scram.charAt(enclen / 4)) % 84)) >= 92) ? ++temp : temp;
        int lendig2 = ((temp = len % 10 + 33 + (((int) lev2scram.charAt(enclen * 3 / 4)) % 84)) >= 92) ? ++temp : temp;
        int totlen = ((temp = enclen + 33) >= 92) ? ++temp : temp;
        return lev2scram.insert(0, (char) (lendig2)).append((char) (lendig1)).append((char) (totlen)).toString();
    }

    public static String decrypt(String encstr) {

        int temp = 0;
        int enclen = ((temp = (int) encstr.charAt(encstr.length() - 1)) >= 92) ? --temp - 33 : temp - 33;

        if (encstr.length() != enclen + 3)
            return null;

        int lendig1 =
                (((temp = (int) encstr.charAt(enclen + 1)) >= 92) ? --temp : temp) - 33
                        - ((int) encstr.charAt(enclen / 4 + 1) % 84);
        int lendig2 =
                (((temp = (int) encstr.charAt(0)) >= 92) ? --temp : temp) - 33
                        - ((int) encstr.charAt(enclen * 3 / 4 + 1) % 84);
        int len = lendig1 * 10 + lendig2;
        int pad = enclen - len;
        int half = len / 2;
        int frt = pad / 3;
        int mid = 2 * frt++ + 1 + len - half;
        String part1 = encstr.substring(mid, mid + half);
        String part2 = encstr.substring(frt, frt + len - half);
        String lev1scram = new StringBuffer(part1).append(part2).reverse().toString();
        StringBuffer unscram = new StringBuffer();

        for (int i = 0, j = 10; i < len; i++, j += 2 * i + 3) {
            int shift = (int) ((i * i + j / 2) % 95);
            int val = (int) lev1scram.charAt(i);
            val = (val >= 92) ? --val : val;
            val -= shift;
            val = (val < 32) ? 95 + val : val;
            unscram.append((char) val);
        }

        return unscram.toString();
    }
}
