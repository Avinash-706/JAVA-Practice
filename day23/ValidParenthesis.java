import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesis {
    public static boolean validParenthesis(String str){
        Stack<Character> st = new Stack<>();

        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } 
            else{
                if(st.isEmpty())    return false;
                
                if((ch == ')' && st.peek() != '(') || 
                    (ch == '}' && st.peek() != '{') || 
                    (ch == ']' && st.peek() != '[')) 
                {
                    return false;
                } 
                else {
                    st.pop();
                }
            }
        }

        return st.isEmpty();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter parenthesis: ");
        String input = sc.next();

        if(validParenthesis(input)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        sc.close();

    }
}
    