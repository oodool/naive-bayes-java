import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainProgram extends Bayes {
    static Scanner sc = new Scanner(System.in);
	JTextField inputName, inputAge;
	JButton btnTampil, bSampleY, bSampleN, bKembali, bProses;
	JLabel judul, judul1, subjudul, jForm, jSample, jInputName, jInputAge, jInputDefaulted, jInputHousing, jInputLoan;
	JRadioButton defaultY, defaultN, housingY, housingN, loanY, loanN;
	ButtonGroup bgLoan, bgHousing, bgDefault;
	int sessContext;

    public static void main(String[] args) {
        new MainProgram();
    }

    public MainProgram() {
		Start();
    }

	public void Start() {
		JFrame frame = new JFrame();
		frame.setSize(410,310);
		frame.setTitle("Tubes");
		frame.setLocationRelativeTo(null);

		/* dari sini untuk JFrame frame */
		judul = new JLabel("<html><div style='text-align:center'>Aplikasi Klasifikasi Nasabah<br>Membuka Deposito</div></html>", SwingConstants.CENTER);
		judul.setBounds(50,15,300,55);
		judul.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
		
		judul1 = new JLabel("<html><div style='text-align:center'>M. Fahli Saputra, M. Nadhil Mawarid, M. Ghazi Mubarokah</div></html>", SwingConstants.CENTER);
		judul1.setBounds(50,70,300,40);
		judul1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		
		subjudul = new JLabel("<html><div style='text-align:center'>Dibuat sebagai penilaian Tugas Besar Pemrograman<br>Berorientasi Objek<br><br>Universitas Negeri Malang 23 April, 2022</div></html>");
		subjudul.setBounds(50,120,300,70);
		subjudul.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		
		btnTampil = new JButton("Mulai");
		btnTampil.setBounds(150,210,100,40);
		/* akhir frame */
		
		/* dari sini untuk JFrame frameSample */
		jSample = new JLabel("<html><div style='text-align:center'>Pilih sampel data yang akan digunakan</div></html>", SwingConstants.CENTER);
		jSample.setBounds(50,15,300,30);
		jSample.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		
		bSampleY = new JButton("Default");
		bSampleY.setBounds(70,70,100,40);
		bSampleY.setToolTipText("Sampel data yang diambil dari Rumus Slovin");
		
		bSampleN = new JButton("Semua");
		bSampleN.setBounds(240,70,100,40);
		bSampleN.setToolTipText("Seluruh data dengan akurasi yang lebih tinggi");
		
		/* akhir frameSample*/

		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(subjudul);
		frame.add(judul);
		frame.add(judul1);
		frame.add(btnTampil);
		btnTampil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JFrame frameSample = new JFrame();
				frameSample.setSize(410,175);
				frameSample.setTitle("Tubes");
				frameSample.setLocationRelativeTo(null);
				
				frameSample.add(jSample);
				frameSample.add(bSampleY);
				frameSample.add(bSampleN);
				
				frameSample.setLayout(null);
				frameSample.setVisible(true);
				frameSample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				bSampleY.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						sessContext = 1;
						frameSample.dispose();
						frame.dispose();
						mainContext(sessContext);
					}
				});
				
				bSampleN.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						sessContext = 2;
						frameSample.dispose();
						frame.dispose();
						mainContext(sessContext);
					}
				});
			}
		});
	}
	
    private void mainContext(int sample) {
        if (sample == 1) {
            this.initReader("bank-small.csv");
        } else if (sample == 2) {
            this.initReader();
        } else {
            System.out.println("Error: Terjadi error yang tidak diketahui.");
        }
		
		JFrame frameForm = new JFrame();
		frameForm.setSize(420,460);
		frameForm.setTitle("Tubes");
		frameForm.setLocationRelativeTo(null);
		
		jForm = new JLabel("Masukkan data nasabah:");
		jForm.setBounds(50, 10, 310, 40);
		jForm.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
		
		jInputName = new JLabel("Nama");
		jInputName.setBounds(50, 55, 310, 20);
		jInputName.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
		
		inputName = new JTextField();
		inputName.setBounds(50, 80, 310, 20);
		
		jInputAge = new JLabel("Usia");
		jInputAge.setBounds(50, 115, 310, 20);
		jInputAge.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
		
		inputAge = new JTextField();
		inputAge.setBounds(50, 140, 310, 20);
		inputAge.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					inputAge.setEditable(true);
				} else {
					inputAge.setEditable(false);
				}
			}
		});
		
		jInputDefaulted = new JLabel("Apakah punya kredit gagal?");
		jInputDefaulted.setBounds(50, 175, 310, 20);
		jInputDefaulted.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
		
		jInputHousing = new JLabel("Apakah punya pinjaman rumah?");
		jInputHousing.setBounds(50, 235, 310, 20);
		jInputHousing.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
		
		jInputLoan = new JLabel("Apakah punya pinjaman pribadi?");
		jInputLoan.setBounds(50, 295, 310, 20);
		jInputLoan.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
		
		bgDefault = new ButtonGroup();
		defaultN = new JRadioButton("Tidak");
		defaultY = new JRadioButton("Ya");
		defaultN.setActionCommand("n");
		defaultY.setActionCommand("y");
		defaultN.setBounds(50, 200, 100, 20);
		defaultY.setBounds(150, 200, 100, 20);
		bgDefault.add(defaultN);
		bgDefault.add(defaultY);
		
		bgHousing = new ButtonGroup();
		housingN = new JRadioButton("Tidak");
		housingY = new JRadioButton("Ya");
		housingN.setActionCommand("n");
		housingY.setActionCommand("y");
		housingN.setBounds(50, 260, 100, 20);
		housingY.setBounds(150, 260, 100, 20);
		bgHousing.add(housingN);
		bgHousing.add(housingY);
		
		bgLoan = new ButtonGroup();
		loanN = new JRadioButton("Tidak");
		loanY = new JRadioButton("Ya");
		loanN.setActionCommand("n");
		loanY.setActionCommand("y");
		loanN.setBounds(50, 320, 100, 20);
		loanY.setBounds(150, 320, 100, 20);
		bgLoan.add(loanN);
		bgLoan.add(loanY);
		
		bProses = new JButton("Proses");
		bProses.setBounds(155, 355, 100, 40);
		
		frameForm.add(jForm);
		frameForm.add(jInputName);
		frameForm.add(jInputAge);
		frameForm.add(jInputDefaulted);
		frameForm.add(jInputHousing);
		frameForm.add(jInputLoan);
		frameForm.add(inputName);
		frameForm.add(inputAge);
		frameForm.add(defaultY);
		frameForm.add(defaultN);
		frameForm.add(housingN);
		frameForm.add(housingY);
		frameForm.add(loanN);
		frameForm.add(loanY);
		frameForm.add(bProses);
		
		frameForm.setLayout(null);
		frameForm.setVisible(true);
		frameForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bProses.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if ((defaultY.isSelected() || defaultN.isSelected()) && (housingY.isSelected() || housingN.isSelected()) && (loanY.isSelected() || loanN.isSelected())){
					try {
						String nama = inputName.getText();
						int age = Integer.parseInt(inputAge.getText());
						String defaulted = bgDefault.getSelection().getActionCommand();
						String housing = bgHousing.getSelection().getActionCommand();
						String loan = bgLoan.getSelection().getActionCommand();
						
						calculate(nama, age, defaulted, housing, loan);
						frameForm.dispose();
						mainContext(sessContext);
					} catch (Exception er) {
						JOptionPane.showMessageDialog(null, "ERROR: Mohon masukkan usia dengan benar");
					}
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: Mohon data diisi dengan lengkap");
				}
			}
		});
    }
	
	public void calculate(String nama, int age, String defaulted, String housing, String loan){
        Classifications cls = new Classifications();
        super.read();
		
		super.age = cls.age(age);
		super.defaulted = cls.yesNoFilter(defaulted);
		super.housing = cls.yesNoFilter(housing);
		super.loan = cls.yesNoFilter(loan);
		
		double[] probabiility = super.calculate();
		
		if (probabiility[0] > probabiility[1]) {
			JOptionPane.showMessageDialog(null, "Hasil	: YA (Kemungkinan nasabah " + nama + " akan membuka deposito)\n\nProbabilitas: " + probabiility[0] + "\nAkurasi: " + Math.round(probabiility[2]) + "\nProb. Bersyarat: " + probabiility[3]);
		} else {
			JOptionPane.showMessageDialog(null, "Hasil	: TIDAK (Kemungkinan nasabah " + nama + " tidak akan membuka deposito)\n\nProbabilitas: " + probabiility[1] + "\nAkurasi: " + Math.round(probabiility[2]) + "\nProb. Bersyarat: " + probabiility[3]);
		}
		
		if (probabiility[2] <= 0) {
			System.out.println("\nSangat sedikit kriteria yang mirip dengan dataset");
		}
	}

    public void initReader() { // Polimorfisme
        super.initReader();
    }

	public void initReader(String file) { // Polimorfisme
		super.initReader(file);
	}
	

	public int inputInteger(String message) {
		while (true) {
			try {
				System.out.print(message + ": ");
				return sc.nextInt();
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Error: Input harus berupa angka.");
			}
		}
	}
}