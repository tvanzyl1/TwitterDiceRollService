package com.tvz.app.diceservice;

import org.apache.log4j.*;
import java.util.Random;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterService extends Thread{

final static Logger logger = Logger.getLogger(TwitterService.class);

public TwitterService(){
    super();
    logger.info("Twitter service initialising");
}

@Override
public void run(){
  logger.info("Twitter service executing");
    
    TwitterFactory factory = new TwitterFactory();
    AccessToken accessToken = loadAccessToken();
    Twitter twitter = factory.getInstance();
    twitter.setOAuthConsumer("", "");
    twitter.setOAuthAccessToken(accessToken);
    while(true){
        try {
            try {
                Status status = twitter.updateStatus(getResults());
                //logger.info("Successfully updated the status to [" + status + "].");
            } catch (TwitterException ex) {
                logger.error(ex);
            }
            
            Thread.sleep(120000);
            logger.info("Twitter service executed");
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(TwitterService.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
        }
    }
  

}

  private static AccessToken loadAccessToken(){
    String token = "";
    String tokenSecret = "";
    return new AccessToken(token, tokenSecret);
  }

private String getResults(){
Random rand = new Random();
    String result = String.format("D4 : %1$d \n" +
              "D6 : %2$d \n" +
              "D8 : %3$d \n" +
              "D12 : %4$d \n" +
              "D20 : %5$d \n",
              rand.nextInt(4) + 1,
              rand.nextInt(6) + 1,
              rand.nextInt(8) + 1,
              rand.nextInt(12) + 1,
              rand.nextInt(20) + 1);      
    return result; 
}

}
