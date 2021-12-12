package com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Util;

import java.util.Scanner;

public class InputUtil {
    // ENCAPSULATION
    private static Scanner scanner = new Scanner(System.in);

    public static String input(String info) {
        System.out.print(info + " : ");
        String data = scanner.nextLine();
        return data;
    }
    
}
