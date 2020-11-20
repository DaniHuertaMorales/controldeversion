/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.MaestroVO;
import modelo.Maestro_DAO_Imp;


public class FXML_GestionMaestroController implements Initializable {

    @FXML
    private Button agregarMaestro;

    @FXML
    private Button eliminarMaestro;

    @FXML
    private Button actualizarMaestro;
    
    @FXML
    private TableView<MaestroVO> tablaMaestros;

    @FXML
    private TableColumn<MaestroVO, String > columnaMatricula;

    @FXML
    private TableColumn<MaestroVO, String> columnaNombre;

    @FXML
    private TableColumn<MaestroVO, String> columnaContra;
    
    @FXML
    private Button botonVolver;
    
    private ObservableList<MaestroVO> listaMaestros;
    
    private Stage stageLogin;
    
    private FXML_AgregarMaestroController controlador;
     
    private Maestro_DAO_Imp implementacionDAO;
    
    private Stage stagePrincipal;
    
    private Stage stageDialogoEdicion;
    
    private boolean esEdicion;
     
    public void setControlador (FXML_AgregarMaestroController controlador){
        this.controlador = controlador;
    }
    
    public void setStageDialogo (Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }
    
    public boolean getEsEdicion (){
        return esEdicion;
    }
    
     public void setStageLogin (Stage stageLogin){
        this.stageLogin= stageLogin;
    }
     
     
    /*@FXML
    void volver(ActionEvent event) {
        Stage stage = (Stage) this.botonVolver.getScene().getWindow();
        stage.close();
        this.cambiarVentana1();
    }*/
    
    public void obtenerMaestros(){
        List listaConsulta = null;
        try{
            listaConsulta = implementacionDAO.readAll();
        } catch (Exception e){
            System.out.println("Error en consulta");
        }
        
        Iterator it = listaConsulta.iterator();
        listaMaestros.clear();
        while(it.hasNext()){
            listaMaestros.add((MaestroVO)it.next());
        }
    }
    
    public void colocarMaestrosTabla(){
        this.obtenerMaestros();
        this.columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaContra.setCellValueFactory(new PropertyValueFactory<>("contra"));
        this.tablaMaestros.setItems(listaMaestros);
    }
    
     
   /* public void cambiarVentana1 (){        
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/FXML_Principal.fxml"));
            Parent dialogoEditar = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Ventana Principal");
            dialogoStage.initModality(Modality.NONE);
            dialogoStage.initOwner(stageLogin);
            Scene scene = new Scene(dialogoEditar);
            dialogoStage.setScene(scene);
            dialogoStage.showAndWait();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/

    public boolean mostrarDialogoEditar(MaestroVO maestro){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/FXML_AgregarMaestro.fxml"));
            Parent dialogoEditar = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Agregar Maestro");
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(stagePrincipal);
            Scene scene = new Scene(dialogoEditar);
            dialogoStage.setScene(scene);
            
            FXML_AgregarMaestroController controlador = loader.getController();
            controlador.setStageDialogo(dialogoStage);
            this.setControlador(controlador);
            controlador.setMaestro(maestro);
            dialogoStage.showAndWait();
           return controlador.getEsEdicion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    void agregar(ActionEvent event) {
        MaestroVO maestro = new MaestroVO();
        boolean esEdicion = this.mostrarDialogoEditar(maestro);
        if (esEdicion){
            try{
                this.implementacionDAO.create(maestro);
            } catch (Exception e){
                System.out.print("Fallo agregar el maestro");
                e.printStackTrace();
            }
            this.colocarMaestrosTabla();
            this.tablaMaestros.getSelectionModel().selectLast();
        }      
    }
  
    @FXML
    void actualizar(ActionEvent event) {
        MaestroVO maestroSeleccionado = this.tablaMaestros.getSelectionModel().getSelectedItem();
        if(maestroSeleccionado != null){
            boolean esEdicion = this.mostrarDialogoEditar(maestroSeleccionado);
            if (esEdicion){
                int ultimoSeleccionado = this.tablaMaestros.getSelectionModel().getSelectedIndex();
                try{
                    //this.implementacionDAO.update(maestroSeleccionado, "Matricula");
                    this.implementacionDAO.update(maestroSeleccionado, "Nombre");
                    this.implementacionDAO.update(maestroSeleccionado, "Contra");
                }catch (Exception e){
                    System.out.print("Error al editar maestro");
                    e.printStackTrace();
                }
                this.colocarMaestrosTabla();
                this.tablaMaestros.getSelectionModel().select(maestroSeleccionado);
            }
            
        }else{
            System.out.print("No hay maestro seleccionado");
        }
        //this.cambiarVentana2();
    }
        
    @FXML
    void eliminar(ActionEvent event) {
        int selectedIndex = this.tablaMaestros.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            MaestroVO maestro = this.tablaMaestros.getSelectionModel().getSelectedItem();
            this.tablaMaestros.getSelectionModel().selectLast();
            try{
                this.implementacionDAO.delete(maestro);
            }catch (Exception e){
                System.out.println("Error en eliminar Maestro");
            }
            this.colocarMaestrosTabla();
            if(selectedIndex != 0){
                selectedIndex --;
                this.tablaMaestros.getSelectionModel().select(selectedIndex);
            }
        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.implementacionDAO = new Maestro_DAO_Imp();
        this.listaMaestros = FXCollections.observableArrayList();
        this.colocarMaestrosTabla();
        this.esEdicion = false;
    }    
    
}
