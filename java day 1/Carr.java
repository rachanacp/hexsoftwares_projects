public class Carr 
{
    String brand;
    int year;
    public Carr(String brand,int year)
    {
    this.brand=brand;
    this.year=year;
    }
    public void DisplayCarrinfo()
    {
       System.out.println("CAr brand:"+brand);
       System.out.println("Manufacturer:"+year);
    }

    public static void main(String[] args) 
    {
        Carr mycar=new Carr("BMW",1995);
        mycar.DisplayCarrinfo();
    }
}
