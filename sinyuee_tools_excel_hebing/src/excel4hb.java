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
	
	private static String Path = "C:\\Users\\jishu\\Desktop\\��ʳ���-ÿ������-3��-����Դ.xls";
	private static String NewPath = "C:\\Users\\jishu\\Desktop\\��ʳ���-ÿ������-3��-����Դ2.xls";
	private static String ConfigPath = "C:\\Users\\jishu\\Desktop\\������������.xls";
	
	

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
			// Sheet���±��Ǵ�0��ʼ
			// ��ȡ��һ��Sheet��
			String[][][] table = inputMemory(readwb,readSheet);
			String[][] configname = getConfigData(ConfigPath);
			String[][] writetable = new String[readwb.getSheet(0).getRows()][30];
			System.out.println("**����׼������" +  readSheet + "�ű�");
			for (int k = 0; k < readSheet; k++) {
				Sheet readsheet = readwb.getSheet(k);
				int rsRows = readsheet.getRows();
				System.out.println("**��ʼ������" + (k + 1));
				
				
				switch(k){
				case 0:
				{
					for (int i = 3; i < rsRows; i++) {
						
						writetable[i][0] = getDate(table[k][i][0]);//����
						writetable[i][1] = changeName(configname,table[k][i][1]);//��������
						writetable[i][2] = table[k][i][2];//�豸����
						writetable[i][3] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 17, 21);//�����û���
						writetable[i][4] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 17, 18);//���ո���
						writetable[i][5] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 7, 10);//��������
						writetable[i][6] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 7, 12);//��������
						writetable[i][7] = getdatafromtable(
								table, table[k][i][0], 0, table[k][i][1], 7, 14);//��������
						
						
					}
					break;
				}
				case 1:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//����
						String q_name = changeName(configname,table[k][i][6]);//��������
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("���棺��" + (k+1) + "�ű�\t����:" + table[k][i][0] + "\t����:" + table[k][i][6] + "\tû����dataeye���ҵ���");
						}
						
						
						if(writetable[curRow][8] == null){
							writetable[curRow][8] = "0";//��ʼ�����ѽ��
						}
						
						if(writetable[curRow][9] == null){
							writetable[curRow][9] = "0";//��ʼ�������û���
						}

						if(writetable[curRow][10] == null){
							writetable[curRow][10] = "0";//��ʼ�����Ѵ���
						}
						if(writetable[curRow][11] == null){
							writetable[curRow][11] = "0";//��ʼ������arppu
						}
						
						writetable[curRow][8] = String.valueOf(Double.parseDouble(writetable[curRow][8]) 
									+ Double.parseDouble(table[k][i][9].replaceAll(",", "")));//������
						writetable[curRow][9] = String.valueOf(Double.parseDouble(writetable[curRow][9]) 
									+ Double.parseDouble(table[k][i][10].replaceAll(",", "")));//�����û���
						writetable[curRow][10] = String.valueOf(Double.parseDouble(writetable[curRow][10]) 
									+ Double.parseDouble(table[k][i][11].replaceAll(",", "")));//���Ѵ���
						writetable[curRow][11] = String.valueOf(Double.parseDouble(writetable[curRow][11]) 
									+ Double.parseDouble(table[k][i][26].replaceAll(",", "")));//��arppu
						
					}
					break;
				}
				case 2:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//����
						String q_name = changeName(configname,table[k][i][3]);//��������
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("���棺��" + (k+1) + "�ű�\t����:" + table[k][i][0] + "\t����:" + table[k][i][3] + "\tû����dataeye���ҵ���");
						}
						
						if(writetable[curRow][12] == null){
							writetable[curRow][12] = "0";//��ʼ��������
						}
						
						if(writetable[curRow][13] == null){
							writetable[curRow][13] = "0";//��ʼ�������û���
						}

						if(writetable[curRow][14] == null){
							writetable[curRow][14] = "0";//��ʼ�����Ѵ���
						}
						if(writetable[curRow][15] == null){
							writetable[curRow][15] = "0";//��ʼ����arppu
						}
						
						writetable[curRow][12] = String.valueOf(Double.parseDouble(writetable[curRow][12]) 
									+ Double.parseDouble(table[k][i][8].replaceAll(",", "")));//������
						writetable[curRow][13] = String.valueOf(Double.parseDouble(writetable[curRow][13]) 
									+ Double.parseDouble(table[k][i][11].replaceAll(",", "")));//�����û���
						writetable[curRow][14] = String.valueOf(Double.parseDouble(writetable[curRow][14]) 
									+ Double.parseDouble(table[k][i][12].replaceAll(",", "")));//���Ѵ���
						writetable[curRow][15] = String.valueOf(Double.parseDouble(writetable[curRow][15]) 
									+ Double.parseDouble(table[k][i][13].replaceAll(",", "")));//��arppu
						
						
					}
					break;
				}
				case 3:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][1]);//����
						String q_name = changeName(configname,table[k][i][2]);//��������
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);
						if(curRow == 0){
							System.out.println("���棺��" + (k+1) + "�ű�\t����:" + table[k][i][1] + "\t����:" + table[k][i][2] + "\tû����dataeye���ҵ���");
						}
						
						if(writetable[curRow][16] == null){
							writetable[curRow][16] = "0";//��ʼ����Ϸ����
						}
						
						if(writetable[curRow][17] == null){
							writetable[curRow][17] = "0";//��ʼ�������û���
						}

						if(writetable[curRow][18] == null){
							writetable[curRow][18] = "0";//��ʼ��arppu
						}
						
						
						writetable[curRow][16] = String.valueOf(Double.parseDouble(writetable[curRow][16]) 
									+ Double.parseDouble(table[k][i][6].replaceAll(",", "")));//��Ϸ����
						writetable[curRow][17] = String.valueOf(Double.parseDouble(writetable[curRow][17]) 
									+ Double.parseDouble(table[k][i][10].replaceAll(",", "")));//�����û���
						writetable[curRow][18] = String.valueOf(Double.parseDouble(writetable[curRow][18]) 
									+ Double.parseDouble(table[k][i][13].replaceAll(",", "")));//arppu
						
						
						
					}
					break;
				}
				case 4:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//����
						String q_name = changeName(configname,"�滢360��׿�ƹ�����1");//��������
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("���棺��" + (k+1) + "�ű�\t����:" + table[k][i][0] + "\t����:" + "�滢360��׿�ƹ�����1" + "\tû����dataeye���ҵ���");
						}
						
						if(writetable[curRow][19] == null){
							writetable[curRow][19] = "0";//��ʼ��������
						}
						
						if(writetable[curRow][20] == null){
							writetable[curRow][20] = "0";//��ʼ���ճ�ֵ���
						}

						if(writetable[curRow][21] == null){
							writetable[curRow][21] = "0";//��ʼ����ֵ����
						}
						if(writetable[curRow][22] == null){
							writetable[curRow][22] = "0";//��ʼ������arppu
						}
						
						writetable[curRow][19] = String.valueOf(Double.parseDouble(writetable[curRow][19]) 
									+ Double.parseDouble(table[k][i][1].replaceAll(",", "")));//������
						writetable[curRow][20] = String.valueOf(Double.parseDouble(writetable[curRow][20]) 
									+ Double.parseDouble(table[k][i][4].replaceAll(",", "")));//�ճ�ֵ���
						writetable[curRow][21] = String.valueOf(Double.parseDouble(writetable[curRow][21]) 
									+ Double.parseDouble(table[k][i][5].replaceAll(",", "")));//��ֵ����
						writetable[curRow][22] = String.valueOf(Double.parseDouble(writetable[curRow][22]) 
									+ Double.parseDouble(table[k][i][6].replaceAll(",", "")));//����arppu
					}
						
					break;
				}
				case 5:
				{
					for(int i = 1; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//����
						String q_name = changeName(configname,"UC����");//��������
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("���棺��" + (k+1) + "�ű�\t����:" + table[k][i][0] + "\t����:" + "UC����" + "\tû����dataeye���ҵ���");
						}
						
						if(writetable[curRow][23] == null){
							writetable[curRow][23] = "0";//��ʼ���ɹ����Ѵ���
						}
						
						if(writetable[curRow][24] == null){
							writetable[curRow][24] = "0";//��ʼ���ɹ���������
						}

						if(writetable[curRow][25] == null){
							writetable[curRow][25] = "0";//��ʼ��֧��ʧ�ܽ��
						}
						if(writetable[curRow][26] == null){
							writetable[curRow][26] = "0";//��ʼ��δ֧�����
						}
						
						writetable[curRow][23] = String.valueOf(Double.parseDouble(writetable[curRow][23]) 
									+ Double.parseDouble(table[k][i][1].replaceAll(",", "")));//�ɹ����Ѵ���
						writetable[curRow][24] = String.valueOf(Double.parseDouble(writetable[curRow][24]) 
									+ Double.parseDouble(table[k][i][2].replaceAll(",", "")));//�ɹ���������
						writetable[curRow][25] = String.valueOf(Double.parseDouble(writetable[curRow][25]) 
									+ Double.parseDouble(table[k][i][3].replaceAll(",", "")));//֧��ʧ�ܽ��
						writetable[curRow][26] = String.valueOf(Double.parseDouble(writetable[curRow][26]) 
									+ Double.parseDouble(table[k][i][4].replaceAll(",", "")));//δ֧�����
					}
						
					break;
				}
				case 6:
				{
					for(int i = 2; i < rsRows; i++){
						String d_time = getDate(table[k][i][0]);//����
						String q_name = changeName(configname,"С��");//��������
						
						int curRow = getCurRowfromWriteTable(writetable,d_time,q_name);	
						if(curRow == 0){
							System.out.println("���棺��" + (k+1) + "�ű�\t����:" + table[k][i][0] + "\t����:" + "С��" + "\tû����dataeye���ҵ���");
						}
						
						if(writetable[curRow][27] == null){
							writetable[curRow][27] = "0";//��ʼ��������
						}
						
						if(writetable[curRow][28] == null){
							writetable[curRow][28] = "0";//��ʼ���ո����û���
						}

						if(writetable[curRow][29] == null){
							writetable[curRow][29] = "0";//��ʼ���ո��ѽ��
						}
						
						
						writetable[curRow][27] = String.valueOf(Double.parseDouble(writetable[curRow][27]) 
									+ Double.parseDouble(table[k][i][1]));//�ɹ�������
						writetable[curRow][27] = String.valueOf(Double.parseDouble(writetable[curRow][28]) 
									+ Double.parseDouble(table[k][i][11]));//�ո����û���
						writetable[curRow][27] = String.valueOf(Double.parseDouble(writetable[curRow][29]) 
									+ Double.parseDouble(table[k][i][12]));//�ո��ѽ��
						
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
					System.out.println(ss + "�������ڣ�");
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
			// Sheet���±��Ǵ�0��ʼ
			// ��ȡ��һ��Sheet��
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
		System.out.println("**��ȡ���ñ���������Ƴɹ�");
		return config_ids;
	}
	
	public static String[][][] inputMemory(Workbook readwb, int readSheet) {
		// ���ص��ڴ���
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
		System.out.println("**�ѱ������д���ڴ�ɹ�");
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
//			System.out.println(id + name + "����δ�ҵ�");
//		}
		return curRow;
	}
	
	public static String changeName(String[][] configname, String ss){
		for(int i = 0 ; i < configname.length; i++){
			for(int j = 0; j < configname[i].length; j++){
				if(ss.equalsIgnoreCase(configname[i][j])){
					//System.out.println(ss+ "����:" + configname[i][j]);
					return configname[i][0];
				}
			}
		}
		//System.out.println(ss+ "δ����");
		return ss;
	}
	
	public static void outXls(Workbook readwb,String[][][] table,String[][] writetable, int readSheet) throws RowsExceededException, WriteException {
		// �����Ѿ�������Excel������,�����µĿ�д���Excel������
		jxl.write.WritableWorkbook wwb;
		try {
			wwb = Workbook.createWorkbook(new File(
					NewPath), readwb);
			//��ȡ
			jxl.write.WritableSheet ws = wwb.getSheet(readSheet);
			
			//д��
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
			
			System.out.println("**ȫ���������������");
			// д��Excel����
			wwb.write();
			wwb.close();
			System.out.println("**�Ѿ������µı����ݣ��򿪿����ɣ�");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

}
