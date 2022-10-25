import java.io.File;
import java.util.*;
public class Driver 
{
    static Scanner read = new Scanner(System.in);
    static int DriverCount=0;
    static String cheapestFare[][] = new String[10][10];
    static int cheapFareRecordCount=0;
    String name;
    String SurName;
    String email;
    String VehicalType;
    double baseFarePrice;
    double baseFareDistance;
    
    public Driver()
    {
        this.name=null;
        this.SurName=null;
        this.email=null;
        this.VehicalType=null;
        this.baseFarePrice=0.0;
        this.baseFareDistance=0.0;
    }
    public Driver(String n, String sn,String em,String Vt, double bfp, double bfd)
    {
        this.name=n;
        this.SurName=sn;
        this.email=em;
        this.VehicalType=Vt;
        this.baseFarePrice=bfp;
        this.baseFareDistance=bfd;
    }
    public static void farecalculate(Driver[] A,int DT, int TU, int CPU)//Calculating the Fare both the case given
    {
        char innn='Y';
        while(innn=='Y'||innn=='y')
        {
            for(int i=0; i<DriverCount; i++)
            {
                if(A[i].baseFareDistance < DT)
                {
                    cheapestFare[cheapFareRecordCount][0]= A[i].email;
                    double Price = A[i].baseFarePrice + (((DT - A[i].baseFareDistance)/TU )*CPU);
                    cheapestFare[cheapFareRecordCount][1]=String.valueOf(Price);
                    cheapFareRecordCount++;
                }
                else
                {
                    cheapestFare[cheapFareRecordCount][0]= A[i].email;
                    double Price = A[i].baseFarePrice;
                    cheapestFare[cheapFareRecordCount][1]=String.valueOf(Price);
                    cheapFareRecordCount++;
                }
            }
            System.out.println("***********************************************************************");
            System.out.println("Email\t\t\tCheapest Fare Calculated");
            for(int j=0; j<cheapFareRecordCount;j++)
            {
                System.out.println(cheapestFare[j][0]+"\t\t\t"+cheapestFare[j][1]);
            }
            System.out.println("***********************************************************************");
            System.out.println("Press 'Y' if you want to look at the latest value of Cheap fares recorded.\nPress 'N' to exit.");
            innn= read.next().charAt(0);
        }   
    }
    public static boolean search(String tempEmail,Driver[] A)
    {
        //System.out.println(DriverCount);
        for(int i=0 ; i<DriverCount; i++)
        {
            if(A[i].email.equalsIgnoreCase(tempEmail))
            {
                //System.out.println(tempEmail+"\t\t\t"+A[i].email);
                return true;
            }
        }
        return false;
    }

    
    public static Integer searchindex(String tempEmail,Driver[] A)
    {
        //System.out.println(DriverCount);
        for(int i=0 ; i<DriverCount; i++)
        {
            if(A[i].email.equalsIgnoreCase(tempEmail))
            {
                //System.out.println(tempEmail+"\t\t\t"+A[i].email);
                return i;
            }
        }
        return -1;
    }

    public static void Display(Driver[] A)
    {
        System.out.println("***********************************************************************************************************************");
        System.out.println("First Name\tLast Name\tEmail\t\t\tVehical Type\tBase Fare Price\t\tBase Fare Distance");

        for(int i=0 ; i<DriverCount; i++)
        {
            System.out.println(A[i].name+"\t\t"+A[i].SurName+"\t\t"+A[i].email+"\t\t"+A[i].VehicalType+"\t\t"+A[i].baseFarePrice+"\t\t\t"
            +A[i].baseFareDistance);
        }
        System.out.println("***********************************************************************************************************************");
    }

