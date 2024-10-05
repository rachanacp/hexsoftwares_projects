import java.util.Scanner;
public class calculator 
{
    public static void main(String[] args) {
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("enter first number");
            int a=sc.nextInt();
            System.out.println("enter second number");
            int b=sc.nextInt();
            int c;
            System.out.println("enter your choice");
            int ch=sc.nextInt();
            2
            switch(ch)
            {
                case 1:
                     c=a+b;
                     System.out.println("c="+c);
                     break;
                case 2:
                     c=a-b;
                     System.out.println("c="+c);
                     break;
                case 3:
                     c=a*b;
                     System.out.println("c="+c);
                     break;
                case 4:
                     c=a/b;
                     System.out.println("c="+c);
                     break;

            }
        }
    }
}
