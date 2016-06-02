package crawler;

import java.util.Set;
import org.apache.http.HttpException;

/**
 * 爬虫类
 * 
 * @ClassName: Crawler
 * @Description: TODO
 * @author zhaihuayang
 * @date 2016年5月30日 下午5:51:09
 */
public class Crawler implements Runnable {
	private UrlQueue urlQueue = new UrlQueue();
	private VisitedUrlQueue visitedUrlQueue = new VisitedUrlQueue();
	private PageDownloader pageDownloader = new PageDownloader();

	/**
	 * 初始化待访问队列
	 * @Title: initUrlQueue 
	 * @Description: TODO
	 * @param @param seeds   
	 * @return void 
	 * @throws
	 */
	private void initUrlQueue(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			urlQueue.enQueue(seeds[i]);
		}
	}

	/**
	 * 网页抓取
	 */
	public void run() {
		LinkFilter filter = new LinkFilter() {
			@Override
			public boolean accepte(String url) {
				// TODO Auto-generated method stub
				if (url.contains("baidu.com")) {
					return true;
				}
				return false;
			}
		};

		this.initUrlQueue(new String[] { "http://www.baidu.com" });
		// 循环条件：待抓去队列不为空，并且已访问连接数量小于最大量
		while (!urlQueue.isEmpty() && visitedUrlQueue.size() < visitedUrlQueue.MAX_SIZE) {
			String url = urlQueue.deQueue();
			try {
				pageDownloader.downloadPage(url);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			visitedUrlQueue.enQueue(url);
			Set<String> links = HttpParser.getLinks(url, filter);
			for (String link : links) {
				urlQueue.enQueue(link);
			}
		}
	}
}
