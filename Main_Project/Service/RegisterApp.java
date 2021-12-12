package com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Service;

import java.util.Scanner;

import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository.GuruRepoImpl;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository.KelasRepository;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository.SiswaRepoImpl;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Util.ClearScreenUtil;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Util.ValidationUtil;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.ApplicationView;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.SiswaRegView;

public class RegisterApp {
    Scanner in = new Scanner(System.in);
    // CONSTRUCTOR & COMPOSITION
    public RegisterApp (String inputtt, GuruRepoImpl guru, SiswaRepoImpl siswa){
        if(inputtt.toLowerCase().equals("guru")){
            ClearScreenUtil.ClearConsole();
            System.out.println("------------------------------");
            System.out.println("       * REGISTER GURU *");
            System.out.println("------------------------------\n");
            var nip = 2100+ApplicationView.countTableGuru()+1;
            System.out.println("NIP      : "+nip);
            System.out.print("Nama     : ");
            String nama = in.nextLine();
            System.out.println();
            ApplicationView.mapelTable();
            System.out.println("=====================");
            var mapel = ValidationUtil.inputInteger("1. Pilih  Mapel  : ",ApplicationView.countTableMapel());
            System.out.println("=====================\n");
            System.out.print("Username : ");
            String username = in.nextLine();
            System.out.print("Password : ");
            String password = in.nextLine();
            System.out.println("\n------------------------------");
            guru.setDataToDatabase(nama, username, password, "guru", ApplicationView.getArrayMapel()[mapel-1]);
        }
        else{
                ClearScreenUtil.ClearConsole();
                System.out.println("------------------------------");
                System.out.println("       * REGISTER SISWA *");
                System.out.println("------------------------------\n");
                var nis = 1000+ApplicationView.countTableSiswa()+1;
                System.out.println("NIS      : "+nis);
                System.out.print("Nama     : ");
                String nama = in.nextLine();
                new SiswaRegView(new KelasRepository());
                var kelass = ValidationUtil.inputInteger("Kelas    : ",ApplicationView.countTableKelas());
                System.out.print("Username : ");
                String username = in.nextLine();
                System.out.print("Password : ");
                String password = in.nextLine();
                System.out.println("\n------------------------------");
                siswa.setDataToDatabase(nama, username,password,"siswa", ApplicationView.getArrayKelas()[kelass-1]);
    }
}
}
