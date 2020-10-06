package com.minimini;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class baseUi {
	public WebDriver driver;
	public Properties prop;
	public void callBrowser(String browserName) {

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\ObjectRepository\\projectConfig.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Opeara")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\operadriver.exe");
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	public void openUrl(String websiteURLKey) {
		try {
			driver.get(prop.getProperty(websiteURLKey));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void quitBrowser() {
		driver.quit();
	}
	public void enterText(String xpathKey, String data) {
		try {
			driver.findElement(By.xpath(prop.getProperty(xpathKey))).sendKeys(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void elementClick(String xpathKey) {
		try {
			driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void pressEscape(String xpathKey) {
		try {
			driver.findElement(By.xpath(prop.getProperty(xpathKey))).sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public String callElements(String xpathKey,int gi) {
		return driver.findElements(By.xpath(prop.getProperty(xpathKey))).get(gi).getText();
		}
	
	
	public void printOutput(String[] arr1,String[] arr2) throws IOException{
		
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("873089");
		Row r=sheet.createRow(0);
		r.createCell(0).setCellValue("Phones");
		r.createCell(1).setCellValue("Price");
		for(int i=0;i<5;i++) {
			Row rr=sheet.createRow(i+1);
			rr.createCell(0).setCellValue(arr1[i]);
			rr.createCell(1).setCellValue(arr2[i]);
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		try {
		FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"\\outputIGot\\OutputFile.xlsx");
		wb.write(fos);
		wb.close();
		fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
