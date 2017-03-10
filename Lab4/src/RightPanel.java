import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = -1073170544184446100L;
	private BufferedImage img;

	public RightPanel(BufferedImage img){
		this.img = img;
	}

	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(this.img, 0, 0 , null);		
	}

	public void clearImage(Graphics g) {
		int width = img.getWidth();
		int height = img.getHeight();
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				img.setRGB(i,j,0xffffffff);
			}
		}
	}
	
	//
	// Transition by columns top to bottom left to right
	public void transitionTBLR(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				int pixelColor= leftImage.getRGB(i,j);
				img.setRGB(i, j, pixelColor);

			}

			repaint();

			try { Thread.sleep(10); } catch (InterruptedException e) { };

		}

	}
	

	//
	// Transition by rows left to right top to bottom
	public void transitionLRTB(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();
		for (int j=0; j<height; j++){
			for (int i=0; i<width; i++){
				int pixelColor= leftImage.getRGB(i,j);
				img.setRGB(i, j, pixelColor);
			}
			repaint();
			try { Thread.sleep(10); } catch (InterruptedException e) { };
		}
	}
	// Transition random pixels
	public void transitionRandom(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();
		for (int j=0; j<(height*width); j++){
			Random randomGenerator = new Random();
			int randomx=randomGenerator.nextInt(width);
			int randomy=randomGenerator.nextInt(height);
			int pixelColor= leftImage.getRGB(randomx,randomy);
			img.setRGB(randomx,randomy, pixelColor);
			repaint();
			try { Thread.sleep(1); } catch (InterruptedException e) { };
		}			
		try { Thread.sleep(15); } catch (InterruptedException e) { };
		}
	
	
	// Transition diagonal form top to bottom, left to right
	public void transitionDiagonal45LR(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();

		if (width > height) {
			// Image with larger width than height		
			for (int row=0; row<height; row++){
				int diagonalRow = row;
				for (int col=0; col<=row; col++) {
					int pixelColor= leftImage.getRGB(col,diagonalRow);//observation: in the diagonal as one rises another falling
					img.setRGB(col, diagonalRow, pixelColor);
					diagonalRow--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
			for (int i=0; i<(width-height); i++) {
				int row = height-1;
				int col = i+1;
				for (int j=0; j<height; j++) {
					int pixelColor= leftImage.getRGB(col,row);
					img.setRGB(col, row, pixelColor);
					col++; row--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
			for (int i=0; i<=(width); i++) {
				int diagonalRow = height-1;
				for (int col=(width-height+i); col<width; col++) {
					int pixelColor= leftImage.getRGB(col,diagonalRow);
					img.setRGB(col, diagonalRow, pixelColor);
					diagonalRow--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
		}
		else 
		{
			for (int col=0; col<width; col++){
				int diagonalCol = col;
				for (int row=0; row<=col; row++) {
					int pixelColor= leftImage.getRGB(diagonalCol,row);
					img.setRGB(diagonalCol, row, pixelColor);
					diagonalCol--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
			for (int i=0; i<(height-width); i++) {
				int row = i+1;
				int col = width-1;
				for (int j=0; j<width; j++) {
					int pixelColor= leftImage.getRGB(col,row);
					img.setRGB(col, row, pixelColor);
					col--; row++;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
			for (int i=0; i<=(height); i++) {
				int diagonalCol = width-1;
				for (int row=(height-width+i); row<height; row++) {
					int pixelColor= leftImage.getRGB(diagonalCol,row);
					img.setRGB(diagonalCol,row, pixelColor);
					diagonalCol--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
		}
	}	
}
