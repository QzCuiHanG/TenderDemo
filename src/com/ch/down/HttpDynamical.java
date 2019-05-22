package com.ch.down;

import java.sql.Connection;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ch.pojo.WebSite;
import com.ch.util.MysqlOperation;
import com.ch.zhaobiao.ZhaoBiao;

public class HttpDynamical {
	MysqlOperation operation = new MysqlOperation();
	Connection conn = operation.mysqlConn();
	WebSite website = new WebSite();
	ZhaoBiao zb = new ZhaoBiao();
	//�첽�ɼ�
	public void selenium() throws InterruptedException{
		long waitLoadBaseTime = 10000;
		int waitLoadRandomTime = 3000;
		Random random = new Random(System.currentTimeMillis());
		//���������������λ�ã�����Ҫ����Ȼ�򿪵Ļ������ǿհ�ҳ
		System.setProperty("webdriver.chrome.driver", "D:/Selenium/chromedriver.exe"); 
		//ʵ����һ�����������
		WebDriver driver = new ChromeDriver();
		String baseUrl = "";
		//�������
		driver.get(baseUrl);
		//���ʱ����һ������������ӵ�����get����վ
		//��Ϊ��վ��һ���������ϴ򿪣��ý���ͣһ�£�����ҳ���Ԫ�ػ��Ҳ�����
		Thread.sleep(5000);
		
		//��javascript�������
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//��һ�ε��
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("")));
		Thread.sleep(3000);
		//�ڶ��ε��
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("")));
	}
}
