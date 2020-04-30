package supercalendar;
import java.io.*;

import java.util.ArrayList;

public class App implements java.io.Serializable{
    private ArrayList<User> users;

    /**
     * Constructs a new calendar.
     */
    public App() {
        this.users = new ArrayList<User>();
    }


    /**
     * getter Method for the ArrayList of Users
     * @return the ArrayList of Users.
     */

    public ArrayList<User> getUsers(){
        return this.users;
    }

    /**
     * Add the given user to this calendar.
     * @param user The given user that need to be added.
     */

    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Search and Return the user by the given name.
     * @param name the user name.
     * @return the user that has the name.
     */
    public User searchUser(String name) {
        for (User user : this.users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Check whether the password is correct.
     * @param user the user that wants to log in.
     * @param password the password entered.
     * @return True if the passwords match, or False if not.
     */
    public Boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    /**
     * Store the information so far to the given filepath.
     * @param filepath the directory of the file.
     */
    public void WriteToFile(String filepath) {
        try {
            //Saving object in a file
            FileOutputStream file = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // write this Calendar into the file
            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    /**
     * Return a calendar that has been loaded from give filepath.
     * @param filepath the directory of the file.
     * @return A calendar that has all information from the file.
     */
    public static App LoadFromFile(String filepath) {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            App c1 = (App) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            return c1;
        }

        catch(IOException ex) {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return null;

    }

}
