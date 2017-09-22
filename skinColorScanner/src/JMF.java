import com.sun.image.codec.jpeg.*;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JMF extends JFrame{

	public  Player player;
	private CaptureDeviceInfo di;
	private MediaLocator ml;
	JFrame jf = new JFrame();
    JButton getCapBt=new JButton("����");
    
	
    
public JMF(){
	
	
    //int X1 = Toolkit.getDefaultToolkit().getScreenSize().width;
    //int Y1 = Toolkit.getDefaultToolkit().getScreenSize().height;
	int X1 = 1280/2;
	int Y1 = 720/2;
	
    jf.setTitle("����");
    jf.setBounds(50, 20, 1280, 680);
    jf.setDefaultCloseOperation(3);
    jf.add(getCapBt,BorderLayout.SOUTH);
    String str2 = "vfw:Microsoft WDM Image Capture (Win32):0";
    di = CaptureDeviceManager.getDevice(str2);
    ml = di.getLocator();
    try {
        player = Manager.createRealizedPlayer(ml);
        player.start();
        Component comp = null;
        if ((comp = player.getVisualComponent()) != null) {
            jf.add(comp, BorderLayout.NORTH);
            //jf.add(getCapBt,BorderLayout.SOUTH);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    getCapBt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FrameGrabbingControl fgc = (FrameGrabbingControl) player
                    .getControl("javax.media.control.FrameGrabbingControl");
            Buffer buffer = fgc.grabFrame();
            BufferToImage bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
            Image image = bufferToImage.createImage(buffer);
            try {
				saveImage(image, getTimeStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}// ������Ƭ����
        }
    });
    jf.setVisible(true);
}
	public static void main(String[] args) {
        new JMF();
	}

	public static String getTimeStr() {
		Date currentTime = new Date();// ʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");// ��ʽ��ʱ��
		String dateString = formatter.format(currentTime);// ת�����ַ���
		String[] str = dateString.split(" ");
		return "F:\\pic\\" + str[0] + str[1] + ".jpg";
	}

	public static void saveImage(Image image,String path) throws IOException     
{     
    BufferedImage bi=new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
   // Graphics2D g2 = bi.createGraphics();     
   // g2.drawImage(image, null, null);     
   // FileOutputStream fos=null;     //�ļ����������
    try {     
            //fos=new FileOutputStream(path);     //���·��
           // ImageIO.write(bi, "jpg",  new File(path));
            
            JPanel  p4 = new JPanel();
            
            
            Graphics g = bi.createGraphics();

    		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), p4);
    		ImageIO.write(bi, "jpg",  new File(path));
    		g.dispose();
            
            
             
    } catch (FileNotFoundException e) {
        e.printStackTrace();     
    }     
   /* JPEGImageEncoder je= JPEGCodec.createJPEGEncoder(fos);   //����һ��ָ��  fos��JPEGImageEncoder����
    JPEGEncodeParam jp=je.getDefaultJPEGEncodeParam(bi);     //@@@
    jp.setQuality(0.5f, false);      //���������ǰ�ѽ����������������
    je.setJPEGEncodeParam(jp);      //����JPEGImageEncoder����������
    try {     
        je.encode(bi);      // �� BufferedImage ��Ϊ JPEG ���������롣
        fos.close();     
    } catch (ImageFormatException e) {
        e.printStackTrace();     
    } catch (IOException e) {
        e.printStackTrace();     
    }              */
}
}