import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Vector;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Processor;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TextWindow extends JFrame {
	static TextWindow frame ;
	private JPanel contentPane,p3,p4;
	private JPanel panel ;
	public CaptureDeviceInfo device2;
	public Player player2;
	Component comV, comVC, comA;
	public static Player player = null;
	private CaptureDeviceInfo device = null;
	private MediaLocator locator = null;
	static String str1 = "vfw:Logitech USB Video Camera:0";	  
    static String str = " vfw:Microsoft WDM Image Capture (Win32):0";//vfw:Microsoft WDM Image Capture (Win32):0
    static String aa="JavaSound audio capture";//��Ƶ
    static String bb="DirectSoundCapture";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TextWindow();
					
					frame.setVisible(true);
					frame.speaker();
					frame.jbInit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void speaker() throws Exception {
		p3=new JPanel();
		p3.setLayout(new BorderLayout(0, 0));
		Vector deviceList = CaptureDeviceManager.getDeviceList(new AudioFormat(AudioFormat.LINEAR, 44100, 16, 2));
		
		if (deviceList.size() > 0) {
			device2 = (CaptureDeviceInfo) deviceList.firstElement();
		} else {
			System.out.println("�Ҳ�����Ƶ�豸��");
		}
		try {
			 player2 = Manager.createRealizedPlayer(device2.getLocator());
			player2.start();
		
			if ((comA = player2.getControlPanelComponent()) != null) {
				p3.add(comA,"Center");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane.add(p3,"South");		
	}
	
	private void jbInit() throws Exception {
		// ��ʼ���豸��strΪ�豸����
		p4=new JPanel();
		p4.setLayout(new BorderLayout(0, 0));
		p4.setOpaque(false);
		device=CaptureDeviceManager.getDevice(str);
		MediaLocator locatorss = new MediaLocator("vfw://0");
		player=Manager.createRealizedPlayer(locatorss);
		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
		//  player.addControllerListener(new PlayerEventHandler()); // �����Ž���		
		player.start();
		comV = player.getVisualComponent();//��ȡ��������ʾ���
FrameGrabbingControl fgc =(FrameGrabbingControl) player.getControl( "javax.media.control.FrameGrabbingControl "); 

		if ((comV ) != null)			
			{
				p4.add(comV, "Center");
			}
		p4.setVisible(true);
		contentPane.add(p4,"Center");
	}
	/**
	 * Create the frame.
	 */
	public TextWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setOpaque(false);		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));			
	}
}
