/**
 * 
 */
package photo.common;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.AsyncCallback;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * @author jack
 *
 */
public class BlogSender {

	private static String updateRelationshipById(String blogid, String termid) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.34.7:3306/ols?useUnicode=true&amp;characterEncoding=utf8";
		String user = "jack";
		String password = "isword";

		try {
			Class.forName(driver);

			java.sql.Connection conn = DriverManager.getConnection(url, user,
					password);
			conn.setAutoCommit(false);// Disables auto-commit.
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			Statement statement = conn.createStatement();

			String sql = "update ols_term_relationships set term_taxonomy_id="
					+ termid + " where object_id=" + blogid;
			System.out.println(sql);
			int rs = statement.executeUpdate(sql);
			System.out.println(rs);
			statement.close();
			conn.close();

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return blogid;

	}

	/**
	 * 
	 * @param article
	 * @param xmlRpc
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws XmlRpcException
	 * @throws InterruptedException
	 */
	public static String sendBlog(Article article, Param p)
			throws ClientProtocolException, IOException, XmlRpcException,
			InterruptedException {
		String title = article.getTitle();
		String content = article.getContent();
		String cat = article.getCategory();
		String xmlrpcApi = p.getXmlRpcApi();
		String username = p.getXmlRpcUsername();
		String password = p.getXmlRpcPassword();
		// Set up XML-RPC connection to server
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(xmlrpcApi));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		// Set up parameters required by newPost method

		Map<String, String> catMap = new HashMap<String, String>();
		catMap.put("name", cat);
		catMap.put("slug", cat);
		catMap.put("parent_id", "0");
		catMap.put("description", cat);
		Object[] catParams = new Object[] { "default", username, password,
				catMap };
		Integer catId = null;
		try {
			catId = (Integer) client.execute("wp.newCategory", catParams);
			System.out.println(catId);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}

		Map<String, String> post = new HashMap<String, String>();
		post.put("title", title);
		post.put("description", content);

		Object[] params = new Object[] { "default", username, password, post,
				Boolean.TRUE };

		// Call newPost
		String blogId = null;
		try {
			blogId = (String) client.execute("metaWeblog.newPost", params);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}

		updateRelationshipById(blogId, String.valueOf(catId));
		// client.executeAsync("metaWeblog.newPost", params, new
		// EchoCallback());

		// Thread.sleep(new Random().nextInt(3) * 1000);
		System.out.println(" Created with blogid " + blogId);
		return blogId;
	}

	/**
	 * 
	 * @param url
	 * @param startPage
	 * @param endPage
	 * @param param
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws XmlRpcException
	 * @throws InterruptedException
	 */
	public static void sendUseDriverGet(String url, int startPage, int endPage,
			Param param) throws ClientProtocolException, IOException,
			XmlRpcException, InterruptedException {
		int articleCount = 0;
		for (int i = startPage; i <= endPage; i++) {
			String pageUrl = url + i;
			System.out.println("page:" + (i) + " " + pageUrl);

			String html = Tools.driverGet(pageUrl);
			System.out.println(html);

			// get article urls and titles

			ArrayList<String[]> articleUrlsTitles = BlogsDownloader
					.getArticleUrlTitleByHtml(html, param);
			System.out.println(articleUrlsTitles.size());
			if (articleUrlsTitles.isEmpty()) {
				continue;
			}

			for (String[] urltitle : articleUrlsTitles) {
				System.out.println(urltitle[0] + " " + urltitle[1]);
			}
			for (String[] articleUrlTitle : articleUrlsTitles) {

				String articleUrl = articleUrlTitle[0];
				String articleTitle = articleUrlTitle[1];

				String content = BlogsDownloader.getArticleContentUseDriverGet(
						articleUrl, param.getArticleContentStart(),
						param.getArticleContentEnd());

				String imgSrcContent = BlogsDownloader
						.getImgSrcContent(content);

				System.out.println("page: " + i + " article:"
						+ (articleCount++) + " " + articleUrl + "\n"
						+ articleTitle + "\n");

				if (!"Out Of Index".equals(content) && !"NULL".equals(content)) {
					Article article = new Article();
					article.setUrl(articleUrl);
					article.setTitle(articleTitle + param.getBlogSEOTitle());

					// article.setContent(content + param.getBlogSEOFooter());
					article.setContent(imgSrcContent + param.getBlogSEOFooter());

					String catPrefix = param.getCatPrefix();
					article.setCategory(catPrefix + Const.TIMESTAMP);
					sendBlog(article, param);
				}
			}

		}
	}

	/**
	 * 
	 * @param url
	 * @param startPage
	 * @param endPage
	 * @param param
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws XmlRpcException
	 * @throws InterruptedException
	 */

	public static void sendUseHttpGet(String url, int startPage, int endPage,
			Param param) throws ClientProtocolException, IOException,
			XmlRpcException, InterruptedException {

		int articleCount = 0;
		for (int i = startPage; i <= endPage; i++) {
			String pageUrl = url + i;
			System.out.println("page:" + (i) + " " + pageUrl);

			String html = Tools.httpGet(pageUrl);

			ArrayList<String[]> articleUrlsTitles = BlogsDownloader
					.getArticleUrlTitleByHtml(html, param);
			System.out.println(articleUrlsTitles.size());

			for (String[] urltitle : articleUrlsTitles) {
				System.out.println(urltitle[0] + " " + urltitle[1]);
			}
			for (String[] articleUrlTitle : articleUrlsTitles) {

				String articleUrl = articleUrlTitle[0];
				String articleTitle = articleUrlTitle[1];

				String content = BlogsDownloader.getArticleContentUseHttpGet(
						articleUrl, param.getArticleContentStart(),
						param.getArticleContentEnd());

				System.out.println("page: " + i + " article:"
						+ (articleCount++) + " " + articleUrl + "\n"
						+ articleTitle + "\n" + content);

				if (!"Out Of Index".equals(content) && !"NULL".equals(content)) {
					Article article = new Article();
					article.setUrl(articleUrl);
					article.setTitle(articleTitle + param.getBlogSEOTitle());
					article.setContent(content + param.getBlogSEOFooter());

					sendBlog(article, param);
				}
			}

		}
	}

}

class EchoCallback implements AsyncCallback {

	public void handleResult(XmlRpcRequest pRequest, Object pResult) {
		System.out.println("Server returns: " + (String) pResult);

	}

	public void handleError(XmlRpcRequest pRequest, Throwable pError) {
		System.out.println("Error occurs: " + pError.getMessage());

	}
}
