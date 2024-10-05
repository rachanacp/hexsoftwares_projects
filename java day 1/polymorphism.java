class poly 
{
    public int add(int a,int b)
    {
        return a+b;
    }
    //overload method for adding 3 integers
    public int add(int a,int b,int c)
    {
        return a+b+c;
    }
    //overload method for adding two doubles
    public double add(double a,double b,double c)
    {
        return a+b;
    }
}
public class polymorphism
{
    public static void main(String[] args) 
    {
         poly p=new poly();
         System.out.println("sum of integers:"+p.add(10,20));
         System.out.println("sum of integers:"+p.add(10,20,30));
         System.out.println("sum of integers:"+p.add(10.5,20.5,30.5));
    }
}
