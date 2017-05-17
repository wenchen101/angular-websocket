package hello;

import java.util.Random;

/**
 * Created by wenchen on 5/17/2017.
 */
public class Student {
    private static final String[] COUNTRIES = new String[] { "USA", "China", "India", "UK" };
    private static Random rnd = new Random();

    private int id;
    private String name;

    private static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public  Student(){
        id =  rnd.nextInt(100);
        name = getSaltString();
        grade = rnd.nextInt(100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int grade;
    private String address;

}
