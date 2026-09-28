package HotelMS

data class Abastecimento(
    val posto: String,
    val alcool: Double,
    val gasolina: Double,
    val combustivelIdeal: String,
    val custo: Double
)

fun abastecer(abastecimentos: MutableList<Abastecimento>){

    println("ABASTECIMENTO")
    println("Informe o preço do álcool no Wayne Oil: ")
    val alcoolWayne=readln().toDoubleOrNull() ?: 0.0
    println("Informe o preço da gasolina no Wayne OIl: ")
    val gasolinaWayne=readln().toDoubleOrNull() ?: 0.0
    println("Informe o preço do álcool no Stark Petrol: ")
    val alcoolStark=readln().toDoubleOrNull() ?: 0.0
    println("Informe o preço da gasolina no Stark Petrol: ")
    val gasolinaStark=readln().toDoubleOrNull() ?: 0.0

    var combustivelWayne: String
    var custoWayne: Double

    if(alcoolWayne<=gasolinaWayne*0.70){
        combustivelWayne="Álcool"
        custoWayne=alcoolWayne*42
    }
    else{
        combustivelWayne="Gasolina"
        custoWayne=gasolinaWayne*42
    }

    var combustivelStark: String
    var custoStark: Double

    if(alcoolStark<=gasolinaStark*0.70){
        combustivelStark="Álcool"
        custoStark=alcoolStark*42
    }
    else{
        combustivelStark="Gasolina"
        custoStark=gasolinaStark*42
    }

    println("Wayne Oil: melhor opção=$combustivelWayne | Total (42L)=R$$custoWayne.")
    println("Stark Petrol: melhor opção=$combustivelStark | Total (42L)=R$$custoStark.")

    if(custoWayne<custoStark){
        println("No momento, é mais barato abastecer no posto Wayne Oil.")
    }
    else if(custoStark<custoWayne){
        println("No momento, é mais barato abastecer no posto Stark Petrol.")
    }
    else{
        println("Os dois postos possuem o mesmo custo.")
    }

    println("RANKING DE MENOR PARA MAIOR CUSTO: ")
    if(custoWayne<custoStark){
        println("1º Wayne Oil: R$$custoWayne")
        println("2º Stark Petrol: R$$custoStark")
    }
    else if(custoStark<custoWayne){
        println("1º Stark Petrol: R$$custoStark")
        println("2º Wayne Oil: R$$custoWayne")
    }
    else{
        println("Os dois postos possuem o mesmo custo. Empate de preço.")
    }

    abastecimentos.add(
        Abastecimento(
            "Wayne Oil",
            alcoolWayne,
            gasolinaWayne,
            combustivelWayne,
            custoWayne
        )
    )
    abastecimentos.add(
        Abastecimento(
            "Stark Petrol",
            alcoolStark,
            gasolinaStark,
            combustivelStark,
            custoStark
        )
    )
}

//feito