package com.selenium.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Selenium1 {

	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
		ChromeOptions chrome=new ChromeOptions();
		
		chrome.addArguments("start-maximized");
		
		WebDriver driver=new ChromeDriver(chrome);
		driver.get("https://chromedriver.chromium.org/capabilities");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Alert alert=driver.switchTo().alert();
		alert.accept();
		alert.dismiss();
		alert.sendKeys("enter msg");
		
		Select select=new Select(driver.findElement(By.xpath("")));
		select.selectByIndex(1);
		select.getAllSelectedOptions();
		
		
		

	}

}
