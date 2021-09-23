package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class PRODUCT implements Initializable {

    public Button INSERTBT;
    public Button UPDATEBT;
    public Button DELETEBT;
    public TextField PRODNR_T;
    public TextField PRODNAME_T;
    public TextField PRODTYPE_T;
    public TextField QUANTITY_T;
    public TableView<PRODUCT_O> PRODUCT_TABLE;
    public TableColumn<PRODUCT_O, Integer> SUPNR_C;
    public TableColumn<PRODUCT_O, String> SUPNAME_C;
    public TableColumn<PRODUCT_O, String> SUPADD_C;
    public TableColumn<PRODUCT_O, Integer> SUPCITY_C;

    public PRODUCT(){ }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            show_products();
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
        SUPNR_C.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,Integer>("PRODNR"));
        SUPNAME_C.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,String>("PRODNAME"));
        SUPADD_C.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,String>("PRODTYPEAVAILABLE"));
        SUPCITY_C.setCellValueFactory(new PropertyValueFactory<PRODUCT_O,Integer>("QUANTITY"));
        PRODUCT_TABLE.setItems(list);
    }

    public void insertRecord() throws SQLException, ClassNotFoundException {
        String querry = "INSERT INTO PRODUCT VALUES ("
                + PRODNR_T.getText().strip() + ",'"
                + PRODNAME_T.getText().strip() + "','"
                + PRODTYPE_T.getText().strip() + "',"
                + QUANTITY_T.getText().strip()
                + ")";
        executeQuerry(querry);
    }

    public void updateRecord() throws SQLException, ClassNotFoundException {
        String querry =     "UPDATE PRODUCT SET "  +
                            "PRODNAME = '"    + PRODNAME_T.getText().strip()   +
                            "',PRODTYPE = '" + PRODTYPE_T.getText().strip()    +
                            "',AVAILABLE_QUANTITY = " + QUANTITY_T.getText().strip()   +
                            " WHERE PRODNR = "     + PRODNR_T.getText();
        executeQuerry(querry);

    }

    public void deleteRecord() throws SQLException, ClassNotFoundException {
        String querry = "DELETE FROM PRODUCT WHERE PRODNR = " + PRODNR_T.getText();
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
        show_products();
    }

    public void handleMouseAction(MouseEvent mouseEvent) {

    }
}

