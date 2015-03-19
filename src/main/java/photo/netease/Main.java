/**
 * 
 */
package photo.netease;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.xmlrpc.XmlRpcException;

import photo.common.Const;
import photo.common.Param;

/**
 * @author jack
 *
 */
public class Main {
	public final static String BLOG_CAT_PREFIX = Messages
			.getString("cat.prefix");

	static String ReMenUrl = Messages.getString("ReMen");// 500
	static String TuiJianUrl = Messages.getString("TuiJian");// 407
	static String ZuiXinUrl = Messages.getString("ZuiXin");// 500

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws InterruptedException
	 * @throws XmlRpcException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			IOException, XmlRpcException, InterruptedException {
		Param param = new Param(articleUrlRegex, articleUrlStartIndex,
				articleUrlEndIndex, articleUrlStartIndexOffset,
				articleUrlEndIndexOffset, articleTitleRegex,
				articleTitleStartIndex, articleTitleEndIndex,
				articleTitletartIndexOffset, articleTitleEndIndexOffset,
				articleContentStart, articleContentEnd, xmlrpcApi, username,
				password, Const.BLOG_TITLE_SEO, Const.BLOG_FOOTER_SEO,
				BLOG_CAT_PREFIX);

		photo.common.BlogSender.sendUseDriverGet(ReMenUrl, 1, 500, param);
		photo.common.BlogSender.sendUseDriverGet(TuiJianUrl, 1, 407, param);
		photo.common.BlogSender.sendUseDriverGet(ZuiXinUrl, 1, 500, param);

	}

	/**
	 * wordpress http://localhost/ols/xmlrpc.php
	 */
	public final static String xmlrpcApi = Messages.getString("Main.xmlrpcApi"); //$NON-NLS-1$
	public final static String username = Messages
			.getString("Main.xmlrpcUserName"); //$NON-NLS-1$
	public final static String password = Messages
			.getString("Main.xmlRpcPassword"); //$NON-NLS-1$

	public static final String BLOG_SEO_FOOTER_KEYWORDS = Messages
			.getString("Main.BlogSEOFooter"); //$NON-NLS-1$
	public static final String BLOG_SEO_TITLE_KEYWORDS = Messages
			.getString("Main.BlogSEOTitle"); //$NON-NLS-1$

	/**
	 * ÿҳ�����б��url
	 */
	//	public final static String url = Messages.getString("Main.blogsPageUrl"); //$NON-NLS-1$

	/**
	 * article Url
	 */
	public static final String articleUrlRegex = Messages
			.getString("Main.articleUrlRegex"); //$NON-NLS-1$
	public static final String articleUrlStartIndex = Messages
			.getString("Main.articleUrlStartIndex"); //$NON-NLS-1$
	public static final String articleUrlEndIndex = Messages
			.getString("Main.articleUrlEndIndex"); //$NON-NLS-1$
	public static final int articleUrlStartIndexOffset = 0;
	public static final int articleUrlEndIndexOffset = 0;
	/**
	 * article Title
	 */
	public static final String articleTitleRegex = Messages
			.getString("Main.articleTitleRegex"); //$NON-NLS-1$
	public static final String articleTitleStartIndex = Messages
			.getString("Main.articleTitleStartIndex"); //$NON-NLS-1$
	public static final String articleTitleEndIndex = Messages
			.getString("Main.articleTitleEndIndex"); //$NON-NLS-1$
	public static final int articleTitletartIndexOffset = Messages.getString(
			"Main.articleTitleStartIndexOffset").length(); //$NON-NLS-1$
	public static final int articleTitleEndIndexOffset = 0;

	/**
	 * article Content
	 */
	public static final String articleContentStart = Messages
			.getString("Main.articleContentStart"); //$NON-NLS-1$
	public static final String articleContentEnd = Messages
			.getString("Main.articleContentEnd"); //$NON-NLS-1$

}
