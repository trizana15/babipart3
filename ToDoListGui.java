import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//subcalss dari jFrame
//kerangka utama aplikasi GUI.
//mengimplementasikan ActionListener untuk menangani aksi pengguna
public class ToDoListGui extends JFrame implements ActionListener {
    private JPanel taskPanel, taskComponentPanel;

    //mengatur properti utama jendela GUI dan memanggil metode untuk menambahkan komponen GUI
    public ToDoListGui() {
        super("To Do List Application");
        //menutup aplikasi saat jendela ditutup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setPreferredSize = ukuran jendela diatur sesuai konstanta GUI_SIZE
        setPreferredSize(CommontConstant.GUI_SIZE);
        //jendela dibuka ditengah layar
        setLocationRelativeTo(null);
        setResizable(true); // Memungkinkan jendela untuk diubah ukurannya
        setLayout(new BorderLayout()); // Menggunakan BorderLayout untuk pengelolaan tata letak

        //menambahkan semua komponen GUI ke jendela
        addGuiComponents();
        // Mengatur ukuran jendela agar sesuai dengan komponen
        pack();
    }

    //membuat dan menambahkan elemen-elemen utama GUI, yaitu banner, task panel, tombol ADD Task
    private void addGuiComponents() {
        // JLabel digunakan sebagai banner (judul aplikasi)
        JLabel bannerLabel = new JLabel("TO DO LIST");
        //font diatur dengan craeteFont
        bannerLabel.setFont(createFont("resource/LEMONMILK-Light.otf", 36f));
        //posisi teks ditengah secara horizontal
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Tengah secara horizontal
        bannerLabel.setPreferredSize(new Dimension(CommontConstant.GUI_SIZE.width, 50));
        add(bannerLabel, BorderLayout.NORTH);

        // panel utama yang menampung daftar tugas
        taskPanel = new JPanel();
        taskPanel.setLayout(new BorderLayout());

        // panel yang menampung setiap tugas (berupa objek taskComponent)
        taskComponentPanel = new JPanel();
        //boxLayout = agar tugas ditampilkan secara vertikal
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel, BorderLayout.CENTER);

        // menambhakm scroll pane agar daftar tugas dapat digulir jika melebihi batas layar
        //scrollbar vertikal hanya muncul jika diperlukan, sedangkan scrollbar horizontal dinonaktifkan
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);
        add(scrollPane, BorderLayout.CENTER); // Scroll pane ada di tengah

        // ditambakan dibagian bawah
        //ketika ditekan, aksinya akan ditangani oleh actionPerformed
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(createFont("resource/LEMONMILK-Light.otf", 18f));
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.addActionListener(this);
        add(addTaskButton, BorderLayout.SOUTH); // Tombol di bagian bawah
    }

    //memuat font custom dari file
    //jika font gagal dimuat, menggunakan font default SansSerif
    private Font createFont(String resource, float size) {
        try {
            String filePath = getClass().getClassLoader().getResource(resource).getPath();

            // Ganti %20 dengan spasi jika ada
            filePath = filePath.replaceAll("%20", " ");

            File customFontFile = new File(filePath);
            return Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
        } catch (Exception e) {
            System.out.println("Error loading font: " + e);
        }
        return new Font("SansSerif", Font.PLAIN, (int) size); // Default font jika font khusus gagal dimuat
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add Task")) {
            // membuat objek dari TaskComponent dan menambahkannya ke taskComponentPanel
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // menonaktifkan tugas sebelumnya
            //jika sudah ada tugas sebelumnya, warna latar belakangnya diseret menjadi default
            if (taskComponentPanel.getComponentCount() > 1) {
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            // memfokuskan tugas baru
            //memindahkan fokus ke tugas baru
            taskComponent.getTaskField().requestFocus();
            //memperbarui tampilan GUI agar perubahan terlihat
            taskComponentPanel.revalidate();
            taskComponentPanel.repaint();
        }
    }

    // Main Method untuk menjalankan aplikasi
    public static void main(String[] args) {
        //memastikan resposivitas GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGui().setVisible(true);
            }
        });
    }
}
