package org.aptech.on_Tap.TestJP2;

import java.util.Scanner;

public class MainApp {

        public static void menu(){
            CustomerMap cm = new CustomerMap();
            while (true){
                System.out.println("1.Add new customer");
                System.out.println("2. Display all customer");
                System.out.println("3.Search customer by name");
                System.out.println("4.Exit");
                Scanner sc = new Scanner(System.in);
                String op = sc.nextLine().trim();
                switch (op){
                    case "1":cm.add();
                        break;
                    case "2":cm.displayAll();
                        break;
                    case "3":cm.searchByName();
                        System.out.println("Enter customer name: ");
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Nhap sai chuc nang.");
                        break;
                }
            }

    }
}
