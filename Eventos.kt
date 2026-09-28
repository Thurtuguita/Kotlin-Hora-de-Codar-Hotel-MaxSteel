package HotelMS

import kotlin.math.ceil
import kotlin.math.floor

data class Evento(
    val nome: String,
    val data: String,
    val horaInicio: Int,
    val horaFim: Int,
    val quantidadeParticipantes: Int,
    val auditorio: String,
    val duracao: Int,
    val totalGarcons: Int,
    val custoGarcons: Double,
    val custoBuffet: Double,
    val custoTotal: Double
)

fun cadastrarEvento(eventos: MutableList<Evento>){

    var auditorio=" "
    var cadeirasExtras=0

    println("Informe o número de convidados:")
    val convidados=readln().toIntOrNull() ?: 0
    if(convidados<1 || convidados>350){
        println("Número de convidados inválido.")
        return
    }
    else{

        if(convidados<=220){
            auditorio="Laranja"
            if(convidados>150){
                cadeirasExtras=convidados-150
            }
        }
        else{
            auditorio="Colorado"
        }
        println("Auditório escolhido: $auditorio")
        println("Cadeiras extras: $cadeirasExtras")
    }

    println("Informe o dia da semana: ")
    val dia=readln().lowercase()
    println("Informe o horário de início desejado: ")
    val horario=readln()
    val horaInicio=horario.split(":")[0].toIntOrNull() ?: 0

    var horarioValido=false

    when(dia){
        "segunda", "terca", "quarta", "quinta", "sexta"->{
            if(horaInicio in 7..23){
                horarioValido=true
            }
        }
        "sabado", "domingo"->{
            if(horaInicio in 7..15){
                horarioValido=true
            }
        }
    }
    if(!horarioValido){ //se horario n for valido
        println("Horário indisponível para este dia.")
        return
    }
    println("Informe a duração do evento em horas: ")
    val duracao=readln().toIntOrNull() ?: 0
    if(duracao<1 || duracao>12){
        println("Duração inválida.")
        return
    }
    val horaFim=horaInicio+duracao
    if(dia=="segunda" || dia=="terca" || dia=="quarta" || dia=="quinta" || dia=="sexta"){
        if(horaFim>23){
            println("O evento termina fora do horário permitido.")
            return
        }
    }
    else if(dia=="sabado" || dia=="domingo"){
        if(horaFim>15){
            println("O evento termina fora do horário permitido.")
            return
        }
    }

    for(evento in eventos){
        if(evento.auditorio==auditorio && evento.data==dia && horaInicio<evento.horaFim && horaFim>evento.horaInicio){
            println("Este auditório já está reservado para esse horário.")
            return
        }
    }

    println("Informe o nome da empresa:")
    val empresa=readln()
    println("Auditório reservado para $empresa: $dia às ${horaInicio}h.")

    val garconsBase=ceil(convidados/12.0).toInt() //calcular garçons
    val garconsReforco=floor(duracao/2.0).toInt() //calcular reforços
    val totalGarcons=garconsBase+garconsReforco //total garçons
    val custoGarcons=totalGarcons*duracao*10.50 //calcular custo

    println("Garçons necessários: $totalGarcons")
    println("Custo total GARÇONS: R$$custoGarcons")

    val cafeLitros=convidados*0.2
    val custoCafe=cafeLitros*0.80
    val aguaLitros=convidados*0.5
    val custoAgua=aguaLitros*0.40
    //bebidas

    val salgados=convidados*7
    val centenasSalgados=ceil(salgados/100.0)
    val custoSalgados=centenasSalgados*34
    val custoBuffet=custoCafe+custoAgua+custoSalgados

    println("Café: $cafeLitros L - R$ $custoCafe")
    println("Água: $aguaLitros L - R$ $custoAgua")
    println("Salgados: $salgados unidades - R$ $custoSalgados")
    println("Custo total do buffet: R$ $custoBuffet\n")

    val custoTotal=custoGarcons+custoBuffet

    println("""
        RESUMO DO EVENTO
        Auditório: $auditorio
        Empresa: $empresa
        Data: $dia
        Horário: ${horaInicio}h às ${horaFim}h
        Convidados: $convidados
        Garçons: $totalGarcons
        Duração: $duracao horas
        Custo dos garçons: R$$custoGarcons
        Custo do buffet: R$$custoBuffet
        Custo total (GARÇONS+BUFFET): R$$custoTotal
    """.trimIndent())

    println("Deseja confirmar a reserva? (S/N)")
    val resposta=readln().uppercase()
    if(resposta=="S"){
        println("Reserva efetuada com sucesso.")
        eventos.add(
            Evento(
                empresa,
                dia,
                horaInicio,
                horaFim,
                convidados,
                auditorio,
                duracao,
                totalGarcons,
                custoGarcons,
                custoBuffet,
                custoTotal
            )
        )
    }
    else if(resposta=="N"){
        println("Reserva não efetuada.")
        return
    }
}

fun listarEventos(eventos: MutableList<Evento>){

    if(eventos.isEmpty()){
        println("Nenhum evento cadastrado.")
        return
    }
    for(evento in eventos){
        println("""
            RELATÓRIO DO EVENTO
            
        Auditório: ${evento.auditorio}
        Empresa: ${evento.nome}
        Data: ${evento.data}
        Horário: ${evento.horaInicio}h às ${evento.horaFim}h
        Convidados: ${evento.quantidadeParticipantes}
        Garçons: ${evento.totalGarcons}
        Duração: ${evento.duracao} horas
        Custo dos garçons: R$${evento.custoGarcons}
        Custo do buffet: R$${evento.custoBuffet}
        Custo total (GARÇONS+BUFFET): R$${evento.custoTotal}
        """.trimIndent())
    }
}

//feito