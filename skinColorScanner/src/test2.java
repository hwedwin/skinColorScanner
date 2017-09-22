
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class test2 extends JFrame {
	/**
	 * ����Ӧ��JMF������ͷ����˷��вɼ����ݣ��ɼ�������ݿ��Ա��������桢��ȾΪ�Ժ�Ӧ�á� ����ý��������Ҫ�����¹�����
	 * 1.��λ����Ҫ�õĲ����豸������ͨ����ѯCaptureDeviceManager����λ
	 * 2.��ȡ��������豸����ϢCaptureDeviceInfo����
	 * 3.��CaptureDeviceInfo�����л�ȡ�����豸��λ��Medialocator 4.����MediaLocator����DataSource
	 * 5.ʹ��DataSource����Player����Processor��6.Ȼ������Player�Ϳ�ʼ��ý��Ĳ���
	 */
	private JPanel contentPane;
	private JPanel avPane;
	private JButton startBtn, stopBtn;
	private JPanel p4;
	private CaptureDeviceInfo device;
	private Player player;
	Component comV;
	// ����ͷ��������
	String deviceName = "vfw:Microsoft WDM Image Capture (Win32):0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 frame = new test2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(false);
		setContentPane(contentPane);

		startBtn = new JButton("start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// jbInit();
					start();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		startBtn.setBounds(159, 529, 63, 23);
		stopBtn = new JButton("stop");
		stopBtn.setBounds(368, 529, 57, 23);
		contentPane.setLayout(null);

		contentPane.add(startBtn);
		contentPane.add(stopBtn);

	}

	/**
	 * ������Ƶ���
	 * 
	 * @throws IOException
	 * @throws CannotRealizeException
	 * @throws NoPlayerException
	 * @throws InterruptedException
	 */
	public void start() throws NoPlayerException, CannotRealizeException,
			IOException, InterruptedException {

		System.out.println("��ʼ��أ�");
		p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.setOpaque(false);
		// ��ȡ��Ƶ�豸
		 device = CaptureDeviceManager.getDevice(deviceName);

		String locatorString = "vfw://0";
		MediaLocator locator = new MediaLocator(locatorString);
		player = Manager.createRealizedPlayer(locator);
		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
		player.start();

		comV = player.getVisualComponent();
		if (comV != null) {
			p4.add(comV);
		}
		p4.setVisible(true);
		contentPane.add(p4, "Center");
		// MediaTracker�����һ��Image�����װ�أ����ͼ�����
		// MediaTracker ����һ�����ٶ���ý�����״̬��ʵ�ù����ࡣý�������԰�����Ƶ������ͼ�񣬵�Ŀǰ��֧��ͼ��

		MediaTracker mt = new MediaTracker(this.p4);
		Image image = null;
		mt.addImage(image, 0); // װ��ͼ��

		mt.waitForID(0); // �ȴ�ͼ��ȫ��װ��
		int w =200;
		int h = 300;
		
		BufferedImage buffImg = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = buffImg.createGraphics();

		g.drawImage(image, 0, 0, w, h, this.p4);
		ImageIO.write(buffImg, "PNG", new File("F:/pictest/JMF.png"));
		g.dispose();
	}

	
}