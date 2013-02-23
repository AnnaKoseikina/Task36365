import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class DataGrabber {
	public void makeConnection(String https_url, String filex) {
		URL url;
		try {
			url = new URL(https_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			print_content(con, filex);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void print_content(HttpsURLConnection con, String filex) {

		if (con != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				PrintWriter out = new PrintWriter(new FileWriter(filex, true));
				String input;
				while ((input = br.readLine()) != null) {
					out.println(input);
				}

				out.flush();
				out.close();
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}