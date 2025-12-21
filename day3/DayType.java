public class DayType {
    public static void main(String[] args){
        String day = "Saturday";
        
        if( day  == "Monday"  || day == "Tuesday" || day == "Wednesday" 
         || day == "Thursday" || day == "Friday")       
                System.out.print(day + " is a " + "Weekday");

        else if(day == "Sunday" || day == "Saturday")   
                System.out.print(day + " is a " +"Weekend");
        else    System.out.print(day + " is a " + "Invalid Output");
    }
}
