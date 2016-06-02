package crawler;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * Html标记处理
 * @ClassName: HttpParser
 * @Description: TODO
 * @author zhaihuayang
 * @date 2016年5月30日 下午4:03:01
 */
public class HttpParser {
	/**
	 * 过滤网页连接
	 * @Title: getLinks 
	 * @Description: TODO
	 * @param @param url
	 * @param @param filter
	 * @param @return   
	 * @return Set<String> 
	 * @throws
	 */
	public static Set<String> getLinks(String url, LinkFilter filter) {
		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("utf-8");
			// 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性
			NodeFilter frameFilter = new NodeFilter() {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean accept(Node node) {
					// TODO Auto-generated method stub
					if (node.getText().startsWith("frame src=")) {
						return true;
					}
					return false;
				}
			};
			// OrFilter 来设置过滤 <a> 标签和 <frame> 标签
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class), frameFilter);
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag) {//<a>标签
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();//url
					if (filter.accepte(url)) {
						links.add(linkUrl);
					} else {//frame标签
						// 提取 frame 里 src 属性的链接， 如 <frame src="test.html"/>
						String frame = tag.getText();
						int start = frame.indexOf("src=");
						if (start != -1) {
							frame = frame.substring(start);
						}
						int end = frame.indexOf(" ");
						String frameUrl = "";
						if (end != -1) {
							end = frame.indexOf(">");
							if (end - 1 > 5) {
								frameUrl = frame.substring(5, end - 1);
							}
						}
						if (filter.accepte(frameUrl)) {
							links.add(frameUrl);
						}
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
}
