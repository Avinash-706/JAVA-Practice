import java.util.Stack;

public class StackOne {
    public static void main(String[] args) {

        Stack<Integer> st = new Stack<>();

        System.out.println("Checking if stack is empty");
        System.out.println("isEmpty(): " + st.empty());
		System.out.println("Stack before any operations: " + st);

        // These will throw EmptyStackException if uncommented
        // System.out.println(st.peek());
        // System.out.println(st.pop());

        System.out.println("\nPushing elements into stack");
        System.out.println("push(10): " + st.push(10));
        st.push(14);
        st.push(16);
        st.push(12);
        st.push(13);

        System.out.println("Stack after pushes: " + st);

        System.out.println("\nPeeking top element (does not remove)");
        System.out.println("peek(): " + st.peek());
        System.out.println("peek() again: " + st.peek());

        System.out.println("\nPopping top element (removes element)");
        System.out.println("pop(): " + st.pop());

        System.out.println("Stack after pop: " + st);

        System.out.println("\nChecking if stack is empty again");
        System.out.println("isEmpty(): " + st.empty());
    }
}
