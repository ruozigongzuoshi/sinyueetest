import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class excel4hb {
	
	private static String Path = "C:\\Users\\jishu\\Desktop\\吞食天地-每日数据-3月-数据源.xls";
	private static String NewPath = "C:\\Users\\jishu\\Desktop\\吞食天地-每日数据-3月-数据源2.xls";
	private static String ConfigPath = "C:\\Users\\jishu\\Desktop\\三网渠道名称.xls";
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		jxl.Workbook readwb = null;
		try {
			InputStream instream = new FileInputStream(Path);
			readwb = Workbook.getWorkbook(instream);
			int countSheet = readwb.getNumberOfSheets();
			int readSheet = countSheet - 1;
			// Sheet的下标是从0开始
			// 获取第一张Sheet表
			String[][][] table = inputMemory(readwb,readSheet);
			String[][] configname = getConfigData(ConfigPath);
			String[][] writetable = new String[readwb.getSheet(0).getRows()][30];
			System.out.println("**现在准备分析" +  readSheet + "张表");
			for (int k = 0; k < readSheet; k++) {
				Sheet readsheet = readwb.getSheet(k);
				int rsRows = readsheet.getRows();
				System.out.println("**开始分析表" + (k + 1));
				
				
				switch(k){
				case 0:
				{
					for (int i = 3; i < rsRows; i++) {
						
						writetable[i][0] = getDate(table[k][i][0]);//日期
						writetable[i][1] = changeName(configname,table[k][i][1]);//渠道名称
						writetable[i][2] = table[k][i][2];//设备激活
						writetable[i][3] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 17, 21);//付费用户数
						writetable[i][4] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 17, 18);//当日付费
						writetable[i][5] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 7, 10);//当日留存
						writetable[i][6] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 7, 12);//三日留存
						writetable[i][7] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 7, 14);//七日留存
						
						
					}
					break;
				}
				case 1:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//日期
						String q_name = changeName(configname,table[k][i][6]);//渠道名称
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("警告：第" + (k+1) + "张表\t日期:" + table[k][i][0] + "\t渠道:" + table[k][i][6] + "\t没有在dataeye里找到！");
						}
						
						
						if(writetable[curRow][8] == null){
							writetable[curRow][8] = "0";//初始化付费金额
						}
						
						if(writetable[curRow][9] == null){
							writetable[curRow][9] = "0";//初始化付费用户数
						}

						if(writetable[curRow][10] == null){
							writetable[curRow][10] = "0";//初始化付费次数
						}
						if(writetable[curRow][11] == null){
							writetable[curRow][11] = "0";//初始化付费arppu
						}
						
						writetable[curRow][8] = String.valueOf(Double.parseDouble(writetable[curRow][8]) 
									+ Double.parseDouble(table[k][i][9].replaceAll(",", "")));//总收入
						writetable[curRow][9] = String.valueOf(Double.parseDouble(writetable[curRow][9]) 
									+ Double.parseDouble(table[k][i][10].replaceAll(",", "")));//付费用户数
						writetable[curRow][10] = String.valueOf(Double.parseDouble(writetable[curRow][10]) 
									+ Double.parseDouble(table[k][i][11].replaceAll(",", "")));//付费次数
						writetable[curRow][11] = String.valueOf(Double.parseDouble(writetable[curRow][11]) 
									+ Double.parseDouble(table[k][i][26].replaceAll(",", "")));//日arppu
						
					}
					break;
				}
				case 2:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//日期
						String q_name = changeName(configname,table[k][i][3]);//渠道名称
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("警告：第" + (k+1) + "张表\t日期:" + table[k][i][0] + "\t渠道:" + table[k][i][3] + "\t没有在dataeye里找到！");
						}
						
						if(writetable[curRow][12] == null){
							writetable[curRow][12] = "0";//初始化总收入
						}
						
						if(writetable[curRow][13] == null){
							writetable[curRow][13] = "0";//初始化付费用户数
						}

						if(writetable[curRow][14] == null){
							writetable[curRow][14] = "0";//初始化付费次数
						}
						if(writetable[curRow][15] == null){
							writetable[curRow][15] = "0";//初始化日arppu
						}
						
						writetable[curRow][12] = String.valueOf(Double.parseDouble(writetable[curRow][12]) 
									+ Double.parseDouble(table[k][i][8].replaceAll(",", "")));//总收入
						writetable[curRow][13] = String.valueOf(Double.parseDouble(writetable[curRow][13]) 
									+ Double.parseDouble(table[k][i][11].replaceAll(",", "")));//付费用户数
						writetable[curRow][14] = String.valueOf(Double.parseDouble(writetable[curRow][14]) 
									+ Double.parseDouble(table[k][i][12].replaceAll(",", "")));//付费次数
						writetable[curRow][15] = String.valueOf(Double.parseDouble(writetable[curRow][15]) 
									+ Double.parseDouble(table[k][i][13].replaceAll(",", "")));//日arppu
						
						
					}
					break;
				}
				case 3:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][1]);//日期
						String q_name = changeName(configname,table[k][i][2]);//渠道名称
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);
						if(curRow == 0){
							System.out.println("警告：第" + (k+1) + "张表\t日期:" + table[k][i][1] + "\t渠道:" + table[k][i][2] + "\t没有在dataeye里找到！");
						}
						
						if(writetable[curRow][16] == null){
							writetable[curRow][16] = "0";//初始化游戏收入
						}
						
						if(writetable[curRow][17] == null){
							writetable[curRow][17] = "0";//初始化付费用户数
						}

						if(writetable[curRow][18] == null){
							writetable[curRow][18] = "0";//初始化arppu
						}
						
						
						writetable[curRow][16] = String.valueOf(Double.parseDouble(writetable[curRow][16]) 
									+ Double.parseDouble(table[k][i][6].replaceAll(",", "")));//游戏收入
						writetable[curRow][17] = String.valueOf(Double.parseDouble(writetable[curRow][17]) 
									+ Double.parseDouble(table[k][i][10].replaceAll(",", "")));//付费用户数
						writetable[curRow][18] = String.valueOf(Double.parseDouble(writetable[curRow][18]) 
									+ Double.parseDouble(table[k][i][13].replaceAll(",", "")));//arppu
						
						
						
					}
					break;
				}
				case 4:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//日期
						String q_name = changeName(configname,"奇虎360安卓推广渠道1");//渠道名称
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("警告：第" + (k+1) + "张表\t日期:" + table[k][i][0] + "\t渠道:" + "奇虎360安卓推广渠道1" + "\t没有在dataeye里找到！");
						}
						
						if(writetable[curRow][19] == null){
							writetable[curRow][19] = "0";//初始化下载量
						}
						
						if(writetable[curRow][20] == null){
							writetable[curRow][20] = "0";//初始化日充值金额
						}

						if(writetable[curRow][21] == null){
							writetable[curRow][21] = "0";//初始化充值次数
						}
						if(writetable[curRow][22] == null){
							writetable[curRow][22] = "0";//初始化付费arppu
						}
						
						writetable[curRow][19] = String.valueOf(Double.parseDouble(writetable[curRow][19]) 
									+ Double.parseDouble(table[k][i][1].replaceAll(",", "")));//下载量
						writetable[curRow][20] = String.valueOf(Double.parseDouble(writetable[curRow][20]) 
									+ Double.parseDouble(table[k][i][4].replaceAll(",", "")));//日充值金额
						writetable[curRow][21] = String.valueOf(Double.parseDouble(writetable[curRow][21]) 
									+ Double.parseDouble(table[k][i][5].replaceAll(",", "")));//充值次数
						writetable[curRow][22] = String.valueOf(Double.parseDouble(writetable[curRow][22]) 
									+ Double.parseDouble(table[k][i][6].replaceAll(",", "")));//付费arppu
					}
						
					break;
				}
				case 5:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//日期
						String q_name = changeName(configname,"UC九游");//渠道名称
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("警告：第" + (k+1) + "张表\t日期:" + table[k][i][0] + "\t渠道:" + "UC九游" + "\t没有在dataeye里找到！");
						}
						
						if(writetable[curRow][23] == null){
							writetable[curRow][23] = "0";//初始化成功付费次数
						}
						
						if(writetable[curRow][24] == null){
							writetable[curRow][24] = "0";//初始化成功付费收入
						}

						if(writetable[curRow][25] == null){
							writetable[curRow][25] = "0";//初始化支付失败金额
						}
						if(writetable[curRow][26] == null){
							writetable[curRow][26] = "0";//初始化未支付金额
						}
						
						writetable[curRow][23] = String.valueOf(Double.parseDouble(writetable[curRow][23]) 
									+ Double.parseDouble(table[k][i][1].replaceAll(",", "")));//成功付费次数
						writetable[curRow][24] = String.valueOf(Double.parseDouble(writetable[curRow][24]) 
									+ Double.parseDouble(table[k][i][2].replaceAll(",", "")));//成功付费收入
						writetable[curRow][25] = String.valueOf(Double.parseDouble(writetable[curRow][25]) 
									+ Double.parseDouble(table[k][i][3].replaceAll(",", "")));//支付失败金额
						writetable[curRow][26] = String.valueOf(Double.parseDouble(writetable[curRow][26]) 
									+ Double.parseDouble(table[k][i][4].replaceAll(",", "")));//未支付金额
					}
						
					break;
				}
				case 6:
				{
					for(int i = 2; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//日期
						String q_name = changeName(configname,"小米");//渠道名称
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("警告：第" + (k+1) + "张表\t日期:" + table[k][i][0] + "\t渠道:" + "小米" + "\t没有在dataeye里找到！");
						}
						
						if(writetable[curRow][27] == null){
							writetable[curRow][27] = "0";//初始化下载量
						}
						
						if(writetable[curRow][28] == null){
							writetable[curRow][28] = "0";//初始化日付费用户数
						}

						if(writetable[curRow][29] == null){
							writetable[curRow][29] = "0";//初始化日付费金额
						}
						
						
						writetable[curRow][27] = String.valueOf(Double.parseDouble(writetable[curRow][27]) 
									+ Double.parseDouble(table[k][i][1]));//成功下载量
						writetable[curRow][27] = String.valueOf(Double.parseDouble(writetable[curRow][28]) 
									+ Double.parseDouble(table[k][i][11]));//日付费用户数
						writetable[curRow][27] = String.valueOf(Double.parseDouble(writetable[curRow][29]) 
									+ Double.parseDouble(table[k][i][12]));//日付费金额
						
					}
						
					break;
				}
				default:break;
				}
				
			}
			outXls(readwb, table, writetable, readSheet);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readwb.close();
		}
	
	}
	
	public static String getDate(String ss){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		
		try {
			date = df.parse(ss);
		} catch (Exception e) {
			try{
				date = df2.parse(ss);
			}catch(Exception e2){
				try{
					if(Long.valueOf(ss).longValue() > 99999){
						return ss.substring(2,4).trim() + "-" + ss.substring(4,6).trim() + "-"
							+ ss.substring(6,8).trim();
						}	
						Long l = (Long.valueOf(ss).longValue() - 25569) * 86400000;
						date = new Date(l);
					}
				catch(Exception e3){
					System.out.println(ss + "不是日期！");
					return null;
				}
				
			}
			
		}
		return df.format(date).substring(2);
	}
	
	public static String[][] getConfigData(String ss){
		jxl.Workbook readwb = null;
		String[][] config_ids = null;
		try {
			InputStream instream = new FileInputStream(ss);
			readwb = Workbook.getWorkbook(instream);
			int countSheet = readwb.getNumberOfSheets();
			// Sheet的下标是从0开始
			// 获取第一张Sheet表
			Sheet readsheet = readwb.getSheet(0);
			int rsRows = readsheet.getRows();
			int rsCells = readsheet.getColumns();
			config_ids = new String[rsRows][];
			for (int k = 0; k < rsRows; k++) {
				config_ids[k] = new String[rsCells];
				for (int i = 0; i < rsCells; i++) {
					config_ids[k][i] = readsheet.getCell(i, k).getContents();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readwb.close();
		}
		System.out.println("**获取配置表格渠道名称成功");
		return config_ids;
	}
	
	public static String[][][] inputMemory(Workbook readwb, int readSheet) {
		// 加载到内存中
		String[][][] table = new String[readSheet][][];
		for (int i = 0; i < readSheet; i++) {
			Sheet st = readwb.getSheet(i);
			int r = st.getRows();
			int c = st.getColumns();
			table[i] = new String[r][c];
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					table[i][j][k] = st.getCell(k, j).getContents();
				}
			}
		}
		System.out.println("**把表格数据写入内存成功");
		return table;
	}
	
	public static String getdatafromtable(String[][][] table, String id, int i_id, String name, int i_name, int num){
		String data = "0";
		for(int i = 0; i < table[0].length; i++){
			if(table[0][i][i_id].equalsIgnoreCase(id)&&table[0][i][i_name].equalsIgnoreCase(name)){
				data = table[0][i][num];
				break;
			}
		}
		return data;
	}
	
	public static int getCurRowfromWriteTable(String[][] writetable, String id, String name){
		int curRow = 0;
		for(int i = 3; i < writetable.length; i++){
			if(writetable[i][0].equalsIgnoreCase(id)&&(writetable[i][1].equalsIgnoreCase(name)))
			{
				curRow = i;
				break;
			}
			
		}
//		if(curRow == 0){
//			System.out.println(id + name + "此列未找到");
//		}
		return curRow;
	}
	
	public static String changeName(String[][] configname, String ss){
		for(int i = 0 ; i < configname.length; i++){
			for(int j = 0; j < configname[i].length; j++){
				if(ss.equalsIgnoreCase(configname[i][j])){
					//System.out.println(ss+ "改名:" + configname[i][j]);
					return configname[i][0];
				}
			}
		}
		//System.out.println(ss+ "未改名");
		return ss;
	}
	
	public static void outXls(Workbook readwb,String[][][] table,String[][] writetable, int readSheet) throws RowsExceededException, WriteException {
		// 利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
		jxl.write.WritableWorkbook wwb;
		try {
			wwb = Workbook.createWorkbook(new File(
					NewPath), readwb);
			//读取
			jxl.write.WritableSheet ws = wwb.getSheet(readSheet);
			
			//写入
			for(int i = 2; i < writetable.length ; i++){
				for(int j = 0; j < 30; j++){
					String str = writetable[i][j];
					try {
						double d = Double.parseDouble(str);
						Number number = new Number(j, i, d);
						ws.addCell(number);
					} catch (Exception e) {
						Label label = new Label(j, i, str);
						ws.addCell(label);
					}
				}
				
			}
			
			System.out.println("**全部分析工作已完成");
			// 写入Excel对象
			wwb.write();
			wwb.close();
			System.out.println("**已经生成新的表单数据，打开看看吧！");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

}
