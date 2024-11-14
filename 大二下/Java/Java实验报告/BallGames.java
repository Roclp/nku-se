package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;
class Racket  //球拍
{
	//球拍位置
	public int x,y;
	//球拍大小
	public int width,height;
	//打球方向
	public boolean upper=true;
	//球拍颜色
	Color outerC=Color.red;
	Color innerC=Color.pink;
	//绘制球拍
	public void drawRacket(Graphics2D g2)
	{
		int w=width;
		int h=height;
		int w2 = w/2;
        int h2 = h/2;
        //设置渐变色
        g2.setPaint(new GradientPaint(x,y,outerC,x+w*.35f,y+h*.35f,innerC));
        g2.fillRect(x, y, w2, h2);
        g2.setPaint(new GradientPaint(x+w,y,outerC,x+w*.65f,y+h*.35f,innerC));
        g2.fillRect(x+w2, y, w2, h2);
        g2.setPaint(new GradientPaint(x,y+h,outerC,x+w*.35f,y+h*.65f,innerC));
        g2.fillRect(x, y+h2, w2, h2);
        g2.setPaint(new GradientPaint(x+w,y+h,outerC,x+w*.65f,y+h*.65f,innerC));
        g2.fillRect(x+w2, y+h2, w2, h2);

        g2.setColor(Color.black);
        TextLayout tl = new TextLayout(
                "Racket", g2.getFont(), g2.getFontRenderContext());
        tl.draw(g2, (int) (x+w/2-tl.getBounds().getWidth()/2),
                (int) (y+h/2+tl.getBounds().getHeight()/2));
	}
}
//球
class Ball {
    
	 public int bsize;
     public float x, y;
     public float Vx = 0.1f;
     public float Vy = 0.05f;
     public int nImgs = 5;
     public BufferedImage imgs;
     public boolean out=false;
 
     private final float inelasticity = 1f;
     private final float Ax = 0.0f;
     private final float Ay = 0.0002f;
     private final float Ar = 0.9f;
     private final int UP = 0;
     private final int DOWN = 1;
     private int indexDirection = UP;
     private boolean collision_x, collision_y;
     private float jitter;
     private Color color;
     private boolean isSelected;
     
    
    
        public Ball(Color color, int bsize) {
            this.color = color;
            makeImages(bsize);
        }
    
    //根据颜色和大小创建立体视觉效果球的图片
	public void makeImages(int bsize) {
		this.bsize = bsize * 2;
		int R = bsize;
		byte[] data = new byte[R * 2 * R * 2];
		int maxr = 0;
		for (int Y = 2 * R; --Y >= 0;) {
			int x0 = (int) (Math.sqrt(R * R - (Y - R) * (Y - R)) + 0.5);
			int p = Y * (R * 2) + R - x0;
			for (int X = -x0; X < x0; X++) {
				int x = X + 15;
				int y = Y - R + 15;
				int r = (int) (Math.sqrt(x * x + y * y) + 0.5);
				if (r > maxr) {
					maxr = r;
				}
				data[p++] = r <= 0 ? 1 : (byte) r;
			}
		}
		int bg = 255;
		byte red[] = new byte[256];
		red[0] = (byte) bg;
		byte green[] = new byte[256];
		green[0] = (byte) bg;
		byte blue[] = new byte[256];
		blue[0] = (byte) bg;
		float b = 0.5f;
		for (int i = maxr; i >= 1; --i) {
			float d = (float) i / maxr;
			red[i] = (byte) blend(blend(color.getRed(), 255, d), bg, b);
			green[i] = (byte) blend(blend(color.getGreen(), 255, d), bg, b);
			blue[i] = (byte) blend(blend(color.getBlue(), 255, d), bg, b);
		}
		IndexColorModel icm = new IndexColorModel(8, maxr + 1, red, green,
				blue, 0);
		DataBufferByte dbb = new DataBufferByte(data, data.length);
		int bandOffsets[] = { 0 };
		WritableRaster wr = Raster.createInterleavedRaster(dbb, R * 2, R * 2,
				R * 2, 1, bandOffsets, null);
		imgs = new BufferedImage(icm, wr, icm.isAlphaPremultiplied(), null);
	}

