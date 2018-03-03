package com.incedoinc.file.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.incedoinc.dao.PostgreSQLJSONDAO;

public class ExcelFileReaderJSON {
	
	
	
	public ExcelFileReaderJSON() {
		
	}

	public String  processFileRead(final InputStream inputStream) {
		try {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			final String json = createPatientVisit(datatypeSheet);
			workbook.close();
			return json;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String createPatientVisit(Sheet datatypeSheet) {

		Iterator<Row> iterator = datatypeSheet.iterator();
		final PostgreSQLJSONDAO insertDao = new PostgreSQLJSONDAO();
		List<String> columnString = new ArrayList<String>();
		
		if (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				columnString.add(currentCell.getStringCellValue());
			}
		}
		StringBuilder json = new StringBuilder();
		json.append("[");
		while (iterator.hasNext()) {
			StringBuilder column = new StringBuilder();
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			int i = 0;
			column.append("{");
			while (cellIterator.hasNext() && i < columnString.size()) {
				final String s = columnString.get(i);
				Cell currentCell = cellIterator.next();
				if (currentCell.getCellTypeEnum() == CellType.STRING) {
					column.append("\"" + s + "\": " + "\"" + currentCell.getStringCellValue() + "\"");
				} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
					column.append("\"" + s + "\": " + "\"" + currentCell.getNumericCellValue() + "\"");
				}
				i++;
				if (columnString.size() != i) {
					column.append(",");
				}
			}
			column.append("}");
			insertDao.insert(column.toString());
			if(iterator.hasNext())
				json.append(column.toString()+",");
			else
				json.append(column.toString());
			
		}
		json.append("]");

		return json.toString();
	}
}