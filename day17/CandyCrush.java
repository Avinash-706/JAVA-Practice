import java.util.Scanner;

class Level1 {
    public Level1() {
        System.out.println("Level 1 Started");
    }

    public boolean check() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value for Level 1 : ");
        int val = sc.nextInt();

        if (val == 1) {
            System.out.println("Level 1 Completed Successfully");
            return true;
        }
        System.out.println("Level 1 Failed");
        return false;
    }
}

class Level2 extends Level1 {
    public Level2() {
        System.out.println("Level 2 Started");
    }

    @Override
    public boolean check() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value for Level 2 : ");
        int val = sc.nextInt();

        if (val == 2) {
            System.out.println("Level 2 Completed Successfully");
            return true;
        }
        System.out.println("Level 2 Failed");
        return false;
    }
}

class Level3 extends Level2 {
    public Level3() {
        System.out.println("Level 3 Started");
    }

    @Override
    public boolean check() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value for Level 3 : ");
        int val = sc.nextInt();

        if (val == 3) {
            System.out.println("🎉 Level 3 Completed – GAME FINISHED 🎉");
            return true;
        }
        System.out.println("Level 3 Failed");
        return false;
    }
}

public class CandyCrush {
    public static void main(String[] args) {

        System.out.println("Main Start");

        Level1 ref;

        // -------- LEVEL 1 --------
        ref = new Level1();
        if (!ref.check()) return;

        // -------- UPCAST TO LEVEL 2 --------
        ref = new Level2();   // Upcasting
        if (!ref.check()) return;

        // -------- UPCAST TO LEVEL 3 --------
        ref = new Level3();   // Upcasting
        ref.check();

        System.out.println("Main End");
    }
}
