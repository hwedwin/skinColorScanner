package skinColorScanner;

import java.awt.image.BufferedImage;

/**
 * ͼ������
 * 
 * @author Administrator
 * 
 */
public class Image_Utility {

	// /�ṹԪ��
	private static int sData[] = { 0, 0, 0, 0, 1, 1, 0, 1, 0 };

	/**
	 * ͼ��Ŀ����㣺 �ȸ�ʴ������
	 * 
	 * @param sourceImage
	 *            �˴�����Ҷ�ͼ����߶�ֵͼ��
	 * @param shreshold
	 *            :��ֵ�������������ͽ��С����ֵʱ����Ȼ����ͼ��λ�õ�ֵΪ0�������и�ʴ����ʱ��
	 *            ���Ҷ�ֵ���ڵ�����ֵ��С����ֵ��ʱ���ҽṹԪ��Ϊ1��0��ʱ������Ϊ��Ӧλ��ƥ���ϣ� ���Ϊ��ֵͼ����Ӧ�ô���1��
	 * @return
	 */
	public static int[][] open(int[][] source, int threshold) {

		int width = source[0].length;
		int height = source.length;

		int[][] result = new int[height][width];
		// /�ȸ�ʴ����
		//result = correde(source, threshold);
		//result = correde(source, threshold);
		// /����������
		//result = dilate(result, threshold);
		//result = dilate(result, threshold);
		/*
		 * for(int j=0;j<height;j++){ for(int i=0;i<width;i++){
		 * System.out.print(result[j][i]+","); } System.out.println(); }
		 */

		return result;
	}

	/**
	 * ��ʴ����
	 * 
	 * @param source
	 * @param shreshold
	 *            ���Ҷ�ֵ������ֵ��С����ֵ��ʱ���ҽṹԪ��Ϊ1��0��ʱ������Ϊ��Ӧλ��ƥ���ϣ�
	 * @return
	 */
	private static int[][] correde(int[][] source, int threshold) {
		int width = source[0].length;
		int height = source.length;

		int[][] result = new int[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// /��Ե�����в�������Ե�ڲŲ���
				if (i > 0 && j > 0 && i < height - 1 && j < width - 1) {
					int max = 0;

					// /�ԽṹԪ�ؽ��б���
					for (int k = 0; k < sData.length; k++) {
						int x = k / 3;// /�̱�ʾxƫ����
						int y = k % 3;// /������ʾyƫ����

						if (sData[k] != 0) {
							// /��Ϊ0ʱ������ȫ��������ֵ�����������Ϊ0����������
							if (source[i - 1 + x][j - 1 + y] >= threshold) {
								if (source[i - 1 + x][j - 1 + y] > max) {
//									max = source[i - 1 + x][j - 1 + y];
									max = 1;
								}
							} else {
								// //��ṹԪ�ز�ƥ��,��ֵ0,��������
								max = 0;
								break;
							}
						}
					}

					// //�˴�����������ֵ����maxС����ֵ��ʱ��͸�Ϊ0
					result[i][j] = max;

				} else {
					// /ֱ�Ӹ�ֵ
					result[i][j] = source[i][j];

				}// /end of the most out if-else clause .

			}
		}// /end of outer for clause

		return result;
	}

	/**
	 * ��������
	 * 
	 * @param source
	 * @param threshold
	 *            ����������ֵС����ֵʱ��ͼ����ֵ��Ȼ��Ϊ0
	 * @return
	 */
	private static int[][] dilate(int[][] source, int threshold) {
		int width = source[0].length;
		int height = source.length;

		int[][] result = new int[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// /��Ե�����в���
				if (i > 0 && j > 0 && i < height - 1 && j < width - 1) {
					int max = 0;

					// /�ԽṹԪ�ؽ��б���
					for (int k = 0; k < sData.length; k++) {
						int x = k / 3;// /�̱�ʾxƫ����
						int y = k % 3;// /������ʾyƫ����

						if (sData[k] != 0) {
							// /���ṹԪ���в�Ϊ0ʱ,ȡ��ͼ���ж�Ӧ��������ֵ����ͼ��ǰλ����Ϊ�Ҷ�ֵ
							if (source[i - 1 + x][j - 1 + y] > max) {
//								max = source[i - 1 + x][j - 1 + y];
								max = 1;
							}
						}
					}

					// //�˴�����������ֵ����maxС����ֵ��ʱ��͸�Ϊ0
					if (max < threshold) {
						result[i][j] = 0;
					} else {
						result[i][j] = max;
					}
					// result[i][j]=max;

				} else {
					// /ֱ�Ӹ�ֵ
					result[i][j] = source[i][j];
				}

			}
		}

		return result;
	}

	/**
	 * �Ҷ�ͼ����ȡ����
	 * 
	 * @param image
	 * @return int[][]����
	 */
	public static int[][] imageToArray(BufferedImage image) {

		int width = image.getWidth();
		int height = image.getHeight();

		int[][] result = new int[height][width];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int rgb = image.getRGB(i, j);
//				int grey = (rgb >> 16) & 0xFF;
//				// System.out.println(grey);
				if(rgb==0x000000){
					result[j][i] = 0;
				}else{
					result[j][i] = 1;
				}
			}
		}
		return result;
	}

	/**
	 * ����תΪ�Ҷ�ͼ��
	 * 
	 * @param sourceArray
	 * @return
	 */
	public static BufferedImage arrayToGreyImage(int[][] sourceArray) {
		int width = sourceArray[0].length;
		int height = sourceArray.length;
		BufferedImage targetImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int greyRGB = sourceArray[j][i];
				int r = (greyRGB & 0xff0000) >> 16;
				int g = (greyRGB & 0xff00) >> 8;
				int b = (greyRGB & 0xff);
				if(sourceArray[j][i]==0){
					greyRGB = 0x000000;
				}else if(sourceArray[j][i]==1){
					greyRGB = 0xffffff;
				}else{
					greyRGB = 0xff0000;
				}
				targetImage.setRGB(i, j, greyRGB);
			}
		}

		return targetImage;
	}

}