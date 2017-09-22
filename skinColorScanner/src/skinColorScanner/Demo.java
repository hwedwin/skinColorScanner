package skinColorScanner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SkinColorScanner scs = null;
		String imgPath = "F:/pic/"+"��������20"+".jpg";
		BufferedImage bi = ImageIO.read(new FileInputStream(imgPath));
		int width = bi.getWidth();
		int height = bi.getHeight();
		int[][] binArray = new int[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bi.getRGB(i, j);

				int r = (rgb & 0xff0000) >> 16;
				int g = (rgb & 0xff00) >> 8;
				int b = (rgb & 0xff);
				if (r==255&&g==255&&b==255) {
					binArray[i][j] = 1;//0��ʾ��ɫ
				} else {
					binArray[i][j] = 0;//1��ʾ��ɫƤ������
					if(r>190&&g<110){
						bi.setRGB(i, j, 0xff0000);
						binArray[i][j] = 2;//2��ʾ��ɫƤ������
					}
					if(r>190&&g<110){
						bi.setRGB(i, j, 0xff0000);
						binArray[i][j] = 3;//3��ʾ��ɫƤ������
					}
				}
			}
		}
		
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
		binArray = correde(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
//		binArray = dilate(binArray);
		BufferedImage bi2 = arrayToImage(binArray);
		ImageIO.write(bi2, "jpg",new File("F:/pic/��ʴ����20.jpg"));
		
	}
	
	private static int[][] correde(int[][] source) {
		int height = source[0].length;
		int width = source.length;

		int[][] result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// /��Ե�����в�������Ե�ڲŲ���
				if (i > 0 && j > 0 && i < width - 1 && j < height - 1) {
					//��Ԫ�ز�Ϊ��ɫʱ
					if(!(source[i][j]==0)){
						//��ṹԪ�ؽ��бȽ�
						if((source[i+1][j]==1)&&(source[i][j+1]==1)){
							result[i][j] = source[i][j];
						}else{
							result[i][j] = 0;
						}
					}
				} 
			}
		}

		return result;
	}
	private static int[][] dilate(int[][] source) {
		int height = source[0].length;
		int width = source.length;

		int[][] result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// /��Ե�����в�������Ե�ڲŲ���
				if (i > 0 && j > 0 && i < width - 1 && j < height - 1) {
					//��Ԫ��Ϊ��ɫʱ
					if(source[i][j]==0){
						//��ṹԪ�ؽ��бȽ�
						if((source[i+1][j]==1||source[i+1][j]==2)||(source[i][j+1]==1||source[i][j+1]==2)){
							
							if (source[i + 1][j + 1]==1) {
								result[i][j] = 1;
							}else if(source[i + 1][j + 1]==2){
								result[i][j] = 2;
							}else{
								result[i][j] = 1;
							}
						}
					} else {
						//ֱ�Ӹ�ֵ
						result[i][j] = source[i][j];
					}
				}
			}
		}
		return result;

	}

	
	public static BufferedImage arrayToImage(int[][] sourceArray) {
		int height = sourceArray[0].length;
		int width = sourceArray.length;
		BufferedImage targetImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int greyRGB = sourceArray[i][j];
				if(sourceArray[i][j]==0){
					greyRGB = 0x000000;
				}else if(sourceArray[i][j]==1){
					greyRGB = 0xffffff;
				}//else{
					//greyRGB = 0xff0000;
				//}
				targetImage.setRGB(i, j, greyRGB);
			}
		}

		return targetImage;
	}
}
