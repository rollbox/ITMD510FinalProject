package iit.controllers;

import iit.models.Cust;
import iit.models.Group;
import iit.service.IitCustService;
import iit.utils.ExcelUtil;
import iit.utils.IitUtil;
import iit.utils.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.*;

public class IitGroupAddController implements Initializable {
    @FXML
    private TableView<Group>   groupTable;

    @FXML
    private TextField groupId;
    @FXML
    private TextField groupName;
    @FXML
    private TextArea groupDesc;


    //Service对象，从DAO工厂通过静态方法获得
    private IitCustService iitCustService = ServiceFactory.getIitCustServiceInstance();



    //初始化方法，通过调用对表格和列表下拉框的两个封装方法，实现数据初始化
    public void initialize(URL location, ResourceBundle resources) {

    }





    public void   createCustomerGroup() throws Exception {
        Stage addStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitGroup_add.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/style.css");
        IitEventAddController iitEventAddController = fxmlLoader.getController();
        addStage.setTitle("CREATE NEW CUST GROUP");
        //界面大小不可变
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.show();

    }

    //数据导出方法，采用hutool提供的工具类
    public void export() throws Exception {
        Stage mRecordDetailStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("LaundryService");
        fileChooser.setInitialFileName("TEMPLATE.xls");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xls Files", "*.xls"));
        File file = fileChooser.showSaveDialog(mRecordDetailStage);
        if (file != null) {
            exportExcel(file.getAbsolutePath());
        }
    }
        private void exportExcel(String fileName) {
            try { // Declare a work sheet
            HSSFWorkbook workbook = new HSSFWorkbook();
            // Generate a form
            HSSFSheet sheet = workbook.createSheet("TEMPLATE");
            // Set the table for 15 byte default column width
            sheet.setDefaultColumnWidth((short) 15);
            // Create a style
            HSSFCellStyle style = workbook.createCellStyle();
            // The style settings
            // Create a font
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            // The font applied to the current style
            style.setFont(font);
            // Create first row
            HSSFRow row = sheet.createRow(0);
            //Create first column cell
            HSSFCell cell1 = row.createCell(0);




                cell1.setCellValue(new HSSFRichTextString("phone"));
            //Create second column cell
            HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(new HSSFRichTextString("name"));
            //Create thrid column cell
            HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(new HSSFRichTextString("sex"));
            //Create four column cell
            HSSFCell cell4 = row.createCell(3);
            cell4.setCellValue("address");
            //Create five column cell
            HSSFCell cell5 = row.createCell(4);
            cell5.setCellValue(new HSSFRichTextString("products"));

                HSSFRow datarow = sheet.createRow(1);
                HSSFCell datacell1 = datarow.createCell(0);

                datacell1.setCellValue("123456789");
                //Create second column cell
                HSSFCell datacell2 = datarow.createCell(1);
                datacell2.setCellValue("Dear Papa");
                //Create thrid column cell
                HSSFCell datacell3 = datarow.createCell(2);
                datacell3.setCellValue("male");
                //Create four column cell
                HSSFCell datacell4 = datarow.createCell(3);
                datacell4.setCellValue("Chicago");
                //Create five column cell
                HSSFCell datacell5 = datarow.createCell(4);
                datacell5.setCellValue("$31 marketing product");

            FileOutputStream fos  = new FileOutputStream(new File(fileName));
                workbook.write(fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public void importCustomerPhones(ActionEvent actionEvent) throws Exception {
        String groupId="";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择Excel文件");
        Stage selectFile = new Stage();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Excel", "*.xls"),
                new FileChooser.ExtensionFilter("XLS", "*.xls"), new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
        File file = fileChooser.showOpenDialog(selectFile);
        if (file != null) {
            try {
                List<Cust> custList = ExcelUtil.readXls( file);
                  groupId=    iitCustService.saveCustList(custList);
                  this.groupId.setText(groupId);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

//            product.setProductId(groupId);
//        groupData.add(product);
//        showProductPane();
    }


    public void addGroup(ActionEvent actionEvent) throws Exception {
        String id = groupId.getText();
        String name = groupName.getText();
        String desc=groupDesc.getText();
        if(IitUtil.isEmpty(id))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("system message");
        alert.setHeaderText("Please import customer data !");
        alert.showAndWait();
        }
        else if(IitUtil.isEmpty(name))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("Please enter the customer group name !");
            alert.showAndWait();
        }
        else {

            Group group = new Group();
            group.setGroupId(id);
            group.setGroupName(name);
            group.setGroupDesc(desc);

            iitCustService.addGroup(group);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("system message");
            alert.setHeaderText("Customer group added successfully!");
            alert.showAndWait();


//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitGroup.fxml"));
//            AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/views/iitGroup.fxml")).load();
//            Stage mainStage= (Stage) groupId.getScene().getWindow();
//            mainStage.setScene(anchorPane.getScene());
              Stage stage = (Stage) groupId.getScene().getWindow();
              stage.close();

//            Stage addStage = (Stage) groupId.getScene().getWindow();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/iitGroup.fxml"));
//            AnchorPane root = fxmlLoader.load();
//            Scene scene = new Scene(root);
//            scene.getStylesheets().add("/css/style.css");
////        IitEventAddController iitEventAddController = fxmlLoader.getController();
//            addStage.setTitle("CREATE NEW CUST GROUP");
//            addStage.setScene(scene);
//            addStage.show();
        }
    }
}
