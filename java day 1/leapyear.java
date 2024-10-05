import java.util.Scanner;
public class leapyear 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the year");
        int year=sc.nextInt();
        if(((year%4==0)&&(year%100!=0))||(year%400==0))
            System.out.println("yes,it is a leap year");
        else
            System.out.println("it is not a leap year");
    }
    
}
