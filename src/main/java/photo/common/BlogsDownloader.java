/**
 * 
 */
package photo.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import org.apache.http.MessageConstraintException;
import org.apache.http.client.ClientProtocolException;

import photo.netease.Messages;

/**
 * @author jack
 *
 */
public class BlogsDownloader {

	private static Object imgSeo = Messages.getString("img_seo");

	/**
	 * 
	 * @param html
	 * @param param
	 * @return ArrayList<String[]> getArticleUrlTitle
	 */
	public static ArrayList<String[]> getArticleUrlTitleByHtml(String html,
			Param param) {

		String articleUrlRegex = param.getArticleUrlRegex();
		System.out.println(articleUrlRegex);
		String articleUrlStartIndex = param.getArticleUrlStartIndex();
		String articleUrlEndIndex = param.getArticleUrlEndIndex();
		int articleUrlStartOffset = param.getArticleUrlStartOffset();
		int articleUrlEndOffset = param.getArticleUrlEndOffset();

		String articleTitleRegex = param.getArticleTitleRegex();
		String articleTitleStartIndex = param.getArticleTitleStartIndex();
		String articleTitleEndIndex = param.getArticleTitleEndIndex();
		int articleTitleStartOffset = param.getArticleTitleStartOffset();
		int articleTitleEndOffset = param.getArticleTitleEndOffset();

		ArrayList<String[]> articleUrlsTitles = new ArrayList<String[]>();
		// System.out.println(html);
		Matcher matcher = Tools.regexgMatch(articleUrlRegex, html);
		if (null == matcher) {
			return null;
		}
		while (matcher.find()) {
			String articleUrlTitle = matcher.group();

			System.out.println(articleUrlRegex);
			System.out.println(articleUrlTitle);

			int s = articleUrlTitle.indexOf(articleUrlStartIndex);
			int t = articleUrlTitle.indexOf(articleUrlEndIndex);

			System.out.println("articleUrlStartIndex: " + s);
			System.out.println("articleUrlEndIndex: " + t);

			int start = s + articleUrlStartOffset;
			int end = t - articleUrlEndOffset;

			if (start > end || start == -1 || end == -1) {
				continue;
			}

			String articleUrl = articleUrlTitle.substring(start, end);

			System.out.println("articleUrl============" + articleUrl);

			int titleStart = articleUrlTitle.indexOf(articleTitleStartIndex)
					+ articleTitleStartOffset;
			int titleEnd = articleUrlTitle.indexOf(articleTitleEndIndex)
					- articleTitleEndOffset;

			System.out.println("titleStart: " + titleStart);
			System.out.println("titleEnd: " + titleEnd);

			if (titleStart > titleEnd || titleEnd == -1 || titleStart == -1) {
				continue;
			}

			String articleTitle = articleUrlTitle.substring(titleStart,
					titleEnd);

			// System.out.println(articleTitle);
			// [0] url [1] title
			// System.out.println(articleUrl + " " + articleTitle);
			articleUrlsTitles.add(new String[] { articleUrl, articleTitle });
		}

		return articleUrlsTitles;
	}

	/**
	 * 
	 * @param url
	 * @param start
	 * @param end
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getArticleContentUseHttpGet(String url, String start,
			String end) throws ClientProtocolException, IOException {
		String result = Tools.httpGet(url);

		if (result != null) {
			int startIndex = result.indexOf(start);
			int endIndex = result.indexOf(end);

			System.out.println(startIndex);
			System.out.println(endIndex);

			if (startIndex != -1 && endIndex != -1) {
				result = result.substring(startIndex, endIndex);
			} else {
				result = "Out Of Index";
			}
		} else {
			result = "NULL";
		}
		return result;

	}

	/**
	 * 
	 * @param url
	 * @param start
	 * @param end
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getArticleContentUseDriverGet(String url,
			String start, String end) throws ClientProtocolException,
			IOException {
		String result = Tools.driverGet(url);
		System.out.println(result);

		if (result != null) {
			int startIndex = result.indexOf(start);
			int endIndex = result.indexOf(end);

			System.out.println(startIndex);
			System.out.println(endIndex);

			if (startIndex != -1 && endIndex != -1) {
				result = result.substring(startIndex, endIndex);
			} else {
				result = "Out Of Index";
			}
		} else {
			result = "NULL";
		}
		return result;

	}

	/**
	 * 
	 * @param content
	 * @return
	 */
	public static String getImgSrcContent(String content) {
		StringBuffer imgList = new StringBuffer();

		String imgSrcRegex = Messages.getString("img_src_regex");

		Matcher matcher = Tools.regexgMatch(imgSrcRegex, content);
		if (null == matcher) {
			return null;
		}
		while (matcher.find()) {
			String imgSrc = matcher.group();
			System.out.println("imgSrcRegex==========" + imgSrcRegex);
			imgList.append(imgSrc);
			imgList.append(imgSeo);

		}

		return imgList.toString();
	}
}
