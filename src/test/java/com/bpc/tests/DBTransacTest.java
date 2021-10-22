package test.java.com.bpc.tests;

import com.bpc.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DBTransacTest {

    @Mock
    private DBManager dbManager;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Statement statement;

    private Employee employee;
    private Enfant enfant;

    private GraphicalUserInterface guiEmployeeTest;
    private GraphicalUserInterface guiEnfantTest;

    private final String INSEE_TEST_EMP = "13";


    @Before
    public void setUp() throws Exception{

        assertNotNull(dbManager);
        when(dbManager.getConnection()).thenReturn(connection);

        guiEmployeeTest = new GraphicalUserInterface();
        guiEmployeeTest.lancer();

        guiEmployeeTest.getInseeTexte().setText(INSEE_TEST_EMP);
        guiEmployeeTest.getNom().setText("TEST");
        guiEmployeeTest.getPrenom().setText("test");
        guiEmployeeTest.getAdresse().setText("address");
        guiEmployeeTest.getGrade().setText("grade");
        guiEmployeeTest.getResponsable().setText("responsable");
        guiEmployeeTest.getMoisNaiss().setSelectedItem("mars");
        guiEmployeeTest.getMoisEmb().setSelectedItem("mars");
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

    private void ajouterSetup() throws SQLException {

        when(connection.prepareStatement("insert into employe (insee,nom, prenom, adresse, grade, responsable, date_naissance, date_embauche) values(?,?,?,?,?,?,?,?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

    }

    @Test
    
    public void ajouterEmploye() throws SQLException {

        ajouterSetup();
        DBTransac dbTransac = new DBTransac(dbManager);
        dbTransac.ajouterEmploye(guiEmployeeTest);
        assertTrue(dbTransac.isSuccess());
    }

    @Test
    public void modifierEmploye() throws SQLException {

        when(connection.prepareStatement("update employe set nom=?,prenom=?,adresse=?,"
                + "grade=?,responsable=?,date_naissance=?,date_embauche=?"
                + "where insee=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        DBTransac dbtransac = new DBTransac(dbManager);
        dbtransac.modifierEmploye(guiEmployeeTest);
        assertTrue(dbtransac.isSuccess());
    }

    @Test
    public void ajouterEnfant() throws SQLException {

        when(connection.prepareStatement("insert into enfant values(?,?,?,?,?,?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.createStatement()).thenReturn(statement);

        // When inseePere exists
        when(resultSet.next()).thenReturn(true);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        DBTransac dbTransac = new DBTransac(dbManager);
        dbTransac.ajouterEnfant(guiEnfantTest);
        assertTrue(dbTransac.isSuccess());

        //When inseePere does not exist
        when(resultSet.next()).thenReturn(false);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        dbTransac = new DBTransac(dbManager);
        dbTransac.ajouterEnfant(guiEnfantTest);
        assertFalse(dbTransac.isSuccess());

    }

    @Test
    public void modifierEnfant() throws SQLException {
        when(connection.prepareStatement("update enfant set nom=?,prenom=?,date_naissance=?,hobby=?"
                + "where insee=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.createStatement()).thenReturn(statement);

        //When Pere Insee exists
        when(resultSet.next()).thenReturn(true);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        DBTransac dbTransac = new DBTransac(dbManager);
        dbTransac.modifierEnfant(guiEnfantTest);
        assertTrue(dbTransac.isSuccess());

        //when Pere Insee does not exist
        when(resultSet.next()).thenReturn(false);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        dbTransac = new DBTransac(dbManager);
        dbTransac.modifierEnfant(guiEnfantTest);
        assertFalse(dbTransac.isSuccess());
    }

    @Test
    public void rechInseeEmp() {
    }

    @Test
    public void afficherAvantModifEmploye() throws SQLException {

        when(connection.createStatement()).thenReturn(statement);

        //when the employee exists
        when(resultSet.next()).thenReturn(true);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        DBTransac dbTransac = new DBTransac(dbManager);
        dbTransac.afficherAvantModifEmploye(guiEmployeeTest);
        assertTrue(dbTransac.isSuccess());

        //When the employee doesn't exist
        when(resultSet.next()).thenReturn(false);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        dbTransac = new DBTransac(dbManager);
        dbTransac.afficherAvantModifEmploye(guiEmployeeTest);
        assertFalse(dbTransac.isSuccess());


    }
}