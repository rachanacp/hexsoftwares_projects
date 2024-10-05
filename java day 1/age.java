import java.util.Scanner;
public class age
{   
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter your age");
        int age=sc.nextInt();
        if(age>=18)
            System.out.println("your age is greater than or equal to 18,you are eligible to vote");
        else
            System.out.println("you are not eligible to vote");
    }
        
        
}
      
    

