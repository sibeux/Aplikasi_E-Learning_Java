package com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Entities.Siswa;
import com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.ApplicationView;

public class SiswaRepoImpl extends DataRepository implements DatatInteRepo { // IMPLEMENTASI INTERFACE

    public static String getCategoryBySiswa(String username){
        String result = "";
        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM siswa WHERE username='"+username+"'");
            while (res.next()){
                result = res.getString("category");
            }
        } catch (Exception e) {
            //TODO: handle exception
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public static int getSiswaByKelas(String category){
        int jumlahSiswa = 0;
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT COUNT(*) as baris FROM siswa WHERE category='"+category+"'");
            var baris = 0;
            while (res.next()){
                    baris = res.getInt("baris");
            }
            jumlahSiswa = baris;
            statement.close();
            con.close();
        } catch (Exception ioe){
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return jumlahSiswa;
    }

    public static String getNamaSiswa(String username){
        String namaSiswa = null;
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM siswa WHERE username='"+username+"'");
            var nama = "";
            while (res.next()){
                    nama = res.getString("nama");
            }

            namaSiswa = nama;
            statement.close();
            con.close();
        } catch (Exception ioe){
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return namaSiswa;
    }

    @Override // OVERRIDING METHOD
    public void setDataToDatabase(String nama, String username,String password,String aktor, String category){
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = 
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");
            java.sql.PreparedStatement statement = con.prepareStatement("insert into siswa values (?,?,?,?,?)");

            var baris = ApplicationView.countTableSiswa();
            Siswa SiswaTemp = new Siswa(1000+baris+1,nama,username, category);

            statement.setInt(1, 1000+baris+1);
            statement.setString(2, nama);
            statement.setString(3, username);
            statement.setString(4, category);
            statement.setBytes(5, KelasRepository.toBytes(SiswaTemp));

            statement.executeUpdate();

            statement.close();
            con.close();
            DataRepository.addRowsLogin(username, password,"siswa");
        } catch (Exception e) {
            Logger.getLogger(KelasRepository.class.getName()).log(Level.SEVERE,null,e);
            System.out.println("Data tidak boleh sama");
        }
    }

    public static Siswa getSiswaFromDatabase(){
        Siswa SiswaTemp = null;
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM siswa");

            while (res.next()){
                Object o = toObject(res.getBytes(6));
                if (o instanceof Siswa){
                    SiswaTemp = (Siswa) o;
                }
            }
            statement.close();
            con.close();
        } catch (Exception ioe){
            Logger.getLogger(KelasRepository.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return SiswaTemp;
    }

    private static Object toObject(byte[] bytes){
        Object object = null;
        try{
            object = new java.io.ObjectInputStream(
                new java.io.ByteArrayInputStream(bytes)).readObject();
    
        } catch(Exception cnfe){
            cnfe.getMessage();
        }

        return object;
    }

    public static String[] getArrayNisByCategory(String category){
        String[] arrayNis = null;
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM siswa WHERE category='"+
            category+"'");
            String dump[] = new String[ApplicationView.countSiswaByCategory(category)];
            var x = 0;
            while (res.next()){
                dump[x] = res.getString("nis");
                x++;
            }
            arrayNis = dump;
            statement.close();
            con.close();
        } catch (Exception ioe){
            Logger.getLogger(KelasRepository.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return arrayNis;
    }

    public static String getNisByUsername(String username){
        String result = "";
        try{
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/School", "postgres", "sibeHBQ342169");

            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM siswa WHERE username='"+
            username+"'");
            
            var dump = "";
            while(res.next()){
                dump = res.getString("nis");
            }
            result = dump;

            statement.close();
            con.close();
        } catch (Exception ioe){
            Logger.getLogger(KelasRepository.class.getName()).log(Level.SEVERE, null, ioe);
        }
        return result;
    }

    @Override
    public void setDataToDatabase(int tingkat, String jurusan) {
        // TODO Auto-generated method stub
        
    }

}
