package moofwd.towns.square;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity{
	
	protected boolean active = true;
    protected int splashTime = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        final Intent i = new Intent(this, MoofwdTownActivity.class);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(active && (waited < splashTime)) {
                        sleep(100);
                        if(active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                } finally {
                    finish();
                    startActivity(i);
               }
           }
       };
       splashTread.start();    
   }

}
