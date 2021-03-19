/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author tomy-
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tomy-
 */
public class Concesionario {

    final static int TAM_CONCESIONARIO = 50;
    private ArrayList <Vehiculo> concesionario;
    int numVehiculos;

    public Concesionario() {
        concesionario = new ArrayList<Vehiculo>();
        
        numVehiculos = 0;
    }

    public ArrayList<Vehiculo> getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(ArrayList<Vehiculo> concesionario) {
        this.concesionario = concesionario;
    }

    public String buscaVehiculo(String matricula) {
        for (int i = 0; i < concesionario.size(); i++) {
            if (concesionario.get(i).getMatricula().equals(matricula)) {
                return concesionario.get(i).getMarca() + " " + concesionario.get(i).getMatricula() + " " + concesionario.get(i).getPrecio();
            }
        }
        return null;
    }
    /**
     
     * @param marca
     * @param matricula
     * @param numkms
     * @param fecha_mat
     * @param descripcion
     * @param precio
     * @param propietario
     * @param dni_propietario
     * @return 
     */   
    public int insertarVehiculo(String marca, String matricula, int numkms, LocalDate fecha_mat, String descripcion, int precio, String propietario, String dni_propietario) {
        if (concesionario.size() >= TAM_CONCESIONARIO) 
            return -1;
        else if (this.buscaVehiculo(matricula)!=null)
            return -2;
        else {
            concesionario.add(new Vehiculo(marca, matricula, numkms, fecha_mat, descripcion, precio, propietario, dni_propietario));
            Collections.sort(concesionario);
        }
        
        return 0;
    }
    
    public void listaVehiculos (){
        int i=0;
        while (i<concesionario.size()){
            System.out.println ("Vehículo:" + concesionario.get(i).getMarca() + " Matrícula: " + concesionario.get(i).getMatricula() + 
                    " Precio: " + concesionario.get(i).getPrecio() + " Descripción: " + concesionario.get(i).getDescripcion());
            i++;
        }
    }
    
    public boolean actualiza_kmVeh (String matricula, int kms){
        for (int i = 0; i < concesionario.size(); i++) {
            if (concesionario.get(i).getMatricula().equals(matricula)) {
                concesionario.get(i).setNum_kms(kms);
                return true;
            }
        }
        return false;
    }
    public boolean eliminiarVehiculo (String matricula){
        
         for(int i=0; i < concesionario.size(); i++) {
            if(concesionario.get(i).getMatricula()==matricula){
                concesionario.remove(i);
                return true;
            }
        }
        return false;
    }
    
}
