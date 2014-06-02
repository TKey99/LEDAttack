package tkey99.ledattack.utilities;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import tkey99.ledattack.Gamefield;
import tkey99.ledattack.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
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
	private final String BLUETOOTH_ADDRESS = "5C:F3:70:09:D9:16";

	/**
	 * Bluetooth port
	 */
	private final int BLUETOOTH_PORT = 16;

	/**
	 * Request code for the bluetooth intent
	 */
	private final int BLUETOOTH_INTENT_RESULT = 99;

	/**
	 * Outputstream to send data through
	 */
	private OutputStream out;

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
	 * @return true if enabled false if not
	 */
	public boolean enableBT(Activity activity) {
		adapter = BluetoothAdapter.getDefaultAdapter();
		if (adapter == null) {
			Toast.makeText(activity, R.string.bt_not_available,
					Toast.LENGTH_LONG).show();
			return false;
		} else {
			Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			activity.startActivityForResult(btIntent, BLUETOOTH_INTENT_RESULT);
			return true;
		}
	}

	/**
	 * Connects to a bluetooth device
	 * 
	 * @return true if connected false if not
	 */
	public boolean connect() {
		if (adapter.isEnabled() && socket == null) {
			BluetoothDevice btDevice = adapter
					.getRemoteDevice(BLUETOOTH_ADDRESS);
			try {
				Method m = btDevice.getClass().getMethod("createRfcommSocket",
						new Class[] { int.class });
				socket = (BluetoothSocket) m.invoke(btDevice, BLUETOOTH_PORT);
				Log.d("bluetooth socket", "invoke success");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			adapter.cancelDiscovery();
			Log.d("bluetooth socket", socket.getRemoteDevice().getAddress());
			try {
				socket.connect();
			} catch (IOException e) {
				Log.d("bluetooth socket", e.getMessage());
				e.printStackTrace();
			}

			try {
				out = socket.getOutputStream();
			} catch (IOException e) {
				Log.d("bluetooth stream", e.getMessage());
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/**
	 * Sends the gamefield via bluetooth
	 * 
	 * @param gamefield
	 *            current gamefield
	 * @return true if success false if not
	 */
	public synchronized boolean send(final byte[][] gamefield) {
		if (socket != null) {
			Log.d("bluetooth", "try to send");
			new Thread() {
				@Override
				public void run() {
					byte[] sendableGamefield = new byte[Gamefield.MAX_LED_X
							* Gamefield.MAX_LED_Y];

					for (int x = 0; x < gamefield.length; x++) {
						for (int y = 0; y < gamefield[0].length; y++) {
							sendableGamefield[x * Gamefield.MAX_LED_X + y] = gamefield[x][y];
						}
					}
					try {
						out.write(sendableGamefield);
					} catch (IOException e) {
						Log.d("bluetooth stream", e.getMessage());
						e.printStackTrace();
					}
				}
			}.start();
			return true;
		}
		return false;
	}

	/**
	 * Returns whether the bluetooth socket is set or not
	 * 
	 * @return true if socket set false otherwise
	 */
	public boolean isEnabled() {
		if (socket == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Closes the bluetooth connection
	 * 
	 * @return true if closed false otherwise
	 */
	public boolean closeConnection() {
		try {
			out.close();
			socket.close();
			Log.d("bluetooth", "connection closed");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
