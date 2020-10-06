package com.mini.phones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.minimini.baseUi;

public class phones extends baseUi {

	@Test
	public void phonesUnder30k() throws IOException {
		System.out.print('\f');
		Scanner ab=new Scanner(System.in);
		System.out.println("Select the Browser of your choice: \n 1.FireFox \n 2.Chrome \n\nEnter your choice: ");
		int a=ab.nextInt();
		boolean b=true;
		
		switch(a) {
		case 1:callBrowser("Firefox");break;
		case 2:callBrowser("Chrome");break;
		default: System.out.println("You entred a invalid choice");b=false;;break;
		}
		if(b) {
		openUrl("websiteURLKey");
		pressEscape("searchtextbox_xpath");
		enterText("searchtextbox_xpath", "Phones under 30000");
		elementClick("seachButton_xpath");
		elementClick("newestFirst_xpath");
		String[] name=new String[5];
		String[] price =new String[5];
		for(int i=0;i<5;i++) {
			name[i]=callElements("phoneNames_xpath",i);
			price[i]=callElements("phonePrices_xpath",i);
		}
		printOutput(name,price);
		quitBrowser();
	}}

}
