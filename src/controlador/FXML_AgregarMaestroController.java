/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.MaestroVO;


public class FXML_AgregarMaestroController implements Initializable {

    @FXML
    private TextField nombreMaestro;

    @FXML
    private TextField contraMaestro;

    @FXML
    private TextField matriculaMaestro;

    @FXML
    private Button agregarMaestro;

    private MaestroVO maestro;
    
    @FXML
    
    private boolean esEdicion;
    
    private Stage stageDialogoEdicion;
    
    public Button getBotonAgregar(){
        return this.agregarMaestro;
    }
    
    public void setStageDialogo (Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }
    
    public boolean getEsEdicion (){
        return esEdicion;
    }

    @FXML
    void cancelar(ActionEvent event) {
        stageDialogoEdicion.close();
    }
    
    public void setMaestro(MaestroVO maestro){
        if(maestro != null){
            this.maestro = maestro;
            this.matriculaMaestro.setText(maestro.getMatricula());
            this.nombreMaestro.setText(maestro.getNombre());
            this.contraMaestro.setText(maestro.getContra());
        }
    }

    @FXML
    void agregar(ActionEvent event) {
        this.maestro.setMatricula(this.matriculaMaestro.getText());
        this.maestro.setNombre(this.nombreMaestro.getText());
        this.maestro.setContra(this.contraMaestro.getText());
        this.esEdicion = true;
        this.stageDialogoEdicion.close();
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.esEdicion = false;
    }   
    
}