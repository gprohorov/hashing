package pro.edu;

public class Main {

    public static void main(String[] args) {
        String str = "Hello, world!";
        int hsh = 0;
        for (int i = 0; i < str.length(); i++) {
            hsh += (int) str.charAt(i);
        }
        System.out.println(hsh);
    }
}
