package utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
@DataProvider(name="LoginData")

public String [][] getdata() throws IOException
{
	String path =".\\testdata\\opencart_logindata.xlsx";
	
	 Excelutility xlutil = new  Excelutility(path);
	 
	 int totalrows = xlutil.getRowCount("sheet1");
	 int totalcolomn = xlutil.getCellCount("sheet1", 1);
	 
	 String logindata[][]=  new String[totalrows][totalcolomn];
	 
	 for(int i=1 ; i<=totalrows ; i++)
	 {
		 for(int j=0 ; j<totalcolomn ;j++)
		 {
			 logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);
		 }
	 }
	 return logindata;
}
}
