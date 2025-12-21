public class Grade2 {
    public static void main(String[] args) {
        char grade = 'a';
        switch (grade) {
            case 'A':
                System.out.print(grade + " : " + "Excellent");
                break;
            case 'B':
                System.out.print(grade + " : " + "Good");
                break;
            case 'C':
                System.out.print(grade + " : " + "Fair");
                break;
            case 'D':
                System.out.print(grade + " : " + "Pass");
                break;
            case 'F':
                System.out.print(grade + " : " + "FAIL !!");
                break;
            default :
                System.out.print("Invalid Input !!");
                break;
        }
    }
}
