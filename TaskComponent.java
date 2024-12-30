import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//kelas TaskComponent merupakan turunan dari JPanel, yang merupakan sebuah panel GUI dalam Java Swing

//menampilkan tugas dalam bentuk text filed, checkbox, dan tombol hapus
//menyedeiakan logika untuk menangani perubahan status tugas
//berinteraksi dengan parent panel tempat semua tugas ditampilkan
public class TaskComponent extends JPanel implements ActionListener {
    //menandai apakah tugas sudah selsai, jika dicentang teks tugas akan diberi efek coret
    private JCheckBox checkBox;
    //area untuk menampilkan atau mengedit tugas
    //mendukung format HTML sehingga memungkinkan efek seperti coret
    private JTextPane taskField;
    // tombol untuk menghapus
    private JButton deleteButton;

    //memungkinkan komponen lain untuk mendapatkan referensi ke taskfield
    //ex: jika aplikasi ingin membaca atau meodifikasi teks tugas secara programatis
    public JTextPane getTaskField(){
        return taskField;
    }

    //refrensi ke parent panel (panel tempat komponen tugas ini berada)
    //dibutuhkan agar komponen ini dapat dihapus dari daftar tugas saat tombol "X" ditekan
    private JPanel parentPanel;

    //menginisialisasi komponen-komponen GUI dan mengatur properti/proses terkait
    public TaskComponent(JPanel parentPanel){
        this.parentPanel = parentPanel;

        //mengubah warna latar belakang menjadi putih ketika fokus didapatkan
        //mengembalikan warna default saat fokus hilang
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(CommontConstant.TASKFIELD_SIZE);
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener() {
            //indicate which task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        //checkbox diberi ukuran tertentu dan krusor berubah menjadi tangan saat diarahkan
        //saat checkbox dicentang atau dihapus centangnya, aksi akan ditangani di method actionPerformed
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommontConstant.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);

        //memanggil actionPerformed untuk menghapus tugas saat diklik
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommontConstant.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        //menambahkan komponen dibawah ke TaskComponent
        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    //menangani berbagai aksi yang dilakukan pengguna, seperti mencentang checkbox atau menekan tombol hapus
    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            //ketika checkbox dicentang
            //menghapus semua tag HTML dari teks menggunakan regex

            //ketika checkbox tidak dicentang
            //menghapus efek coret dan menampilkan teks tanpa HTML
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            //menambahkan tag HTML untuk memberikan efek coret
            taskField.setText("<html><s>"+ taskText + "</s></html>");
        }else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);

        }
        if(e.getActionCommand(). equalsIgnoreCase("X")){
            //jika tombol 'x' ditekan :
            //komponen tugas ini di hapus dari parentpanel
            parentPanel.remove(this);
            //dipanggil untuk memperbarui tampilan GUI agar perubahan terlihat
            parentPanel.repaint();
            parentPanel.revalidate();

        }
    }
}
