class bird 
{
     void fly()
     {
          System.out.println("Bird can fly");

     }
}
class Parrot extends bird
{
     void color()
     {
          System.out.println("iam green");
     }
}
class singleparrot extends Parrot
{
     void sing()
     {
          System.out.println("I can sing");
     }
}
class cow extends bird
{
    void whatcoloriam()
    {
     System.out.println("Iam black");
    }
}
public class Main
{
     public static void main(String[] args)
     {
          Parrot p=new Parrot();
          singleparrot sp=new singleparrot();
          cow s=new cow();
          sp.sing();
          p.fly();
          p.color();
          s.whatcoloriam();

     }
} 

//why java do not support multiple inheritance because compiler gets confused

class A
{
void testMethod()
{

}
class B
{
     void Method(){

     }

}
class C extends A,B
{

}
}

    

