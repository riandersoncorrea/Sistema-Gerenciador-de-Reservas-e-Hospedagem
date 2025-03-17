package Exceptions;

public class ServicoNaoPermitidoException extends Exception{
    public ServicoNaoPermitidoException(String mensagem){
        super(mensagem);
    }
}
