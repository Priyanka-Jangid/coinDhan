import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.logging.log4j.*;
import java.util.*;


public class testcase2 {

    //private static Logger Log = LogManager.getLogger(testcase2.class.getName());
	
	public static void main(String[] args) throws Exception {
		
		
		System.setProperty("webdriver.chrome.driver", "/Users/prijangi/Documents/selenium_driver/chromedriver");
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);	
		driver.get("https://www.coindhan.com/trading/btcusdt");			
		System.out.println(driver.getTitle());	
	
		
//		Thread.sleep(3000);
	
		ArrayList<String> totalArr =  new ArrayList<String>();

		totalArr.addAll(OrderBookLoseTable(driver));
		
		totalArr.add(OrderBookMarketValue(driver));
		
		totalArr.addAll(OrderBookProfitTable(driver));
		
		int size = totalArr.size();
		System.out.println("size of total arr :"+size);
		
		
		
		System.out.println("final :"+totalArr);
		
		
		ArrayList<Integer> intArr =  new ArrayList<Integer>();
		
		for(String arr : totalArr) {
			char delimiter = ',';
			  
		       
	        System.out.println("Original String: " + arr);
	  
	
	        int index = arr.lastIndexOf(delimiter);
	        System.out.println("index: " + index);
	     
	        arr = arr.substring(0, index) + arr.substring(index + 1);
	        
	        intArr.add(Integer.parseInt(arr));   
		}
		
		System.out.println("Total list :"+intArr);
		  
		
		ArrayList<Integer> descArr =  new ArrayList<Integer>();
		descArr.addAll(intArr);
		
		 Collections.sort(descArr, Collections.reverseOrder());
		 
		 System.out.println("Descending list :"+descArr);
		 
		 ArrayList<Integer> as =  new ArrayList<Integer>();
			as.addAll(intArr);
			
			 Collections.sort(as);
		 
		 
		 Assert.assertEquals(intArr, as) ;
		 
//	 boolean bool = intArr.equals(descArr); 
//	 System.out.println("Descending  :"+bool);
//	 
//		 if(intArr.equals(descArr))
//			 System.out.println("values are in descending order");
//		 else
//			 System.out.println("values are not in descending order"); 
	}
	
	

	
	@SuppressWarnings("null")
	public static ArrayList<String> OrderBookProfitTable(WebDriver driver) throws Exception {
		
 System.out.println("***********  Profit  *****************");
		 
		 WebDriverWait wait=new WebDriverWait(driver,60);
			
		 WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cr-order-book'][2]//child::table//tr//td[1]")));
	        int len = row.findElements(By.xpath("//div[@class='cr-order-book'][2]//child::table//tr//td[1]")).size();
	        System.out.println("size ::::"+len);
//	        String arr[] = new String[len];
	        ArrayList<String> arr =  new ArrayList<String>();
			int count=0;
	        for (int index = 0; index < len; index++) {
	            WebElement tr = row.findElements(By.xpath("//div[@class='cr-order-book'][2]//child::table//tr//td[1]")).get(index);
	 
	                JavascriptExecutor jse = (JavascriptExecutor) driver;
	                jse.executeScript("window.scrollBy(0,250)");
	                len = row.findElements(By.xpath("//div[@class='cr-order-book'][2]//child::table//tr//td[1]")).size();
//	                System.out.println("value ; "+tr.getText());
//	               arr[count] = tr.getText();
	                arr.add(tr.getText());
	              count++;

	        }
	
	    		  arr.removeAll(Collections.singleton(""));
	     
	      
//	      arr.removeAll(Collections.singletonList(null));
	        
	        System.out.println("value ; "+arr);
	        System.out.println("***********  end profit   *****************");
	return arr;

	}
	
	
	
	
	public static String OrderBookMarketValue(WebDriver driver) throws Exception {
		
		 System.out.println("***********  market  *****************");
		WebElement midVal =	driver.findElement(By.xpath("//span[contains(@class,'order-book__market')]"));	
		 String mid = midVal.getText().split(" ")[0];
		 System.out.println("value of mid vlaue array ::::"+mid);
		 System.out.println("***********  end market value   *****************");
		 return mid;
		
	}
	
	
	@SuppressWarnings("null")
	public static ArrayList<String> OrderBookLoseTable(WebDriver driver) throws Exception {
		
		///////////////////////	 
				 
System.out.println("***********  lose  *****************");
		
		WebDriverWait wait=new WebDriverWait(driver,30);
			int counter=1;
			System.out.println("counter ::"+counter);
		WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cr-order-book'][1]//child::table//tr//td[1]")));
		  int len = row.findElements(By.xpath("//div[@class='cr-order-book'][1]//child::table//tr//td[1]")).size();
		
		 
		//  String arr[] = new String[len];
		  ArrayList<String> arr =  new ArrayList<String>();
		 
		  System.out.println("size ::::"+len);
			int count=0;
		  for (int index = 0; index < len; index++) {
		      WebElement tr = row.findElements(By.xpath("//div[@class='cr-order-book'][1]//child::table//tr//td[1]")).get(index);
		
		      if(counter<len) {
		  		 Actions action = new Actions(driver);
					    action.moveToElement(driver.findElements(By.xpath("//div[@class='cr-order-book'][1]//child::table//tr//td[1]")).get(counter++)).perform();
					    Thread.sleep(2000); 			 
		  	  }
		         
		         arr.add(tr.getText());
		        count++;
		
		  }
		  
		//  arr.removeAll(Collections.singleton(""));
		
		   System.out.println("value ; "+arr);
		 
		   
		  System.out.println("***********  end lose   *****************");
		 return arr;
		
		
	}

}
