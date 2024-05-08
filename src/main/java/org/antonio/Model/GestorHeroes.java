package org.antonio.Model;

import org.antonio.Exception.HeroeNoEncontradoException;
import org.antonio.Exception.PoderNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class GestorHeroes {
    private List<Heroe> heroes;

    public GestorHeroes() {
        this.heroes = new ArrayList<>();
    }

    public void agregarHeroe(Heroe heroe) {
        this.heroes.add(heroe);
    }

    public Heroe buscarHeroe(String nombre) throws HeroeNoEncontradoException {
        for (Heroe heroe : this.heroes) {
            if (heroe.getNombre().equals(nombre)) {
                return heroe;
            }
        }
        throw new HeroeNoEncontradoException(nombre);
    }
    public Heroe buscarHeroePorSuperPoder(String nombre) throws PoderNoEncontradoException {
        for (Heroe heroe : this.heroes) {
            if (heroe.getSuperpoderes().equals(nombre)) {
                return heroe;
            }
        }
        throw new PoderNoEncontradoException(nombre);
    }
    public boolean eliminarheroe(String nombre){
        boolean eliminado = false;
        for (int i = 0; i < this.heroes.size(); i++) {
            if(this.heroes.get(i).getNombre().equals(nombre)){
                this.heroes.remove(i);
                eliminado = true;
            }
        }
        return eliminado;
    }
    public List<Heroe> getHeroes(){
        return heroes;
    }
    public void act_her_existente(Heroe h){
        for (int i = 0; i < getHeroes().size(); i++) {
            if (getHeroes().get(i).getNombre().equals(h.getNombre())){
                getHeroes().get(i).setBiografia(h.getBiografia());
                getHeroes().get(i).setSuperpoderes(h.getSuperpoderes());

            }
        }
    }
    public String listarHeroes(){
        String nombres = "";
        if(heroes.size()>=1){
            nombres = heroes.get(0).getNombre();
        }
        ;
        for (int i = 1; i < heroes.size(); i++) {

             nombres += ", "+heroes.get(i).getNombre() ;
        }
        return nombres;
    }
    public List buscarHeroesPorSuperpoder(String poder){
        List <Heroe> lisheroe = new ArrayList<Heroe>();
        for (int i = 0; i < getHeroes().size(); i++) {
            if (heroes.get(i).getSuperpoderes().contains(poder)){
                lisheroe.add(heroes.get(i));
            }
        }

            return lisheroe;

    }
}
