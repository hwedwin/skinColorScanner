package skinColorScanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class ShowImage extends JFrame {
	JPanel panel = null;
	JLabel label = null;

	JPanel panel2 = null;
	JLabel label2 = null;

	public ShowImage() {
		super("�������ʾ");
		this.setBounds(400, 100, 400, 400);
		this.setBackground(Color.gray);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				//System.exit(0);
			}
		});

		this.panel = new JPanel();
		this.label = new JLabel();
		panel.add(label);

		this.panel2 = new JPanel();
		this.label2 = new JLabel();
		panel2.add(label2);

		this.add(panel, BorderLayout.CENTER);
		this.add(panel2, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setResizable(true);
	}

	public void openFile(File file) {
		// �ļ��Ƿ���ڻ����Ƿ�ѡ��
		if (file == null) {
			System.out.println("δ�ҵ�ͼƬ�ļ���");
			return;
		}

		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);

			String path = file.getPath();
			ImageIcon image = new ImageIcon(path);
			label.setIcon(image); // ����JLabel����ʾͼƬ
			label.setText("");
			this.pack();
			validate(); // ʹ��Ч

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}

}
