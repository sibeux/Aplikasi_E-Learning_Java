package com.javaoop.tugas.SMT_3.Project_Akhir.Main_Project.View.Pretty_Table;

public class test {
    public static void main(String[] args) {
        PrettyTable table = new PrettyTable("Firstname", "Lastname", "Email", "Phone");
        table.addRow("John", "Doe", "johndoe@nothing.com", "+2137999999");
        table.addRow("Jane", "Doe", "janedoe@nothin.com", "+2137999999");
        System.out.println(table);
}
}
