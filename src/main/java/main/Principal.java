/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import util.Validar;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Principal {

    static Scanner sca = new Scanner(System.in);

    /*
    Muestra el menú en pantalla.
     */
    public static int mostrarMenu() {

        System.out.println("GESTIÓN DE VEHÍCULOS DE UN CONCESIONARIO");

        System.out.println("1.Nuevo Vehículo.");
        System.out.println("2.Listar Vehículos.");
        System.out.println("3.Buscar Vehículo.");
        System.out.println("4.Modificar Kilómetros de Vehículo.");
        System.out.println("5.Borrar vehiculo .");
        System.out.println("6. Salir");
        System.out.println("INTRODUCE UNA OPCION");

        int opt = sca.nextInt();
        sca.nextLine(); //consumimos el salto de línea sino al leer el siguiente error tenemos error.
        return opt;
    }

    public static void main(String args[]) {
        int opt;

        String marca, matricula, descripcion, propietario, dni_propietario;
        int numkms, precio;
        int dia_mat, mes_mat, anio_mat;
        LocalDate fecha_mat;

        //En principio la referencia al Vehículo apuntará a null.
        Concesionario concesionario = new Concesionario();
    /** VAMOS A CREAR EL FICHERO DONDE VAMOS A GUARDAR NUESTROS VEHICULOS DE MANERA PERSISTENTE 
     *  -LO HACEMOS CON LA CLASE FILEOUTPUTSTREAM Y OBJECTOUTPUTSTREAM 
     * @param fichero 
     * @param salida
     */
        System.out.println("Introduce el nombre del fichero que crearemos");
        String entradaFichero = sca.nextLine();
        FileOutputStream fichero = null;
        ObjectOutputStream salida = null;        
    try{    
        fichero = new FileOutputStream(entradaFichero); // creamos el fichero
        salida = new ObjectOutputStream(fichero); // objeto con el que vamos a escribir los vehiculos en el fichero
               
    }catch (IOException e){
        System.out.println("no se creo nada ");//capturo la exception 
    }
    
    /**CREAMOS EL FICHERO DE LECTURA PARA COMPROBAR QUE EXISTEN LOS FICHEROS  
     * PARA ELLO NECESITAMSO LAS CLASES FILEINPUTSTREAM Y OBJECTINPUTSTREAM 
     * @param ficheroIn
     * @param entrada 
     */    
        FileInputStream ficheroIn = null;
        ObjectInputStream entrada = null;    
    try {
        ficheroIn = new FileInputStream(entradaFichero);
        entrada = new ObjectInputStream(ficheroIn);
    } catch (IOException ex) {
            System.out.println("No se pudo leer fichero ");;
    }
    
        do {
            opt = mostrarMenu();

            switch (opt) {

                //Crear nuevo vehículo. Si ya existe alguno, desaparecerá su referencia al crear el nuevo.
                case 1:
                    System.out.println("Nuevo Vehículo");
                    System.out.println("Introduce la marca del vehículo");
                    marca = sca.nextLine();
                    System.out.println("Introduce la matrícula del vehículo");
                    matricula = sca.nextLine();
                    do {
                        if (!Validar.validaMatricula(matricula)) {
                            System.out.println("El formato de la matrícula no es correcto. Debe ser NNNNLLL. Introdúcela otra vez.");
                            matricula = sca.nextLine();
                        }
                    } while (!Validar.validaMatricula(matricula));
                    System.out.println("Introduce la descripción del vehículo");
                    descripcion = sca.nextLine();
                    System.out.println("Introduce el número de kilómetros del vehículo");
                    numkms = sca.nextInt();
                    sca.nextLine();
                    System.out.println("Introduce el precio del vehículo");
                    precio = sca.nextInt();
                    sca.nextLine();
                    System.out.println("Introduce el propietario del vehículo");
                    propietario = sca.nextLine();

                    do {
                        if (!Validar.validaNombre(propietario)) {
                            System.out.println("El nombre del propietario no puede exceder de 40 caracteres y deben contener al menos dos espacio en blanco. INtrodúcelo otra vez");
                            propietario = sca.nextLine();
                        }
                    } while (!Validar.validaNombre(propietario));

                    System.out.println("Introduce el dni propietario del vehículo");
                    dni_propietario = sca.nextLine();
                    do {
                        if (!Validar.validaDNI(dni_propietario)) {
                            System.out.println("El formato del DNI no es correcto. Debe ser NNNNNNNNL. Introdúcelo otra vez.");
                            dni_propietario = sca.nextLine();
                        }
                    } while (!Validar.validaDNI(dni_propietario));

                    System.out.println("Introduce el dia de matriculacion");
                    dia_mat = sca.nextInt();
                    sca.nextLine();

                    System.out.println("Introduce el mes de matriculacion");
                    mes_mat = sca.nextInt();
                    sca.nextLine();

                    System.out.println("Introduce el año de matriculacion");
                    anio_mat = sca.nextInt();
                    sca.nextLine();

                    fecha_mat = LocalDate.of(anio_mat, mes_mat, dia_mat);

                    //Llegados a este punto, hay que insertar el vehículo
                    int result = concesionario.insertarVehiculo(marca, matricula, numkms, fecha_mat, descripcion, precio, propietario, dni_propietario);
                    switch (result) {
                        case 0:
                            System.out.println("El vehículo ha sido creado en el concesionario");
                            break;
                        case -1:
                            System.out.println("El concesionario está completo.");
                            break;
                        case -2:
                            System.out.println("El vehículo ya existe.");
                            break;
                        default:
                            break;
                    }
                    /**Despues de comprobar que existe el vehiculo lo que hacemos es escribirlo en el fichero "fichero" creado
                     * para ello a traves del objeto salida vamos a realizar esta accion 
                     * controlamos las excepciones que puedan pasar en caso de no escribir en el fichero 
                     */
                    try {
                    salida.writeObject(result);
                    }                    
                    catch (IOException e){
                        System.out.println("No se guardo objeto ");
                    }finally {
                        try {
                        if (salida != null)                         
                            salida.close();
                        if (fichero  != null)
                            fichero.close();
                        } catch (IOException ex) {
                                System.out.println("hubo un error ");;
                        }
                    
                    }
                    /*Aquí vamos a leer el fichero para confirmar que esta guardado correctamente y luego lo cerramos 
                     @param vehiculofichero 
                    */
                 

                    break;


                case 2:
                    System.out.println("Listado de Vehículos");
                    concesionario.listaVehiculos();
                    ;
                    break;

                case 3:
                    System.out.println("Búsqueda de Vehículo por Matrícula");
                    System.out.println("Introduce la matrícula");
                    String mat_a_buscar = sca.nextLine();
                    String veh = concesionario.buscaVehiculo(mat_a_buscar);

                    if (veh == null) {
                        System.out.println("No existe vehículo con la matrícula introducia");
                    } else {
                        System.out.println(veh);
                    }
                    break;
                case 4:
                    System.out.println("Modificar kilómetros de Vehículo");
                    System.out.println("Introduce la matrícula");
                    String mat_a_busc = sca.nextLine();
                    System.out.println("Introduce los kilómetros");
                    int kms = sca.nextInt();
                    sca.nextLine();
                   
                    if (concesionario.actualiza_kmVeh(mat_a_busc, kms))
                        System.out.println("Se han actualizado los kilómetros del vehículo con matrícula " + mat_a_busc);
                    break;
                case 5: 
                    System.out.println("Eliminar un vehiculo  ");
                    System.out.println("introduce matricula");
                    String eliminarMatricula = sca.nextLine();
                    boolean eliminado = concesionario.eliminiarVehiculo(eliminarMatricula);
                    if (!eliminado){
                        System.out.println("No existe esta matricula");
                    }else {
                        System.out.println("Vehiculo "+eliminarMatricula+" eliminado correctamente");
                    }                    
                    break;
                
                case 6:{
                    System.out.println("cIERRE DE PROGRAMA ");
                       Vehiculo vehiculofichero ;
                {
                    try {
                        vehiculofichero= (Vehiculo)entrada.readObject(); //necesario casting del objeto
                        System.out.println("el vehiculo con matricula "+vehiculofichero.getMarca()+ "esta en fichero");
                    } catch (IOException e) {
                        System.out.println("no se ha leido el fichero");
                    } catch (ClassNotFoundException ex) { 
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }finally {
                        try {
                        if(entrada !=null) 
                            entrada.close();
                        if (ficheroIn !=null)
                            ficheroIn.close();
                        }catch(IOException e){
                            System.out.println("Hubo error de lectura ");
                        }
                    } 
                }
                    break;
                }
                    
            }
        } while (opt != 6);
    }

}

