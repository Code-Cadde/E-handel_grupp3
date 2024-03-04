package org.ehandel.Product;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class ProductService {

    @Inject
    EntityManager em;
    
    public List<Product> findAll() {
        List<Product> product = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        return product;
    }

    public Product find(Long id) {

        return em.find(Product.class, id);
    }

    public Long countAll() {

        return em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public Product create(Product product) {
        em.persist(product);
        return product;

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        em.remove(em.getReference(Product.class, id));

    }
}
