public class TextEditor{
    public static void main(String[] args){
        TextEditorDriver sol = new TextEditorDriver();
        sol.textBlockDisplay();
        sol.editorBlock();

    }
}

class TextBlock {
    private final String content;
    public TextBlock(String content){
        this.content = content;
    }
    public TextBlock(){
        this.content = "";
    }

    public String getContent(){
        return this.content;
    }

    public void display(){
        System.out.println(this.content);
    } 
}

class Editor {
    private StringBuilder liveText;

    public Editor() {
        this.liveText = new StringBuilder("");
    }
    public Editor(String liveText) {
        this.liveText = new StringBuilder(liveText);
    }
    public void appendText(String text) {
        this.liveText.append(text);
    }
    public void deleteText(int start, int end) {
        this.liveText.delete(start, end);
    }
    public void replaceText(int start, int end, String text) {
        this.liveText.replace(start, end, text);
    }

    public String substring(int start, int end){
        return this.liveText.substring(start, end);
    }
    public int search(String word){
        return this.liveText.indexOf(word);
    }
    public boolean validate(String input){
        return this.liveText.toString().equals(input);
    }

    public String current(){
        return liveText.toString();
    }
}

class TextEditorDriver {
    private TextBlock tb1 = new TextBlock("Hello World!");
    private TextBlock tb2 = new TextBlock("Java Programming");
    private TextBlock tb3 = new TextBlock("");
    Editor editor = new Editor("Initial Text. ");

    public void textBlockDisplay(){
        tb1.display();
        tb2.display();
        tb3.display();
    }

    public void editorBlock (){

        System.out.println("After Append:");
        editor.appendText("This is a live edit.");
        System.out.println(editor.current());

        System.out.println("After Delete:");
        editor.deleteText(0,7);
        System.out.println(editor.current());

        System.out.println("After Replace:");
        int index = editor.search("live");
        editor.replaceText(index, index + 4, "real-time");
        System.out.println(editor.current());

        System.out.println("Substring (0-12):");
        System.out.println(editor.substring(0,13));

        System.out.println("Index of 'edit':");
        System.out.println(editor.search("edit"));

        System.out.println("Validation Check:");
        System.out.println("Does live text match 'Java Programming'? " + 
        editor.validate(tb2.getContent()));
    }
}


