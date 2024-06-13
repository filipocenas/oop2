package potapanjeBrodova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 8080);
			System.out.println("Uspostavili smo konekciju sa serverom...");
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

				boolean kraj = false;
				while (!kraj) {
					System.out.println("Unesi koordinate: ");
					String koordinate = stdIn.readLine();
					out.println(koordinate);

					String red1 = in.readLine();
					System.out.println(red1);
					String red2 = in.readLine();
					System.out.println(red2);
					String red3 = in.readLine();
					System.out.println(red3);
					String red4 = in.readLine();
					System.out.println(red4);
					String red5 = in.readLine();
					System.out.println(red5);
					String red6 = in.readLine();
					System.out.println(red6);
					String red7 = in.readLine();
					System.out.println(red7);
					String red8 = in.readLine();
					System.out.println(red8);
					String red9 = in.readLine();
					System.out.println(red9);
					String red10 = in.readLine();
					System.out.println(red10);

					String pogodjeni = in.readLine();
					int pogodak = Integer.parseInt(pogodjeni);
					if (pogodak == 9) {
						System.out.println("Pogodili ste sve brodove!");
						kraj = true;
					}
				}

			} finally {
				s.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
