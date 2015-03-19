/**
 * 
 */
package photo.common;

/**
 * @author jack
 *
 */
public class Param {
	String articleUrlRegex;
	String articleUrlStartIndex;
	String articleUrlEndIndex;
	int articleUrlStartOffset;
	int articleUrlEndOffset;

	String articleTitleRegex;
	String articleTitleStartIndex;
	String articleTitleEndIndex;
	int articleTitleStartOffset;
	int articleTitleEndOffset;

	String articleContentStart;
	String articleContentEnd;

	String xmlRpcApi;
	String xmlRpcUsername;
	String xmlRpcPassword;

	String BlogSEOTitle;
	String BlogSEOFooter;
	String catPrefix;

	public String getCatPrefix() {
		return catPrefix;
	}

	public void setCatPrefix(String catPrefix) {
		this.catPrefix = catPrefix;
	}

	public String getArticleUrlRegex() {
		return articleUrlRegex;
	}

	public void setArticleUrlRegex(String articleUrlRegex) {
		this.articleUrlRegex = articleUrlRegex;
	}

	public String getArticleUrlStartIndex() {
		return articleUrlStartIndex;
	}

	public void setArticleUrlStartIndex(String articleUrlStartIndex) {
		this.articleUrlStartIndex = articleUrlStartIndex;
	}

	public String getArticleUrlEndIndex() {
		return articleUrlEndIndex;
	}

	public void setArticleUrlEndIndex(String articleUrlEndIndex) {
		this.articleUrlEndIndex = articleUrlEndIndex;
	}

	public int getArticleUrlStartOffset() {
		return articleUrlStartOffset;
	}

	public void setArticleUrlStartOffset(int articleUrlStartOffset) {
		this.articleUrlStartOffset = articleUrlStartOffset;
	}

	public int getArticleUrlEndOffset() {
		return articleUrlEndOffset;
	}

	public void setArticleUrlEndOffset(int articleUrlEndOffset) {
		this.articleUrlEndOffset = articleUrlEndOffset;
	}

	public String getArticleTitleRegex() {
		return articleTitleRegex;
	}

	public void setArticleTitleRegex(String articleTitleRegex) {
		this.articleTitleRegex = articleTitleRegex;
	}

	public String getArticleTitleStartIndex() {
		return articleTitleStartIndex;
	}

	public void setArticleTitleStartIndex(String articleTitleStartIndex) {
		this.articleTitleStartIndex = articleTitleStartIndex;
	}

	public String getArticleTitleEndIndex() {
		return articleTitleEndIndex;
	}

	public void setArticleTitleEndIndex(String articleTitleEndIndex) {
		this.articleTitleEndIndex = articleTitleEndIndex;
	}

	public int getArticleTitleStartOffset() {
		return articleTitleStartOffset;
	}

	public void setArticleTitleStartOffset(int articleTitleStartOffset) {
		this.articleTitleStartOffset = articleTitleStartOffset;
	}

	public int getArticleTitleEndOffset() {
		return articleTitleEndOffset;
	}

	public void setArticleTitleEndOffset(int articleTitleEndOffset) {
		this.articleTitleEndOffset = articleTitleEndOffset;
	}

	public String getArticleContentStart() {
		return articleContentStart;
	}

	public void setArticleContentStart(String articleContentStart) {
		this.articleContentStart = articleContentStart;
	}

	public String getArticleContentEnd() {
		return articleContentEnd;
	}

	public void setArticleContentEnd(String articleContentEnd) {
		this.articleContentEnd = articleContentEnd;
	}

	public String getXmlRpcApi() {
		return xmlRpcApi;
	}

	public void setXmlRpcApi(String xmlRpcApi) {
		this.xmlRpcApi = xmlRpcApi;
	}

	public String getXmlRpcUsername() {
		return xmlRpcUsername;
	}

	public void setXmlRpcUsername(String xmlRpcUsername) {
		this.xmlRpcUsername = xmlRpcUsername;
	}

	public String getXmlRpcPassword() {
		return xmlRpcPassword;
	}

	public void setXmlRpcPassword(String xmlRpcPassword) {
		this.xmlRpcPassword = xmlRpcPassword;
	}

	public String getBlogSEOTitle() {
		return BlogSEOTitle;
	}

	public void setBlogSEOTitle(String blogSEOTitle) {
		BlogSEOTitle = blogSEOTitle;
	}

	public String getBlogSEOFooter() {
		return BlogSEOFooter;
	}

	public void setBlogSEOFooter(String blogSEOFooter) {
		BlogSEOFooter = blogSEOFooter;
	}

	public Param(String articleUrlRegex, String articleUrlStartIndex,
			String articleUrlEndIndex, int articleUrlStartOffset,
			int articleUrlEndOffset, String articleTitleRegex,
			String articleTitleStartIndex, String articleTitleEndIndex,
			int articleTitleStartOffset, int articleTitleEndOffset,
			String articleContentStart, String articleContentEnd,
			String xmlRpcApi, String xmlRpcUsername, String xmlRpcPassword,
			String blogSEOTitle, String blogSEOFooter, String catPrefix) {
		super();
		this.articleUrlRegex = articleUrlRegex;
		this.articleUrlStartIndex = articleUrlStartIndex;
		this.articleUrlEndIndex = articleUrlEndIndex;
		this.articleUrlStartOffset = articleUrlStartOffset;
		this.articleUrlEndOffset = articleUrlEndOffset;
		this.articleTitleRegex = articleTitleRegex;
		this.articleTitleStartIndex = articleTitleStartIndex;
		this.articleTitleEndIndex = articleTitleEndIndex;
		this.articleTitleStartOffset = articleTitleStartOffset;
		this.articleTitleEndOffset = articleTitleEndOffset;
		this.articleContentStart = articleContentStart;
		this.articleContentEnd = articleContentEnd;
		this.xmlRpcApi = xmlRpcApi;
		this.xmlRpcUsername = xmlRpcUsername;
		this.xmlRpcPassword = xmlRpcPassword;
		this.BlogSEOTitle = blogSEOTitle;
		this.BlogSEOFooter = blogSEOFooter;
		this.catPrefix = catPrefix;

	}

}
