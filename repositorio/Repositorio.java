package repositorio;

import Exceptions.ReservaNaoEncontradaException;

//interface genérica 
public interface Repositorio<Item> {

    void adicionar(Item item);

    Item buscar(String id) throws ReservaNaoEncontradaException;
    
    //tipo de retorno genérico(list<>,map<>,...)
    Object listar();
}
