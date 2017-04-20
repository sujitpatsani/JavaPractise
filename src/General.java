
package utill;

public class General extends Base {

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import frame.Status;
import frame.Util;
import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;
import com.thoughtworks.selenium.webdriven.commands.SeleniumSelect;

import PageObjects.POGeneric;






/**
 * Verification Components class
 * @author Cognizant
 */
public class FuncGeneric extends ReusableLibrary
{
	WebDriver driver =new FirefoxDriver();
	public FuncGeneric(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}
	//FuncLesson  Lesson = new FuncLesson(scriptHelper);
	//FuncContent Content = new FuncContent(scriptHelper);
	GlobalData global_data=new GlobalData(scriptHelper);
	
	public static Set<?> setOfOldHandles = null;
    public static Set<?> setOfNewHandles = null; 
			
	//*-********************************************************************************
    //Window Handlers
	//*-********************************************************************************  
	public void saveOldHandles()
	{
		
		if (setOfOldHandles != null) {
			setOfOldHandles.clear();
		}
		setOfOldHandles = driver.getWindowHandles(); // here we save id's of windows
	}

	public void saveNewHandles()
	{
		if (setOfNewHandles != null)
		{
			setOfNewHandles.clear();
		}
		setOfNewHandles = driver.getWindowHandles();
	}

	public boolean ifNewWindowOccursFocusOnIt()
	{
		boolean flag= false;
		if (setOfNewHandles != null)
		{
			setOfNewHandles.removeAll(setOfOldHandles); // this method removeAll() take one set and puts it in another set and if there are same positions it will erase them and leaves only that are not equals
		}

		if (!setOfNewHandles.isEmpty()) {
			String newWindowHandle = (String) setOfNewHandles.iterator().next();// here IF we have new window it will shift on it
			driver.switchTo().window(newWindowHandle);
			flag= true;
		}   
		
		return flag;
	}
	
