public class days


{
    public static void main(String[] args)
    {
       int choice=4;
       
       switch(choice)
       {
        case 1:
            System.out.println("Monday:I need Coffee day");
            break;
        case 2:
            System.out.println("Tuesday:Still not friday");
            break;
        case 3:
            System.out.println("Wednesday:hump day");
            break;
        case 4:
            System.out.println("Thursday:Almost friday");
            break;
        case 5:
            System.out.println("Friday:party tym");
            break;
        case 6:
            System.out.println("Saturday:weekend");
            break;
        case 7:
            System.out.println("Sunday:Oh Shut!! tomorrow is monday");
            break;
        default:
            System.out.println("error:you entered a day that doesn't exist");
            break;
       }
    }
}