	// 球图片颜色计算
	private final int blend(int fg, int bg, float fgfactor) {
		return (int) (bg + (fg - bg) * fgfactor);
	}
     //球移动
     public void step(long deltaT, int w, int h,Racket r) {
         collision_x = false;
         collision_y = false;
 
         jitter = (float) Math.random() * .01f - .005f;
 
         x += Vx * deltaT + (Ax / 2.0) * deltaT * deltaT;
         y += Vy * deltaT + (Ay / 2.0) * deltaT * deltaT;
         if (x <= 0.0f) {
             x = 0.0f;
             Vx = -Vx * inelasticity + jitter;
             collision_x = true;
         }
         if (x + bsize >= w) {
             x = w - bsize;
             Vx = -Vx * inelasticity + jitter;
             collision_x = true;
         }

			if (y <= 0) {
                y = 0;
                Vy = -Vy * inelasticity + jitter;
                collision_y = true;
            }
		
		if (r.upper) {
			if (y + bsize >= h - r.height) {
				if ((x >= (r.x - bsize)) && (x <= (r.x + r.width))) {
					y = h - r.height - bsize;
					Vx *= inelasticity;
					Vy = -Vy * inelasticity + jitter;
					collision_y = true;
				} else {
					System.out.println("out!");
					this.out=true;
				}
			}
		}
		else
		{
			if (y + bsize >= h) {
                y = h - bsize;
                Vx *= inelasticity;
                Vy = -Vy * inelasticity + jitter;
                collision_y = true;
            }
		}
         Vy = Vy + Ay * deltaT;
         Vx = Vx + Ax * deltaT;
 
       
     }
     public void step(long deltaT, int w, int h) {
         collision_x = false;
         collision_y = false;
 
         jitter =0;// (float) Math.random() * .01f - .005f;
 
         x += Vx * deltaT + (Ax / 2.0) * deltaT * deltaT;
         y += Vy * deltaT + (Ay / 2.0) * deltaT * deltaT;
         if (x <= 0.0f) {
             x = 0.0f;
             Vx = -Vx * inelasticity + jitter;
             collision_x = true;
         }
         if (x + bsize >= w) {
             x = w - bsize;
             Vx = -Vx * inelasticity + jitter;
             collision_x = true;
         }
         if (y <= 0) {
             y = 0;
             Vy = -Vy * inelasticity + jitter;
             collision_y = true;
         }
         if (y + bsize >= h) {
             y = h - bsize;
             Vx *= inelasticity;
             Vy = -Vy * inelasticity + jitter;
             collision_y = true;
         }
         Vy = Vy + Ay * deltaT;
         Vx = Vx + Ax * deltaT;
 
       
     }
    }
//使用线程进行球与球拍重绘
class move implements Runnable{

	ballp p;
	
	public move(ballp p) {
		super();
		this.p = p;
	}


	public void run() {
		// TODO Auto-generated method stub
	
		while(true)
		{
			
	        p.deltaT =5;
	        p.b.step(p.deltaT, p.getWidth(), p.getHeight(),p.r);
		if (p.b.out) {
			p.repaint();
			break;
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.repaint();
		
		}
}
}
//绘制球拍和球的容器
class ballp extends JPanel {
	Ball b;
	public long now, deltaT, lasttime;
    private boolean active;
    Racket r;
	ballp()
	{
		b=new Ball(Color.red,10);
		b.Vx = (float)Math.random() / 4.0f - 0.125f;
        b.Vy = -(float)Math.random() / 4.0f - 0.2f;
		b.makeImages(b.bsize);
		this.setBackground(Color.white);
		setDoubleBuffered(true);
		r=new Racket();
		r.width=200;
		r.height=20;
		r.x=0;
		
	}
	@Override
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		
		super.paintComponent(arg0);
		Graphics2D g2=(Graphics2D) arg0;
		g2.setPaint(new GradientPaint(0, 0, Color.white,
                0, this.getHeight()*2/3, Color.green));
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2.drawImage(b.imgs, (int) b.x, (int) b.y, this);
		g2.setColor(Color.red);
		r.y=this.getHeight()-r.height;
		//绘制球拍
		r.drawRacket(g2);
		//绘制出局信息
		if (b.out)
		{
			g2.clearRect(0, 0, this.getWidth(), this.getHeight());
			int w=this.getWidth();
			int h=this.getHeight();
			FontRenderContext frc = g2.getFontRenderContext();
	        Font f = new Font("Arial",Font.PLAIN,w/8);
	        Font f1 = new Font("Arial",Font.ITALIC,w/8);
	        String s = "out!";
	        AttributedString as = new AttributedString(s);
	        as.addAttribute(TextAttribute.FONT, f, 0, s.length());
	        AttributedCharacterIterator aci = as.getIterator();
	        TextLayout tl = new TextLayout (aci, frc);
	        float sw = (float) tl.getBounds().getWidth();
	        float sh = (float) tl.getBounds().getHeight();
	        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w/2-sw/2, h*0.2+sh/2));
	        g2.setColor(Color.green);
	        g2.setStroke(new BasicStroke(2.5f));
	        g2.draw(sha);
	        g2.setColor(Color.pink);
	        g2.fill(sha);
		}
		
	}
	
	}
	
	
//监听键盘事件
class keyleft implements KeyListener{
	ballp p;
	
	public keyleft(ballp p) {
		super();
		this.p = p;
	}
	public void keyPressed(KeyEvent ke) {
		//System.out.println("test");
		// TODO Auto-generated method stub
		//按下向左、向右键时，球拍水平座标分别减少、增加
		if (ke.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (p.r.x > 0)
				p.r.x -= 10;
			
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (p.r.x < p.getWidth() - p.r.width)
				p.r.x += 10;
		}
	p.repaint();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

public class BallGames extends JFrame{
public static void main (String[] arg)
{
	BallGames b=new BallGames();
	b.getContentPane().setLayout(new BorderLayout());
	ballp p=new ballp();
	
	Thread t=new Thread(new move(p));
	t.start();
	b.addKeyListener(new keyleft(p));
	b.getContentPane().add(p,"Center");
	b.setDefaultCloseOperation(EXIT_ON_CLOSE);
	b.setSize(600, 700);
	b.setVisible(true);
	
}
}
