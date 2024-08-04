import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the prime number; ");
        BigInteger p = new BigInteger(sc.nextLine());
        System.out.println("Enter the primitive root of "+p+":");
        BigInteger g = new BigInteger(sc.nextLine());
        System.out.println("Enter the value of x lkess than p");
        BigInteger x = new BigInteger(sc.nextLine());
        BigInteger R1 = g.modPow(x,p);
        System.out.println("R1="+R1);

        System.out.println("Enter the value of y less than p: ");
        BigInteger y = new BigInteger(sc.nextLine());
        BigInteger R2 = g.modPow(y,p);
        System.out.println("R2="+R2);

        BigInteger k1 = R2.modPow(x,p);
        System.out.println("Key calculated at sender side: "+k1);

        BigInteger k2 = R1.modPow(y,p);
        System.out.println("Key calculated at receiver  side: "+k2);

    }
}
