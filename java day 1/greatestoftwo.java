import java.util.Scanner;
public class greatestoftwo 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the number m");
        System.out.println("enter the number n");
        int m=sc.nextInt();
        int n=sc.nextInt();
        if(m>n)
           System.out.println("m is the greatest");
        else
           System.out.println("n is the greatest");
    }
    
}
