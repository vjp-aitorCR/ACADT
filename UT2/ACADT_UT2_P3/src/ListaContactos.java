/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alumno
 */
public class ListaContactos {
    private List<Contacto> lista = new ArrayList<>();
    
    public ListaContactos(){
    }
    public void add(Contacto con){
        lista.add(con);
    }
    public List<Contacto> getListaContactos(){
        return lista;
    }
}
