package tkey99.ledattack;

import android.bluetooth.BluetoothAdapter;

public class BluetoothManager {

	private static BluetoothManager instance;

	private BluetoothAdapter adapter;

	private BluetoothManager() {
		adapter = BluetoothAdapter.getDefaultAdapter();
	}

	public static BluetoothManager getInstance() {
		if (instance == null) {
			instance = new BluetoothManager();
		}
		return instance;
	}

	/*private void showAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder();
		alertDialog.setTitle(R.string.delete_dialog_header);
		alertDialog.setMessage(R.string.delete_dialog);
		alertDialog.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
		alertDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertDialog.show();
	}*/

}
