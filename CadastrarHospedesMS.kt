package HotelMS

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Hospede(
    val nome: String,
    val dataCadastro: String)

fun cadastroHospedes(hospedes: MutableList<Hospede>) {

    var escolha=0

    while (true) {

        println("Informe a opção desejada: ")
        println("""Cadastro de Hóspedes
            Selecione uma opção:
            1. Cadastrar
            2. Pesquisar nome
            3. Pesquisar por prefixo
            4. Listar
            5. Atualizar cadastro
            6.Remover cadastro
            7. Voltar""") //menu

        escolha=readln().toIntOrNull() ?: 0

        when(escolha){
            1->{
                cadastrarHospede(hospedes)
            }
            2-> {
                pesquisarHospede(hospedes)
            }
            3->{
                pesquisaPorPrefixo(hospedes)
            }
            4->{
                listarHospedes(hospedes)
            }
            5->{
                atualizarCadastro(hospedes)
            }
            6->{
                removerCadastro(hospedes)
            }
            7->{
                if(sairCadastroDeHospedes(hospedes)){
                    return
                }
            }
            else-> erroCadastroDeHospedes()
        }
    }
}

fun cadastrarHospede(hospedes: MutableList<Hospede>) {

        if(hospedes.size >= 15){
            println("Limite de 15 hóspedes atingido.")
            return
        }
        println("Cadastro de Hóspedes.")
        println("Por favor, informe o nome do Hóspede:")
        val novoHospede = readln()

        if(novoHospede.isBlank()){
            println("O nome não pode ficar vazio.")
            return
        }
        if(hospedes.any {it.nome.equals(novoHospede, ignoreCase = true)}){
            println("Hóspede já cadastrado.")
            return
        }

    val agora= LocalDateTime.now()
    val formato= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    val dataCadastro=agora.format(formato)

    val hospede=Hospede(
        novoHospede,
        dataCadastro)
        hospedes.add(hospede)
        println("$novoHospede cadastrado com sucesso!")
}

fun pesquisarHospede(hospedes: MutableList<Hospede>){
    println("Pesquisa de Hóspedes.\nPor favor, informe o nome do Hóspede:")
    val nomeHospede=readln().trim()

    if(hospedes.any {it.nome.contains(nomeHospede, ignoreCase = true)}){
        println("\nEncontramos o(s) hóspede(s):")
        hospedes
            .filter{it.nome.contains(nomeHospede, ignoreCase = true)}
            .forEach{
                println(it.nome)
            }

    }
    else{
        println("Não encontramos nenhum hóspede com esse nome.")
    }
}

fun pesquisaPorPrefixo(hospedes: MutableList<Hospede>){

    println("Pesquisa por Prefixo.")
    println("Digite o início do nome:")
    val prefixo=readln().trim()

    val encontrados=hospedes.filter{it.nome.startsWith(prefixo, ignoreCase = true)}

    if(encontrados.isNotEmpty()) {
        println("Hóspedes encontrados:")
        encontrados.forEach {
            println(it.nome)
        }
    }
    else{
        println("Nenhum hóspede encontrado com esse prefixo.")
    }
}

fun listarHospedes(hospedes: MutableList<Hospede>){

    if(hospedes.isEmpty()){
        println("Nenhum hóspede cadastrado.")
        return
    }
    println("\nLista de Hóspedes:")
    val hospedesOrdenados=hospedes.sortedBy{
        it.nome.lowercase()
    }
    hospedesOrdenados.forEachIndexed{indice, hospede->
        println("${indice + 1}.${hospede.nome} - ${hospede.dataCadastro}")
    }
}

fun atualizarCadastro(hospedes: MutableList<Hospede>){

    if(hospedes.isEmpty()){
        println("Nenhum hóspede cadastrado.")
        return
    }

    listarHospedes(hospedes)
    println("Informe o índice do hóspede que deseja atualizar:")
    val indice=readln().toIntOrNull() ?: 0

    if(indice<1 || indice>hospedes.size){
       println("Índice inválido.")
       return
    }

    val posicao=indice-1
    val hospede=hospedes[posicao]

    println("Hóspede selecionado: ${hospede.nome}")
    println("Informe o novo nome: ")
    val novoNome=readln().trim()

    if(novoNome.isBlank()){
        println("O nome não pode ficar vazio.")
        return
    }
    if(hospedes.any{it.nome.equals(novoNome, ignoreCase = true)&& it != hospede}){
        println("Esse nome já está cadastrado.")
        return
    }
    hospedes[posicao]=Hospede(
        novoNome,
        hospede.dataCadastro
    )
    println("Cadastro atualizado com sucesso!")
}
fun removerCadastro(hospedes: MutableList<Hospede>){

    if(hospedes.isEmpty()){
        println("Nenhum hóspede cadastrado.")
        return
    }

    listarHospedes(hospedes)
    println("Informe o índice do hóspede que deseja remover: ")
    val indice=readln().toIntOrNull() ?: 0

    if(indice<1 || indice>hospedes.size){
        println("Índice inválido.")
        return
    }
    val posicao=indice-1
    val hospede=hospedes[posicao]

    println("Deseja realmente remover ${hospede.nome}? (S/N)")
    val resposta=readln().uppercase()

    if(resposta=="S"){
        hospedes.removeAt(posicao) //remove da lista exatamente aquela posição
        println("Hóspede removido com sucesso!")
    }
    else{
        println("Remoção cancelada.")
    }
}

fun sairCadastroDeHospedes(hospedes: MutableList<Hospede>): Boolean{
    println("Você deseja voltar ao menu principal? (S/N)")
    val escolha=readln()

    return when(escolha.uppercase()){
        // uppercase fará o que for digitado ser convertido para maiúsculo por exemplpo x -> X
        "S"->{
            println("Voltando para o menu principal...")
            println(" ")
            return true
            //exitProcess(0)
        }
        "N"->{
            return false
        }
        else->{
            println("Desculpe, mas não compreendi.")
            sairCadastroDeHospedes(hospedes)
        }
    }
}

fun erroCadastroDeHospedes() {
    println("Por favor, informe um número entre 1 e 7.")
}

//feito