package com.ch.zhaobiao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ch.down.HttpDynamical;
import com.ch.down.HttpStatical;
import com.ch.pojo.KeyWord;
import com.ch.pojo.WebSite;
import com.ch.util.MysqlOperation;

public class ZhaoBiao {
	MysqlOperation operation = new MysqlOperation();
	Connection conn = operation.mysqlConn();
	KeyWord keyword = new KeyWord();
	List<KeyWord> listKeyWord = null;
	Statement stKeyWord = null;

	public void collect() throws SQLException{
		Statement st = conn.createStatement();		
		String sql = "SELECT * FROM website";
		ArrayList<String> url = new ArrayList<>();
		List<WebSite> listWebSite = operation.webSiteQuery(conn,st,sql);
		for (WebSite webSite : listWebSite) {
			//�ж��Ƿ�ɼ� state��1 �ɼ���0 ���ɼ�
			if(webSite.getState()==1){
				//�ж��Ƿ�վ������ issearcher��1 �ǣ�0 ����				
				if(webSite.getIssearcher()==1){
					//��ȡ�ؼ���ƴ����ַ
					String sqlKeyWord = "SELECT * FROM keyword";
					stKeyWord = conn.createStatement();
					listKeyWord = operation.keyWordQuery(conn, stKeyWord, sqlKeyWord);
					int pagenum = webSite.getPagenum();
					//�жϵ�һҳ�Ƿ��0,1��ʼ(�Ƿ�Ϊ��)
					if(webSite.getPageone()==null){
						//ƴ�ӹؼ��֣���òɼ������ַ
						for (KeyWord keyWord : listKeyWord) {
							System.out.println("�ɼ��ؼ���" +keyWord.getKeyword()+ "��ʼ");
							String urls = webSite.getCrawerurl()+keyWord.getKeyword();
							url.add(urls);
						}
					} 						
				} else if(webSite.getIssearcher()==0){
					//ƴ��ҳ������òɼ������ַ
					String urls = webSite.getCrawerurl();					
					url.add(urls);
				}
				
				//�ж���ҳ���� 1 ��̬��2 �첽
				
				if(webSite.getUrlwebtype()==1){
					//��̬ client��jsoup��1xpath��2css
					HttpStatical statical = new HttpStatical();
					
					
					 
				} else if(webSite.getUrlwebtype()==2){
					//�첽 selenium��unit
					HttpDynamical dynamical = new HttpDynamical();
								
				}
				if(webSite.getPreurl()!=null){
					String urlZi = "";
				} else {
					String urlZi = "";
				}
				//redisȥ��
				//�ж�����ҳ����
				if(webSite.getContentwebtype()==1){
					
				} else if (webSite.getContentwebtype()==2){
					
				}
				if(webSite.getContenttype()==1){
					
				} else if (webSite.getContenttype()==2){
					
				}
				if(webSite.getCharset()=="urf-8"){
					
				}else if(webSite.getCharset()=="gb2312"){
					
				}
		
		}else {
				System.out.println("����ַ���ɼ�");
				break;				
			}
		}
		}

	

	public static void main(String[] args) throws SQLException {
		ZhaoBiao zb = new ZhaoBiao();
		zb.collect();
	}
}