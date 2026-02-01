public class SimpleTextEditor{
    public static void main(String[] args){
        TextEditor t1 = new TextEditor("Hello World");
        t1.insertText(6, "Beautiful ");
        t1.deleteText(0,6);
        t1.replaceText(0,9, "Java ");

        System.out.println("Final Text:");
        System.out.println(t1.getContent());
    }
}

class TextEditor{
    private StringBuilder content;

    TextEditor(String str){
        this.content = new StringBuilder(str);
    }
    public void addText(String text) {
        this.content.append(text);
    }
    public void insertText(int pos, String str){
        this.content.insert(pos, str);
    }
    public void deleteText(int start, int end) {
        this.content.delete(start, end);
    }
    public void replaceText(int start, int end, String text) {
        this.content.replace(start, end, text);
    }
    public String getContent(){
        return this.content.toString();
    }

}