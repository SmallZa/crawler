package crawler;

public class CrawlerTest {
	private static Crawler[] crawler = new Crawler[10];

	public static void main(String args[]) {
		for (int i = 0; i < 10; i++) {
			crawler[i] = new Crawler();
			crawler[i].run();
		}
		System.out.println("process finished");
	}
}
