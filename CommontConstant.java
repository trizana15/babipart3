import java.awt.*;
//kegunaan kelas untuk mempermudah pengaturan ukuran komponen GUI di aplikasi

//menyimpan nilai-nilai konfigurasi dimensi GUI yang digunakan untuk di berbagai bagian aolikasi
public class CommontConstant {
    //dimension merupakan bagian dari package java.awt dan digunakan untuk mendefinisikan ukuran 2D dan komponen GUI
    // GUI_SIZE = menentukan ukuran keseluruhan jendela
    public static final Dimension GUI_SIZE = new Dimension(540, 760);

    //BANNER_SIZE = menentukan ukuran banner (bagian atas jendela, seperti header)
    //lebarnya mengikuti ukuran GUI_SIZE
    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);

    //TASK_PANEL = menentukan ukuran task panel (area untuk menampilkan daftar tugas)
    //lebarnya lebih kecil dari GUI_SIZE sebesar 30 piksel (untuk margin/padding)
    //tingginya lebih kecil dari GUI sebesar 175 (mungkin untuk mengakomodasi banner, tombol, dll)
    public static final Dimension TASKPANEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175);

    //ADDTASK_BUTTON_SIZE = digunakan untuk menentukan ukuran tombol untuk menambahkan tugas
    //lebarnya sama dengan GUI
    public static final Dimension ADDTASK_BUTTON_SIZE = new Dimension(GUI_SIZE.width, 50);

    //TASKFIELD_SIZE = menentukan ukuran kolom teks tugas di dalam task panel
    public static final Dimension TASKFIELD_SIZE = new Dimension((int)(TASKPANEL_SIZE.width * 0.80), 50);
    //CHECKBOX_SIZE = menentukan ukuran kotak centang (checkbox) untuk menandai status tugas
    public static final Dimension CHECKBOX_SIZE = new Dimension((int)(TASKFIELD_SIZE.width * 0.05), 50);
    //DELETE_BUTTON_SIZE = menentukan ukuran tombol hapus untuk setiap tugas
    public static final Dimension DELETE_BUTTON_SIZE = new Dimension((int)(TASKFIELD_SIZE.width * 0.10), 50);
}
