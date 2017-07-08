package com.tvz.app.diceservice;

import org.apache.log4j.*;
/**
 * Hello world!
 *
 */
public class App 
{

    final static Logger logger = Logger.getLogger(App.class);
   
    public static void main( String[] args )
    {
        setupAppender();
        logger.info("Application starting");
       new TwitterService().start(); 
    }
    
    private static void setupAppender(){
    ConsoleAppender console = new ConsoleAppender(); //create appender
    //configure the appender
    String PATTERN = "%d [%p|%c|%C{1}] %m%n";
    console.setLayout(new PatternLayout(PATTERN)); 
    console.setThreshold(Level.FATAL);
    console.activateOptions();
    //add appender to any Logger (here is root)
    Logger.getRootLogger().addAppender(console);

    FileAppender fa = new FileAppender();
    fa.setName("FileLogger");
    fa.setFile("DiceService.log");
    fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
    fa.setThreshold(Level.DEBUG);
    fa.setAppend(true);
    fa.activateOptions();

    //add appender to any Logger (here is root)
    Logger.getRootLogger().addAppender(fa);
    //repeat with all other desired appenders
    }
}
