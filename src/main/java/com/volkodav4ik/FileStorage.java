package com.volkodav4ik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Storage {

    private String fileName;
    private List<User> list;
    private int lastId;

    public FileStorage(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileName = fileName;
        writeDataToGson(this);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void addUser(User user) {
        FileStorage tmp = readDataFromJson();
        if (tmp.lastId == 0) {
            tmp.list = new ArrayList<>();
        }
        tmp.lastId++;
        user.setId(tmp.lastId);
        tmp.list.add(user);
        writeDataToGson(tmp);
    }

    @Override
    public void removeAll() {
        FileStorage tmp = readDataFromJson();
        tmp.lastId = 0;
        tmp.list.clear();
        writeDataToGson(tmp);
    }

    @Override
    public void removeUser(int id) {
        FileStorage tmp = readDataFromJson();
        int index = -1;
        for (User user : tmp.list) {
            if (user.getId() == id) {
                index = tmp.list.indexOf(user);
            }
        }
        if (index != -1) {
            tmp.list.remove(index);
        } else {
            System.out.println("There is no User with this ID");
        }
        writeDataToGson(tmp);
    }

    @Override
    public void removeUserByName(String name) {
        FileStorage tmp = readDataFromJson();
        int index = -1;
        for (User user : tmp.list) {
            if (user.getName().equalsIgnoreCase(name)) {
                index = tmp.list.indexOf(user);
            }
        }
        if (index != -1) {
            tmp.list.remove(index);
        } else {
            System.out.println("There is no User with this name");
        }
        writeDataToGson(tmp);
    }

    @Override
    public User getUser(int id) {
        FileStorage tmp = readDataFromJson();
        int index = -1;
        for (User user : tmp.list) {
            if (user.getId() == id) {
                index = tmp.list.indexOf(user);
            }
        }
        if (index != -1) {
            return tmp.list.get(index);
        } else {
            System.out.println("There is no User with this ID");
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        FileStorage tmp = readDataFromJson();
        return tmp.list;
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() != 0) {
            FileStorage tmp = readDataFromJson();
            boolean success = false;
            for (User listUsers : tmp.list) {
                if (listUsers.getId() == user.getId()) {
                    listUsers.setName(user.getName());
                    listUsers.setAge(user.getAge());
                    writeDataToGson(tmp);
                    success = true;
                }
            }
            if (!success) {
                System.out.println("There is no User with this ID");
                return;
            }
        } else {
            System.out.println("Please specify ID of User which you want to update");
            return;
        }

    }

    private void writeDataToGson(FileStorage fileStorage) {
        Gson gson = new GsonBuilder().create();
        String strJson = gson.toJson(fileStorage);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFileName()))) {
            writer.write(strJson);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private FileStorage readDataFromJson() {
        String strFromJson = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.getFileName()))) {
            strFromJson = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(strFromJson, FileStorage.class);
    }

    @Override
    public String toString() {
        FileStorage tmp = readDataFromJson();
        return "FileStorage{" +
                "fileName='" + tmp.fileName + '\'' +
                ", list=" + tmp.list +
                '}';
    }
}
