package star.trek.cameratest;

import star.trek.cameratest.view.CameraSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout);
        CameraSurfaceView cameraSurfaceView = new CameraSurfaceView(this);
        cameraSurfaceView.setImageView((ImageView)findViewById(R.id.imageView1));
        linearLayout.addView(cameraSurfaceView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
}
