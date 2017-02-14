package org.did.dao;

import java.util.List;


import org.did.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitRestService {
	
	@Autowired
  private ProduitRepository produitRepository ;
	@RequestMapping(value="/produits", method= RequestMethod.GET)
	public List <Produit> listProduit(){
		return produitRepository.findAll();
		
	}
	
	@RequestMapping(value="/produits/{id}", method= RequestMethod.GET)
	public Produit getProduit(@PathVariable("id") Long id){
		return produitRepository.findOne(id);
		
	}
	@RequestMapping(value="/produits", method= RequestMethod.POST)
	public Produit save (@RequestBody Produit p){
		return produitRepository.save(p);
	}
	
	@RequestMapping(value="/produits/{id}", method= RequestMethod.PUT)
	public Produit update (@RequestBody Produit p, @PathVariable Long id){
		p.setId(id);
		return produitRepository.save(p);
	}
	@RequestMapping(value="/produits/{id}",method= RequestMethod.DELETE)
	public void delete (@PathVariable ("id") Long id){
		produitRepository.delete(id);
	}
	
	@RequestMapping(value="/chercheProduits", method = RequestMethod.GET)
	public Page<Produit> chercher (String mc, @RequestParam (name="page", defaultValue="0") int page, @RequestParam (name="size", defaultValue="5") int size) {
		return produitRepository.chercherProduit ("%"+mc+"%", new PageRequest(page,size));
	}
	
	
  
  
}
