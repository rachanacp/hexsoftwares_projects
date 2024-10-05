
import java.util.Scanner;

public class greatestofthree 
{
    public static void main(String[] args) {
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("enter the number n1");
            System.out.println("enter the number n2");
            System.out.println("enter the number n3");
            int n1=sc.nextInt();
            int n2=sc.nextInt();
            int n3=sc.nextInt();
            if(n1>n2 && n1>n3)
               System.out.println("n1 is the greatest");
            else if(n2>n1 && n2>n3)
               System.out.println("n2 is the greatest");
            else
               System.out.println("n3 is the greatest");
        }
    }
    
}
