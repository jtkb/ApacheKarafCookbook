package com.packt.jpa.demo.dao;

import com.packt.jpa.demo.api.RecipeBookService;
import com.packt.jpa.demo.entity.Recipe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Transactional
public class RecipeBookServiceDAOImpl implements RecipeBookService {

    // Use the PersistentContext annotation instead of specifying in the 
    // Blueprint XML file
    @PersistenceContext(unitName = "recipe")
    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }   

    // Use the Transactional annotation instead of specifying in the 
    // Blueprint XML
    @Transactional(TxType.SUPPORTS)
    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> result = new ArrayList<Recipe>();
        result = em.createQuery("select r from RECIPE r", Recipe.class).getResultList();
        return result;
    }

    @Override
    public void addRecipe(String title, String ingredients) {
        em.persist(new Recipe(title, ingredients));
    }

    @Override
    public void deleteRecipe(String title) {
        em.remove(em.getReference(Recipe.class, title));
    }
}
