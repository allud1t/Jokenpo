/**
 * A função principal ficará responsável pelos laços de repetição pré-fixados e o laço em caso de empate.
 */
fun main() {
    var placarUser: Byte = 0
    var placarGame: Byte = 0

    for (i in 1..3) {
        val pair = getScore(placarUser, placarGame)
        placarGame = pair.first
        placarUser = pair.second
    }

    if (placarUser == placarGame) {
        do {
            val pair = getScore(placarUser, placarGame)
            placarGame = pair.first
            placarUser = pair.second
        } while (placarUser == placarGame)
    }

    println("|Placar|       Você $placarUser X $placarGame Computador       |Placar|")
    println("--------------------------------------------------------------")
}

/**
 * Função para evitar repetição de código caso haja empate. As demais funções são executadas internamente a esta.
 */
fun getScore(placarUser: Byte, placarGame: Byte): Pair<Byte, Byte> {
    var placarUser1 = placarUser
    var placarGame1 = placarGame
    val options = arrayOf("Pedra", "Papel", "Tesoura")
    val gameChoice = getGameChoice(options)
    val userChoice = getUserChoice(options)

    when (printResult(userChoice, gameChoice)) {
        "Você ganhou!!!" -> placarUser1++
        "Você perdeu!" -> placarGame1++
    }
    return Pair(placarGame1, placarUser1)
}

/**
 * Função para gerar escolha aleatória de pedra, papel ou tesoura pelo computador
 */
fun getGameChoice(optionParam: Array<String>): String = optionParam[(Math.random() * optionParam.size).toInt()]

/**
 * Função para que o usuário faça a escolha entre pedra papel ou tesoura.
 */
fun getUserChoice(optionParam: Array<String>): String {
    var isValidChoice = false
    var userChoice = ""
    //Loop até o usuário fornecer uma entrada válida
    while (!isValidChoice) {
        //Pede que o usuário escolha entre as opções listadas
        print("Por favor, escolha uma das opções abaixo:")
        for (item in optionParam) print(" * $item")
        println(".")
        //Lê a entrada do usuário
        val userInput = readLine()?.capitalize()
        //Valida a entrada do usuário
        if (userInput != null && userInput in optionParam) {
            isValidChoice = true
            userChoice = userInput
        }
        //Se a entrada for invalida, informar o usuário
        if (!isValidChoice) println("Você precisa entrar com uma opção válida")
    }
    return userChoice
}

/**
 * Imprime o resultado do jogo comparando as escolhas do computador e do usuário.
 */
fun printResult(userChoice: String, gameChoice: String): String {
    //Descobrir o resultado
    val result: String = if (userChoice == gameChoice) "Empate"
    else if ((userChoice == "Pedra" && gameChoice == "Tesoura") || (userChoice == "Papel" && gameChoice == "Pedra") || (userChoice == "Tesoura" && gameChoice == "Papel")) "Você ganhou!!!"
    else "Você perdeu!"
    //Imprime o resultado
    println("Você escolheu $userChoice. Eu escolhi $gameChoice. $result")
    println("--------------------------------------------------------------")
    return result
}