    public static void main(String[] args) 
    {
        int c,i,j;
        boolean found= false;
        char in='Y';
        char inn='Y';
        String tempemail;
         Driver[] A = new Driver[10];
        // A.name ="Rohan";
        // A.SurName="Roy";
        // A.VehicalType="Van";
        // A.baseFarePrice=50;
        // A.baseFareDistance=200;

        //System.out.println(A.name);
        

        System.out.println("***********************************************************************************************************************");
        System.out.println("\t\t\t\t\tWelcome to Taxi Fare Calculation !");
        System.out.println("***********************************************************************************************************************");

        while(in=='Y'||in=='y')
        {
            System.out.println("***********************************************************************************************************************");
            System.out.println("\t\t\t\t\t\tMenu Options");
            System.out.println("***********************************************************************************************************************\n");
            System.out.println("Please choose one of the Following options: (use the number to choose your option)\n");
            System.out.println("1.Add/Register Driver\n2.Delete Driver\n3.Update Driver\n4.Display All Drivers\n5.Fare Calculation");
            c=read.nextInt();
            switch (c)
            {
                case 1: //Add
                    
                    System.out.println("Enter Email: ");
                    tempemail= read.next();
                    found = search(tempemail,A);
                    //Search the Email in the existing data if present redirect to Update else Add new Driver.

                    if(!found)
                    {
                        A[DriverCount] = new Driver();
                        A[DriverCount].email=tempemail;
                        System.out.println("Enter First Name: ");
                        A[DriverCount].name= read.next(); 
                        System.out.println("Enter Last Name: ");
                        A[DriverCount].SurName= read.next(); 
                        System.out.println("Enter Vehical Type: ");
                        A[DriverCount].VehicalType= read.next(); 
                        System.out.println("Base Fare Price: ");
                        A[DriverCount].baseFarePrice= read.nextDouble(); 
                        System.out.println("Base Fare Distance: ");
                        A[DriverCount].baseFareDistance= read.nextDouble(); 
                        DriverCount++;
                        System.out.println();
                        System.out.println("Driver Details Successfully Added!");
                    }
                    else
                    {
                        System.out.println("Driver Email Already Present. Please Choose update Option to modify details.");
                        in = 'Y';
                    }
                break;
                case 2: //Delete
                    System.out.println("Enter Email: ");
                    tempemail= read.next();
                    j = searchindex(tempemail,A);
                    i =j;
                    if(j>=0)
                    {
                        for(i = j;i<DriverCount-1;i++ )
                        {
                            A[i].email= A[i+1].email;
                            A[i].name= A[i+1].name;
                            A[i].SurName= A[i+1].SurName;
                            A[i].VehicalType= A[i+1].VehicalType;
                            A[i].baseFarePrice= A[i+1].baseFarePrice;
                            A[i].baseFareDistance= A[i+1].baseFareDistance;
                            
                        }
                        if(i<DriverCount)
                        {
                            DriverCount--;
                        }
                    }
                break;
                case 3: //Modify
                System.out.println("Enter Email: ");
                tempemail= read.next();
                j = searchindex(tempemail,A);
                if(j>=0)
                {
                    while(inn=='Y'||inn=='y')
                    {
                        System.out.println("Please Choose what do you want to Modify:");
                        System.out.println("1.First Name\n2.Last Name\n3.Vehical Type\n4.Base Fare Price\n5.Base Fare Distance");
                        int cc= read.nextInt();
                        
                        if(cc==1)
                        {
                            System.out.println("Enter First Name: ");
                            A[j].name= read.next(); 
                            System.out.println("First Name Updated !! ");

                        }
                        else if(cc==2)
                        {
                            System.out.println("Enter Last Name: ");
                            A[j].SurName= read.next(); 
                            System.out.println("Last Name Updated !! ");

                        }
                        else if(cc==3)
                        {
                            System.out.println("Enter Vehical Type: ");
                            A[j].VehicalType= read.next();
                            System.out.println("Vehical Type Updated !! ");

                        }
                        else if(cc==4)
                        {
                            System.out.println("Base Fare Price: ");
                            A[j].baseFarePrice= read.nextDouble(); 
                            System.out.println("Base Fare Price Updated !! ");

                        }
                        else if(cc==5)
                        {
                            System.out.println("Base Fare Distance: ");
                            A[j].baseFareDistance= read.nextDouble(); 
                            System.out.println("Base Fare Distance Updated !! ");

                        }
                        else 
                        {
                            System.out.println();
                            System.out.println("Please enter the correct Choice and Try Again!");
                        }
                    
                        System.out.println();
                        System.out.println("Do you still want to Continue Modifying the same Driver?");
                        inn= read.next().charAt(0);
                    }
                }
                else
                {
                    System.out.println();
                    System.out.println("The Email was not Found Please Continue from the Main Menu:");
                }
                break;
                case 4: //display
                    Display(A);
                break;
                case 5: //Fare Calculation
                    try
                    {
                        int a[] = new int[3];
                        Scanner scanner = new Scanner(new File("faredetails.csv"));
                        scanner.useDelimiter(",");
                        i=0;
                        while(scanner.hasNext())
                        {
                            a[i]=Integer.parseInt(scanner.next());
                            i++;
                        }
                        int DistanceTraveled = a[0];
                        int TravelUnit = a[1];
                        int CostPerUnit = a[2];
                        scanner.close(); 
                        farecalculate(A,DistanceTraveled,TravelUnit,CostPerUnit);                  
                    } 
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                break;
                default:
                    System.out.println("Wrong Choice: Please try again!");
                break;
            }
                            
            System.out.println();
            System.out.println("Do you still want to Continue?");
            in= read.next().charAt(0);
        }

    }
    
}


