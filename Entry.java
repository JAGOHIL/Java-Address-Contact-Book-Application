//Import all the necessary modules, here java swing is going to be used to make the interface of the address book.
import javax.swing.*;
import java.util.*;
import java.io.*;

//We want to create a class for the profile that the address book will store, here we want the address book to store information on the name of the person, address as well as phone number.
class PersonInfo {
  String name;
  String address;
  String phoneNumber;
  
 //A constructor for personInfo will be used by the application in which whenever a user wants to add information to the address book, an instance of the PersonInfo class will be used.

  PersonInfo(String n, String a, String p) {
    name = n;
    address = a;
    phoneNumber = p;
  }

  void display() {
    JOptionPane.showMessageDialog(null, "Name:" + name + "\nAddress: " + address + "\nPhone no: " + phoneNumber);

  }
}

//A Second class which will be the address book itself, it will have the functions of the application, for instance adding a person, searching for a contact and deleting a contact.
class AddressBook {
  ArrayList persons;

  AddressBook() {
    persons = new ArrayList();
    loadPersons();
  }

  void addPerson() {
    String name = JOptionPane.showInputDialog("Please enter Name: ");
    String add = JOptionPane.showInputDialog("Please enter Address: ");
    String pNum = JOptionPane.showInputDialog("Please enter Phone no: ");
    //Creating a personInfo object
    PersonInfo p = new PersonInfo(name, add, pNum);
    persons.add(p);

  }

  void searchPerson(String n) {
    for (int i = 0; i < persons.size(); i++) {
      PersonInfo p = (PersonInfo) persons.get(i);
      if (n.equals(p.name)) {
        p.display();

      }
    }
  }

  void deletePerson(String n) {
    for (int i = 0; i < persons.size(); i++) {
      PersonInfo p = (PersonInfo) persons.get(i);
      if (n.equals(p.name)) {
        persons.remove(i);

      }
    }
  }
//We also want the application to save contacts as well, in which the program will remember the last contacts when the program runs again. This function will be responsible for saving the contact information in a txt file.
  void savePersons() {
    try {
      PersonInfo p;
      String line;
      FileWriter fw = new FileWriter("Persons.txt");
      PrintWriter pw = new PrintWriter(fw);
      for (int i = 0; i < persons.size(); i++) {
        p = (PersonInfo) persons.get(i);
        line = p.name + "," + p.address + "," + p.phoneNumber;
        pw.println(line);

      }
      pw.flush();
      pw.close();
      fw.close();

    } catch (IOException ioEx) {
      System.out.println(ioEx);

    }
  }
  
  // Next time when a user runs the program, they can load the txt file to get all of their information.

  void loadPersons() {
    String tokens[] = null;
    String name, add, ph;
    try {
      FileReader fr = new FileReader("Persons.txt");
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();
      while (line != null) {
        tokens = line.split(",");
        name = tokens[0];
        add = tokens[1];
        ph = tokens[2];
        PersonInfo p = new PersonInfo(name, add, ph);
        persons.add(p);
        line = br.readLine();
      }
      br.close();
      fr.close();

    } catch (IOException ioEx) {
      System.out.println(ioEx);

    }
  }

}

public class Entry {
  public static void main(String[] args) {
    AddressBook ab = new AddressBook();
    String input, s;
    int ch;

    while (true) {
      input = JOptionPane.showInputDialog("Enter 1 to Add\nEnter 2 to Search\nEnter 3 to Delete\nEnter 4 to Exit ");
      ch = Integer.parseInt(input);

      switch (ch) {
      case 1:
        ab.addPerson();
        break;
      case 2:
        s = JOptionPane.showInputDialog("Enter name to search: ");
        ab.searchPerson(s);
        break;
      case 3:
        s = JOptionPane.showInputDialog("Enter name to delete: ");
        ab.deletePerson(s);
        break;
      case 4:
        ab.savePersons();
        System.exit(0);

      }
    }

  }

}

