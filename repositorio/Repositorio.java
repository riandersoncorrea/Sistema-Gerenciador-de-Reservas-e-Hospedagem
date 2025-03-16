package repositorio;


//interface genérica 
public interface Repositorio<Item> {

    void adicionar(Item item);

    Item buscar(String id);
    
    //tipo de retorno genérico(list<>,map<>,...)
    Object listar();
}
