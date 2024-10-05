

public class multiplecatchblocks 
{
    public static void main(String[] args) 
    {
        try
        {
            int arr[]=new int[10];
            arr[10]=30;
        }
        catch(ArithmeticException e)
        {
            System.out.println("Arithmetic exception divisible by zero");
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ArrayIndexOutOfBoundsException:array index out of bound");
        }
        catch(Exception e)
        {
            System.out.println("exception some other exception occured");
        }
        finally
        {
        System.out.println("rest of the code....");
        }
        
    }
    
}
