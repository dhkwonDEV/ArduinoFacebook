import jssc.SerialPort;
import jssc.SerialPortException;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Page;

public class arduino_facebook {
	public static final String MY_TOKEN = "CAACEdEose0cBANIFciOjFYviiscEaaZCgL4gkM6XT5jkTmYHFVNnwZC5F8iZAkNhApN3eOd2xvNRCdCmGFsfiZBBdmzoZAV5kFRaL1snZBKHbqkd2BdYOC7WV4a6dLM42rcUertn7d4AndKENOZBU1xgG6FzP6edMAZD";
	public static long numLikes = Long.MIN_VALUE;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		SerialPort serialPort = new SerialPort("COM3");
		try {
			serialPort.openPort();
			serialPort.setParams(9600, 8, 1, 0);
			char bit;
			while (true) {
				FacebookClient facebookClient = new DefaultFacebookClient(
						MY_TOKEN);
				Page page = facebookClient.fetchObject("cocacola", Page.class);
				long newLikes = page.getLikes();

				if (newLikes > numLikes) {
					numLikes = newLikes;
					bit = '1';
					serialPort.writeByte((byte) bit);
					System.out.println("the number of LIKES of cocacola page(https://www.facebook.com/cocacola) " + numLikes + "->" + newLikes + " : Increase");
				} else {
					bit = '0';
					serialPort.writeByte((byte) bit);
					System.out.println("the number of LIKES of cocacola page(https://www.facebook.com/cocacola) " + numLikes + "->" + newLikes + " : ");
				}
				
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
