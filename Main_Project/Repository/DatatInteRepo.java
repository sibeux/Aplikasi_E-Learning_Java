package com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.Repository;

public interface DatatInteRepo {
    
    public void setDataToDatabase(String nama, String username,String password,String aktor, String index);

    public abstract void setDataToDatabase(int tingkat, String jurusan);

}
