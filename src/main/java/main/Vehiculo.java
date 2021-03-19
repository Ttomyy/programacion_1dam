package main;
/**Implementamos la interfaz comparable y sobreescribimos el metodo Y Serializable que no se sobreescribe
 * IMPORTANTE QUE LA CLASE VEHICULO SEA LA CLASE SERIALIZABLE PUESTO QUE ES LOS DATOS DE LOS VEHICULOS LOS QUE SE VAN A GUARDAR 
 * Y SI NO ES SERIALIZABLE NO SE PODRIA HACER EL FICHERO BINARIO
 */
import java.time.LocalDate;
import java.time.Period;
import java.io.Serializable;
public class Vehiculo implements Comparable<Vehiculo> , Serializable{

    String marca;
    String matricula;
    int num_kms;
    LocalDate fecha_mat;
    String descripcion;
    int precio;
    String propietario;
    String dni_propietario;

    public Vehiculo(String marca, String matricula, int num_kms, LocalDate fecha_mat, String descripcion, int precio, String propietario, String dni_propietario) {
        this.marca = marca;
        this.matricula = matricula;
        this.num_kms = num_kms;
        this.fecha_mat = fecha_mat;
        this.descripcion = descripcion;
        this.precio = precio;
        this.propietario = propietario;
        this.dni_propietario = dni_propietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNum_kms() {
        return num_kms;
    }

    public void setNum_kms(int num_kms) {
        this.num_kms = num_kms;
    }

    public LocalDate getFecha_mat() {
        return fecha_mat;
    }

    public void setFecha_mat(LocalDate fecha_mat) {
        this.fecha_mat = fecha_mat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDni_propietario() {
        return dni_propietario;
    }

    public void setDni_propietario(String dni_propietario) {
        this.dni_propietario = dni_propietario;
    }

    public int get_Anios() {
        LocalDate hoy = LocalDate.now();
        return  (Period.between(this.fecha_mat, hoy).getYears());
    }
    
    public void act_kms (int nuevos_kms){
        this.num_kms=this.num_kms+nuevos_kms;
    }
    
    @Override
    public int compareTo(Vehiculo v){
        
        if (!v.getMatricula().equals(matricula)){
            return -1;
        }
        else if (v.getMatricula()==(matricula)){
            return 0;
        }
        else return 1;
    }

}
