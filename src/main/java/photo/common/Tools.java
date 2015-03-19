/**
 * 
 */
package photo.common;

import java.util.List;
import java.util.Random;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author jack
 *
 */
public class Tools {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		String url = "http://blog.51cto.com/artcommend";
		driverGet(url);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */

	public static String driverGet(String url) {

		String html = null;

		System.setProperty(
				"webdriver.firefox.bin",
				"D:\\XMLRPC\\Firefox_Portable_33.1.1\\FirefoxPortable\\App\\Firefox\\firefox.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxProfile firefoxprofile = new FirefoxProfile(
				new File(
						"D:\\XMLRPC\\Firefox_Portable_33.1.1\\FirefoxPortable\\Data\\profile\\"));
		capabilities.setCapability(FirefoxDriver.PROFILE, firefoxprofile);

		WebDriver driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// set browser window size
		Dimension targetSize = new Dimension(0, 0);
		// driver.manage().window().setSize(targetSize);
		// set browser position
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		Point targetPosition = new Point(screenSize.width, screenSize.height);
		// driver.manage().window().setPosition(targetPosition);
		// WebDriver driver = new InternetExplorerDriver();

		try {

			driver.get(url);
			Thread.sleep(1000);
			html = driver.getPageSource();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				driver.close();
				driver.quit();
			} catch (Exception e) {
			}
		}
		return html;
	}

	/**
	 * httpget
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String httpGet2(String url) throws ClientProtocolException,
			IOException {
		String result = "";
		HttpGet request = new HttpGet(url);
		HttpResponse response = HttpClients.createDefault().execute(request);
		if (response.getStatusLine().getStatusCode() == 200) {
			result = EntityUtils.toString(response.getEntity());
			// System.out.println(result);
		}
		return result;

	}

	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ���������������� url
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	public static String httpGet(String url) {
		String result = "";
		BufferedReader in = null;
		HttpURLConnection connection = null;
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			connection = (HttpURLConnection) realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			int timeout = 30000;
			connection.setConnectTimeout(timeout);
			connection.connect();

			// ��ȡ������Ӧͷ�ֶ�
			// Map<String, List<String>> map = connection.getHeaderFields();
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// ���� BufferedReader����������ȡURL����Ӧ
			// in = new BufferedReader(new InputStreamReader(
			// connection.getInputStream(), "gb2312"));

			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));

			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.disconnect();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param regex
	 * @param matchContent
	 * @return
	 */
	public static Matcher regexgMatch(String regex, String matchContent) {

		Pattern pArticle = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		// ��Pattern���matcher()��������һ��Matcher����
		Matcher matcher = pArticle.matcher(matchContent);
		return matcher;
	}

	public static void wait4About1Min() {
		int randInt = (int) (new Random().nextInt(20));
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis() + (50 + randInt) * 1000;
		while (System.currentTimeMillis() < end) {
			;
		}
	}

}
