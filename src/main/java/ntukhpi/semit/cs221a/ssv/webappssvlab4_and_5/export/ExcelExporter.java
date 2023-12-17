package ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.export;

import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Address;
import ntukhpi.semit.cs221a.ssv.webappssvlab4_and_5.entity.Client;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

public class ExcelExporter {

    public static Optional<ByteArrayOutputStream> createXLSXFileFromData(List<Client> clientList, List<Address> addressList){
        try{
            Workbook workbook = new XSSFWorkbook();
            createClientSheet(workbook, clientList);
            createAddressSheet(workbook, addressList);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return Optional.of(byteArrayOutputStream);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private static void createClientSheet(Workbook workbook, List<Client> clients) {
        Sheet sheet = workbook.createSheet("Clients");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("First Name");
        headerRow.createCell(2).setCellValue("Second Name");
        headerRow.createCell(3).setCellValue("Phone");
        headerRow.createCell(4).setCellValue("Email");
        headerRow.createCell(5).setCellValue("Gender");
        headerRow.createCell(6).setCellValue("VIP");

        int rowNum = 1;
        for (Client client : clients) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(client.getId());
            row.createCell(1).setCellValue(client.getFirstName());
            row.createCell(2).setCellValue(client.getSecondName());
            row.createCell(3).setCellValue(client.getPhone());
            row.createCell(4).setCellValue(client.getEmail());
            row.createCell(5).setCellValue(client.getGender().name());
            row.createCell(6).setCellValue(client.getIsVIP());
        }
    }

    private static void createAddressSheet(Workbook workbook, List<Address> addresses) {
        Sheet sheet = workbook.createSheet("Addresses");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Cod Address");
        headerRow.createCell(2).setCellValue("Client Email");
        headerRow.createCell(3).setCellValue("Region");
        headerRow.createCell(4).setCellValue("City");
        headerRow.createCell(5).setCellValue("Address");
        headerRow.createCell(6).setCellValue("Point NP");
        headerRow.createCell(7).setCellValue("Point UkrPost");

        int rowNum = 1;
        for (Address address : addresses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(address.getId());
            row.createCell(1).setCellValue(address.getCodAddress());
            row.createCell(2).setCellValue(address.getClient().getEmail());
            row.createCell(3).setCellValue(address.getRegion());
            row.createCell(4).setCellValue(address.getCity());

            String strAddr = address.getAddressStr();
            Integer pointNP = address.getPointNP();
            Integer pointUkr = address.getPointUkrPost();

            if(strAddr != null){
                row.createCell(5).setCellValue(strAddr);
            }

            if(pointNP != null){
                row.createCell(6).setCellValue(pointNP);
            }

            if(pointUkr != null){
                row.createCell(7).setCellValue(pointUkr);
            }
        }
    }
}
