package crawler;

import java.util.HashSet;

/**
 * 已访问url队列
 * @ClassName: VisitedUrlQueue
 * @Description: TODO
 * @author zhaihuayang
 * @date 2016年5月30日 上午11:47:43
 */
public class VisitedUrlQueue {
	
	/**
	 * 已访问url队列
	 */
	private HashSet<String> visitedUrlQueue = new HashSet<String>();
	public static final int MAX_SIZE = 10000;
	/**
	 * 已访问url入队
	 * @Title: enQueue 
	 * @Description: TODO
	 * @param @param url   
	 * @return void 
	 * @throws
	 */
	public synchronized void enQueue(String url){
		this.visitedUrlQueue.add(url);
	}
	
	/**
	 * 判断队列中是否存在某条url
	 * @Title: contains 
	 * @Description: TODO
	 * @param @param url
	 * @param @return   
	 * @return boolean 
	 * @throws
	 */
	public boolean contains(String url){
		return this.visitedUrlQueue.contains(url);
	}
	
	/**
	 * 返回队列大小
	 * @Title: size 
	 * @Description: TODO
	 * @param @return   
	 * @return int 
	 * @throws
	 */
	public long size(){
		return this.visitedUrlQueue.size();
	}
	
}
