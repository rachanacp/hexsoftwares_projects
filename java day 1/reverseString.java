import java.util.Scanner;

public class reverseString {

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string:");
        String str1 = sc.nextLine();
        String reverse = " ";
        for (int i = str1.length() - 1; i >= 0; i--) 
        {
            
            reverse+=str1.charAt(i);

        }
        System.out.println("reversed string=" + reverse);
    }
}
