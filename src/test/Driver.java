package test;

import java.lang.reflect.Method;

import utitlities.ExcelHelper;
import utitlities.GenericHelper;

public class Driver {

	public static void main(String[] args) {
		ExcelHelper tcExcel = new ExcelHelper();
		tcExcel.openExcel("", GenericHelper.readProperty("tcdoc"), GenericHelper.readProperty("casesheet"));

		ExcelHelper tsExcel = new ExcelHelper();
		tsExcel.openExcel("", GenericHelper.readProperty("tsdoc"), GenericHelper.readProperty("stepsheet"));

		Keywords keywords = new Keywords();
		Method[] methods = keywords.getClass().getMethods();

		int nor = tcExcel.rowCount();
		for (int i = 1; i < nor; i++) {
			String tcd_tcName = tcExcel.readData(i, ColumnNumbers.TCD_TCNAME);
			String runMode = tcExcel.readData(i, ColumnNumbers.RUNMODE);
			if (runMode.equals("yes")) {
				for (int j = 1; j < tsExcel.rowCount(); j++) {
					String tsd_tcname = tsExcel.readData(j, ColumnNumbers.TSD_TCNAME);
					if (tcd_tcName.equals(tsd_tcname)) {
						String tsname = tsExcel.readData(j, ColumnNumbers.TSNAME);
						String locType = tsExcel.readData(j, ColumnNumbers.LOCTYPE);
						String locValue = tsExcel.readData(j, ColumnNumbers.LOCVALUE);
						String data = tsExcel.readData(j, ColumnNumbers.DATA);
						String keyword = tsExcel.readData(j, ColumnNumbers.KEYWORDS);
						for(Method method : methods) {
							if(method.getName().equals(keyword)) {
								try {
									method.invoke(keywords, locType, locValue, data);
									break;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}

	}

}
