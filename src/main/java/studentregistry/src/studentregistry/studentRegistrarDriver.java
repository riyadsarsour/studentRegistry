package studentregistry;
import java.util.Scanner;


public class studentRegistrarDriver {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		admin  admin1 = new admin();
		
		int selection = 10;
		
		while(selection != 0) {
			System.out.println("Choose an option (eneter option number):\n 1. Add\n 2. Edit\n"
					+ " 3. Delete\n 4. View\n 5. Save\n 6. Load\n 0. Exit");
			selection = input.nextInt();
			switch(selection) {
				case 1: 
					System.out.println("Enter the Student's name:");
					String name = input.next();
					System.out.println("Enter student's ID:");
					int id = input.nextInt();
					System.out.println("Enter the student's renrolledsubjects seperated by commas");
					String subjectList = input.next();
					String[] subjects = subjectList.split(",");
					admin1.add(id, name, subjects);
					break;
					
				case 2:
					System.out.println("Enter student's ID:");
					int editID = input.nextInt();
					admin1.edit(editID);
					break;
					
				case 3:
					System.out.println("Enter student's ID:");
					int deleteID = input.nextInt();
					admin1.delete(deleteID);
					break;
					
				case 4:
					admin1.view();
					break;
				
				case 5:
					System.out.println("Now saving...");
					try {
						admin1.save();
					}catch(Exception e) {e.printStackTrace();}
					break;
					
				case 6:
					System.out.println("Now loading...");
					try {
						admin1.load();
					}catch(Exception e) {e.printStackTrace();}
					break;
					
				case 0:
					System.out.println("Exiting, Goodbye");
					break;
					
				default:
					System.out.println("Invalid input");
					break;
			}
		}
		if(selection == 0) {
			input.close();
			System.exit(0);
		}
	}
	
	
}
