package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getdata() throws IOException
	{
		String path = System.getProperty("user.dir") + "/testdata/TestData_DDT.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);//creating object for ExcelUtility class
		int rowcount=xlutil.getRowCount("Sheet1");
		int columncount=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]= new String [rowcount][columncount];//created 2 dimensional array to store the data username and password
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<columncount;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
			 	
		}
		return logindata;//returning 2 dimensional array
		
		
	//DataProvider 2
		
		
	//DataProvider 3
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
