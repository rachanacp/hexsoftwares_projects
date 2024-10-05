import java.util.Scanner;
public class simpleinterest 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the value of amount");
        int P=sc.nextInt();
        System.out.println("enter the time");
        int T=sc.nextInt();
        System.out.println("enter the rate of interest in %");
        int R=sc.nextInt();
        int S;
        S=(P*T*R)/100;
        System.out.println("Simple interest="+S);

    }
}
