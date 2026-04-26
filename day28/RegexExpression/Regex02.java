
// Q1. Write a regular expression to validate a mobile number with the following rules:
// 	Must start with 6, 7, 8, or 9
// 	Must contain exactly 10 digits

// Q2. Write a regular expression to validate an email ID with the following conditions:
// 	Username part can contain:
// 	letters (a–z, A–Z)
// 	digits (0–9)
// 	special characters: . _ % + -
// 	Must contain @
// 	Domain name should contain only letters
// 	Extension should contain at least 2 letters

// Q3. Write a regular expression to validate a password with the following rules:
// 	Minimum 8 characters
// 	Must contain at least one uppercase letter
// 	Must contain at least one digit

public class Regex02{
    public static void main(String[] args) {
        System.out.println("Validate Mobile Number : ");
        System.out.println("8974512554 : " + "8974512554".matches("[6-9][0-9]{9}"));
        System.out.println("98762 : " + "98762".matches("[6-9][0-9]{9}"));
        System.out.println(" : " + "".matches("[6-9][0-9]{9}"));
        System.out.println("1234567890 : " + "1234567890".matches("[6-9][0-9]{9}"));

        System.out.println("avinash213@gmail.com : " + "avinash213@gmail.com".matches("[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}"));
        System.out.println("dhanuka213@.com : " + "dhanuka213@.com".matches("[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}"));

        System.out.println("Asansol@0341 : " + "Asansol@0341".matches("(?=.*[A-Z])(?=.*\\d).{8,}"));
    }
}