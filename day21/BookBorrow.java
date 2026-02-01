import java.util.ArrayList;
import java.util.List;

class BookUnavailableException extends Exception {
    public BookUnavailableException() {
        super("Cannot borrow: No copies available.");
    }
}

class DuplicateBorrowException extends Exception {
    public DuplicateBorrowException() {
        super("Cannot borrow: Employee has already borrowed this book.");
    }
}

public class BookBorrow{
    public static void main(String[] args) {
        Book b = new Book("xyz", "A song of ice and fire", 2);

        String[] employee = {"Amit", "Neha", "Amit", "Rohit"};

        for(String emp:employee){
            try {
                b.borrowBook(emp);
            } catch(DuplicateBorrowException | BookUnavailableException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Book {
    protected String id, title;
    protected int maxCopies;
    protected List<String> borrowedBy;

    Book(String id, String title, int maxCopies) {
        this.id = id;
        this.title = title;
        this.maxCopies = maxCopies;
        this.borrowedBy = new ArrayList<>();
    }

    public void borrowBook(String name) 
        throws BookUnavailableException, DuplicateBorrowException{
            if(borrowedBy.contains(name)){
                throw new DuplicateBorrowException();
            }

            if(borrowedBy.size() >= maxCopies){
                throw new BookUnavailableException();
            }

            borrowedBy.add(name);
            System.out.println(name + " borrowed successfully.");
    }
}
