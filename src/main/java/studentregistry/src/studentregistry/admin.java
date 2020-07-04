package studentregistry;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class admin {

    // to store student to corresponding ID
    HashMap<Integer, Student> studentregistrar  = new HashMap<Integer, Student>();
    Scanner input = new Scanner(System.in);

    // add function to add student
    public void add(Integer ID, String name, String[] subjects){
        // create student type to add
        Student tempStudent = new Student(name, ID, subjects);
        //now add to Datstruc
        studentregistrar.put(ID, tempStudent);
    }

    // edit fxn
    public void edit(Integer ID){
        // retrieve student
        Student tempStudent = studentregistrar.get(ID);
        // if no student found
        if(tempStudent == null){
            System.out.println("Student ID not valid");
        }else{
            System.out.println(tempStudent.toString());
            System.out.println("What would you like to edit?\n 1. Name \n 2. Subjects");
            int selection = input.nextInt();
            // could be Integer.parseInt(input.next())
            switch (selection){
                case 1:
                    System.out.println("Enter new name:\n");
                    //retrieve edit
                    String newName = input.next();
                    // store w setter
                    tempStudent.setStudentName(newName);
                    // update hashmap db
                    studentregistrar.put(ID, tempStudent);
                    break;

                case 2:
                    // retrive current subject
                    String[] subjectTemp = tempStudent.getSubjects();
                    // list subjects
                    for(int i =0; i<subjectTemp.length; i++){
                        System.out.println(i+" " + subjectTemp[i]);
                    }
                    // ask which they'd like to change
                    System.out.println("Which subject would you like to change?");

                    int indexToChange = Integer.parseInt(input.next());
                    System.out.println("Enter new subject:");
                    String newSubject = input.next();
                    subjectTemp[indexToChange] =newSubject;
                    // set and save update
                    tempStudent.setSubjects(subjectTemp);
                    studentregistrar.put(ID, tempStudent);
                    break;
                default: System.out.println("Invalid input");
                    break;
            }
        }
    }

    // delete student function
    public void delete(Integer ID){
        Student tempStudent = studentregistrar.remove(ID);
        if(tempStudent == null){
            System.out.println("ID invalid, no student found");
        }else{
            System.out.println(tempStudent.toString() + "deleted");
        }
    }

    // save fxn to add to DB or to file
    public void save() throws IOException {
        System.out.println("How would you like to save the data?\n 1. Save to File\n 2. Save to Database");
        int selection = input.nextInt();
        switch(selection){
            case 1: System.out.println("Enter Qualified filename");
            String filename = input.next();
            File file = new File(filename);
            FileOutputStream fileOutStream = null;
            try{
                fileOutStream = new FileOutputStream(file);
            }catch (Exception e){
                // file not found so make one
                try{
                    file.createNewFile();
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);

            objectOutStream.writeObject(studentregistrar);
            objectOutStream.close();
            fileOutStream.close();
            System.out.println("Saved");
            break;

            case 2: // Add DB
                break;

            default: System.out.println("Invalid Input");
            break;
        }
    }

    // load data from DB or file
    public void load() throws IOException, ClassNotFoundException {
        System.out.println("How would you like to load the data?\n 1. Load from File\n 2. Load from Database");
        int selection = input.nextInt();

        switch(selection){
            case 1:
                System.out.println("Enter Qualified filename");
                String filename = input.next();
                File file = new File(filename);

                if(file.exists()){
                    FileInputStream fileInStream = new FileInputStream(file);
                    ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
                    // create hash

                    studentregistrar = (HashMap<Integer, Student>) objectInStream.readObject();
                    objectInStream.close();
                    fileInStream.close();
                }else{
                    System.out.println("File not found");
                }
                break;

            case 2:
                // handle DB stuff
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    // view fxn to view students in DB
    public void view(){
        if(studentregistrar.isEmpty()){
            System.out.println("No values stored");
        }else{
            for (Student s: studentregistrar.values()) {
                System.out.println(s.toString());
            }
        }
    }

}
