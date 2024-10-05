class animal
{
    public void sound()
    {
        System.out.println("animal make Sound");
    }
}
class Dog extends animal
{
    //subclass method override the superclass method
    public void sound()
    {
        System.out.print("the dog can bark");
    }
}
class cat extends animal
{
    //subclass method override the superclass method
    public void sound()
    {
        System.out.println("the cat moves");
    }
}
public class Override
{
    public static void main(String[] args) 
        {
            animal myanimal=new animal();
            animal myDog=new Dog();
            animal mycat=new cat();
            myanimal.sound();
            myDog.sound();
            mycat.sound();
            
        }
}

