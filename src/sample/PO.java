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

public class PO implements Initializable {


    public TableView<PO_O> PO_TABLE;
    public TableColumn<PO_O,Integer> PONR_C;
    public TableColumn<PO_O,String> PODATE_C;
    public TableColumn<PO_O,Integer> POSUPNR_C;


    public Button INSERTBT;
    public Button UPDATEBT;
    public Button DELETEBT;

    public TableView<SUPPLIER_O> SUPPLIER_TABLE;
    public TableColumn<SUPPLIER_O,Integer> SUPNR_C;
    public TableColumn<SUPPLIER_O,String> SUPNAME_C;
    public TableColumn<SUPPLIER_O,String> SUPADD_C;
    public TableColumn<SUPPLIER_O,String> SUPCITY_C;
    public TableColumn<SUPPLIER_O,String> SUPSTATUS_C;
    public TextField PONR_T;
    public TextField PODATE_T;

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
            show_pos();
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

    public ObservableList<PO_O> getPOlist() throws SQLException, ClassNotFoundException {
        ObservableList<PO_O> polist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String querry = "SELECT * FROM PURCHASE_ORDER";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(querry);
            PO_O po_o;
            while (rs.next()) {
                po_o = new PO_O(
                        rs.getInt("PONR"),
                        rs.getString("PODATE"),
                        rs.getInt("SUPNR")
                );
                polist.add(po_o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return polist;
    }

    public void show_pos() throws SQLException, ClassNotFoundException {
        ObservableList<PO_O> list = getPOlist();
        PONR_C.setCellValueFactory(new PropertyValueFactory<PO_O,Integer>("PONR"));
        PODATE_C.setCellValueFactory(new PropertyValueFactory<PO_O,String>("PODATE"));
        POSUPNR_C.setCellValueFactory(new PropertyValueFactory<PO_O,Integer>("SUPNR"));
        PO_TABLE.setItems(list);
    }

    public void insertRecord() throws SQLException, ClassNotFoundException {
        SUPPLIER_O supplier = SUPPLIER_TABLE.getSelectionModel().getSelectedItem();
        String querry = "INSERT INTO PURCHASE_ORDER VALUES ("+
                PONR_T.getText() + ",'" + PODATE_T.getText() +"'," + supplier.getSUPNR() + ")";
        System.out.println(querry);
        executeQuerry(querry);
    }

    public void updateRecord() throws SQLException, ClassNotFoundException {
        SUPPLIER_O supplier = SUPPLIER_TABLE.getSelectionModel().getSelectedItem();
        String querry = "UPDATE PURCHASE_ORDER SET "  +
                "PODATE = '" + PODATE_T.getText().strip()    +
                "',SUPNR = "    + supplier.getSUPNR() +
                " WHERE PONR = "     + PONR_T.getText();
        System.out.println(querry);
        executeQuerry(querry);

    }

    public void deleteRecord() throws SQLException, ClassNotFoundException {
        String querry = "DELETE FROM PURCHASE_ORDER WHERE PONR = " + PONR_T.getText();
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
        show_suppliers();
        show_pos();
    }

    public void MOUSECLICKED(MouseEvent mouseEvent) {
        PO_O po_o = PO_TABLE.getSelectionModel().getSelectedItem();
        PONR_T.setText(""+po_o.getPONR());
        PODATE_T.setText(po_o.getPODATE());
    }
}
