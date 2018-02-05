package system.mil.simplebirthdaycard;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RelativeLayout rrlayout;
    private MediaPlayer mp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        getActionBar().hide();
        getActionBar().setTitle("");


        rrlayout = findViewById(R.id.rootlayout);

        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        final Animation finishAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        rrlayout.startAnimation(startAnimation);

        AssetManager assetManager = this.getAssets();
        Typeface tf = Typeface.createFromAsset(assetManager, "fonts/hbd_fonts.ttf");
        Typeface tc = Typeface.createFromAsset(assetManager, "fonts/number.ttf");
        Typeface ta = Typeface.createFromAsset(assetManager, "fonts/forWho_font.ttf");

        TextView texthbd = findViewById(R.id.textHBD);
        TextView textAgex = findViewById(R.id.agesView);
        TextView textAkhix = findViewById(R.id.textForWho);

        texthbd.setTypeface(tf);
        textAgex.setTypeface(tc);
        textAkhix.setTypeface(ta);

        mp2 = MediaPlayer.create(this, R.raw.song_birthday);
        mp2.start();
        mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rrlayout.startAnimation(finishAnimation);
            }
        },30000);

    }

    @Override
    public void onBackPressed(){
        mp2.stop();
        finish();
    }
}
