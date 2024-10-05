import java.util.Scanner;
public class palindrome 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the value of r");
        int r=sc.nextInt();
        System.out.println("enter the value of n");
        int n=sc.nextInt();
        System.out.println("enter the value of sum");
        int sum=sc.nextInt();
        int temp=r;
        while(n>0)
        {
        n=r%10;
        sum=(sum*10)+n;
        r=r/10;
        }
        if(temp==sum)
        {
           System.out.println("it is palindrome");
        }
        else
        {
            System.out.println("it is not a palindrome");
        }
    }
}
