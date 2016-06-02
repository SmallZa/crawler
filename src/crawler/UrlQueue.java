package crawler;

import java.util.LinkedList;

/**
 * 未访问url队列
 * @ClassName: UrlQueue
 * @Description: TODO
 * @author zhaihuayang
 * @date 2016年5月30日 上午11:44:25
 */
public class UrlQueue {
	
	/**
	 * 未访问url队列
	 */
	private LinkedList<String> urlQueue = new LinkedList<String>();
	
	/**
	 * url入队
	 * @Title: enQueue 
	 * @Description: TODO
	 * @param @param url   
	 * @return void 
	 * @throws
	 */
	public synchronized void enQueue(String url) {
		this.urlQueue.add(url);
	}
	
	/**
	 * 首个元素出队
	 * @Title: deQueue 
	 * @Description: TODO
	 * @param    
	 * @return void 
	 * @throws
	 */
	public synchronized String deQueue() {
		return this.urlQueue.removeFirst();
	}
	
	/**
	 * 判断队列是否为空
	 * @Title: isEmpty 
	 * @Description: TODO
	 * @param @return   
	 * @return boolean 
	 * @throws
	 */
	public boolean isEmpty() {
		return this.urlQueue.isEmpty();
	}
	
	/**
	 * 判断队列中是否包含一条url
	 * @Title: isContains 
	 * @Description: TODO
	 * @param @param url
	 * @param @return   
	 * @return boolean 
	 * @throws
	 */
	public boolean isContains(String url) {
		return this.urlQueue.contains(url);
	}
	
	/**
	 * 返回队列大小
	 * @Title: size 
	 * @Description: TODO
	 * @param @return   
	 * @return long 
	 * @throws
	 */
	public long size() {
		return this.urlQueue.size();
	}
}