	public boolean isAlertPresent(int number) 
    { 
    	for (int i=0 ; i< number ; i++) 
		{
				try 
				{
					driver.switchTo().alert();
					return true;
				} 
				catch (Exception e) 
				{
				  try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		}
		return false;
    } 
	
	/*
	 * **********************************************************************************************************
	 * *******************Pop-up as Alert check and click OK******************************************************
	 */
	public boolean isAlertPresent() 
    { 
        try 
        { 
            driver.switchTo().alert(); 
            return true; 
        }   
        catch (NoAlertPresentException Ex) 
        { 
            return false; 
        }   
    }  
	
	public void iesecurityHandler()
	{
			String IEcheck= driver.toString();
			if (IEcheck.contains("InternetExplorerDriver") && driver.getTitle().contains("Certificate"))
			{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
	}
	
	
	
	/*
	 * Random number generator
	 */
	public int randomnumbergenerator(int START,int END)
	{		
	    Random random = new Random();	   
    	long range = (long)END - (long)START + 1;
    	long fraction = (long)(range * random.nextDouble());
    	int randomNumber =  (int)(fraction + START);  	
    	return randomNumber;
    }
	
	/*
	 * Title and desc verification
	 */
	public void verifyPageHeader(WebElement header, String headervalue, String headerdesc) 
	{
		
	
		if(header.getText().contains(headervalue))
		{
			report.updateTestLog("Verify page title", "Correctly displayed as :"+headervalue, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify page title", "Incorrect title displayed", Status.FAIL);
		}
		
		if (headerdesc !="")
		{
			if(header.getText().contains(headerdesc))
			{
				report.updateTestLog("Verify page description", "Correctly displayed as :"+headerdesc, Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify page description", "Incorrect description displayed", Status.FAIL);
			}
		}		
	}
	
	public void waitForPageLoaded()
	{ 
		try {
			if(!Global.browserName.equalsIgnoreCase("Firefox"))  // Set the mouse position on title bar
			{
				getResolutionMoveCursor();
			}
		}
		catch (Exception e)
		{
			// Nothing to be done
			// TODO Auto-generated catch block
		}
		
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()
        		{ 
        			public Boolean apply( WebDriver driver)
        			{ 
        				try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
        				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"); 
        			}
					
        		}; 

        Wait<WebDriver> wait = new WebDriverWait(driver,1000); 
        try { 
                wait.until(expectation); 
        } catch(Throwable error) { 
        	report.updateTestLog("Login As " +global_data.Uname , "Timeout waiting for Page Load Request to complete.", Status.PASS);
        	terminate();
        } 
        
        //report.updateTestLog("Check Page Load Event", "Page Loaded Successfully", Status.PASS);
        
	} 
	
	public boolean isElementPresent(WebElement Element) 
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		for (int i=0 ; i< 100 ; i++) 
		{
				try 
				{
					 if(Element.isDisplayed())
						return true;
					 else
						 Thread.sleep(100);
				} 
				catch (Exception e) 
				{
					 try {
							Thread.sleep(100);
						} catch (Exception e1) {
							//System.out.println("Failed Loop");
						}
				}
				
		}
		return false;
	}
	public boolean isElementPresent(WebElement Element, int timeoutMiliSeconds)
    {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			long count=timeoutMiliSeconds/100;
	        for (int second = 0; second< count ; second++)
	        {
	            try
	            {
	            	if(Element.isDisplayed())
						return true;
					 else
						 Thread.sleep(100);
	            }
	            catch (Exception e)
	            {
	                try {
						Thread.sleep(100);
					} catch (Exception e1) {
					}
	                continue;
	            }
	        }
	        return false;
	}
    public void terminate()
    {              
          driver.quit();
                    
    }
    
    /* Disconnect Multiple Session */
    
	
	
	
	
	public String getElementXPath(WebElement element) {
		    return (String)((JavascriptExecutor)driver).executeScript("gPt=function{if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
	}
	 
	 public String getXpath (WebElement elt)
	 {
		 return (String)((JavascriptExecutor)driver).executeScript ("function{var val=element.value; var xpath = '';for ( ; element && element.nodeType == 1; element = element.parentNode ){var id = $(element.parentNode).children(element.tagName).index(element) + 1;id > 1 ? (id = '[' + id + ']') : (id = '');xpath = '/' + element.tagName.toLowerCase() + id + xpath;}return xpath;}",elt);
	 }
	
	public void switchTo_cas_iframe()
	{
			int i=0;
			if (true)
				i=1500;
					while( i > 0) 
					{
						try 
						{ 
							i=i-1;
							Thread.sleep(10);
							driver.switchTo().frame("cas_iframe");
		                    i=0;
		                //   Thread.sleep(1000);
		                    break;
	                   } 
					   catch(Exception e){}
					}
	}
	
	public void switchTo_Any_iframe(String id)
	{
			int i=0;
			if (true)
				i=1500;
					while( i > 0) 
					{
						try 
						{ 
							i=i-1;
							Thread.sleep(10);
							driver.switchTo().frame(id);
		                    i=0;
		                //   Thread.sleep(1000);
		                    break;
	                   } 
					   catch(Exception e){}
					}
	}
	
	public void switchTo_Any_iframe(WebElement ee)
	{
			int i=0;
			if (true)
				i=1500;
					while( i > 0) 
					{
						try 
						{ 
							i=i-1;
							Thread.sleep(10);
							driver.switchTo().frame(ee);
		                    i=0;
		                //   Thread.sleep(1000);
		                    break;
	                   } 
					   catch(Exception e){
						   System.out.println("Fail - Couldnot get into ifrmae");
					   }
					}
	}
	
	
	//Checkbox selection de-selection
	
	public void checkboxselectiondeselection(WebElement Obj, String Checkboxname, String value)
	{
		//funSleep(1000);
		if (isElementPresent(Obj))
		{
			boolean selection = Obj.isSelected();
			if (value.equalsIgnoreCase("YES") && !selection)
			{
				Obj.click();
				report.updateTestLog("Select "+Checkboxname, Checkboxname+" Selected", Status.PASS);
			}	
			else if ((value.equalsIgnoreCase("NO")||value.equalsIgnoreCase("")) && selection)
			{
				Obj.click();
				report.updateTestLog("Select "+Checkboxname, Checkboxname+" de-Selected", Status.PASS);
			}
			
		}
		else
		{
			report.updateTestLog("Checkbox "+Checkboxname, Checkboxname+" Checkbox not displayed", Status.FAIL);
		}
		
	}
	
	
	/* Select or Click Menu */ /* Important Common Function */

	
	public boolean verifyElementNotPresent(WebElement we)
	{
		if(isElementPresent(we))
		{
			 return false;
		}
		else
		{
			 return true;
		}
	}
	
	public void negetiveTesting(WebElement we, String name)
	{
		String userType = dataTable.getData("TestData", "UserType");
		boolean flag = verifyElementNotPresent(we);
		if(flag)
		{
			report.updateTestLog("Verify "+name+" is present or not for user role: "+Global.HTMLFormatOpen+userType+Global.HTMLFormatClose, name+" should not be displayed", Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify "+name+" is present or not for user role: "+Global.HTMLFormatOpen+userType+Global.HTMLFormatClose, name+" is appearing.", Status.FAIL);
		}
	}
	
	public void selectMenu(WebElement we, String name)
	{
		if(isElementPresent(we))
		{
			we.click();
			 report.updateTestLog("Verify "+name+" menu is present or not", name+" menu is appearing correctly and user clicks on it", Status.PASS);
		}
		else
		{
			 report.updateTestLog("Verify "+name+" menu is present or not", name+" menu is not displayed", Status.FAIL);
		}
	}
	
	public void clickHyperLinkMenu(String link, String name, String type, int reportAction, String action)
	{
		try
		{
			waitUntilElementVisible(driver.findElement(By.linkText(link)));
			if(isElementPresent(driver.findElement(By.linkText(link))))
			{
				if(action=="click")
				{
					driver.findElement(By.linkText(link)).click();
					waitForPageLoaded();
				}
			
				if(reportAction==1)
				{
					report.updateTestLog("Verify "+"<b>"+name+"</b> "+type+" is present or not","<b>"+name+"</b>"+" "+type+" is appearing correctly and user clicks on it", Status.PASS);
				}
			}
			else
			{
				report.updateTestLog("Verify "+"<b>"+name+"</b> "+type+" is present or not","<b>"+name+"</b>"+" "+type+" is not displayed", Status.FAIL);
			}
		}
		catch(Exception e){
			
		}
	}
	
	public void funcFieldvalueSet(WebElement we, String action, String value, String type, String idName)
	{
		if(isElementPresent(we))
		{
			 String perform = performAction(we,action,value,type,idName);
			 if(action!="dropdown")
			 {
				 report.updateTestLog("Verify "+"<b>"+type+"</b>"+" field is present or not", "<b>"+type+"</b>"+" field is appearing correctly and user "+perform+Global.HTMLFormatOpen+value, Status.PASS);
			 }
		}
		else
		{
			 report.updateTestLog("Verify "+"<b>"+type+"</b>"+" field is present or not", "<b>"+type+"</b>"+" field is not displayed", Status.FAIL);
		}
	}
	
	public void verifyElementpresent(WebElement we, String type)
	{
		if(isElementPresent(we))
		{
			report.updateTestLog("Verify <b>"+type+"</b> is present or not", "<b>"+type+"</b> is displayed.", Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify <b>"+type+"</b> is present or not", "<b>"+type+"</b> is not displayed", Status.FAIL);
		}
	}
	
	public String performAction(WebElement we, String action, String value, String type, String idName)
	{
		try
		{
			if(action=="text")
			{
				we.clear();
				we.sendKeys(value);
				return "enters value: ";
			}
			else if(action=="radio")
			{
				we.click();
				return "clicks the radio button.";
			}
			else if(action=="button")
			{
				we.click();
				waitForPageLoaded();
				return "clicks button.";
			}
			else if(action=="check" || action.contains("checkbox"))
			{
				we.click();
				return "clicks the checkbox.";
			}
			else if(action=="dropdown")
			{
				selectDropdown(idName,value,type,we);
				return "";
			}
			else if(action=="autocomplete")
			{
				we.click();
				return "clicks the autocomplete Item.";
			}
			else if(action=="hyperlink")
			{
				we.click();
				return "clicks the Hyperlink.";
			}
		}
		catch(Exception e)
		{
			
		}
		
		return null;
	}
	
	public void selectDropdown(String idName,String valueToChange, String Type, WebElement element)
	{
		
		boolean selected=false;
		
		String[] dropdownList = getdropdownlist(idName);
		for (int i=0; i<dropdownList.length; i++)
		{
			if(dropdownList[i].equalsIgnoreCase(valueToChange))
			{
				Select selectbox=new Select(element);
					selectbox.selectByVisibleText(valueToChange);
				selected=true;
				break;
			}
		}
		
		if(selected)
		{
			report.updateTestLog("Select "+Type+" from dropdown", ""+Type+" from dropdown selected: "+"<font face=\"calibri\" color=\"blue\" style=\"font-size:17px\">"+valueToChange, Status.PASS);
			waitForPageLoaded();
		}
		else
		{
			report.updateTestLog("Select "+Type+" from dropdown", ""+Type+" cannot be found: "+valueToChange, Status.FAIL);
		}
	}
	
	public void selectDropdown(String idName,String valueToChange, String Type, WebElement element, String drpdownaction, int index)
	{
		
		boolean selected=false;
		
		String[] dropdownList = getdropdownlist(idName);
		
		if(drpdownaction=="index")
		{
			Select selectbox=new Select(element);
			selectbox.selectByIndex(index);
			funSleep(2000);
			selected=true;
		}
		else if(drpdownaction=="value")
		{
			for (int i=0; i<dropdownList.length; i++)
			{
				if(dropdownList[i].contains(valueToChange))
				{
					Select selectbox=new Select(element);
					funSleep(500);
					selectbox.selectByVisibleText(valueToChange);
					selected=true;
					break;
				}
			}
		}

		if(selected && drpdownaction=="value")
		{
			report.updateTestLog("Select <b>"+Type+"</b> from dropdown", "<b>"+Type+"</b> from dropdown selected: "+"<font face=\"calibri\" color=\"blue\" style=\"font-size:17px\">"+valueToChange, Status.PASS);
			waitForPageLoaded();
		}
		else if(!selected)
		{
			report.updateTestLog("Select <b>"+Type+"</b> from dropdown", "<b>"+Type+"</b> cannot be found: "+valueToChange, Status.FAIL);
		}
	}
	
	public String getDropdownValue(String idName, int index)
	{
		String[] dropdownList = getdropdownlist(idName);
		return dropdownList[index];
	}
	

/* Download	CSV read, write, archive */
	
	public void archiveExistingfiles()
	{
		 final String dir = System.getProperty("user.dir")+"\\Downloaded";
		 File fil=new File(dir);
		 File[] FileListing=fil.listFiles();
		 final String dir1 = System.getProperty("user.dir")+"\\Archive";

			if(FileListing != null)
			{
				for(File ff : FileListing)
				{
					Path source=Paths.get(dir+"\\"+ff.getName());
					Path destination=Paths.get(dir1+"\\"+ff.getName());
					try
					{
						Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
					}
					catch(Exception e)
					{
						//System.out.println() ;
					}
				}
			}
	}
	
	public String checkDownloadcomplete()
	{
		 final String dir = System.getProperty("user.dir")+"\\Downloaded";
		 File fil=new File(dir);
		 File[] FileListing=fil.listFiles();
		 boolean isDownloadCompleted=false;
		 String fileName=null;
				
		 while(!isDownloadCompleted)
				{
					FileListing=fil.listFiles();
					for(int i = 0; i<FileListing.length;i++) 
					{
				        if(FileListing[i].getName().contains(".part"))
				        {
				        	try {
				        			Thread.sleep(1000);
				        		} 
				        	catch (InterruptedException e) 
				        	    {
				        			e.printStackTrace();
				        		}
				        	break;
				        }
				        
				        if(i==(FileListing.length-1))
				        {
				        	isDownloadCompleted=true;
				        	fileName = FileListing[i].getName();
				        }
				    }			
				}
		 
		 if(isDownloadCompleted)
		 {
			 report.updateTestLog("Verify whether the <b>file download</b> is completed or not ", "<i>File has been downloaded successfully</i>", Status.PASS);
			 return fileName;
		 }
		 else
		 {
			 report.updateTestLog("Verify whether the <b>file download</b> is completed or not ", "File couldn't be downloaded due to broken link", Status.FAIL);
			 return null;
		 }
	}
	
	@SuppressWarnings("unused")
	public void readFiledata(String filename)
	{
		//File file = new File("D:/data.csv");
		 final String loc = System.getProperty("user.dir")+"\\Downloaded\\"+filename;
		 File fil = new File(loc);
		
		 if(fil.exists())
		 {
			 report.updateTestLog("File has been found and can be accessed.", "Location: "+System.getProperty("user.dir")+"\\Downloaded\\"+filename, Status.PASS);
		 }
		 else
		 {
			 report.updateTestLog("File Not Found !!", "Location: "+System.getProperty("user.dir")+"\\Downloaded\\"+filename, Status.FAIL);
		 }
		 
		 BufferedReader br;
		try {
			br = new BufferedReader( new FileReader(fil));
		
         String strLine = "";
         StringTokenizer st = null;
         int lineNumber = 0, tokenNumber = 0, counter = 0;
     	 
         //String dropdownset[] = {"\"Tab Name\"","\"Unit Name\"","\"Lesson Name\"","\"Content Name\"","\"Content type\"","\"Content value\"","\"Reason for failure\""};

         
         //read comma separated file line by line
         
         while((strLine = br.readLine()) != null)
         {
                 lineNumber++;
                 String columnNames[] = new String[7];
                
                 //break comma separated line using ","
                 st = new StringTokenizer(strLine, ",");
                
                 while(st.hasMoreTokens())
                 {
                         //display csv values
                	 
                	 		if(counter>=columnNames.length)
                	 		{
                	 			break;
                	 		}
                         tokenNumber++;
                         //System.out.println("Row # " + lineNumber + ", Column # " + tokenNumber + ", Token : "+ st.nextToken());
                         //System.out.println("");
                         columnNames[counter]=st.nextToken().toString();
                        // System.out.println("Row # " + lineNumber + ", Column # " + tokenNumber + ", Token : "+ columnNames[counter]);
                         counter++;
                 }
                
                 //reset token and counter number
                 tokenNumber = 0;
                 counter = 0;
                 
                 if(lineNumber==1)
                 {
                	 //if (Arrays.equals(columnNames, dropdownset))
                	 displayFiledata(columnNames,"names");
                 }
                 else if(lineNumber>1)
                 {
                	 displayFiledata(columnNames,"values");
                 }
                
                 
             	 columnNames=null;
         }
		//Now you can store this in a array or use your custom class to store it 
         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			report.updateTestLog("Exception occured Couldn't read File", "Message: "+e, Status.FAIL);
			e.printStackTrace();
		} 
         
	}
	
	

	
	public void displayFiledata(String param[], String type)
	{
        String combinedSet = "";
		if(param!=null)
		 {
			 for(int i=0;i<param.length;i++)
			 {
				 combinedSet=combinedSet+param[i]+"<br>";
				 //System.out.println(dropdownset[i]);
			 }
			 
			 report.updateTestLog("Verify the <b>field "+type+"</b> are correctly displayed in the Report", Global.HTMLFormatOpen+combinedSet+Global.HTMLFormatClose+" - <b>field "+type+"</b> are displayed", Status.PASS);
		 }
		 else
		 {
			 report.updateTestLog("Verify the  <b>field "+type+"</b> are correctly displayed in the Report", Global.HTMLFormatOpen+combinedSet+Global.HTMLFormatClose+" - The  <b>field "+type+"</b> are not matching", Status.FAIL);
		 }
	}

	
// PTAS Function
	
	public boolean ptasSearch(WebElement searchCriteria, WebElement searchField, WebElement searchButton, String searchCriteriaValue, String searchValue)
	{
		boolean returnVal=true;
		try
		{new Select(searchCriteria).selectByVisibleText(searchCriteriaValue);}
		catch(Exception e)
		{
			report.updateTestLog("Verify Search", "User is not able to select a search criteria", Status.FAIL);
			returnVal=false;
		}
		
		try
		{
			searchField.clear();
			searchField.sendKeys(searchValue);
		}
		catch(Exception e)
		{
			report.updateTestLog("Verify Search", "User is not able to enter a search value", Status.FAIL);
			returnVal=false;
		}
		try
		{
			searchButton.click();
			waitForPageLoaded();
			report.updateTestLog("Verify Search", "User is able to search", Status.PASS);
		}
		catch(Exception e)
		{
			report.updateTestLog("Verify Search", "User is not able to search", Status.FAIL);
			returnVal=false;
		}

		return returnVal;
	}
	
/* Generic Common Functions */
	
	public boolean waitForElementInvisible(WebElement element)
	{
		 
		int counter=0;
		boolean willFlowContinue=false;
		while(counter<10)
		{
			String attval = element.getAttribute("style");
	        if (attval.contains("block"))
	        {
	            counter++;
	            try {Thread.sleep(100);}
	            catch (InterruptedException e) 
	            {e.printStackTrace();}
	        }
	        else
	        {
	        	willFlowContinue=true;
	        	return willFlowContinue;
	        }
		}
		
		return willFlowContinue;
	}
	
	public boolean verifyMultipleMessages(WebElement element,String msg[])
	{
		String messages = element.getText();
		String[] array = messages.split("\\n");
		int counter = 1;
		
		for(int i=0;i<array.length;i++)
		{
			if(array[i].trim().equalsIgnoreCase(msg[i]))
			{
				counter++;
			}
		}
		
		if(counter>=array.length)
		{
			report.updateTestLog("Verify the <i>Success/error</i> message","<i>Success/error</i> message is displayed correctly as such: "+Global.HTMLFormatOpen+messages.replace("\n", "<br>")+Global.HTMLFormatClose, Status.PASS);
			return true;
		}
		else
		{
			report.updateTestLog("Verify the <i>Success/error</i> message","<i>Success/error</i> message is not displayed correctly : "+Global.HTMLFailFormatOpen+element.getText()+Global.HTMLFormatClose, Status.FAIL);
			return false;
		}
		
	}

	
	public boolean verifyFieldMessageText(WebElement element,String msg, String type)
	{
		String val=null;
		if(type=="message")
		{
			val="Success/error message";
		}
		else if(type=="field")
		{
			val="Field text value";
		}
		
		try
		{
			if(element.getText().trim().contains(msg.trim()))
			{
				report.updateTestLog("Verify the "+val+"  ",val+" is displayed correctly as such: "+Global.HTMLFormatOpen+msg+Global.HTMLFormatClose, Status.PASS);
				return true;
			}
			else
			{
				report.updateTestLog("Verify the "+val+"  ",val+" is not displayed correctly : "+Global.HTMLFailFormatOpen+element.getText()+Global.HTMLFormatClose, Status.FAIL);
				return false;
			}
		}
		catch(Exception e)
		{
			report.updateTestLog("Verify the "+val+"  ",val+" container ELEMENT NOT FOUND.", Status.FAIL);
			return false;
		}
	}
	
	public void webHeaderTitleValidation(String text)
	{
		if(driver.getTitle().contains(text))
		{
			report.updateTestLog("Verify that correct header title '"+Global.HTMLFormatOpen+text+Global.HTMLFormatClose+"' is displayed or not.","Correct header title is getting displayed: "+Global.HTMLFormatOpen+driver.getTitle()+Global.HTMLFormatClose, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify that correct header title '"+Global.HTMLFormatOpen+text+Global.HTMLFormatClose+"' is displayed or not.","Incorrect header title is displayed: "+Global.HTMLFailFormatOpen+driver.getTitle()+Global.HTMLFormatClose, Status.FAIL);
		}
	}
	
	public void funVerifyOrg(String orgLeaf)
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		boolean flag = false;
		boolean empty = false;
		int columnsize = Integer.parseInt(dataTable.getData("TestData", "ColumnSize"));
		int rootNodeID = Integer.parseInt(dataTable.getData("General_Data", "RootNodeID"));
		String[] array = new String[2];
		array[0] = dataTable.getData("TestData", "UserType");
		array[1] = dataTable.getData("TestData", "UserStatus");
		String userDataFile = dataTable.getData("General_Data", "UserDataFileName");
		
		try
		{
			//clickHyperLinkMenu(orgLeaf, "'"+orgLeaf+"'","Org Node",1,"click");
			if(orgLeaf==null)
			{
				orgLeaf = "Org_"+Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
				empty = true;
			}
			
			driver.findElement(By.linkText(orgLeaf)).click();
			Global.currentOrgName = orgLeaf;
	
		}
		catch(Exception y)
		{
			clickHyperLinkMenu("Create Org Leaf", "'Create Org Leaf'","Menu Item",1,"click");
			funcFieldvalueSet(Home.orgTitle, "text", orgLeaf, "Org Title Textbox", "");
			funcFieldvalueSet(Home.orgDesc, "text", orgLeaf, "Org Description Textbox", "");
			selectDropdown("edit-clms-org-parent-hierarchical-select-selects-0", "", "'Parent Org'", Home.parentSelect,"index",1);
			waitUntilElementVisible(Home.parentSelect);
			String getDropdownValue = getDropdownValue("edit-clms-org-parent-hierarchical-select-selects-0",rootNodeID);
			funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");
			
			if(empty)
			{
				boolean CBSflag = Boolean.parseBoolean(dataTable.getData("TestData", "CBSFlag"));
				
				
				String[] dataVal = {orgLeaf, Global.currentusrname, String.valueOf(CBSflag),getDropdownValue,"0","No","NonAffiliated","Negetive",null,null,null}; 
				String orgDataFile = dataTable.getData("General_Data", "OrgDataFileName");
				try {
					writeOnExcel(dataVal,orgDataFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
				
			flag=updateInExcel(Global.currentusrname,orgLeaf,7,columnsize,userDataFile);
			
			Global.currentOrgName = orgLeaf;
			
			if(flag)
			{
				report.updateTestLog("Enter the Org value for "+array[0], "Org has been updated to: "+array[1]+" to: "+"<font face=\"calibri\" color=\"blue\" style=\"font-size:17px\">"+orgLeaf, Status.PASS);
			}
			else
			{
				report.updateTestLog("Enter the Org value for "+array[0], "Org couldn't be updated. ", Status.FAIL);
			}
			
		}
	}
	
	public void funVerifyCoursepresence(String orgLeaf)
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		String userDataFile = dataTable.getData("General_Data", "UserDataFileName");
		
		Global.coursename ="Auto_"+Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-");
		String coursecode = Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
		Random rn = new Random();
		
		
		try
		{
			if(isElementPresent(Home.myCourses))
			{
				funcFieldvalueSet(Home.myCourses, "button", "", "'My Courses' menu button", "");
			}
			else
			{
				String[] arrays = new String[1];
				arrays[0] = dataTable.getData("TestData", "UserType");
				
				funcFieldvalueSet(Home.myTools, "button", "", "My Tools button", "");
				clickHyperLinkMenu("Manage Organization", "'Manage Organization'","Menu Item",1,"click");
				funVerifyOrg(orgLeaf);
				clickHyperLinkMenu("Create Course", "'Create Course'","Menu Item",1,"click");
				funcFieldvalueSet(Home.courseTitle, "text", Global.coursename, "Course Title Textbox", "");
				funcFieldvalueSet(Home.courseCode, "text", coursecode.substring(12)+rn.nextInt(9999), "Course Code Textbox", "");
				funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");
				updateColumnNo(arrays,"course",9,userDataFile,1);
				funVerifyCoursepresence(orgLeaf);
			}
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void createCourseAssignClassToTeacher()
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		String userDataFile = dataTable.getData("General_Data", "UserDataFileName");
		
		String userType = dataTable.getData("TestData", "UserType");
		Global.coursename ="Auto_Course_"+Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-");
		Global.className = "Auto_Class_"+Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-");
		String coursecode = Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
		String classcode = "Class_"+Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
		Random rn = new Random();
		String orgLeaf = Global.currentOrgName;
		
		if(userType.contains("Teacher"))
		{
			funcFieldvalueSet(Home.myTools, "button", "", "My Tools button", "");
			clickHyperLinkMenu("Manage Organization", "'Manage Organization'","Menu Item",1,"click");
			funVerifyOrg(orgLeaf);
			
			String existingCoursename = findExistingCourse();
			System.out.println(existingCoursename);
			
			if( existingCoursename=="NOTFOUND")
			{
				clickHyperLinkMenu("Create Course", "'Create Course'","Menu Item",1,"click");
				funcFieldvalueSet(Home.courseTitle, "text", Global.coursename, "Course Title Textbox", "");
				funcFieldvalueSet(Home.courseCode, "text", coursecode.substring(12)+rn.nextInt(9999), "Course Code Textbox", "");
				funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");
				funcFieldvalueSet(Home.orgTool, "button", "", "Org Tools button", "");
			}
			else if(existingCoursename!="NOTFOUND")
			{
				Global.coursename=existingCoursename;
			}
			
			String existingClassname = findExistingClass();
			System.out.println(existingClassname);
			
			String msg=null;
			
			if( existingClassname=="NOTFOUND")
			{
				clickHyperLinkMenu("Create Class", "'Create Class'","Menu Item",1,"click");
				funcFieldvalueSet(Home.classCode, "text", classcode.substring(18)+rn.nextInt(9999), "Class Code Textbox", "");
				funcFieldvalueSet(Home.classTitle, "text", Global.className, "Class Title Textbox", "");
				selectDropdown("edit-field-class-course-nid", Global.coursename, "'Master Course'", Home.masterCourse,"value",0);
				funcFieldvalueSet(Home.toDate, "text", "2018-08-19", "To Date Calender", "");
				funcFieldvalueSet(Home.instructor2, "text", Global.currentusrname, "Instructor textbox", "");
				funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");
				msg = "Class "+Global.className+" has been created.";
			}
			else if(existingClassname!="NOTFOUND")
			{
				Global.className=existingClassname;
				clickHyperLinkMenu(Global.className, Global.className,"Class",1,"click");
				clickHyperLinkMenu("Manage teachers", "'Manage teachers'","Class Tab Item",1,"click");
				clickHyperLinkMenu("Edit Teachers", "'Edit Teachers'","Tab Item",1,"click");
				enterTeacher();
				funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");
				msg = "Class "+Global.className+" has been updated.";
			}
			
			//updateColumnNo("course",9);
			
			try
			{
				funAcceptPopup();
			}
			catch(Exception e)
			{
				
			}
			waitForPageLoaded();
			this.funSleep(1000);
			
			boolean feedback = verifyFieldMessageText(driver.findElement(By.xpath(".//*[@id='content-area']/div/div[1]")), msg,"message");
			if(feedback)
			{
				String[] arrays = new String[1];
				arrays[0] = dataTable.getData("TestData", "UserType");
				updateColumnNo(arrays,"class",10,userDataFile,1);
			}
			else
			{
				driver.close();
			}
			
		}
		else{
			report.updateTestLog("User Verifies the presence of any class and performs actions on it.", "User is unable to verify the same. Please try again or contact Test Automation Developer", Status.FAIL);
		}	
	}
	
	public String findExistingCourse()
	{
		POGeneric gener = PageFactory.initElements(driver, POGeneric.class);
		String coursename = null;
		
		try{
			if(isElementPresent(gener.firstMasterCourse))
			{
				coursename = gener.firstMasterCourse.getText();
				return coursename;
			}
			else
				return "NOTFOUND";
		}
		catch(Exception e)
		{
			return "NOTFOUND";
		}
	
	}
	
	public String findExistingClass()
	{
		POGeneric gener = PageFactory.initElements(driver, POGeneric.class);
		String classname = null;
		
		try{
			if(isElementPresent(gener.firstClass))
			{
				classname = gener.firstClass.getText();
				return classname;
			}
			else
				return "NOTFOUND";
		}
		catch(Exception e)
		{
			return "NOTFOUND";
		}
	
	}
	
	public void enterTeacher()
	{
		int c=0;
		try
		{
			while(true)
			{
				String teacherfield = driver.findElement(By.xpath(".//*[@id='edit-field-class-instructors-"+c+"-uid-uid']")).getAttribute("value");
				if(teacherfield.isEmpty())
				{
					funcFieldvalueSet(driver.findElement(By.xpath(".//*[@id='edit-field-class-instructors-"+c+"-uid-uid']")), "text", Global.currentusrname, "Instructor Textbox", "");
					break;
				}
				c++;
				if(c>100)
				{
					report.updateTestLog("Enter the Teacher in 'Edit teacher' section", "User is unable to enter teacher name: "+Global.HTMLFailFormatOpen+Global.currentusrname, Status.FAIL);
					break;
				}
			}
		}
		catch(Exception e)
		{
			report.updateTestLog("Enter the Teacher in 'Edit teacher' section", "User is unable to enter teacher name: "+Global.HTMLFailFormatOpen+Global.currentusrname, Status.FAIL);
		}
	}
	
	// Course Maintainer
	
	/*public void findCMUser()
	{
		int cNo = findCourseForUser(Global.currentusrname);
		String Uname = dataTable.getData("General_Data", "Username").trim();	
		if(cNo<0)
		{
			Global.currentusrname = Uname;
		}
	}
	*/
/*	public int findCourseForUser(String username)
	{
		String[] array = new String[1];
		array[0] = dataTable.getData("TestData", "UserType");
		array[1] = dataTable.getData("TestData", "UserStatus");
		String userDataFile = dataTable.getData("General_Data", "UserDataFileName");
		
		int columnsize = Integer.parseInt(dataTable.getData("TestData", "ColumnSize"));
		int courseNo = getRowNo(array,10,columnsize,userDataFile);
		return courseNo;
	}*/
	
	public String fillUserRegTemplate(String type)
	{
		POGeneric splash = PageFactory.initElements(driver, POGeneric.class);
		String userType = dataTable.getData("TestData", "UserType");
		String classDataFile = dataTable.getData("General_Data", "ClassDataFileName");
		int classcolumn = Integer.parseInt(dataTable.getData("TestData", "ClassColumnSize"));
		
		int randomVal = randomnumbergenerator(111111, 999999);
		//int getYear = randomnumbergenerator(1900, 2013);
		
		String userName = type+randomVal;
		
		if(userType.contains("Clientadmin"))
		{
			waitUntilElementVisible(splash.email);
			funSleep(1000);
			funcFieldvalueSet(splash.email, "text", userName+"@clms.com", "Email Textbox", "");
			funcFieldvalueSet(splash.confEmail, "text", userName+"@clms.com", "Confirm Email Textbox", "");
			funcFieldvalueSet(splash.user, "text", userName, "User Name Textbox", "");
			funcFieldvalueSet(splash.neverSend, "check", userName, "Never Send checkbox", "");
			funcFieldvalueSet(splash.fName, "text", "CLMS", "First Name Textbox", "");
			funcFieldvalueSet(splash.LName, "text", userName, "Last Name Textbox", "");
			funcFieldvalueSet(splash.DName, "text", "CLMS "+userName, "Display Name Textbox", "");
			//funcFieldvalueSet(splash.createAcc, "button", "", "Create new account button", "");
			
			try
			{
				List<WebElement> element = driver.findElements(By.id("edit-submit"));
				element.get(1).click();
				waitForPageLoaded();
				report.updateTestLog("Check Submit Button.", "It is displayed and user clicks on it.", Status.PASS);
			}
			catch(Exception y)
			{
				report.updateTestLog("Check Submit Button.", "NOT DISPLAYED", Status.FAIL);
			}

			funSleep(2000);
			
		}
		else
		{
			funcFieldvalueSet(splash.user, "text", userName, "User Name Textbox", "");
			funcFieldvalueSet(splash.email, "text", userName+"@clms.com", "Email Textbox", "");
			funcFieldvalueSet(splash.pwd, "text", "123456", "Password Textbox", "");
			funcFieldvalueSet(splash.confpwd, "text", "123456", "Confirm Password Textbox", "");
			funcFieldvalueSet(splash.fName, "text", "CLMS", "First Name Textbox", "");
			funcFieldvalueSet(splash.LName, "text", userName, "Last Name Textbox", "");
			funcFieldvalueSet(splash.DName, "text", "CLMS "+userName, "Display Name Textbox", "");
			funcFieldvalueSet(splash.neverSend, "radio", "", "Never Send Checkbox", "");
			funcFieldvalueSet(splash.createAcc, "button", "", "Create new account button", "");
			waitForPageLoaded();
		}
		
		
		if(isElementPresent(driver.findElement(By.linkText(userName))))
		{
			
			updateInExcel(Global.currentProductName,"Enrolled",6,classcolumn,classDataFile);
			report.updateTestLog("Verify New User Creation.", "A message is displayed as such: "+Global.HTMLFormatOpen+splash.messageDiv.getText().replace("\\n", "<br>"), Status.PASS);
			
			/*String[] dataVal = {userName,  userName+"@clms.com", "123456", "CLMS "+userName,"1-Jan-"+Integer.toString(getYear),"Student","Created",Global.currentOrgName,"0","0","0"}; 
			
			try {
				writeOnExcel(dataVal,userDataFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			return userName;
		}
		else
		{
			report.updateTestLog("Verify New User Creation.", "Inocrrect message is displayed as such: "+Global.HTMLFailFormatOpen+splash.messageDiv.getText().replace("\\n", "<br>"), Status.FAIL);
			return null;
		}
		
		

	}
	
	public void clickOrgTool()
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		funcFieldvalueSet(Home.orgTool, "button", "", "'Org Tools' menu", "");
		funSleep(1000);
	}
	public void createCourseDirectly()
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		String parentOrg = Global.currentOrgName;
		Global.coursename ="Auto_Course_"+Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-");
		String coursecode = Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
		Random rn = new Random();
		Global.courseCode = coursecode.substring(12)+rn.nextInt(9999);
		funcFieldvalueSet(Home.myTools, "button", "", "'My Tools' menu", "");
		funSleep(2000);
		clickHyperLinkMenu("Manage Organization", "'Manage Organization'","Menu Item",0,"click");
		funVerifyOrg(parentOrg);
		clickHyperLinkMenu("Create Course", "'Create Course'","Menu Item",1,"click");
		funcFieldvalueSet(Home.courseTitle, "text", Global.coursename, "Course Title Textbox", "");
		funcFieldvalueSet(Home.courseCode, "text",Global.courseCode , "Course Code Textbox", "");
			
	}
	
	public void createProductBasedCourse(String type, String productName)
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		String parentOrg = Global.currentOrgName;
		Global.coursename ="Auto_Product_Course_"+Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-");
		String coursecode = Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
		Random rn = new Random();
		String[] dataVal = null;
		
		clickHyperLinkMenu("Create Course", "'Create Course'","Menu Item",1,"click");
		funcFieldvalueSet(Home.courseTitle, "text", Global.coursename, "Course Title Textbox", "");
		funcFieldvalueSet(Home.courseCode, "text", coursecode.substring(12)+rn.nextInt(9999), "Course Code Textbox", "");
		if (type.contains("Self")){
			funcFieldvalueSet(Home.selfStudy, "check", "", "Self-Study Checkbox", "");
		}
		funcFieldvalueSet(Home.productName, "text", productName, "'Product Name' Textbox", "");
		
		try
		{
			//List<WebElement> autoComplete = driver.findElements(By.xpath(".//*[contains(@class,'reference-autocomplete')]"));
			//autoComplete.get(0).click();
			funSleep(1000);
			driver.findElement(By.xpath(".//*[@id='autocomplete']/ul/li/div/div")).click();
		}
		catch(Exception e)
		{
			
		}
		
		
		//funcFieldvalueSet(Home.pDName, "text", productName, "'Product Name' Textbox", "");
		 //driver.findElement(By.cssSelector("#edit-field-product-0-nid-nid")).sendKeys(productName);
		funSleep(1000);
		funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");	
		
		
		String msg = "Course "+Global.coursename+" has been created.";
		verifyFieldMessageText(Home.messageDiv, msg,"message");
		
		String prodDataFile = dataTable.getData("General_Data", "ProdDataFileName");
		int prodColumnsize = Integer.parseInt(dataTable.getData("TestData", "ProdColumnSize"));
		String courseDataFile = dataTable.getData("General_Data", "CourseDataFileName");
		
		if (type.contains("Self")){
			dataVal = new String[]{Global.coursename,coursecode,Global.currentusrname, parentOrg,"Yes","0",productName,"Associated",null,null,null}; 
		}
		else if(type.contains("Generic"))
		{
			dataVal = new String[]{Global.coursename,coursecode,Global.currentusrname, parentOrg,"No","0",productName,"Associated",null,null,null}; 
		}
		
		
		try {
			writeOnExcel(dataVal,courseDataFile);
			updateInExcel(productName,"Present",8,prodColumnsize,prodDataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createClassNormal()
	{
		assignClassToCourseCreated();
		saveClassCourse("class");
		String courseDataFile = dataTable.getData("General_Data", "CourseDataFileName");
		
		String array[] = {Global.coursename};
		updateColumnNo(array,"ClassAssociated",5,courseDataFile,1);
		
	}
	
	
	public void assignClassToCourseCreated()
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		Global.className = "Auto_Class_"+Util.getCurrentFormattedTime().replace(" ", "_").replace(":", "-");
		String classcode = "Class_"+Util.getCurrentFormattedTime().replace(":", "").replace(" ", "").replace("PM", "");
		Random rn = new Random();
		Global.classCode = classcode.substring(18)+rn.nextInt(9999);
		
		
		clickHyperLinkMenu("Create Class", "'Create Class'","Menu Item",1,"click");
		funcFieldvalueSet(Home.classCode, "text",Global.classCode, "Class Code Textbox", "");
		funcFieldvalueSet(Home.classTitle, "text", Global.className, "Class Title Textbox", "");
		selectDropdown("edit-field-class-course-nid", Global.coursename, "'Master Course'", Home.masterCourse,"value",0);
		funcFieldvalueSet(Home.toDate, "text", "2018-08-19", "To Date Calender", "");
		
	}
	
	public void saveClassCourse(String type)
	{
		POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		String msg =null;
		String userDataFile = dataTable.getData("General_Data", "UserDataFileName");
		boolean ClassFlag = false;
		boolean check = false;
		String datMessage = dataTable.getData("Message", "Message");
		try
		{
			ClassFlag = Boolean.parseBoolean(dataTable.getData("TestData", "ClassFlag"));
			check = ClassFlag;
			if(ClassFlag)
			{
				driver.findElement(By.xpath("//input[@type='checkbox' and @id='edit-field-classbasedscoring-value']")).click();
				report.updateTestLog("Verify <b>Class Based Scoring</b> checkbox is displayed or not.", "CBS checkbox is displayed and user clicks on it.", Status.PASS);
			}
		}
		catch(Exception e)
		{
			check = false;
		}
		
		//funcFieldvalueSet(Home.saveOrg, "button", "", "'Save' button", "");
		Home.saveOrg.click();
		funSleep(2000);
		
		alertCheck(1);
		
		if(check)
		{
			if(Global.alertText.trim().contains(datMessage.trim()))
			{
				report.updateTestLog("Verify the CBS alert message. ","Alert message is displayed correctly as such: "+Global.HTMLFormatOpen+Global.alertText+Global.HTMLFormatClose, Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify the CBS alert message. ","Alert message is not displayed correctly : "+Global.HTMLFailFormatOpen+Global.alertText+Global.HTMLFormatClose, Status.FAIL);
			}
		}
		
		//waitForPageLoaded();
		
		funcFieldvalueSet(Home.orgTool, "button", "", "'Org Tools' menu", "");
		
		
		if(type.equalsIgnoreCase("class"))
		{
			msg = "Class "+Global.className+" has been created.";
		}
		else if(type.equalsIgnoreCase("course"))
		{
			msg = "Course "+Global.coursename+" has been created.";
		}
		
		boolean feedback = verifyFieldMessageText(driver.findElement(By.xpath(".//*[@id='content-area']/div/div[1]")), msg,"message");
		
		if(feedback)
		{
			String[] array = new String[1];
			array[0] = dataTable.getData("TestData", "UserType");
			
			if(type.equalsIgnoreCase("class"))
			{
				updateColumnNo(array,"class",10,userDataFile,1);
			}
			else if(type.equalsIgnoreCase("course"))
			{
				updateColumnNo(array,"course",9,userDataFile,1);
			}
		}
		else
		{
			driver.close();
		}
	}
	
	public void searchandclickCourse()
	{
		searchandclickItem("Course");
	}
	
	public void searchandclickClass()
	{
		searchandclickItem("Class");
	}
	
	public void searchandclickItem(String type)
	{
		//POGeneric Home = PageFactory.initElements(driver, POGeneric.class);
		
		String masterItemName = null;
		if(type.contains("Class"))
		{
			masterItemName=Global.className;
		}
		else
		{
			masterItemName=Global.coursename;
		}
		
		int row=0;
		boolean flag=false;
		try
		{		
				
				row=getrow(masterItemName);	
				flag=true;
		}
		catch (Exception e)
		{
			report.updateTestLog("Reason For Failure", e.getMessage(), Status.FAIL);
			driver.close();
		}
		
		
		if(flag)
		{
			if(row>0 && isElementPresent(driver.findElement(By.linkText(masterItemName))))
			{
				driver.findElement(By.linkText(masterItemName)).click();
				waitForPageLoaded();
				report.updateTestLog("Search for "+type+": <b>'"+masterItemName+"'</b> To be Edited", ""+type+" Found and user clicks on it", Status.PASS);
			}
			else if (row<0)
			{
				report.updateTestLog("Search for "+type+": <b>'"+masterItemName+"'</b> To be Edited", ""+type+" Not Found", Status.FAIL);
			}
			else
			{
				report.updateTestLog("Search for "+type+": <b>'"+masterItemName+"'</b> To be Edited", ""+type+" Not Found", Status.FAIL);
			}
		}
		else
		{
			report.updateTestLog("Search for "+type+": <b>'"+masterItemName+"'</b> To be Edited", ""+type+" Not Found", Status.FAIL);
		}
		
	}
	
	public void editCourse()
	{
		clickHyperLinkMenu(Global.coursename, Global.coursename,"Course",1,"click");
		clickHyperLinkMenu("Edit","'Edit'","Tab",1,"click");
	}
	
	public void assignCourseMaintainer()
	{
		
		
		POGeneric Gener = PageFactory.initElements(driver, POGeneric.class);
		Gener.courseMaintainer2.clear();
		funcFieldvalueSet(Gener.courseMaintainer2, "text", Global.currentusrname, "Course Title Textbox", "");
		funSleep(1000);
		funcFieldvalueSet(Gener.selectCM, "button", "", "'Autocomplete' dropdown", "");
		funSleep(1000);
		funcFieldvalueSet(Gener.saveOrg, "button", "", "'Save' button", "");
		
		String courseDataFile = dataTable.getData("General_Data", "CourseDataFileName");
		int columnsize = Integer.parseInt(dataTable.getData("TestData", "CourseColumnSize"));
		
		String[] array = new String[1];
		array[0] = "Yes";
		
		String parentOrg = readFromExcel(array,3,columnsize,courseDataFile);

		String[] dataVal = {Global.coursename,Global.courseCode,Global.currentusrname, parentOrg,"No","0","None","CLMSCourses",null,null,null}; 

		
		
		try {
			writeOnExcel(dataVal,courseDataFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateColumnNo(String array[],String columntype, int columnno, String dataFileName, int increment)
	{
		//String[] array = new String[1];
		//array[0] = dataTable.getData("TestData", "UserType");
		//String userDataFile = dataTable.getData("General_Data", "UserDataFileName");
		//array[1] = dataTable.getData("TestData", "UserStatus");
		int columnsize = 0;
		String primaryKey = null;
		if(dataFileName.contains("UserDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "ColumnSize"));
			primaryKey = Global.currentusrname;
		}
		else if(dataFileName.contains("OrgDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "OrgColumnSize"));
			primaryKey = Global.currentOrgName;
		}
		else if(dataFileName.contains("CourseDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "CourseColumnSize"));
			primaryKey = Global.coursename;
		}
		else if(dataFileName.contains("ActivationCodeDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "ActivateColumnSize"));
			primaryKey = Global.currentActivationCode;
		}
		else if(dataFileName.contains("ProdDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "ProdColumnSize"));
			primaryKey = Global.currentProductName;
		}
		else if(dataFileName.contains("UserActivationDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "UserActivationColumnSize"));
			primaryKey = Global.currentActivationCode;
		}
		else if(dataFileName.contains("ClassDetails"))
		{
			columnsize = Integer.parseInt(dataTable.getData("TestData", "ClassColumnSize"));
			primaryKey = Global.classCode;
		}
		
		
		
		boolean flag=false;
		String currentCourseval = readFromExcel(array,columnno,columnsize,dataFileName);
		int existingCourseval = Integer.parseInt(currentCourseval);
		int newCourseVal = existingCourseval+increment;
		flag=updateInExcel(primaryKey,String.valueOf(newCourseVal),columnno,columnsize,dataFileName);
		if(flag)
		{
			report.updateTestLog("Update the Status for "+array[0], "No. of "+columntype+" has been changed from "+currentCourseval+" to: "+"<font face=\"calibri\" color=\"blue\" style=\"font-size:17px\">"+newCourseVal, Status.PASS);
		}
		else
		{
			report.updateTestLog("Update the Status for "+array[0], "No. of "+columntype+" couldn't be updated from "+currentCourseval+" to: "+"<font face=\"calibri\" color=\"red\" style=\"font-size:17px\">"+newCourseVal, Status.FAIL);
		}
	}
	
	
	/* Excel Data Read, Write & Update */
	
	public void putInExcel(List<String[]> data, int Type)
	{
		String tempRecord[];
        //FileInputStream FIS=null;
        String Nam="";
        String tempDataPath="";
        FileOutputStream fileOut=null;
        if (Type==1)
        {
               tempDataPath=frameworkParameters.getRelativePath()+"\\UserData"+"\\ActivationCodeDetails.xls";
               Nam="ActivationCodeDetails";
        }

        try
        {
               int y=0;
            FileInputStream file = new FileInputStream(new File(tempDataPath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = null;
            while(y>-1 && y<workbook.getNumberOfSheets())
            {
               if (workbook.getSheetName(y).equalsIgnoreCase(Nam))
               {
                      sheet = workbook.getSheetAt(y);
                      y=-2;
               }
               y++;
               if (y==workbook.getNumberOfSheets() && y>-1)
               {sheet = workbook.createSheet(Nam);}
            }
            //HSSFRow datarow = null;
            int excelRowNum=0;
           /* try
               {
                     do{excelRowNum++;}
                     while((sheet.getRow(excelRowNum).getCell(2).toString().trim() != null) && (sheet.getRow(excelRowNum).getCell(2).toString().trim().length()>0));
               }
               catch (Exception e){}*/
            
             excelRowNum = sheet.getLastRowNum();
             HSSFRow datarow = null;
             
             HSSFCellStyle style = workbook.createCellStyle();

             style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
             style.setBorderTop(HSSFCellStyle.BORDER_THIN);
             style.setBorderRight(HSSFCellStyle.BORDER_THIN);
             style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
             
               //datarow=sheet.getRow(excelRowNum);
              // datarow=sheet.createRow((short)excelRowNum);
               for(int i=0;i<data.size();i++)
               {
            	   datarow = sheet.createRow((short) excelRowNum++);
                     tempRecord=data.get(i);
                     for(int j=0;j<tempRecord.length;j++)
                     {
                    	 datarow.createCell(j).setCellValue(tempRecord[j]);
                    	 datarow.getCell(j).setCellStyle(style);
                     }
                     fileOut = new FileOutputStream(tempDataPath);                  
                     
               }
            workbook.write(fileOut);
            file.close();
                       
        }
        catch(Exception e){System.out.println(e);}             
	
	}
	
	public void dataforExcel(String[] values, HSSFSheet sheet, HSSFCellStyle style)
	{
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		
		data.put("2", new Object[] {values[0], values[1], values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9],values[10]});
		
		Set<String> keyset = data.keySet();
		int rownum = 0;
		
		try
		{
			do
			{
				//s=sheet.getRow(excelRowNum).getCell(0).toString().trim();
				rownum++;
			}
			while(sheet.getRow(rownum).getCell(0).toString().trim() != null);
		}
		catch (Exception e)
		{
			System.out.println(e + "No Row is empty impossible condition");
		}
		
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		System.out.println("Current Rownumber: "+rownum);
		
		for (String key : keyset) {
			HSSFRow row = sheet.createRow(rownum);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr) {
		       HSSFCell cell = row.createCell(cellnum++);
		        if(obj instanceof Date) 
		        {
		        	cell.setCellValue((Date)obj);
		        	cell.setCellStyle(style);
		        }
		        else if(obj instanceof Boolean)
		        {
		        	cell.setCellValue((Boolean)obj);
		        	cell.setCellStyle(style);
		        }
		        else if(obj instanceof String)
		        {
		        	cell.setCellValue((String)obj);
		        	cell.setCellStyle(style);
		        }
		        else if(obj instanceof Double)
		        {
		        	cell.setCellValue((Double)obj);
		        	cell.setCellStyle(style);
		        }
		    }
		}

	}
	
	public void writeOnExcel(String[] values,String dataFilename) throws IOException
	{
		try 
		{
			
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			HSSFCellStyle style=workbook.createCellStyle();
			dataforExcel(values,sheet,style);

			try {
			    FileOutputStream outFile = new FileOutputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			    workbook.write(outFile);
			    outFile.close();
			    System.out.println("Excel written successfully..");
			     
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException f) {
			    f.printStackTrace();
			}
			
		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
		}
		catch (FileNotFoundException g) {
			// TODO Auto-generated catch block
			g.printStackTrace();
			report.updateTestLog("File NOT Found / Couldn't read File", "Message: "+g, Status.FAIL);
		}
		
		
	}
	
	public String readFromExcel(String[] searchString, int cellnum, int no_of_column,String dataFilename)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			String dataRequired = searchStringinExcel(searchString,cellnum,sheet,no_of_column);
			
			file.close();
		    FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
		    workbook.write(out);
		    out.close();
			
		    return dataRequired;

		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			
			report.updateTestLog("File NOT Found / Couldn't read File", "Message: "+e, Status.FAIL);
			return null;
		}
	}
	
	public String searchStringinExcel(String[] searchString, int cellnum, HSSFSheet sheet, int no_of_column)
	{
		boolean flag=false;
		String searchData = null;
		for(int y=0;y<searchString.length;y++)
		{
			searchData = searchData + searchString[y] + ";";
		}
		
		try
		{
			@SuppressWarnings("rawtypes")
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) 
			{
                HSSFRow row = (HSSFRow) rows.next();
                @SuppressWarnings("rawtypes")
				Iterator cells = row.cellIterator();
                
                if(row.getCell(0).getStringCellValue().equals(""))
                {
                	return "";
                }

                int counter=0;
                
                while (cells.hasNext())
                {
                    HSSFCell cell = (HSSFCell) cells.next();
                    
                    for(int i=0; i<no_of_column; i++)
                    {
                    	System.out.println(row.getCell(i).getStringCellValue()); // Print Cell Value
                    	
                    	for(int j=0; j<searchString.length; j++)
                    	{
                    		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(searchString[j]))
                    		{
                    			 counter++;
                    			 if(counter==searchString.length)
                                 {
                             		return row.getCell(cellnum).getStringCellValue();
                                 }
                    		}
                    	}
                    }
                    
                    if(counter<searchString.length)
                    {
                    	break;
                    }
                    	
                    
                   /*if(row.getCell(no_of_column).getStringCellValue().equals(""))
                    {
                    	break;
                    }*/
                }
                
			}
			
			if(flag)
			{
				funSleep(300);
				report.updateTestLog("Search "+searchData.replace(";", "<br>")+"from Datasheets. ", Global.HTMLFormatOpen+searchData.replace("\\n", "<br>")+" not found. ", Status.PASS);
				return "";
			}
			
			
		}
		catch(Exception e)
		{
			report.updateTestLog("Problem occured in reading file due to: ",e.getMessage().toString(), Status.PASS);
			return "";
		}
		
		return null;
	}
	
	
	public String skipDataFromExcel(String[] searchString, String[] dataToBeSkipped,  int skippedDataCellNo, int cellnum, int no_of_column,String dataFilename)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			String dataRequired = searchAndSkipStringinExcel(searchString,dataToBeSkipped,skippedDataCellNo, cellnum,sheet,no_of_column);
			
			file.close();
		    FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
		    workbook.write(out);
		    out.close();
			
		    return dataRequired;

		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			
			report.updateTestLog("File NOT Found / Couldn't read File", "Message: "+e, Status.FAIL);
			return null;
		}
	}
	
	
	public String searchAndSkipStringinExcel(String[] searchString,  String dataToBeSkipped[], int skippedDataCellNo, int cellnum, HSSFSheet sheet, int no_of_column)
	{
		boolean flag=false;
		boolean availability = false;
		
		try
		{
			@SuppressWarnings("rawtypes")
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) 
			{
                HSSFRow row = (HSSFRow) rows.next();
                @SuppressWarnings("rawtypes")
				Iterator cells = row.cellIterator();
                
                if(row.getCell(0).getStringCellValue().equals(""))
                {
                	return "";
                }

                int counter=0;
                int datafound=0;
                
                while (cells.hasNext())
                {
                    HSSFCell cell = (HSSFCell) cells.next();
                    int i=0;
                    for(i=0; i<no_of_column; i++)
                    {
                    	System.out.println(row.getCell(i).getStringCellValue()); // Print Cell Value
                    	
                    	for(int j=0; j<searchString.length; j++)
                    	{
                    		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(searchString[j]))
                    		{
                    			 counter++;
                    			 if(counter==searchString.length)
                                 {
                    				availability = true;
                             		break;
                                 }
                    		}
                    	}
                    	
                    	
                    	
                    	/*for(int k=0; k<dataToBeSkipped.length; k++)
                     	{
                     		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(dataToBeSkipped[k]))
                     		{
                     			counter2++; 
                     			 if(counter2==searchString.length)
                                  {
                     				availability=false;
                              		break;
                                  }
                     		}
                     	}*/
                    }
                    
                    for(int k=0; k<dataToBeSkipped.length; k++)
                    {
                    	 if(row.getCell(skippedDataCellNo).getStringCellValue().equalsIgnoreCase(dataToBeSkipped[k]))
                         {
                         	availability = false;
                         	break;
                         }
                    }
                   
                    if(availability)
                    {
                    	int check=0;
                    	
                    	 for(int k=0; k<dataToBeSkipped.length; k++)
                         {
                         	 if(!row.getCell(skippedDataCellNo).getStringCellValue().equalsIgnoreCase(dataToBeSkipped[k]))
                              {
                              	check++;
                              }
                         }
                    	 
                    	 if(check>0)
                    	 {
                    		 return row.getCell(cellnum).getStringCellValue(); 
                    	 }
                    	
                    }
                    
                    if(i<=no_of_column)
                    {
                    	break;
                    }
                    	
                }
                
			}
			
			if(flag)
			{
				return "";
			}
			
			
		}
		catch(Exception e)
		{
			report.updateTestLog("Problem occured in reading file due to: ",e.getMessage().toString(), Status.PASS);
			return "";
		}
		
		return null;
	}
	
	
	public String getMultipleDataFromExcel(String[] searchString,int cellnum, int no_of_column,String dataFilename)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			String dataRequired = searchMultipleDataFromExcel(searchString, cellnum,sheet,no_of_column);
			
			file.close();
		    FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
		    workbook.write(out);
		    out.close();
			
		    return dataRequired;

		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			
			report.updateTestLog("File NOT Found / Couldn't read File", "Message: "+e, Status.FAIL);
			return null;
		}
	}
	
	 public boolean isRowEmpty(HSSFRow row){
	     boolean isEmptyRow = true;
	         for(int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++){
	        	 HSSFCell cell = row.getCell(cellNum);
	            if(cell != null && cell.getCellType() != org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK && StringUtils.isNotBlank(cell.toString())){
	            isEmptyRow = false;
	            }    
	         }
	     return isEmptyRow;
	   }
	
	public String searchMultipleDataFromExcel(String[] searchString, int cellnum, HSSFSheet sheet, int no_of_column)
	{
		String dataValues = "";
		
		try
		{
			@SuppressWarnings("rawtypes")
			Iterator rows = sheet.rowIterator();
			
			while (rows.hasNext()) 
			{
				
                HSSFRow row = (HSSFRow) rows.next();
                @SuppressWarnings("rawtypes")
				Iterator cells = row.cellIterator();
                
                if(row.getCell(0).getStringCellValue().equals(""))
                {
                	return dataValues;
                }

                
                
                
                while (cells.hasNext())
                {
                    HSSFCell cell = (HSSFCell) cells.next();
                    int counter=0;
                    int i=0;
                    for(i=0; i<no_of_column; i++)
                    {
                    	System.out.println(row.getCell(i).getStringCellValue()); // Print Cell Value
                    	
                    	for(int j=0; j<searchString.length; j++)
                    	{
                    		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(searchString[j]))
                    		{
                    			 counter++;
                    			 if(counter==searchString.length)
                                 {
                    				 dataValues = dataValues + row.getCell(cellnum).getStringCellValue() + ";";
                                 }
                    		}
                    	}
                    	
                    	
                    	
                    	/*for(int k=0; k<dataToBeSkipped.length; k++)
                     	{
                     		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(dataToBeSkipped[k]))
                     		{
                     			counter2++; 
                     			 if(counter2==searchString.length)
                                  {
                     				availability=false;
                              		break;
                                  }
                     		}
                     	}*/
                    }
                    
                    if(i<=no_of_column)
                    {
                    	break;
                    }
                }
                
			}
			
			
		}
		catch(Exception e)
		{
			return dataValues;
		}
		
		return dataValues;
	}
	
