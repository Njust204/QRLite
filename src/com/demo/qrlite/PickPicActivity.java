package com.demo.qrlite;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.demo.data.DataHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class PickPicActivity extends Activity {
	private SurfaceView surface = null ;
	private Button but = null ;
	private SurfaceHolder holder = null ;
	private Camera cam = null ;
	private boolean previewRunning =  true ;
	private String fileNameForPic = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_pickpic);
		this.but = (Button) super.findViewById(R.id.pickPic_Btn) ;
		this.surface = (SurfaceView) super.findViewById(R.id.pickPic_surface) ;

		this.holder = this.surface.getHolder() ;
		this.holder.addCallback(new MySurfaceViewCallback()) ;
		this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS) ;
		this.holder.setFixedSize(500, 350);
		
//TODO  获取filename
		Intent intent = PickPicActivity.this.getIntent();
		fileNameForPic = intent.getStringExtra("filename");
		
		this.but.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener {

		public void onClick(View v) {
			if(PickPicActivity.this.cam != null) {
				PickPicActivity.this.cam.autoFocus(new AutoFocusCallbackImpl()) ;
			}
		}
		
	}
	
	private class MySurfaceViewCallback implements SurfaceHolder.Callback {

		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			
		}

		public void surfaceCreated(SurfaceHolder holder) {
			PickPicActivity.this.cam = Camera.open() ;	// ȡ�õ�һ������ͷ
			WindowManager manager = (WindowManager) PickPicActivity.this
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = manager.getDefaultDisplay() ;
			Parameters param = PickPicActivity.this.cam.getParameters() ;
			param.setPreviewSize(display.getWidth(), display.getHeight()) ;
			param.setPreviewFrameRate(5) ;	// һ��5֡
			param.setPictureFormat(PixelFormat.JPEG) ;	// ͼƬ��ʽ
			param.set("jpen-quality", 80) ;
			PickPicActivity.this.cam.setParameters(param) ;
			try {
				PickPicActivity.this.cam.setPreviewDisplay(PickPicActivity.this.holder) ;
			} catch (IOException e) {
			}
			PickPicActivity.this.cam.startPreview() ;	// ����Ԥ��
			PickPicActivity.this.previewRunning = true ;	// �Ѿ���ʼԤ��
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			if(PickPicActivity.this.cam != null) {
				if(PickPicActivity.this.previewRunning) {
					PickPicActivity.this.cam.stopPreview() ;	// ֹͣԤ��
					PickPicActivity.this.previewRunning = false ;
				}
				PickPicActivity.this.cam.release() ;
			}
		}
		
	}
	
	private class AutoFocusCallbackImpl implements AutoFocusCallback {

		public void onAutoFocus(boolean success, Camera camera) {
			if(success) {	// �ɹ�
				PickPicActivity.this.cam.takePicture(sc, pc, jpgcall) ;
			}
		}
		
	}
	
	private PictureCallback jpgcall = new PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {	// ����ͼƬ�Ĳ���
			Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			String fileName = Environment.getExternalStorageDirectory()
					.toString()
					+ File.separator
					+ "QRLitePhotos"
					+ File.separator
					+ fileNameForPic + ".jpg";
			File file = new File(fileName) ;
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs() ;	
			}
			
			try {
				if(file.exists()){
					file.delete();
				}
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file)) ;
				bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos) ; // 添加图片进入缓冲区
				bos.flush() ;
				bos.close() ;
				DataHolder.dtManager.updateItemImgInfo(fileNameForPic, fileName);
				Toast.makeText(PickPicActivity.this,
						"图片拍摄成功，已保存" , Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				Toast.makeText(PickPicActivity.this,
						"拍摄失败", Toast.LENGTH_SHORT)
						.show();
			}
			PickPicActivity.this.cam.stopPreview() ;
			PickPicActivity.this.cam.startPreview() ;
		}
		
	} ;
	
	private ShutterCallback sc = new ShutterCallback(){
		public void onShutter() {
			// 快门回调
		}
	} ;
	private PictureCallback pc = new PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {
			//图片源数据回调
		}
		
	} ;
}