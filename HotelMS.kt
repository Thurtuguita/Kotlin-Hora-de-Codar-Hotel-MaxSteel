package HotelMS

import kotlin.Int

fun main(){ //coloquei alguns dados dentro do main. main() entrega os dados para quem precisa

    val quartosOcupados=mutableListOf<Int>()
    val reservas=mutableListOf<Reserva>()
    val hospedes=mutableListOf<Hospede>()
    val eventos=mutableListOf<Evento>()
    val orcamentosAC=mutableListOf<OrcamentoAC>()
    val abastecimentos=mutableListOf<Abastecimento>()

    print("Nome do usuário: ")
    val nome=readln()
    if(login()){
        println("Bem-vindo ao Hotel MaxSteel, $nome! É um imenso prazer ter você por aqui.")
        inicio(quartosOcupados, reservas, hospedes, eventos, orcamentosAC, abastecimentos, nome)
    } //se login for verdadeiro, roda func inicio
}

fun login(): Boolean{ //verifica o login do usuario antes de dar acesso ao menu

    val senhaCorreta=2678
    var tentativas=0

    while(tentativas<3){
        print("Senha: ")
        val senha=readln().toIntOrNull()
        if (senha==senhaCorreta){
            println("Login realizado com sucesso!")
            return true
        }
        else{
            tentativas+=1
            println("Senha inválida. ${3-tentativas} tentativa(s) restante(s)!")
        }
    }
    println("Número máximo de tentativas atingido. Sistema bloqueado.")
    return false
}

fun inicio(quartosOcupados: MutableList<Int>,
           reservas: MutableList<Reserva>,
           hospedes: MutableList<Hospede>,
           eventos: MutableList<Evento>,
           orcamentosAC: MutableList<OrcamentoAC>,
           abastecimentos: MutableList<Abastecimento>,
           nome: String){

    var escolha=0

    while(escolha!=7){
        println("Informe a opção desejada: ")
        println("1. Reservas de Quartos\n" +
                "2. Cadastro de Hóspedes\n" +
                "3. Eventos\n" +
                "4. Ar-Condicionado\n" +
                "5. Abastecimento\n" +
                "6. Relatórios Operacionais\n" +
                "7. Sair.")
        escolha=readln().toIntOrNull() ?: 0
        when(escolha){
            1->{
                Reservas(quartosOcupados, reservas)
            }
            2->{
                cadastroHospedes(hospedes)
            }
            3->{
                cadastrarEvento(eventos)
            }
            4->{
                ArCondicionado(orcamentosAC)
            }
            5->{
                abastecer(abastecimentos)
            }
            6->{
                Relatorios(
                    quartosOcupados,
                    reservas,
                    hospedes,
                    eventos
                )
            }
            7->{
                println("$nome, obrigado por usar nosso programa. Até logo!")
            }
            else->{
                erro()
            }
        }
    }
}

fun erro() {
    println("Por favor, informe um número entre 1 e 7.")
}

//feito