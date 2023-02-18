import java.util.ArrayList;
import java.util.Scanner;

public class uwaaaaaa {
	static Scanner sc = new Scanner(System.in);
	
	
    public static void main(String[] args) {
    	
        ArrayList<String> contents = new ArrayList<String>();
        contents.add("employee uid");
        contents.add("first name");
        contents.add("middle name");
        contents.add("last name");
        contents.add("gender");
        contents.add("birthday");
        contents.add("address1");
        contents.add("address2");
        contents.add("barangay");
        contents.add("city");
        contents.add("province");
        contents.add("marital status");
        
        String query = "Your Information:  ";
        
        
        boolean flag = false;
        do {
            for (int i = 0; i < contents.size(); i++) {
                System.out.print("Please enter your " + contents.get(i) + ": ");
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Invalid input. Please try again.");
                    flag = false;
                    break;
                }
                query += contents.get(i) + " : " + input + "' ";
                if (i < contents.size() - 1) {
                    query += " | ";
                }
                flag = true;
            }
        } while (!flag);

        System.out.println(query);
    }
}
