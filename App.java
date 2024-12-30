//membuat antarmuka grafis pengguna (GUI)
import javax.swing.*;

//pintu masuk untuk menjalankan program
public class App {
    public static void main(String[] args) {
        //menjadwalkan sebuah runnable (kode yang akan dieksekusi) untuk dijalankan di thread GUI
        //(new Runnable()) digunakan untuk menjalankan kode di thread lain
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ToDoListGui() digunakan untuk membuat objek kelas,
                //yang mengansumsikan kelas yang bertanggung jawab untuk mendesain dan mengelola GUI

                //setVisible() digunakan untuk mengatur jendela GUI agar terlihat oleh pengguna
                new ToDoListGui().setVisible(true);
            }
        });
    }
}
