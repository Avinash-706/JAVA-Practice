public class LibraryMngSystem{
    public static void main(String[] args) {
        Book b = new Book(101, "Clean Code", "Robert Martin");
        System.out.println(b.toString());
    }
}

class Book {
    int id;
    String title, author;

    Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }
    @Override
    public String toString(){
        return "Book[id="+ this.id +", title=" + this.title +", author="+ this.author + "]";
    }
}