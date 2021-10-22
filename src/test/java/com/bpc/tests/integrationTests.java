package test.java.com.bpc.tests;

import com.bpc.DBManager;
import com.bpc.DBTransac;
import com.bpc.Employee;
import com.bpc.GraphicalUserInterface;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class integrationTests {

    private GraphicalUserInterface guiEmployeeTest;
    private GraphicalUserInterface guiEnfantTest;
    private final String INSEE_TEST_EMP = "13";
    @Before
    public void setUp(){

        guiEmployeeTest = new GraphicalUserInterface();
        guiEmployeeTest.lancer();



        guiEmployeeTest.getInseeTexte().setText(INSEE_TEST_EMP);
        guiEmployeeTest.getNom().setText("TEST");
        guiEmployeeTest.getPrenom().setText("test");
        guiEmployeeTest.getAdresse().setText("address");
        guiEmployeeTest.getGrade().setText("grade");
        guiEmployeeTest.getResponsable().setText("responsable");
        guiEmployeeTest.getMoisNaiss().setSelectedIndex(11);
        guiEmployeeTest.getMoisEmb().setSelectedIndex(11);
        guiEmployeeTest.getjNaiss().setText("13");
        guiEmployeeTest.getjEmb().setText("15");
        guiEmployeeTest.getaNaiss().setText("2010");
        guiEmployeeTest.getaEmb().setText("2019");

        guiEnfantTest= new GraphicalUserInterface();
        guiEnfantTest.lancer();

        guiEnfantTest.getInseeTexte().setText("17");
        guiEnfantTest.getInseePereTexte().setText("12");
        guiEnfantTest.getNom().setText("TEST_ENFANT");
        guiEnfantTest.getPrenom().setText("test_enfant");
        guiEnfantTest.getjNaiss().setText("13");
        guiEnfantTest.getMoisNaiss().setSelectedItem("mars");
        guiEnfantTest.getaNaiss().setText("2018");
        guiEnfantTest.getHobby().setText("hobby");


    }

    @Test
    public void ajouterEmploye() throws SQLException {

        DBManager dbManager = new DBManager();
        DBTransac dbTransac = new DBTransac(dbManager);
        dbTransac.ajouterEmploye(guiEmployeeTest);
        assertTrue(dbTransac.isSuccess());

        Employee testEmployee = new Employee();
        Connection connection = dbManager.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select * from employe where insee = "+INSEE_TEST_EMP );

        while(rs.next()) {
            testEmployee.setInsee(rs.getInt(2));
            testEmployee.setNom(rs.getString(3));
            testEmployee.setPrenom(rs.getString(4));
            testEmployee.setAdresse(rs.getString(5));
            testEmployee.setGrade(rs.getString(6));
            testEmployee.setResponsable(rs.getString(7));
            testEmployee.setjNaiss(Integer.valueOf(rs.getString(8).split("-")[2]));
            testEmployee.setMoisNaiss(rs.getString(8).split("-")[1]);
            testEmployee.setaNaiss(Integer.valueOf(rs.getString(8).split("-")[0]));
            testEmployee.setjEmb(Integer.valueOf(rs.getString(9).split("-")[2]));
            testEmployee.setMoisEmb(rs.getString(9).split("-")[1]);
            testEmployee.setaEmb(Integer.valueOf(rs.getString(9).split("-")[0]));

        }

        deleteEmployee();
        Employee addedEmployee = getEmployeeDetailsFromUI(guiEmployeeTest);
        assertEquals(addedEmployee,testEmployee);
    }

    @Test
    public void modifierEmploye(){

    }


    /**
     * utility method to clean up. Not test.
     * @throws SQLException
     */
    private void deleteEmployee() throws SQLException {

        DBManager dbManager = new DBManager();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from employe where insee = ?");
        preparedStatement.setInt(1,Integer.valueOf(INSEE_TEST_EMP));
        preparedStatement.executeUpdate();
    }

    private Employee getEmployeeDetailsFromUI(GraphicalUserInterface G1){

        Employee employee = new Employee();
        employee.setInsee(Integer.valueOf(G1.getInseeTexte().getText()));
        employee.setNom(G1.getNom().getText());
        employee.setPrenom( G1.getPrenom().getText());
        employee.setAdresse(G1.getAdresse().getText());
        employee.setGrade(G1.getGrade().getText());
        employee.setResponsable(G1.getResponsable().getText());
        employee.setMoisNaiss(G1.getMoisNaiss().getSelectedIndex()+"");
        employee.setMoisEmb(G1.getMoisEmb().getSelectedIndex()+"");
        employee.setjNaiss(Integer.valueOf(G1.getjNaiss().getText()));
        employee.setjEmb(Integer.valueOf(G1.getjEmb().getText()));
        employee.setaNaiss(Integer.valueOf(G1.getaNaiss().getText()));
        employee.setaEmb(Integer.parseInt(G1.getaEmb().getText()));
        return employee;
    }


}
