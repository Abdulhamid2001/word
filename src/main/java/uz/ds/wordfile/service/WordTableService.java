package uz.ds.wordfile.service;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;
import uz.ds.wordfile.payload.ApiResponse;

import java.io.*;
import java.util.Arrays;

@Service
public class WordTableService {
    public ApiResponse createTableWord() throws IOException {
        //Blank Document
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("d:/wordT.docx"));

        //Write the Document in file system


//        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //create table
        XWPFTable table = document.createTable();

        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("col one, row one");
        tableRowOne.addNewTableCell().setText("col two, row one");
        tableRowOne.addNewTableCell().setText("col three, row one");

        //create second row
        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("col one, row two");
        tableRowTwo.getCell(1).setText("col two, row two");
        tableRowTwo.getCell(2).setText("col three, row two");

        //create third row
        XWPFTableRow tableRowThree = table.createRow();
        tableRowThree.getCell(0).setText("col one, row three");
        tableRowThree.getCell(1).setText("col two, row three");
        tableRowThree.getCell(2).setText("col three, row three");

        document.write(out);
        out.close();
        File file = new File("d:/wordT.docx");
        byte[] bytes=new byte[(int) file.length()];
        DataInputStream dataInputStream=new DataInputStream(new BufferedInputStream(new FileInputStream("d:/wordT.docx")));
        dataInputStream.readFully(bytes);
//        return out;
        return new ApiResponse("Word Created in D-disk called wordT.docx!! bytes of your file:"+ Arrays.toString(bytes), true);
    }
}
