package br.com.remotemyhome.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.remotemyhome.R;
import br.com.remotemyhome.fragment.ListCompartimentoFragment;
import br.com.remotemyhome.fragment.NovoCampartimentoFragment;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(savedInstanceState == null)
			getFragmentManager().beginTransaction().replace(R.id.content, new ListCompartimentoFragment()).addToBackStack(null).commit();
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getOrder()) {
			
			case 0:
				getFragmentManager().beginTransaction().replace(R.id.content, new ListCompartimentoFragment()).addToBackStack(null).commit();
			break;
		
			case 1:
				getFragmentManager().beginTransaction().replace(R.id.content, new NovoCampartimentoFragment()).addToBackStack(null).commit();
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK) {
//			if(getFragmentManager().getBackStackEntryCount() == 0) {
//				this.finish();
//			}
//			else {
//				getFragmentManager().popBackStack();
//				removeCurrentFragment();
//			}
//		}
//		return super.onKeyDown(keyCode, event);
//	}
//	
//	public void removeCurrentFragment() {
//		FragmentTransaction transaction = getFragmentManager().beginTransaction();
//		Fragment currentFrag = getFragmentManager().findFragmentById(R.id.)
//	}
	
	
	
	
}