	public boolean updateInExcel(String searchValue, String updateValue,int cellno,int no_of_column,String dataFilename)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			boolean flag=searchAndupdateExcel(searchValue,updateValue,cellno,sheet,no_of_column);
			
			file.close();
		    FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
		    workbook.write(out);
		    out.close();
		    
		    return flag;

		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			report.updateTestLog("File NOT Found / Couldn't read File", "Message: "+e, Status.FAIL);
			return false;
		}
	}
	
	public boolean searchAndupdateExcel(String searchValue, String updateValue,int cellno,HSSFSheet sheet,int no_of_column)
	{
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) 
		{
            HSSFRow row = (HSSFRow) rows.next();
            for(int i=0; i<no_of_column; i++)
            {
            	System.out.println(row.getCell(i).getStringCellValue()); // Print Cell Value
            	
            		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(searchValue))
            		{
            			row.getCell(cellno).setCellValue(updateValue);
            			return true;
            		}
            }
		}
		
		return false;
	}
	
	public boolean deleteFromExcel(String searchValue,int no_of_column,String dataFilename)
	{
		try
		{
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			boolean flag=searchAndDeleteRow(searchValue,sheet,no_of_column);
			
			file.close();
		    FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"\\UserData\\"+dataFilename+".xls"));
		    workbook.write(out);
		    out.close();
		    
		    return flag;

		}
		catch(NullPointerException n)
		{
			n.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			report.updateTestLog("File NOT Found / Couldn't read File", "Message: "+e, Status.FAIL);
			return false;
		}
	}
	
	public boolean searchAndDeleteRow(String searchValue,HSSFSheet sheet,int no_of_column)
	{
		//Iterator rows = sheet.rowIterator();
		//while (rows.hasNext()) 
		//{
          //  HSSFRow row = (HSSFRow) rows.next();
		
		HSSFRow row;
		//row = sheet.getRow(0);
		
	    int lastIndex = sheet.getLastRowNum();
	    for (int j=0; j<=lastIndex; j++)
	    {
	    	row = sheet.getRow(j);
	    	
            for(int i=0; i<no_of_column; i++)
            {
            	System.out.println(row.getCell(i).getStringCellValue()); // Print Cell Value
            	
            		if(row.getCell(i).getStringCellValue().equalsIgnoreCase(searchValue))
            		{
                        
            				HSSFRow removingRow = sheet.getRow(j);
                            if (removingRow != null) 
                            {
                            	sheet.removeRow(removingRow);
                            	sheet.shiftRows(j+1, lastIndex, -1);
                            	return true;
                            }
            			
            		}
            }
	    }
		//}
		
		return false;
		
	}
		
	
	
	/*
	 * Hover and click on specific link
	 */
	
	
	public void HoverSelection (String classcoll, String title, WebElement toolpath, WebElement link) throws InterruptedException
	{
		String path = "//*[contains(@class, '"+ classcoll + "')]";
		List<WebElement> mycoll = driver.findElements(By.xpath(path));
		boolean flag =false;
		String txt ="";
		for (WebElement x : mycoll)
		{
			String avlblinks = x.getText().toLowerCase();
						
			if (avlblinks.contains(title))
			{
				
				 WebElement hoover = toolpath;
				 Actions builder = new Actions(driver);
				 Actions hoverOverRegistrar = builder.moveToElement(hoover);
				 hoverOverRegistrar.perform();
				 //Thread.sleep(1000);
				 txt= link.getText();
				 link.click();
				 flag =true;					
				 report.updateTestLog("Verify the presence of "+title, title+"  clicked", Status.PASS);
				 report.updateTestLog("Hover Click", "Click on the "+txt+" Link", Status.PASS);
				 break;
			}
		}
		
		if (!flag)
		{
			report.updateTestLog("Verify the presence of "+title, title+"  not clicked", Status.FAIL);
			report.updateTestLog("Hover Click", "Click on the "+txt+" Link", Status.FAIL);            
		}
		
	}
	public void mousehoverandhold(WebElement path)
	{
		Actions action = new Actions(driver);
		String browserName= getbrowserGeneric();
		funSleep(2000);
		if(isElementPresent(path))
		{
			if(!browserName.equalsIgnoreCase("Firefox"))
			{
				action.moveToElement(path).build().perform();
			}
			else
			{
				action.moveToElement(path).perform();
			}
			report.updateTestLog("Hovering Over The Tab Edit Dropdown: ","Hovering Successful",Status.PASS);
		}
		funSleep(2000);
	}

	public int getrow(String template_name)
	{
	  int row=0,row_found=0;
	  while(true)	
	  {
			for(row=1;row<26;row++)
			{
		    	  String tname=null;
		    	  try
		    	  {
		    		  tname=driver.findElement(By.xpath(".//table/tbody/tr["+row+"]/td[1]")).getText();
		    		 // System.out.println(tname);
		    		  if(template_name.equalsIgnoreCase(tname))
					  {
						 row_found=row;
						 break;
					  }
		    	  }
		    	  catch(Exception e)
		    	  {
		    		  return 0;
		    	  }
				  
		    }
			if(row_found > 0)
			{
				return row_found;
			}
			else
			{
			  if (isElementPresent(driver.findElement(By.linkText("next ›"))))
			  {
				  driver.findElement(By.linkText("next ›")).click();
				  waitForPageLoaded();
					funSleep(300);
			  }
			  else
			  {
				  return 0; 
			  }
	        }  
	     }
}
	
	 public void perform_action(int row,int option)
	 {
		   if(isElementPresent(driver.findElement(By.xpath(".//table/tbody/tr["+row+"]/td[4]/a["+option+"]/span"))))
			{
				driver.findElement(By.xpath(".//table/tbody/tr["+row+"]/td[4]/a["+option+"]/span")).click();
			}
			else
			{
				report.updateTestLog("Perform action Edit,Delete,Clone,Archive", "Option not available", Status.FAIL);
			}
	 }
	 /*public boolean verifypopupmessage(String message)
	 {
		 POTemplateConfiguration TemplateConfiguration = PageFactory.initElements(driver, POTemplateConfiguration.class);
			if (isElementPresent(TemplateConfiguration.popupmessage))
			{
				String text=TemplateConfiguration.popupmessage.getText();
				if(text.equalsIgnoreCase(message))
				{
					 if(isElementPresent(TemplateConfiguration.popupok))
					 {
						 		TemplateConfiguration.popupok.click();
							 report.updateTestLog("Verify popup message displayed", "Expected success/error message displayed: "+text, Status.PASS);
					    	 return true;
					 }
					 else
					    	return false;
				}
				else
				{
					return false;
				}
			}
			return false;
	 }*/

	 public boolean funAcceptPopup()
	 {
		 POGeneric generic = PageFactory.initElements(driver, POGeneric.class);
		
					 if(isElementPresent(generic.popupok))
					 {
						 generic.popupok.click();
					    	 return true;
					 }
					 return false;
					
	 }
	 

	 public String getTimeStamp() 
	 {
		DateFormat format = new SimpleDateFormat("hhmmss");
		String timeStamp = format.format(new Date());
		return timeStamp;
	 }
	

	 public void funSleep(int timer)
	 {
		 try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void waitUntilElementVisible(WebElement element)
	 {
		 int timer=100;
		 boolean flag=false;
		 while(true)
		 {
			 timer=timer+100;
			 try
			 {
				 funSleep(timer);
				 
				 if(isElementPresent(element))
				 {
					 flag = isAttribtuePresent(element,"disabled");
					 if(!flag)
					 {
						 break;
					 }
	 
				 }
				 else
				 {
					 if(timer>10000)
					 {
						report.updateTestLog("Login As " +global_data.Uname , "Timeout waiting for Element to appear.", Status.DONE);
				        terminate(); 
					 }
				 }
			 }
			 catch(Exception e)
			 {
				 funSleep(timer);
				 if(timer>10000)
				 {
					 report.updateTestLog("Login As " +global_data.Uname , "Timeout waiting for Element to appear.", Status.DONE);
				       terminate(); 
				 }
			 }
		 }
	 }
	
	 
	 private boolean isAttribtuePresent(WebElement element, String attribute) {
		    Boolean result = false;
		    try 
		    {
		         String attval = element.getAttribute(attribute);
		        if (attval != null)
		        {
		            result = true;
		        }
		    } 
		    catch (Exception e) 
		    {
		    	
		    }

		    return result;
		}
	 
	 public void funcSetExecutionMode()
	 {
		 String execMode = dataTable.getData("TestData", "Wait_Time");
		 Global.setExecmode = execMode; 
	 }
	 
	 public void funcSetSleep()
	 {
		 if(Global.setExecmode.equalsIgnoreCase("Very Slow"))
		 {
			 funSleep(4000);
		 }
		 else if (Global.setExecmode.equalsIgnoreCase("Slow"))
		 {
			 funSleep(2000);
		 }
		 else if (Global.setExecmode.equalsIgnoreCase("Medium"))
		 {
			 funSleep(1000);
		 }
		 else if (Global.setExecmode.equalsIgnoreCase("Fast"))
		 {
			 funSleep(500);
		 }
		 else if (Global.setExecmode.equalsIgnoreCase(""))
		 {
			 funSleep(1500);
		 }
	 }
	 

	
	
	public void checkval(String xval)
	{
		List<WebElement> mycoll = driver.findElements(By.cssSelector(xval));
		 for (WebElement x : mycoll)
		 {
			 List<WebElement> collection = x.findElements(By.tagName("input"));
   			for (WebElement y : collection)
   			{
   				try {
   					y.click();
   				} catch (Exception e) {
   					// TODO Auto-generated catch block
   					
   				}
   				
   				//System.out.println(y.getText());
   				
   				
   				/*if (y.getAttribute("type").equals("text"))
   				{
   					// do nothing for text boxes
   				}*/
   			}
         
		 }
	}
	
	// Get values of Dropdown
	
	public String[] getdropdownlist(String value)
	{
		WebElement element = driver.findElement(By.id(value));
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();					
		Object[] optList = options.toArray();
		String[] optArr = new String[optList.length];
		
			for(int i=0; i<optList.length; i++)
			{
				optArr[i] = ((WebElement) optList[i]).getText();
			}
			return optArr;
	}
	
	

	/* Alterante Text */
	
	public String verifyButtonAltText(String classname)
	{
		WebElement element= driver.findElement(By.xpath(".//*[contains(@class,'"+classname+"')]"));
		return getAltText(element);
	}
	
	public String getAltText(WebElement element)
	{
		try{
		//WebElement alt = element.findElement(By.tagName(type));
		String value = element.getAttribute("alt").toString();
		return value;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public void nullFieldValidation(String fieldVariable, String reportName)
	{
		if(fieldVariable!=null)
		{
			report.updateTestLog("Verify that correct data is displayed in <b>"+reportName+": <i> field/button</i><b>.", "The "+reportName+" field/button data displayed is: "+Global.HTMLFormatOpen+fieldVariable+Global.HTMLFormatClose, Status.PASS);
		}
		else if(fieldVariable==null || fieldVariable.isEmpty())
		{
			report.updateTestLog("Verify that correct data is displayed in<b>"+reportName+": <i> field/button</i><b>.", "The "+reportName+" field/button data displayed is incorrect: "+Global.HTMLFailFormatOpen+fieldVariable+Global.HTMLFormatClose, Status.FAIL);
		}
	}
    
	public void alertCheck(int action)
	{
		try{
			boolean newwindow =isAlertPresent(10);
	        if (newwindow)
	        {
	                        Alert alert=driver.switchTo().alert();
	                        Global.alertText = alert.getText();
	                        if(action==0)
	                        {
	                        	alert.dismiss();
	                        	waitForPageLoaded();
	                        }
	                        else
	                        {
	                        	alert.accept();
	                        	waitForPageLoaded();
	                        }
	        }
			
		}
		catch (UnhandledAlertException a)
		{
			//report.updateTestLog("Alert Displayed or Not", "No alert displayed", Status.PASS);
			/*Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			if(action==0)
			{
				robot.keyPress(KeyEvent.VK_CANCEL);
				robot.keyRelease(KeyEvent.VK_CANCEL);
			}
			else if (action==1)
			{
				robot.keyPress(KeyEvent.VK_ACCEPT);
				robot.keyRelease(KeyEvent.VK_ACCEPT);
			}*/
		}
		catch (Exception e)
		{
			report.updateTestLog("Alert Displayed or Not", "No alert displayed", Status.PASS);
		}
		
	}
	
	public void getResolutionMoveCursor()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		//double height = screenSize.getHeight();
		
		 Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove( (int) Math.round(width-200), 5); // Moving the mouse cursor to title bar
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
         
	}
	
	public String getbrowserGeneric()
	{
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		return browserName;
	}
	//******************************************************
	 String type=null;
	 String element=null;
	public void waitForElementFound(String type,String element ){
		 
		 if (type.equalsIgnoreCase("id"))
		 {
			 WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOfElementLocated((By
				                .id(element))));
			}
			
			if (type.equalsIgnoreCase("xpath"))
			 {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOfElementLocated((By
				                .xpath(element))));
		 }
			
	 
		 if (type.equalsIgnoreCase("name"))
		 {
			 WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOfElementLocated((By
				                .name(element))));
				
			}
	 
		
	} 
	
