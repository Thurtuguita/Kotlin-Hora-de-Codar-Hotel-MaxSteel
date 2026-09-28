package HotelMS

fun Relatorios(
    quartosOcupados: MutableList<Int>,
    reservas: MutableList<Reserva>,
    hospedes: MutableList<Hospede>,
    eventos: MutableList<Evento>
){

    val totalReservas=reservas.size
    val taxaOcupacao=quartosOcupados.size.toDouble()/20*100
    val totalHospedes=hospedes.size
    val totalEventos=eventos.size

    var receitaHospedagem=0.0

    for(reserva in reservas){
        receitaHospedagem+=reserva.total
    }

    var receitaEventos=0.0

    for(evento in eventos){
        receitaEventos+=evento.custoTotal
    }

    val receitaTotal=receitaHospedagem+receitaEventos

    println("""
        RELATÓRIO OPERACIONAL
        
        Reservas confirmadas: $totalReservas
        Taxa de ocupação: ${taxaOcupacao}%
        Hóspedes cadastrados: $totalHospedes
        Eventos confirmados: $totalEventos
        Receita de hospedagem: R$$receitaHospedagem
        Receita de eventos: R$$receitaEventos
        Receita total: R$$receitaTotal
    """.trimIndent())
}

//feito