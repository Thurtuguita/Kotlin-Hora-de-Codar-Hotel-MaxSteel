package HotelMS

data class OrcamentoAC( //Orçamento Ar-Condicionado
    val empresa: String,
    val valorUnitario: Double,
    val quantidade: Int,
    val desconto: Double,
    val total: Double
)

fun ArCondicionado(orcamentosAC: MutableList<OrcamentoAC>){

    var continuar="S"

    while(continuar=="S"){

        println("Informe o nome da empresa: ")
        val empresa=readln()
        println("Informe o valor do serviço por unidade: ")
        val valorUnitario=readln().toDoubleOrNull() ?: 0.0
        println("Informe a quantidade de unidades desejada: ")
        val quantidade=readln().toIntOrNull() ?: 0
        println("Informe a porcentagem de desconto: ")
        val desconto=readln().toDoubleOrNull() ?: 0.0
        println("Informe a quantidade mínima para receber desconto: ")
        val quantidadeMinima=readln().toIntOrNull() ?: 0
        println("Informe o valor fixo de deslocamento:")
        val deslocamento=readln().toDoubleOrNull() ?: 0.0

        var bruto=valorUnitario*quantidade
        var valorDesconto=0.0

        if(quantidade>=quantidadeMinima){
            valorDesconto-=bruto*(desconto/100)
        }
        val total=bruto-valorDesconto+deslocamento
        println("O serviço de $empresa custará R$$total")

        orcamentosAC.add(
            OrcamentoAC(
                empresa,
                valorUnitario,
                quantidade,
                desconto,
                total
            )
        )

        if(orcamentosAC.size<2){//deve ter ao menos duas empresas cadastradas
            println("É necessário informar pelo menos duas empresas.")
            continuar="S"
        }
        else {
            println("Deseja informar novos dados? (S/N)")
            continuar=readln().uppercase()
        }
    }
    var menorOrcamento=orcamentosAC[0]

    for(orcamento in orcamentosAC){
        if(orcamento.total<menorOrcamento.total){
            menorOrcamento=orcamento
        }
    }

    var maiorOrcamento=orcamentosAC[0]

    for(orcamento in orcamentosAC){
        if(orcamento.total>maiorOrcamento.total){
            maiorOrcamento=orcamento
        }
    }

    val diferencaPercentual=((maiorOrcamento.total-menorOrcamento.total)/menorOrcamento.total)*100

    println("O orçamento de menor valor é o de ${menorOrcamento.empresa} por R$${menorOrcamento.total}")
    println("O orçamento de maior valor é o de ${maiorOrcamento.empresa} por R$${maiorOrcamento.total}")
    println("Diferença percentual: $diferencaPercentual%")

}

//feito