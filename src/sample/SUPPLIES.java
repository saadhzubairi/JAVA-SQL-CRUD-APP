package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class SUPPLIES implements Initializable {
    public TableView<SUPPLIER_O> SUPPLIER_TABLE;
    public TableColumn<SUPPLIER_O,Integer> SUPNR_C;
    public TableColumn<SUPPLIER_O,String> SUPNAME_C;
    public TableColumn<SUPPLIER_O,String> SUPADD_C;
    public TableColumn<SUPPLIER_O,String> SUPCITY_C;
    public TableColumn<SUPPLIER_O,String> SUPSTATUS_C;

    public TableView<PRODUCT_O> PRODUCT_TABLE;

    public TableColumn<PRODUCT_O,Integer> SUPNR_C1;
    public TableColumn<PRODUCT_O, String> SUPNAME_C1;
    public TableColumn<PRODUCT_O, String> SUPADD_C1;
    public TableColumn<PRODUCT_O, Integer> SUPCITY_C1;


    public TableView<SUPPLIES_O> SUPPLIES_TABLE;

    public TableColumn<SUPPLIER_O,Integer> SUPNR_S;
    public TableColumn<SUPPLIER_O,Integer> PRODNR_S;
    public TableColumn<SUPPLIER_O,Double> PUR_S;
    public TableColumn<SUPPLIER_O, String> DEL_S;

    public TextField PURCH_T;
    public TextField DELV_T;

    public Button INSERTBT;
    public Button UPDATEBT;
    public Button DELETEBT;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            show_suppliers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            show_products();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            show_supplies();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        INSERTBT.setOnAction(actionEvent -> {
            try {
                insertRecord();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        UPDATEBT.setOnAction(actionEvent -> {
            try {
                updateRecord();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        DELETEBT.setOnAction(actionEvent -> {
            try {
                deleteRecord();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","OracleUser");
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

    public ObservableList<PRODUCT_O> getProductList() throws SQLException, ClassNotFoundException {
        ObservableList<PRODUCT_O> product_oObservableList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String querry = "SELECT * FROM PRODUCT";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            PRODUCT_O product_o;
            while (rs.next()){
                product_o = new PRODUCT_O(
                        rs.getInt("PRODNR"),
                        rs.getString("PRODNAME"),
                        rs.getString("PRODTYPE"),
                        rs.getInt("AVAILABLE_QUANTITY")
                );
                product_oObservableList.add(product_o);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return product_oObservableList;
    }

    public void show_products() throws SQLException, ClassNotFoundException {
        ObservableList<PRODUCT_O> list = getProductList();
        SUPNR_C1.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,Integer>("PRODNR"));
        SUPNAME_C1.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,String>("PRODNAME"));
        SUPADD_C1.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,String>("PRODTYPEAVAILABLE"));
        SUPCITY_C1.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,Integer>("QUANTITY"));
        PRODUCT_TABLE.setItems(list);
    }

    public ObservableList<SUPPLIES_O> getSupplieslist() throws SQLException, ClassNotFoundException {
        ObservableList<SUPPLIES_O> supplies_os = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String querry = "SELECT * FROM SUPPLIES";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            SUPPLIES_O supplies_o;
            System.out.println("executing this");
            while (rs.next()){
                supplies_o = new SUPPLIES_O(
                        rs.getInt("SUPNR"),
                        rs.getInt("PRODNR"),
                        rs.getDouble("PURCHASE_PRICE"),
                        rs.getString("DELIV_PERIOD")
                );
                System.out.println("success");
                supplies_os.add(supplies_o);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return supplies_os;
    }

    public void show_supplies() throws SQLException, ClassNotFoundException {
        ObservableList<SUPPLIES_O> list = getSupplieslist();
        SUPNR_S.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,Integer>("SUPNR"));
        PRODNR_S.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,Integer>("PRODNR"));
        PUR_S.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,Double>("PURCHASE_PRICE"));
        DEL_S.setCellValueFactory(new PropertyValueFactory<SUPPLIER_O,String>("DELIV_PERIOD"));
        SUPPLIES_TABLE.setItems(list);
    }

    public void insertRecord() throws SQLException, ClassNotFoundException {
        SUPPLIER_O supplierO = SUPPLIER_TABLE.getSelectionModel().getSelectedItem();
        PRODUCT_O product_o = PRODUCT_TABLE.getSelectionModel().getSelectedItem();
        String querry = "INSERT INTO SUPPLIES VALUES (" +
                        supplierO.getSUPNR() + "," +
                        product_o.getPRODNR() + "," +
                        PURCH_T.getText() + ",'" +
                        DELV_T.getText() + "')";
        System.out.println(querry);
        executeQuerry(querry);
    }

    public void updateRecord() throws SQLException, ClassNotFoundException {
        SUPPLIES_O supplieso = SUPPLIES_TABLE.getSelectionModel().getSelectedItem();
        String querry = "UPDATE SUPPLIES SET "  +
                "PURCHASE_PRICE = " +  PURCH_T.getText() + "," +
                "DELIV_PERIOD = '" + DELV_T.getText() +
                "' WHERE SUPNR = " + supplieso.getSUPNR() + " and PRODNR = " + supplieso.getPRODNR();
        System.out.println(querry);
        executeQuerry(querry);

    }

    public void deleteRecord() throws SQLException, ClassNotFoundException {
        SUPPLIES_O supplieso = SUPPLIES_TABLE.getSelectionModel().getSelectedItem();
        String querry = "DELETE FROM SUPPLIES WHERE SUPNR = " + supplieso.getSUPNR() + " and PRODNR = " + supplieso.getPRODNR();
        System.out.println(querry);
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
        show_supplies();
    }

    public void handleMouseAction(MouseEvent mouseEvent) { }
    public void handleMouseAction_PRO(MouseEvent mouseEvent) { }
    public void handleMouseAction_SUP(MouseEvent mouseEvent) { }
}
