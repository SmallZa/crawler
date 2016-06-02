package crawler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 下载网页内容到本地
 * @ClassName: PageDownloader
 * @Description: TODO
 * @author zhaihuayang
 * @date 2016年5月30日 下午4:02:43
 */
public class PageDownloader {

	/**
	 * 根据url生成本地文件名称 
	 * @Title: getFileNameByUrl 
	 * @Description: TODO
	 * @param @param url
	 * @param @return   
	 * @return String 
	 * @throws
	 */
	private String getFileNameByUrl(String url) {
		String fileName = url;
		// 去掉请求协议头 http:// 或者 https://
		if (url.startsWith("http://")) {
			fileName = url.substring(7);
		} else {
			fileName = url.substring(8);
		}
		// 把特殊字符转换成下划线
		fileName = fileName.replaceAll("[/]", "_");
		return fileName;
	}

	/**
	 * 将网页内容保存到本地 
	 * @Title: saveToLocal 
	 * @Description: TODO
	 * @param @param data
	 * @param @param filePath   
	 * @return void 
	 * @throws
	 */
	private void saveToLocal(byte[] data, String filePath) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			for (int i = 0; i < data.length; i++) {
				out.write(data[i]);
				out.flush();				
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载网页内容
	 * @Title: downloadPage 
	 * @Description: TODO
	 * @param @param url
	 * @param @return
	 * @param @throws HttpException   
	 * @return String 
	 * @throws
	 */
	public String downloadPage(String url) throws HttpException {
		String filePath = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() != 200) {
				System.err.println("Method failed: " + response.getStatusLine());
			}
			byte[] responseContent = EntityUtils.toByteArray(response.getEntity());
			String fileName = this.getFileNameByUrl(url);
			filePath = "/Users/baidu/Documents/crawler/" + fileName;
			this.saveToLocal(responseContent, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filePath;
	}
}
