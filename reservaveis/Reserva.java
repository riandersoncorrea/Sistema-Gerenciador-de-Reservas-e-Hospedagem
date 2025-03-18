package reservaveis;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import Exceptions.CheckInInvalidoException;
import Exceptions.HospedagemIndisponivelException;
import Exceptions.ServicoNaoPermitidoException;
import administrativo.Cliente;
import hospedagens.Apartamento;
import hospedagens.Cabana;
import hospedagens.Quarto;
import servicos.Lavanderia;
import servicos.PasseiosTuristicos;
import servicos.ServicoNulo;
import servicos.Transfer;

public class Reserva {
    private Cliente cliente;
    private int id;
    private String idHospedagem;
    private String idServicosAdicionais = "";
    private ItemHospedagem itemReservado; // Enum para o tipo de hospedagem
    private ItemServicosAdicionais itemServicosAdicionais; //Enum para o tipo de servicos adicionais
    private Calendar dataInicioServico;
    private Calendar dataFimServico;
    private Calendar dataCheckIn;
    private Calendar dataCheckOut;
    private StatusReserva status; //é uma variável do tipo StatusReserva(Enum) com as opções do status
    private StatusReserva statusServicoAdicional;
    private String motivosCancelamento;


    //construtor
    public Reserva(Cliente cliente, String idHospedagem, Calendar dataCheckIn, Calendar dataCheckOut) {
        this.cliente = cliente;
        this.idHospedagem = idHospedagem;
        this.itemReservado = ItemHospedagem.NENHUM;
        this.itemServicosAdicionais = ItemServicosAdicionais.NENHUM;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.status = StatusReserva.NENHUM;
        this.statusServicoAdicional = StatusReserva.NENHUM;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemHospedagem getItemReservado() {
        return itemReservado;
    }

    public void setItemReservado(ItemHospedagem itemReservado) {
        this.itemReservado = itemReservado;
    }

    
    
    public String getMotivosCancelamento() {
        return motivosCancelamento;
    }

    public void setMotivosCancelamento(String motivosCancelamento) {
        this.motivosCancelamento = motivosCancelamento;
    }

    public String getIdServicosAdicionais() {
        return idServicosAdicionais;
    }

    public void setIdServicosAdicionais(String idServicosAdicionais) {
        this.idServicosAdicionais = (idServicosAdicionais != null) ? idServicosAdicionais : ""; 
    }

    public Calendar getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(Calendar dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public Calendar getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(Calendar dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public StatusReserva getStatusReserva() {
        return status;
    }

    public void setStatusReserva(StatusReserva status) {
        this.status = status;
    }

    
    public StatusReserva getStatusServicoAdicional() {
        return statusServicoAdicional;
    }

    public void setStatusServicoAdicional(StatusReserva statusServicoAdicional) {
        this.statusServicoAdicional = statusServicoAdicional;
    }

    public String getidHospedagem() {
        return idHospedagem;
    }

    public void setidHospedagem(String idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public ItemServicosAdicionais getItemServicosAdicionais() {
        return itemServicosAdicionais;
    }

    public void setItemServicosAdicionais(ItemServicosAdicionais itemServicosAdicionais) {
        this.itemServicosAdicionais = itemServicosAdicionais;
    }

    
    public Calendar getDataInicioServico() {
        return dataInicioServico;
    }

    public void setDataInicioServico(Calendar dataInicioServico) {
        this.dataInicioServico = dataInicioServico;
    }

    public Calendar getDataFimServico() {
        return dataFimServico;
    }

    public void setDataFimServico(Calendar dataFimServico) {
        this.dataFimServico = dataFimServico;
    }

    //metodos
    public boolean cancelarReserva (Reserva reserva) throws Exception{

        Calendar agora = Calendar.getInstance(); // pega o horário e data atual do dispositivo
        Calendar checkIn = reserva.getDataCheckIn();

        //estamos calculando o horario limite para cancelamento (a diferença de horario tem que ser no máximo 24 antes)
        checkIn.add(Calendar.HOUR_OF_DAY, -24);

        if (agora.before(checkIn)){
            //altera o status da reserva para cancelada
            return true;
        } else {
            throw new Exception("Cancelamento só é permitido com 24h de antecendência da data de Check-in.");
        }
        
        
    } 

    public void realizarCheckIn(Reserva reserva) throws CheckInInvalidoException{

        Calendar agora = Calendar.getInstance(); 
        Calendar checkInPermitido = (Calendar) reserva.getDataCheckIn();

        checkInPermitido.set(Calendar.HOUR_OF_DAY, 14);
        checkInPermitido.set(Calendar.MINUTE, 0);

        
        if (agora.after(checkInPermitido) && agora.get(Calendar.DAY_OF_YEAR)
            == reserva.getDataCheckIn().get(Calendar.DAY_OF_YEAR)){
            reserva.setStatusReserva(StatusReserva.ATIVO);
        } else {
            throw new CheckInInvalidoException("Check-in só é permito após às 14h.");
        }
    }


    public void realizarCheckOut(Reserva reserva) throws Exception{

        Calendar agora = Calendar.getInstance(); 
        Calendar checkOutPermitido = (Calendar) reserva.getDataCheckOut();

        checkOutPermitido.set(Calendar.HOUR_OF_DAY, 12);
        checkOutPermitido.set(Calendar.MINUTE, 0);

            if (agora.before(checkOutPermitido) && agora.get(Calendar.DAY_OF_YEAR)
                == reserva.getDataCheckOut().get(Calendar.DAY_OF_YEAR)){
                reserva.setStatusReserva(StatusReserva.FINALIZADO);
            } else {
                throw new Exception("Check-out atrasado. Sujeito a multa.");
            }
    }

    //calcula o total de dias
    public static long totalDias(Reserva reserva){

        long tempoCheckIn = reserva.getDataCheckIn().getTimeInMillis();
        long tempoCheckOut = reserva.getDataCheckOut().getTimeInMillis();


        return TimeUnit.MILLISECONDS.toDays(tempoCheckOut - tempoCheckIn);
    }

    //calcula o total de horas
    public static long totalHora(Reserva reserva){

        long horaInicial = reserva.getDataInicioServico().getTimeInMillis();
        long horaFinal = reserva.getDataFimServico().getTimeInMillis();

        return TimeUnit.MILLISECONDS.toHours(horaFinal - horaInicial);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatarDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");


        Hospedagem hospedagem;
        ServicosAdicionais servicosAdicionais;
        double valorReserva = 0.0;
        double valorServicoAdicional = 0.0;

        // trata exception se caso a opcao de hospedagem for indisponível
        try {
            hospedagem = criarHospedagemPorTipo();
            valorReserva = hospedagem.calcularValorHospedagem(this);
        } catch (HospedagemIndisponivelException e) {
           System.out.println("Erro ao criar Hospedagem: " + e.getMessage());
        }
        
        // trata exception se caso a opcao de serviço adicional for indisponível
        try {
            servicosAdicionais = criarServicosAdicionaisPorTipo();
            valorServicoAdicional = servicosAdicionais.calcularValorServicos(this);
        } catch (ServicoNaoPermitidoException e){
            System.out.println("Erro ao criar Serviço Adicional: " + e.getMessage());
            valorServicoAdicional = 0.0;
        }

        //caso as datas de inicio e fim do servico adicional sejam nulas, evita deixar nula e mostra uma string de nao definido
        String dataInicioServicoStr = (dataInicioServico != null) ? formatarDataHora.format(dataInicioServico.getTime()) : "Não Definido";
        String dataFimServicoStr = (dataFimServico != null) ? formatarDataHora.format(dataFimServico.getTime()) : "Não Definido";
        
        return "Reserva de Hospedagem\n" +
                "=====================\n" +
                "ID Reserva:                 " + id + "\n" +
                "Cliente:                    " + cliente + "\n" +
                "Status da Reserva:          " + status + "\n" +
                "Tipo de Hospedagem:         " + idHospedagem + " - " + itemReservado + "\n" +
                "Status de Serviços Adicionais: " + statusServicoAdicional + "\n" +
                "Serviço Adicional:          " + idServicosAdicionais + " - " + itemServicosAdicionais + " (" + dataInicioServicoStr + " - " + dataFimServicoStr + ")\n" +
                "Data de Check-in:           " + formatarDataHora.format(dataCheckIn.getTime()) + "\n" +
                "Data de Check-out:          " + formatarDataHora.format(dataCheckOut.getTime()) + "\n" +
                "Valor da Reserva:           R$ " + String.format("%.2f", valorReserva) + "\n" +
                "Valor do Serviço Adicional: R$ " + String.format("%.2f", valorServicoAdicional) + "\n" +
                "Valor Total:                R$ " + String.format("%.2f", valorReserva + valorServicoAdicional) + "\n" +
                "=====================\n";

    }

    //método para criar uma instacia do tipo de hospedagem 
    private Hospedagem criarHospedagemPorTipo () throws HospedagemIndisponivelException{
        switch (itemReservado) {
            case APARTAMENTO:
                return new Apartamento();
            case QUARTO:
                return new Quarto(idHospedagem, id);
            case CABANA:
                return new Cabana(idHospedagem, id);
        
            default:
                throw new HospedagemIndisponivelException ("Tipo de hospedagem Indisponível");
        }
    }

    //método para criar uma instacia do tipo de Serviço 
    private ServicosAdicionais criarServicosAdicionaisPorTipo () throws ServicoNaoPermitidoException{

        if(itemServicosAdicionais == ItemServicosAdicionais.NENHUM){
            //caso nao tenha contratado serviço adicional retorna o valor 0.0
            return new ServicoNulo();
        }

        switch (itemServicosAdicionais) {
            case PASSEIO:
                return new PasseiosTuristicos();
            case LAVANDERIA:
                return new Lavanderia();
            case TRANSFER:
                return new Transfer();

            default:    
                throw new ServicoNaoPermitidoException("Tipo de Serviço Adicional Indisponível");
        }
    }
}
