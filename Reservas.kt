package HotelMS

data class Reserva(
    val nomeHospede: String,
    val numeroQuarto: Int,
    val dias: Int,
    val total: Double
)

fun Reservas(quartosOcupados: MutableList<Int>,
             reservas: MutableList<Reserva>){

    println("Informe o valor da diária: ")
    val valorDiaria=readln().toDoubleOrNull() ?: 0.0 //valor da diaria sempre num
    if(valorDiaria<=0){ //verificação valorDiaria deve ser maior que 0
        println("Valor inválido. A diaria precisa ser maior que R$0,00")
        return
    }
    println("Quantos dias o hóspede ficará hospedado? ")
    val dias=readln().toIntOrNull() ?: 0
    if(dias<1 || dias>30){ //verificação dias deve ser maior que 0 e menor que 31
        println("Valor inválido. ''Dias'' deve ser maior que 0.")
        return
    }
    println("Informe o nome completo do hóspede: ")
    val nomeHospede=readlnOrNull().orEmpty()
    println("Informe o tipo de quarto escolhido:\n" +
            "S. Standard\n" +
            "E. Executivo\n" +
            "L. Luxo")
    val tiposQuartos=readlnOrNull().orEmpty().uppercase()
    val fator=when(tiposQuartos){ //verificcar fator
        "S"->1.00
        "E"->1.35
        "L"->1.65
        else->{
            println("Tipo de quarto inválido.")
            return
        }
    }
    var numeroQuarto=0

    while(true){
        println("Informe o número do quarto desejado (1 a 20): ")
        numeroQuarto=readln().toIntOrNull() ?: 0
        if(numeroQuarto<1 || numeroQuarto>20){
            println("Quarto não existe.")
            continue //
        }
        if(numeroQuarto in quartosOcupados){
            println("Quarto já está ocupado.")
            println("Quartos disponíveis: ")
            for(quarto in 1..20){
                if(quarto !in quartosOcupados){
                    println(quarto)
                }
            }
            continue
        }
        break
    }
    val subtotal=valorDiaria*dias*fator
    val taxaServico=subtotal*0.10
    val total=subtotal+taxaServico

    //resumo da reserva - podemos fazer uma lista mais rápido usando """
    println("""
        Hóspede: $nomeHospede
        Quarto: $tiposQuartos, número $numeroQuarto
        Diária: $valorDiaria
        Dias: $dias
        Subtotal: $subtotal
        Taxa de serviço: $taxaServico
        Total: R$$total
    """.trimIndent())
    println("Deseja confirmar a reserva? (S/N)")
    val resposta=readlnOrNull().orEmpty().uppercase()
    if(resposta=="S"){
        quartosOcupados.add(numeroQuarto)
        reservas.add(Reserva(nomeHospede, numeroQuarto, dias, total))
        println("Reserva realizada com sucesso!")
        println("Mapa dos quartos: ")
        for(quarto in 1..20){
            if(quarto in quartosOcupados){
                print("O ")
            }
            else{
                print("L ")
            }
            if(quarto%5==0){ //% pega o resto da divisao - se eu dividir o numerol do quarto por 5, o resto é zero?
                println()
            }
        }
    }
    else{
        return//menu
    }
}

//feito