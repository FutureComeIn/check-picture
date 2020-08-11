package com.company;

import java.util.*;

public class ListRemoveDup {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        User user1 = new User("张三", "001");
        userList.add(user1);
        User user2 = new User("李四", "002");
        userList.add(user2);
        User user3 = new User("王五", "003");
        userList.add(user3);
        User user4 = new User("王五", "004");
        userList.add(user4);
        for (User u : userList) {
            System.out.println(u.getName());
        }
        System.out.println("去重后=======>");
        List<User> userListNoDupAndSort = removeDuplicateUser(userList);
        for (User u : userListNoDupAndSort) {
            System.out.println(u.getName());
        }
    }
    private static ArrayList<User> removeDuplicateUser(List<User> users) {
        Set<User> set = new TreeSet<User>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //字符串,则按照asicc码升序排列
                return o1.getName().compareTo(o2.getName());
            }
        });
        set.addAll(users);
        return new ArrayList<User>(set);
    }
}
class User {
    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
