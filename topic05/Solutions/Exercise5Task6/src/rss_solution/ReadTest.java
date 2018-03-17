package rss_solution;

public final class ReadTest {
  private ReadTest(){}

  public static void main(String[] args) {
    RSSFeedParser parser = new RSSFeedParser("https://www.tagesanzeiger.ch/rss.html");
    Feed feed = parser.readFeed();
    System.out.println(feed);
    for (FeedMessage message : feed.getMessages()) 
    	System.out.println(message);
  }
} 