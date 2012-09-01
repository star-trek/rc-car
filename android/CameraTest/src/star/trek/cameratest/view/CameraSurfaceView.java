package star.trek.cameratest.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import star.trek.cameratest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
        private SurfaceHolder holder;
        private Camera camera;
        private ImageView ivCam = null;

        public CameraSurfaceView(Context context) 
        {
                super(context);

                this.holder = this.getHolder();
                this.holder.addCallback(this);
                this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void setImageView(ImageView iv) {
			ivCam = iv;
		}

		@Override
        public void surfaceCreated(SurfaceHolder holder) 
        {
                try
                {
                        this.camera = Camera.open();
                        this.camera.setPreviewDisplay(this.holder);

                        this.camera.setPreviewCallback(new PreviewCallback() {

                          public void onPreviewFrame(byte[] _data, Camera _camera) {

                            Camera.Parameters params = _camera.getParameters();
                               int w = params.getPreviewSize().width;
                               int h = params.getPreviewSize().height;
                               int format = params.getPreviewFormat();
                               YuvImage image = new YuvImage(_data, format, w, h, null);

                               ByteArrayOutputStream out = new ByteArrayOutputStream();
                               Rect area = new Rect(0, 0, w, h);
                               image.compressToJpeg(area, 50, out);
                               Bitmap bm = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());

                               if(ivCam!=null) ivCam.setImageBitmap(bm);
                          }
                        });

                }
                catch(IOException ioe)
                {
                        ioe.printStackTrace(System.out);
                }
        }


        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
        {
            this.camera.startPreview();
        }    

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) 
        {
            this.camera.stopPreview();
            this.camera.release();
            this.camera = null;
        }


        public Camera getCamera()
        {
                return this.camera;
        }


}