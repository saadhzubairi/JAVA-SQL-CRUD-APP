package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SUPPLIER implements Initializable {

    @FXML public Button INSERTBT, UPDATEBT, DELETEBT;
    @FXML public TextField SUPNR_T, SUPNAME_T, SUPADD_T, SUPCITY_T, SUPSTAT_T;

    public TableView<SUPPLIER_O> SUPPLIER_TABLE;
    public TableColumn<SUPPLIER_O,Integer> SUPNR_C;
    public TableColumn<SUPPLIER_O,String> SUPNAME_C;
    public TableColumn<SUPPLIER_O,String> SUPADD_C;
    public TableColumn<SUPPLIER_O,String> SUPCITY_C;
    public TableColumn<SUPPLIER_O,String> SUPSTATUS_C;

    public SUPPLIER(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //poppulating the table
        try {
            show_suppliers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        INSERTBT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    insertRecord();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    show_suppliers();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        UPDATEBT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    updateRecord();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    show_suppliers();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        DELETEBT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    deleteRecord();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    show_suppliers();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","OracleUser");
        if(conn==null){
            System.out.println("unable to connect to database");
            return null;
        }
        return conn;
    }



    public ObservableList<SUPPLIER_O> getSupplierList() throws SQLException, ClassNotFoundException {
        ObservableList<SUPPLIER_O> supplierOList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String querry = "SELECT * FROM SUPPLIER";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            SUPPLIER_O supplierO;
            while (rs.next()){
                supplierO = new SUPPLIER_O(
                        rs.getInt("SUPNR"),
                        rs.getString("SUPNAME"),
                        rs.getString("SUPADDRESS"),
                        rs.getString("SUPCITY"),
                        rs.getString("SUPSTATUS")
                );
                supplierOList.add(supplierO);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return supplierOList;
    }

    public void show_suppliers() throws SQLException, ClassNotFoundException {
        ObservableList<SUPPLIER_O> list = getSupplierList();
        SUPNR_C.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,Integer>("SUPNR"));
        SUPNAME_C.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,String>("SUPNAME"));
        SUPADD_C.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,String>("SUPADDRESS"));
        SUPCITY_C.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,String>("SUPCITY"));
        SUPSTATUS_C.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,String>("SUPSTATUS"));
        SUPPLIER_TABLE.setItems(list);
    }

    public void insertRecord() throws SQLException, ClassNotFoundException {
        String querry = "INSERT INTO SUPPLIER VALUES ('"
                + SUPNR_T.getText().strip() + "','"
                + SUPNAME_T.getText().strip() + "','"
                + SUPADD_T.getText().strip() + "','"
                + SUPCITY_T.getText().strip() + "','"
                + SUPSTAT_T.getText().strip() + "')";
        executeQuerry(querry);
    }

    public void updateRecord() throws SQLException, ClassNotFoundException {
        String querry = "UPDATE SUPPLIER SET "  +
                        "SUPNAME = '"    + SUPNAME_T.getText().strip()   +
                        "',SUPADDRESS = '" + SUPADD_T.getText().strip()    +
                        "',SUPCITY = '"    + SUPCITY_T.getText().strip()   +
                        "',SUPSTATUS = '"  + SUPSTAT_T.getText().strip()   +
                        "' WHERE SUPNR = "     + SUPNR_T.getText();
        executeQuerry(querry);

    }

    public void deleteRecord() throws SQLException, ClassNotFoundException {
        String querry = "DELETE FROM SUPPLIER WHERE SUPNR = " + SUPNR_T.getText();
        executeQuerry(querry);
    }

    private void executeQuerry(String querry) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(querry);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        show_suppliers();
    }

    @FXML public void handleMouseAction(MouseEvent mouseEvent) {
        SUPPLIER_O supplierO = SUPPLIER_TABLE.getSelectionModel().getSelectedItem();
        SUPNR_T.setText(""+ supplierO.getSUPNR());
        SUPNAME_T.setText(supplierO.getSUPNAME());
        SUPSTAT_T.setText(supplierO.getSUPSTATUS());
        SUPCITY_T.setText(supplierO.getSUPCITY());
        SUPADD_T.setText(supplierO.getSUPADDRESS());
    }
}