package com.example.surfaceviewtest3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private RendererThread renderer;
	private float distance;
	private List<Map<String,Float>> pointCoordinate;
	private int passStoneHeight;
	private int passStoneWidth;
	private int screenWidth;
	private int screenHeight;
	private Bitmap backBm;
	private Bitmap passStone;
	private Bitmap pressPassStone;
	private Bitmap mountain;
	private Bitmap honey;
	private Bitmap cloud;
	private Bitmap play;
	private Bitmap pressPlay;
	private Path path;
	private List<PointF> passStoneCoordinate;
	private float [] linePath;
	private int pointNo;
	private int pressIndex = -1;
	//是否点击了管卡
	private boolean isPressStone = true;
	//是否点击了开始游戏按钮
	private boolean isPressStart;
	//起始偏移量，距离屏幕左侧和底部
	float xOffset;
	float yOffset;
	//行数
	int rowNo;
	//列数
	int columnsNo;
	//行间隔,列间隔
	float rowGap;
	float columnsGap;
	float maxHeight;
	public MySurfaceView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}
	public void setPressIndex(int index){
		pressIndex = index;
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		backBm = BitmapFactory.decodeResource(getResources(), R.drawable.background).copy(Bitmap.Config.ARGB_8888, true);
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		passStone = BitmapFactory.decodeResource(getResources(), R.drawable.guanka_stone).copy(Bitmap.Config.ARGB_8888, true);
		passStoneHeight = passStone.getHeight();
		passStoneWidth = passStone.getWidth();
		
		pressPassStone = BitmapFactory.decodeResource(getResources(), R.drawable.guanka_stone02).copy(Bitmap.Config.ARGB_8888, true);
		mountain = BitmapFactory.decodeResource(getResources(), R.drawable.moutain,options).copy(Bitmap.Config.ARGB_8888, true);
		honey  = BitmapFactory.decodeResource(getResources(), R.drawable.honey).copy(Bitmap.Config.ARGB_8888, true);
		cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud).copy(Bitmap.Config.ARGB_8888, true);
		play = BitmapFactory.decodeResource(getResources(), R.drawable.startgame01).copy(Bitmap.Config.ARGB_8888, true);
		pressPlay = BitmapFactory.decodeResource(getResources(), R.drawable.startgame02).copy(Bitmap.Config.ARGB_8888, true);
		
		screenWidth = getResources().getDisplayMetrics().widthPixels;
		screenHeight = getResources().getDisplayMetrics().heightPixels;
		
		pointCoordinate = new ArrayList<Map<String,Float>>();
		//起始偏移量，距离屏幕左侧和底部
		xOffset = 40;
		yOffset = 70;
		//行数
		rowNo = 17;
		//列数
		columnsNo = 6;
		//行间隔,列间隔
		rowGap = 100;
		
		maxHeight = (rowNo-8)*rowGap - yOffset;
		
		float columnsGap = (screenWidth - 2*xOffset)/(columnsNo-1);
		for(int i = 0 ; i < rowNo ; i++){
			for(int j = 0 ; j < columnsNo ; j++){
				Map<String,Float> map = new HashMap<String,Float>();
				map.put("xCoordinate", j*columnsGap+xOffset);
				map.put("yCoordinate",screenHeight - i*rowGap - yOffset);
				pointCoordinate.add(map);
			}
		}
		//path = new Path();
		//路径点
		//initPath();
		pointNo = 32;
		linePath = new float[(pointNo-1)*4];
		
		
		passStoneCoordinate = new ArrayList<PointF>();
		initBitmapCoorOnPoint(0);
		initBitmapCoorOnPoint(1);
		initBitmapCoorOnPoint(2);
		initBitmapCoorOnPoint(9);
		initBitmapCoorOnPoint(10);
		initBitmapCoorOnPoint(11);
		initBitmapCoorOnPoint(17);
		initBitmapCoorOnPoint(16);
		initBitmapCoorOnPoint(15);
		initBitmapCoorOnPoint(14);
		initBitmapCoorOnPoint(13);
		initBitmapCoorOnPoint(12);
		initBitmapCoorOnPoint(18);
		initBitmapCoorOnPoint(19);
		initBitmapCoorOnPoint(20);
		initBitmapCoorOnPoint(21);
		initBitmapCoorOnPoint(22);
		initBitmapCoorOnPoint(23);
		initBitmapCoorOnPoint(29);
		initBitmapCoorOnPoint(35);
		initBitmapCoorOnPoint(34);
		initBitmapCoorOnPoint(33);
		initBitmapCoorOnPoint(26);
		initBitmapCoorOnPoint(25);
		initBitmapCoorOnPoint(24);
		initBitmapCoorOnPoint(30);
		initBitmapCoorOnPoint(31);
		initBitmapCoorOnPoint(38);
		initBitmapCoorOnPoint(39);
		initBitmapCoorOnPoint(40);
		initBitmapCoorOnPoint(41);
		initBitmapCoorOnPoint(47);
		initBitmapCoorOnPoint(53);
		initBitmapCoorOnPoint(52);
		initBitmapCoorOnPoint(51);
		initBitmapCoorOnPoint(50);
		initBitmapCoorOnPoint(43);
		initBitmapCoorOnPoint(48);
		initBitmapCoorOnPoint(54);
		initBitmapCoorOnPoint(55);
		initBitmapCoorOnPoint(56);
		initBitmapCoorOnPoint(57);
		initBitmapCoorOnPoint(58);
		initBitmapCoorOnPoint(59);
		initBitmapCoorOnPoint(65);
		initBitmapCoorOnPoint(70);
		initBitmapCoorOnPoint(69);
		initBitmapCoorOnPoint(68);
		initBitmapCoorOnPoint(61);
		initBitmapCoorOnPoint(60);
		initBitmapCoorOnPoint(66);
		initBitmapCoorOnPoint(72);
		initBitmapCoorOnPoint(73);
		initBitmapCoorOnPoint(80);
		initBitmapCoorOnPoint(87);
		initBitmapCoorOnPoint(94);
		initBitmapCoorOnPoint(95);
		initBitmapCoorOnPoint(101);
		
		renderer = new RendererThread(holder);
		renderer.start();
	}
	
	boolean isFirstPoint = true;
	public void initPath(){
		toPoint(0, 2);
		toPoint(2, 9);
		toPoint(9, 11);
		toPoint(11, 17);
		toPoint(17, 12);
		toPoint(12, 18);
		toPoint(18, 23);
		toPoint(23, 35);
		toPoint(35, 33);
		toPoint(33, 26);
		toPoint(26, 24);
		toPoint(24, 30);
		toPoint(30, 31);
		toPoint(31, 38);
		toPoint(38, 41);
		toPoint(41, 53);
		toPoint(53, 50);
		toPoint(50, 43);
		toPoint(43, 48);
		toPoint(48, 54);
		toPoint(54, 59);
		toPoint(59, 65);
		toPoint(65, 70);
		toPoint(70, 68);
		toPoint(68, 61);
		toPoint(61, 60);
		toPoint(60, 72);
		toPoint(72, 73);
		toPoint(73, 94);
		toPoint(94, 95);
		toPoint(95, 101);
		index = 0;
	}
	/*public void initPath(){
		toPoint(0);
		toPoint(2);
		toPoint(9);
		toPoint(11);
		toPoint(17);
		toPoint(12);
		toPoint(18);
		toPoint(23);
		toPoint(35);
		toPoint(33);
		toPoint(26);
		toPoint(24);
		toPoint(30);
		toPoint(31);
		toPoint(38);
		toPoint(41);
		toPoint(53);
		toPoint(50);
	}*/
	int index;
	public void toPoint(int firstPoint,int secondPoint){
		
		Map<String,Float> map = pointCoordinate.get(firstPoint);
		Map<String,Float> map2 = pointCoordinate.get(secondPoint);
		linePath[index++] = map.get("xCoordinate");
		linePath[index++] = map.get("yCoordinate")+distance;
		linePath[index++] = map2.get("xCoordinate");
		linePath[index++] = map2.get("yCoordinate")+distance;

		/*if(isFirstPoint){
			path.moveTo(map.get("xCoordinate"),map.get("yCoordinate")+distance);
			isFirstPoint = false;
		}else{
			path.lineTo(map.get("xCoordinate"),map.get("yCoordinate")+distance);
		}*/
		
	}
	
	public void initBitmapCoorOnPoint(int index){
		Map<String,Float> map = pointCoordinate.get(index);
		PointF point = new PointF(map.get("xCoordinate"),map.get("yCoordinate"));
		passStoneCoordinate.add(point);
 	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		renderer.shutDown();
	}
	public class RendererThread extends Thread{
		private SurfaceHolder mHolder;
		private boolean running;
		private Paint linePaint;
		private Paint circlePaint;
		private Paint textPaint;
		private Paint branchLinePaint;
		
		public RendererThread(SurfaceHolder holder){
			mHolder = holder;
			running = true;
			linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			linePaint.setColor(0xffEEEE00);
			linePaint.setStrokeWidth(15);
			linePaint.setStyle(Paint.Style.STROKE);
			
			branchLinePaint =new Paint(Paint.ANTI_ALIAS_FLAG);
			branchLinePaint.setColor(0xff63B8FF);
			branchLinePaint.setStrokeWidth(15);
			branchLinePaint.setStyle(Paint.Style.STROKE);
			
			textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			textPaint.setColor(Color.WHITE);
			textPaint.setTextSize(20);
			textPaint.setStrokeWidth(5);
			
		}
		@Override
		public void run() {
			
			PointF point1 = passStoneCoordinate.get(11);
			PointF point2 = passStoneCoordinate.get(11);
			PointF point3 = passStoneCoordinate.get(51);
			//分支1
			Map<String,Float> map1 = pointCoordinate.get(28);
			Map<String,Float> map2 = pointCoordinate.get(29);
			//开始按钮
			Map<String,Float> map3 = pointCoordinate.get(19);
			
			while(running){
				Canvas canvas = mHolder.lockCanvas();
				canvas.drawBitmap(backBm, 0, 0, null);
				canvas.drawBitmap(mountain,point1.x+xOffset,point1.y+yOffset+distance, null);
				canvas.drawBitmap(honey,point2.x,point2.y+20+distance, null);
				//canvas.drawPath(path, linePaint);
				canvas.drawLine(map1.get("xCoordinate"), 
						map1.get("yCoordinate")+distance,
						map2.get("xCoordinate"), 
						map2.get("yCoordinate")+distance, branchLinePaint);
				initPath();
				canvas.drawLines(linePath, linePaint);
				for(int i = 0 ; i < passStoneCoordinate.size() ; i++){
					PointF point = passStoneCoordinate.get(i);
					if(i == pressIndex){
						canvas.drawBitmap(pressPassStone, point.x -passStoneWidth/2 , point.y-passStoneHeight/2+distance, null);
					}else{
						canvas.drawBitmap(passStone, point.x -passStoneWidth/2 , point.y-passStoneHeight/2+distance, null);
					}
					
					canvas.drawText(i+1 +"", point.x-passStoneWidth/4, point.y+passStoneHeight/4+distance, textPaint);
				}
				canvas.drawBitmap(cloud,point3.x-20,point3.y- 5*rowGap +distance, null);
				if(isPressStone){
					canvas.drawBitmap(play,map3.get("xCoordinate"),map3.get("yCoordinate"),null);
					if(isPressStart){
						canvas.drawBitmap(pressPlay,map3.get("xCoordinate"),map3.get("yCoordinate"),null);
					}
				}
				mHolder.unlockCanvasAndPost(canvas);
			}
		}
		public void shutDown(){
			running = false;
		}
	}
	float newY = 0;
	float oldDistance;
	
	
	@Override
	
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			distance = oldDistance + event.getY() - newY;
			if(distance < 0){
				distance = 0;
			}else if(distance >= maxHeight){
				distance = maxHeight;
			}
		}else if(event.getAction() == MotionEvent.ACTION_DOWN){
			newY = event.getY();
			float touchXCoordinate = event.getX();
			float touchYCoordinate = event.getY();
			if(passStoneCoordinate != null && !passStoneCoordinate.isEmpty()){
				for(int i = 0 ; i < passStoneCoordinate.size() ; i++){
					PointF point = passStoneCoordinate.get(i);
					float xCoordinate = point.x -passStoneWidth/2;
					float yCoordinate = point.y-passStoneHeight/2+distance;
					float xRange = xCoordinate + passStoneWidth;
					float yRange = yCoordinate + passStoneHeight;
					if(touchXCoordinate > xCoordinate && 
							touchXCoordinate < xRange &&
							touchYCoordinate > yCoordinate && touchYCoordinate < yRange){
						pressIndex = i;
					}
				}
			}
			
			if(isPressStone){
				Map<String,Float> map = pointCoordinate.get(19);
				float rangeX = map.get("xCoordinate") + play.getWidth()/2;
				float rangeY = map.get("yCoordinate") + play.getHeight();
				if(touchXCoordinate > map.get("xCoordinate") &&
						touchXCoordinate < rangeX && 
						touchYCoordinate > map.get("yCoordinate")&&
						touchYCoordinate < rangeY){
					isPressStart = true;
				}else{
					isPressStone = false;
					isPressStart = false;
				}
			}
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			oldDistance = distance;
			float touchXCoordinate = event.getX();
			float touchYCoordinate = event.getY();
			if(Math.abs(event.getY() - newY) < passStoneHeight){
				if(passStoneCoordinate != null && !passStoneCoordinate.isEmpty()){
					for(int i = 0 ; i < passStoneCoordinate.size() ; i++){
						PointF point = passStoneCoordinate.get(i);
						float xCoordinate = point.x -passStoneWidth/2;
						float yCoordinate = point.y-passStoneHeight/2+distance;
						float xRange = xCoordinate + passStoneWidth;
						float yRange = yCoordinate + passStoneHeight;
						if(touchXCoordinate > xCoordinate && 
								touchXCoordinate < xRange &&
								touchYCoordinate > yCoordinate && 
								touchYCoordinate < yRange){
							isPressStone = true;
						}
					}
				}
				if(isPressStart){
					Toast.makeText(getContext(), "跳转"+pressIndex, Toast.LENGTH_SHORT).show();
					System.out.println(pressIndex);
				}
			}else{
				isPressStart = false;
				isPressStone = false;
				pressIndex=-1;
			}
		}
		
		return true;
	}
}
