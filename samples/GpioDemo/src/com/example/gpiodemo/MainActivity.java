package com.example.gpiodemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.android.rkpx2.Gpio;

public class MainActivity extends Activity {
	private static final String TAG = "GPIO";
	private Gpio RKPX2_PIN0_PD4 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onButtonClick(View view){
		Log.d(TAG, "onButtonClick");
		RKPX2_PIN0_PD4 = new com.android.rkpx2.Gpio(Gpio.RKPX2_PIN0_PD4);
		boolean isGpioValid = RKPX2_PIN0_PD4.gpio_request();
		if (isGpioValid){
			Toast.makeText(this, "request gpio success: RKPX2_PIN0_PD4", 1000).show();	
		} else {
			Toast.makeText(this, "request gpio failed: RKPX2_PIN0_PD4", 1000).show();
		}
		
		
	}
	
	public void btnSetGpioDirection(View view){
		boolean isGpioValid = RKPX2_PIN0_PD4.gpio_request();
		if (isGpioValid){
			Log.d(TAG, "gpio valid");
			RKPX2_PIN0_PD4.setDirectionValue(Gpio.TYPE_DIRECTION_OUT, Gpio.TYPE_VALUE_HIGH);
			int type = RKPX2_PIN0_PD4.getDirectionValue();
			
			if (type == Gpio.TYPE_DIRECTION_OUT){
				Toast.makeText(this, "TYPE_DIRECTION_OUT set success!", 1000).show();
			} else {
				Toast.makeText(this, "TYPE_DIRECTION_OUT set failed!", 1000).show();
			}
		}		
	}
	
	public void btnSetGpioValue(View view){
		boolean isGpioValid = RKPX2_PIN0_PD4.gpio_request();
		if (isGpioValid){
			RKPX2_PIN0_PD4.setPortValue(Gpio.TYPE_VALUE_LOW);
			int value = RKPX2_PIN0_PD4.getPortValue();
			
			if (value == Gpio.TYPE_VALUE_LOW){
				Toast.makeText(this, "TYPE_VALUE_HIGH set success!", 1000).show();
			} else {
				Toast.makeText(this, "TYPE_VALUE_HIGH set failed!", 1000).show();
			}
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
