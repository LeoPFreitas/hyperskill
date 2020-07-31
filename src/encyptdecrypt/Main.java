package encyptdecrypt;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        try {
            task.runTask(args);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}


