import java.util.Scanner;
public class floatmultiplication 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter first float number");
        double num1=sc.nextDouble();
        System.out.println("enter second float number");
        double num2=sc.nextDouble();
        double product=num1*num2;
        System.out.println("product="+product);
    }
}
