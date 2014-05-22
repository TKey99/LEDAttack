package tkey99.ledattack;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.widget.Toast;

/**
 * Handles the connection via bluetooth.
 * 
 * @author TKey99
 * 
 */
public class BluetoothManager {

	/**
	 * Instance of this class
	 */
	private static BluetoothManager instance;

	/**
	 * Bluetoothadapter
	 */
	private BluetoothAdapter adapter;

	/**
	 * Bluetoothsocket
	 */
	private BluetoothSocket socket;

	/**
	 * Bluetooth address to connect to
	 */
	private final String BLUETOOTH_ADDRESS = "C4:85:08:3A:D3:9B";

	/**
	 * Bluetooth port
	 */
	private final int BLUETOOTH_PORT = 16;

	/**
	 * Private constructor
	 */
	private BluetoothManager() {

	}

	/**
	 * Singleton method
	 * 
	 * @return instance of this class
	 */
	public static BluetoothManager getInstance() {
		if (instance == null) {
			instance = new BluetoothManager();
		}
		return instance;
	}

	/**
	 * Enables the bluetooth connection
	 * 
	 * @param activity
	 *            calling activity
	 * @return true if connected false if not
	 */
	public boolean enableBT(Activity activity) {
		adapter = BluetoothAdapter.getDefaultAdapter();
		if (adapter == null) {
			Toast.makeText(activity, R.string.bt_not_available,
					Toast.LENGTH_LONG).show();
			return false;
		} else {
			if (!adapter.isEnabled()) {
				Intent btIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				activity.startActivity(btIntent);
				// TODO starts question twice
				// TODO what happens if bluetooth is not enabled afterwards?!
			}

			BluetoothDevice btDevice = adapter
					.getRemoteDevice(BLUETOOTH_ADDRESS);
			try {
				Method m = btDevice.getClass().getMethod("createRfcommSocket",
						new Class[] { int.class });
				socket = (BluetoothSocket) m.invoke(btDevice, BLUETOOTH_PORT);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			adapter.cancelDiscovery();
			return true;
		}
	}

	/**
	 * Sends the gamefield via bluetooth
	 * 
	 * @param gamefield
	 *            current gamefield
	 * @return true if success false if not
	 */
	public boolean send(final byte[] gamefield) {
		if (socket != null) {
			new Thread() {
				public void run() {
					try {
						socket.connect();
						OutputStream out = socket.getOutputStream();
						out.write(gamefield);
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			return true;
		}
		return false;
	}
}
