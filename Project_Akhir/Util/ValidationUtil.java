package com.javaoop.tugas.SMT_3.Project_Akhir.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.lang3.StringUtils;

public class ValidationUtil {
    // ENKAPSULASI
    private static BufferedReader in = 
    new BufferedReader(new InputStreamReader(System.in));

    public static int inputInteger(String kalimat, int range){
        var exit = false;
        var result = 0; 
        while(exit == false){
            try{
                System.out.print(kalimat);
                String input = in.readLine();
                if ((StringUtils.isNumeric(input) == true)){
                    if ((Integer.parseInt(input)<=range) 
                    && (Integer.parseInt(input)>0)){
                        int hasil = Integer.parseInt(input);
                        result = hasil;
                        exit = true;
                        break;
                    } else{
                        System.out.println("$$$< Input tidak ditemukan! (1-"
                    +range+") >$$$");
                    continue;
                    }
                } 
                else{
                    System.out.println("!!!< Data harus berupa angka >!!!");
                    continue;
                }
            } catch (IOException e){
                System.out.println("!!!< Data harus berupa angka >!!!");
            }
        }
        return result;
    }

    // OVERLOADING METHOD
    public static int inputInteger(String kalimat){
        var exit = false;
        var result = 0; 
        while(exit == false){
            try{
                System.out.print(kalimat);
                String input = in.readLine();
                if (StringUtils.isNumeric(input) == true){
                    int hasil = Integer.parseInt(input);
                    result = hasil;
                    exit = true;
                    break;
                } else{
                    System.out.println("!!!< Data harus berupa angka >!!!");
                    continue;
                }
            } catch (IOException e){
                System.out.println("!!!< Data harus berupa angka >!!!");
            }
        }
        return result;
    }

    public static String inputString(String kalimat){
        var result = "";
        try {
            System.out.print(kalimat);
            String input = in.readLine();
            result = input;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("input error");
        }
        return result;
    }
}
