class Person 
{
    private String name;
    private int age;

    //getter method for name
    public String getName()
    {
        return name;
    }

    //setter method for name
    public void setName(String name)
    {
        //we can add validation here if needed
        if(name!=null && name.isEmpty())
        {
            this.name=name;
        }
        else
        {
            System.out.println("invalid name:"+name);
        }
    }
        public int getAge()
        {
            return age;
        }
        public void setAge(int age)
        {
            if(age>0)
               this.age=age;
            else
               System.out.println("ivalid age:"+age);
              
        }
    }
public class Encapsulation
{

    public static void main(String[] args)
    {
        Person person=new Person();

        //set the name and age using setters method
        person.setName("Rachana");
        person.setAge(20);

        System.out.println("Name:"+person.getName());
        System.out.println("Age:"+person.getAge());

        //this will print an error message
        person.setAge(-5);
    }
}    

