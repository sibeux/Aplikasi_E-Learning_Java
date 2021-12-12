package com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository.SiswaRepoImpl;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository.TugasRepository;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Util.ClearScreenUtil;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Util.ConfirmUtil;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Util.ValidationUtil;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.ApplicationView;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.SiswaHomeView;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.Pretty_Table.PrettyTable;
public class SiswaApp {
    private String namaSiswa;
    private String username;
    private String nis;

    public SiswaApp (String namaSiswa, String username, String nis){
        this.namaSiswa = namaSiswa;
        this.username = username;
        this.nis = nis;
    }

    public String getNis() {
        return nis;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public String getUsername() {
        return username;
    }

    public void kerjakanTugas(String name,String username) {
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(
            "SELECT status_tugas.id_status,status_tugas.id_tugas, m.nama,tugas.nama_tugas,"+
            "tugas.urutan"+
            " FROM tugas,status_tugas,"+
            "mapel m JOIN tugas t ON m.id_mapel "+
            "= t.id_mapel JOIN status_tugas st ON t.id_tugas = st.id_tugas where st.nis = '"
            +SiswaRepoImpl.getNisByUsername(username)+"' and st.is_kerjakan = 'false'"+
            " ORDER BY st.id_tugas ASC");
            PrettyTable prettyTable = new PrettyTable("No","Mata Pelajaran","Tugas","Jenis");
            String[] idStatus = new String[getCountTugas()];
            var idTugas = "";
            var index = 1;
            while (res.next()){
                prettyTable.addRow(String.valueOf(index),res.getString("nama"),
                res.getString("nama_tugas"),String.valueOf("Tugas Ke-"+res.getInt("urutan"))) ;
                idStatus[index-1] = res.getString("id_status");
                idTugas = res.getString("id_tugas");
                index++;
            }
            ClearScreenUtil.ClearConsole();
            System.out.println("------------------------------");
            System.out.println("       * DAFTAR TUGAS *");
            System.out.println("------------------------------\n");
            if(index-1 == 0){
                prettyTable.addRow("--","--","--","--");
                System.out.println(prettyTable);
                System.out.print("\nBelum ada tugas tersedia! Klik enter untuk kembali! ");
                new ConfirmUtil();
                new SiswaHomeView(name, username);
            } else{
                System.out.println(prettyTable);
                System.out.println("---------------------------");
                var x = ValidationUtil.inputInteger(
                    "\nPilih tugas untuk dikerjakan  :: ", getCountTugas());
                TugasRepository.addStatusTugasDatabase(idStatus[x-1], idTugas, this.nis,
                this.namaSiswa, this.username);
            }
            statement.close();
            con.close();

        } catch (Exception ioe){
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

    public int getCountTugas(){
        int result = 0;
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(
                "SELECT COUNT(*) as jumlah_baris FROM status_tugas WHERE nis='"+
                SiswaRepoImpl.getNisByUsername(username)+"'");

            while (res.next()){
                result = (res.getInt("jumlah_baris"));
            }
            statement.close();
            con.close();
        } catch (Exception ioe){
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return result;
    }
}
