package org.antonio;

import org.antonio.Exception.PoderNoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.antonio.Exception.HeroeNoEncontradoException;
import org.antonio.Exception.PoderNoEncontradoException;
import org.antonio.Model.GestorHeroes;
import org.antonio.Model.Heroe;

public class TestHeroes {
    Heroe hero1;
    Heroe hero2;
    GestorHeroes gh;
    @Before
    public void setUp(){
        hero1 = new Heroe("Hulk", "Superfuerza", "Mounstro verde parecido a shrek");
        hero2 = new Heroe("Bat-Man", "Millonario", "Millonario dueño de un coche con armas ");
        gh = new GestorHeroes();
    }
    @Test
    public void testGetAndSetHeroe(){
       
        assertEquals("Hulk",hero1.getNombre());
        assertEquals("Superfuerza", hero1.getSuperpoderes());
        assertEquals("Mounstro verde parecido a shrek", hero1.getBiografia());
        hero2.setBiografia("su compañero es robin");
        assertEquals("su compañero es robin", hero2.getBiografia());
    }
    @Test
    public void testAgregaryBuscar() throws HeroeNoEncontradoException{
        gh.agregarHeroe(hero1);
        gh.agregarHeroe(hero2);
        assertSame(hero1,gh.buscarHeroe("Hulk"));
        assertSame(hero2,gh.buscarHeroe("Bat-Man"));
        assertEquals(hero1.getSuperpoderes(),gh.buscarHeroe("Hulk").getSuperpoderes());
    }
    @Test
    public void testUpdateHero(){
        GestorHeroes gh2 = new GestorHeroes(); 
        gh2.agregarHeroe(new Heroe("Capitan america", "Lanzar escudo", null));
        gh2.getHeroes().get(0).setBiografia("Super heroe con traje azul y con escudo");
        assertEquals("Super heroe con traje azul y con escudo", gh2.getHeroes().get(0).getBiografia());
    }

    @Test (expected = HeroeNoEncontradoException.class)
    public void testHeroeNotFound() throws HeroeNoEncontradoException{
        gh.agregarHeroe(hero1);
        gh.buscarHeroe("omni-man");
    }
    @Test
    public void testEliminado(){
        //true elimina el existente , false si no existe.
        gh.agregarHeroe(hero1);
        gh.agregarHeroe(hero2);
        assertEquals(true,gh.eliminarheroe("Hulk") );
        assertEquals(false,gh.eliminarheroe("Spider man"));
        assertTrue(gh.eliminarheroe("Bat-Man"));
    }
    @Test
    public void addSuperHero() throws HeroeNoEncontradoException{
        gh.agregarHeroe(hero1);
        gh.agregarHeroe(hero2);
        gh.agregarHeroe(new Heroe("Duende verde", "ser verde", "no es heroe , quiere matar a spider man"));
        gh.agregarHeroe(new Heroe("thor", null, null));
        assertEquals("Duende verde", gh.buscarHeroe("Duende verde").getNombre());
        assertEquals("thor", gh.buscarHeroe("thor").getNombre());
        assertEquals("Hulk", gh.buscarHeroe("Hulk").getNombre());
        assertEquals("Bat-Man", gh.buscarHeroe("Bat-Man").getNombre());
        assertNotEquals("Bat-Mannnnn", gh.buscarHeroe("Bat-Man").getNombre());
    } 
    @Test
    public void testBuscarHeroePorSuperPoder() throws PoderNoEncontradoException{
        gh.agregarHeroe(hero1);
        gh.agregarHeroe(hero2);
        gh.agregarHeroe(new Heroe("Duende verde", "ser verde", "no es heroe , quiere matar a spider man"));
        gh.agregarHeroe(new Heroe("thor", "Poder del trueno", null));
        assertEquals("Duende verde", gh.buscarHeroePorSuperPoder("ser verde").getNombre());
        assertEquals("thor", gh.buscarHeroePorSuperPoder("Poder del trueno").getNombre());
        assertEquals("Hulk", gh.buscarHeroePorSuperPoder("Superfuerza").getNombre());
        assertEquals("Bat-Man", gh.buscarHeroePorSuperPoder("Millonario").getNombre());
    }
    @Test
    public void testUpdateHeroeExistente() throws PoderNoEncontradoException, HeroeNoEncontradoException {
        gh.agregarHeroe(hero1);
        Heroe heroe3  = new Heroe("Hulk","verde","Pegar duro");
        gh.act_her_existente(heroe3);
        assertEquals("Hulk",gh.buscarHeroePorSuperPoder("verde").getNombre());
        assertEquals("Pegar duro",gh.buscarHeroe("Hulk").getBiografia());
    }
    @Test
    public void testListar() throws HeroeNoEncontradoException {
        gh.agregarHeroe(hero1);
        gh.agregarHeroe(hero2);
        gh.agregarHeroe(new Heroe("Duende verde", "ser verde", "no es heroe , quiere matar a spider man"));
        assertEquals("Hulk, Bat-Man, Duende verde",gh.listarHeroes());
        assertTrue("Hulk, Bat-Man, Duende verde".equals(gh.listarHeroes()));
    }
    @Test
    public void testBusHeroeSuperpoder(){

        gh.agregarHeroe(hero1);
        gh.agregarHeroe(hero2);
        Heroe hero3 = new Heroe("Deadpool","Cortar, inmortal","Le hicieron mal una cirugia");
        Heroe hero4 = new Heroe("Wolwerine","Cortar","Solo corta");
        gh.agregarHeroe(hero3);
        gh.agregarHeroe(hero4);
        List<Heroe> lista = new ArrayList<>();
        lista.add(hero3);
        lista.add(hero4);
        assertEquals(lista,gh.buscarHeroesPorSuperpoder("Cortar"));

    }
}

