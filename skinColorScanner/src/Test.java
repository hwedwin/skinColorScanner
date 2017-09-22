import java.awt.BorderLayout;  
import java.awt.Component;  
import javax.media.CaptureDeviceInfo;  
import javax.media.Manager;  
import javax.media.MediaLocator;  
import javax.media.Player;  
import javax.media.cdm.CaptureDeviceManager;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
public class Test extends JFrame{  
    public  static Player player = null;  
    private CaptureDeviceInfo deviceInfo = null;  
    private MediaLocator mediaLocator = null;  
    private Component component = null;  
    private JPanel vedioPanel = null;  
      
    String   str1   =   "vfw:Logitech   USB   Video   Camera:0";      //��ȡUSB����ͷ���ַ���  
    String   str2   =   "vfw:Microsoft WDM Image Capture (Win32):0";    //��ȡ��������ͷ���ַ���  
    // Creates a new instance of CameraTest   
    public Test() {  
        init();  
    }  
    public void init(){  
        deviceInfo = CaptureDeviceManager.getDevice(str2);  //�����ַ�����ȡ�ɼ��豸������ͷ��������  
     //   System.out.println(deviceInfo);         //��ʾ�ɼ��豸(����ͷ)����Ϣ  
     //   System.out.println(deviceInfo.getName());     //��ʾ�ɼ��豸������ͷ�����豸����  
        mediaLocator = deviceInfo.getLocator(); //��ȡ�ɼ��豸�Ķ�λ�������ã���Ҫ���ݴ�������������Ƶ������  
          
        try{  
            player = Manager.createRealizedPlayer(mediaLocator);// ����mediaLocator ��ȡһ��player  
            component = player.getVisualComponent();  
            if (component != null){  
                vedioPanel = new JPanel();  
                vedioPanel.add(component, BorderLayout.NORTH);  
                this.add(vedioPanel);  
                this.pack();    // �Զ����䴰���С  
                this.setResizable(false);  
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
                this.setVisible(true);  
                player.start();  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) {  
        new Test();  
    }  
}  
