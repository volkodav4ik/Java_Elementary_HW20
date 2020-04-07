package com.volkodav4ik;

public class Main {

    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage("Users.txt");

        fileStorage.addUser(new User("Alex", 11));
        fileStorage.addUser(new User("Ben", 22));
        fileStorage.addUser(new User("Carl", 33));
        fileStorage.addUser(new User("Soule", 44));
        fileStorage.addUser(new User("Paul", 55));

        System.out.println(fileStorage.getAllUsers().toString());
        System.out.println(fileStorage.getUser(2));
        fileStorage.removeUser(2);
        System.out.println(fileStorage.getUser(2));
        fileStorage.removeUserByName("Carl");

        System.out.println(fileStorage.toString());
        fileStorage.removeAll();
        System.out.println(fileStorage.toString());

        FileStorage noobs = new FileStorage("Noobs.txt");
        noobs.addUser(new User("Rita", 25));
        noobs.addUser(new User("Petya", 35));
        System.out.println(noobs.toString());
        noobs.updateUser(new User(1, "Margarita", 26));
        System.out.println(noobs.getUser(1).toString());
    }

}
