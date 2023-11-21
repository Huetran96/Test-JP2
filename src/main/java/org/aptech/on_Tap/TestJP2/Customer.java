package org.aptech.on_Tap.TestJP2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Scanner;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    String name;
    String tel;
    String email;

    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer's infomation: ");
        while (true){
            Pattern pattern = Pattern.compile("[a-z A-Z]{1,}");
            System.out.println("Enter name: ");
            name = sc.nextLine().trim();
            if (pattern.matcher(name).find()){
                break;
            }else{
                System.out.println("Not be empty,please try again.");
                return;
            }
        }
        while (true){
            Pattern pattern = Pattern.compile("\\d{10}");
            System.out.println("Enter tel number: ");
            tel = sc.nextLine();
            if (pattern.matcher(tel).find()){
                break;
            }else {
                System.out.println("Tel must be have 10 digits, please try again. ");
            }
        }

        System.out.println("Enter email: ");
        email = sc.nextLine();

    }


}
