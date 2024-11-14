package gui.lab;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
class MyShape{
	//1,直线，2,矩形，3画笔
	int shapetype;
	Color c;
	Shape s;
}
class DrawMouseMotion implements MouseMotionListener{
DrawPanel dp;
	
DrawMouseMotion(DrawPanel dp){
		this.dp=dp;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(dp.currShapeType==1) { //直线
			Line2D.Double line=(Line2D.Double)dp.currShape.s;
			line.x2=e.getX();
			line.y2=e.getY();
			dp.repaint();						
		}
		if(dp.currShapeType==3) { //画笔
			Line2D.Double line=(Line2D.Double)dp.currShape.s;
			line.x2=e.getX();
			line.y2=e.getY();
			
			dp.drawlist.add(dp.currShape);
			dp.currShape=new MyShape();
			dp.currShape.s=new Line2D.Double(e.getX(), e.getY(),e.getX(), e.getY());
			dp.currShape.shapetype=dp.currShapeType;
  		    dp.currShape.c=dp.currColor;			
			dp.repaint();					
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
class DrawMouse implements MouseListener{
	DrawPanel dp;
	
	DrawMouse(DrawPanel dp){
		this.dp=dp;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		dp.currShape=new MyShape();
		if(dp.currShapeType==1) { //直线
			dp.currShape.s=new Line2D.Double(e.getX(), e.getY(),e.getX(), e.getY());
			dp.currShape.shapetype=dp.currShapeType;
		}
		if(dp.currShapeType==3) { //pen
			dp.currShape.s=new Line2D.Double(e.getX(), e.getY(),e.getX(), e.getY());
			dp.currShape.shapetype=dp.currShapeType;
		}
		dp.currShape.c=dp.currColor;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(dp.currShapeType==1) { //直线
			Line2D.Double line=(Line2D.Double)dp.currShape.s;
			line.x2=e.getX();
			line.y2=e.getY();
			dp.drawlist.add(dp.currShape);
			dp.currShape=null;
			dp.repaint();
						
		}
		if(dp.currShapeType==3) { // 画笔
			Line2D.Double line=(Line2D.Double)dp.currShape.s;
			line.x2=e.getX();
			line.y2=e.getY();
			dp.drawlist.add(dp.currShape);
			dp.currShape=null;
			dp.repaint();
						
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
class DrawPanel extends JPanel{
	ArrayList<MyShape> drawlist=new ArrayList();
	Color currColor;
	MyShape currShape;
	int currShapeType;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D graph=(Graphics2D) g;
		BasicStroke stroke=new BasicStroke(3);
				graph.setStroke( stroke);
		drawlist.forEach(e->{
			graph.setColor(e.c);
			graph.draw(e.s);
		});
		if(currShape!=null) {
		graph.setColor(currShape.c);
		graph.draw(currShape.s);}
	}
	DrawPanel(){
		this.addMouseListener(new DrawMouse(this));
		this.addMouseMotionListener(new DrawMouseMotion(this));
	}
	
}
public class Paint extends JFrame {
    
	JToolBar tb=new JToolBar();
	JButton btnSave=new JButton("保存");
	JButton btnColor=new JButton("选择颜色");
	JButton btnLine=new JButton("直线");
	JButton btnPen=new JButton("画笔");
	DrawPanel dp=new DrawPanel();
	void initEvent(){
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();
				jfc.showDialog(Paint.this,"保存");
				File f=jfc.getSelectedFile();
				System.out.println(f);
				
			}
			
		});
		btnColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JColorChooser jcc=new JColorChooser();
				jcc.setColor(dp.currColor);
				Color temp=jcc.showDialog(Paint.this, "选择颜色", dp.currColor);
				if (temp!=null) {
					dp.currColor=temp;
				}
				System.out.println(temp);
				
			}
			
		});
		btnLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dp.currShapeType=1;
				
			}
			
		});
		btnPen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dp.currShapeType=3;
				
			}
			
		});
	}
	Paint(){
		initEvent();
		tb.add(btnSave);
		tb.add(btnColor);
		tb.add(btnLine);
		tb.add(btnPen);
		this.getContentPane().add("North",tb);
		this.getContentPane().add("Center",dp);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] arg) {
		Paint p=new Paint();
	}
}
