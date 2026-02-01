public class LibraryHub{
    public static void main(String[] args){
        Book b1 = new Book("abc", "Winds of Winter", "George R.R. Martine");
        Book b2 = new Book("abc", "Winds of Winter", "George R.R. Martine");
        Book b4 = new Book("abcd", "Winds", "George R.R. Martine");

        if(b1.equals(b2)){
            System.out.println("The two books have same bookID");
        }else{
            System.out.println("The two books have unique bookID");
        }

        if(b2.equals(b4)){
            System.out.println("The two books have same bookID");
        }else{
            System.out.println("The two books have unique bookID");
        }
    }
}

class Book{
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // @Override
    public boolean equals(Book obj){
        Book ref = (Book) obj;
        return this.id == ref.id && this.title == ref.title && this.author == ref.author;
    }
}