//Frame to load.......................................................	
	String frame="content";
public void loadFrame(){
	   waitForPageLoaded();
		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> itr=windowId.iterator();
		String mainWinID = itr.next();
        String  newAdwinID = itr.next();
        driver.switchTo().window(newAdwinID);
        driver.switchTo().frame("content");
        driver.switchTo().frame("content");
       // report.updateTestLog("Frame load", "Frame loaded successful", Status.DONE);

	}
public void defaultWindow()
{	
	
	try {
		Thread.sleep(4000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Set<String> windowId = driver.getWindowHandles();
	Iterator<String> itr=windowId.iterator();
	String mainWinID = itr.next();
	try{
		String  newAdwinID = itr.next();
	}catch(Exception e){}
    driver.switchTo().window(mainWinID);
    waitForPageLoaded();
    //driver.switchTo().defaultContent();
    report.updateTestLog("Pop up closed", "switch to mainwindow and test pop up closed successfully", Status.DONE);
    
}

public void waitOneSecond(){
	try{
		Thread.sleep(1000);
	}catch (InterruptedException e){
		e.printStackTrace();
	}
}

public String hexFormOfColour(String colour){
	String str= colour.replace("(", "-");
	String str2=str.replace(")", "-");
	String str3=str2.replace(",", "-");
	//System.out.println(str3);
	  /* delimiter */
	  String delimiter = "-";
	  /* given string will be split by the argument delimiter provided. */
	  String[] temp2 = str3.split(delimiter);
	  		  
	  int r=Integer.parseInt(temp2[1].trim());
	  int g=Integer.parseInt(temp2[2].trim());
	  int b=Integer.parseInt(temp2[3].trim());
	  String hex = String.format("%02x%02x%02x", r, g, b);
	  //System.out.println(hex);
	  return hex;
	
	  
 }

public void highlightElement(String path) {
	
	
	driver.findElement(By.xpath(path)).click();
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath(path))).contextClick().build().perform();
		
	/*Action selectMultiple = action.build();
	selectMultiple.perform();*/
	
	
	

}
	



}

}
