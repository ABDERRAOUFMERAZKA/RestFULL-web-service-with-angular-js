package org.did;

import java.util.List;

import org.did.dao.ProduitRepository;
import org.did.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class CataServiceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CataServiceApplication.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		produitRepository.save(new Produit ("Polo 1.4", 90.0, 55));
		produitRepository.save(new Produit("ibiza 1.4", 50.0, 120));
		produitRepository.save(new Produit("Golf6", 500.0, 50));
		List <Produit> prods = produitRepository.findAll();
		prods.forEach(p->System.out.println(p.getDesignation()));
		
		
		
		
	   
		
	}
}
