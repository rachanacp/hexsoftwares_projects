package java;

public class exceptionhandling 
{
    public static void main(String[] args)
    {
        try
        {
            //risky code that may handle cause an exception
            int a=10/0;
            System.out.println("result="+a);
        }
        catch(ArithmeticException e)
        //handle the exception
        {
            System.out.println("NUMBER CANNOT BE DIVIDE BY ZERO");
        }
        finally
        {
            //code that always executes
            System.out.println("this is finally block");
        }
            System.out.println("rest of the code....!!");
    }
}

