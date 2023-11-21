package org.aptech.on_Tap.TestJP2;

import java.util.HashMap;
import java.util.Map;

public class CustomerMap {
    HashMap<String, Customer> list = new HashMap<>();

    public void add() {
        Customer cus = new Customer();
        cus.input();
        list.put(cus.tel, cus);
        System.out.println("Add new customer successed.");

    }

    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("System is empty");
            return;
        }
        System.out.println("List all custormer: ");
        list.values().forEach(System.out::println);
    }

    public void searchByName(String Sname) {
        if (list.isEmpty()) {
            System.out.println("System is empty");
            return;
        }
        for(Map.Entry<String, Customer> entry : list.entrySet()){
            String key  = entry.getKey();
            Customer value  = entry.getValue();
            int cnt=0;
            if (value.name.equalsIgnoreCase(Sname)){
                System.out.println(entry.getValue());
                cnt++;
            }
            if (cnt==0){
                System.out.println(" Not found name.");
            }
        }

    }
}
