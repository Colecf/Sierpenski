import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class Sierpinski{
    public static void main(String[] args) {
        Sierpinski s = new Sierpinski(2048);
        s.saveToImage();
    }

    private int[][] data;

    public Sierpinski(int size) {
        data = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                data[i][j] = 0;
            }
        }
        data[0][0] = 1;
        for(int i=1; i<size; i++) {
            data[i][0] = 1;
            for(int j=1; j<size; j++) {
                data[i][j] = (data[i-1][j] + data[i-1][j-1])%1000;
            }
        }
    }
    
    public Sierpinski() {
    	this(1024);
    }

    public void print() {
        for(int i=0; i<data.length; i++) {
            for(int j=0; j<data.length; j++) {
            	System.out.print(data[i][j] + " ");
//                if(data[i][j] % 2 == 1) {
//                    System.out.print("*");
//                } else {
//                    System.out.print(" ");
//                }
            }
            System.out.println();
        }
    }

    public void saveToImage() {
        BufferedImage img = new BufferedImage(data.length, data.length, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<data.length; i++) {
        		for(int j=0; j<data.length; j++) {
        			int center = data.length/2;
        			int antiblue = 255-(int) (255*Math.sqrt(Math.pow(i-center, 2)+Math.pow(j-center, 2))/724);
        			img.setRGB(j, i, antiblue<<16|antiblue<<8|255);
        		}
        }
        for(int i=0; i<data.length; i++) {
            for(int j=0; j<data.length; j++) {
        		if(data[i][j]%2==1){
        			img.setRGB((int)(j+data.length/2.0d-i/2.0d-1), i, 0);
        		}
            }
        }
        try {
            File outputfile = new File("out.png");
            ImageIO.write(img, "png", outputfile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
