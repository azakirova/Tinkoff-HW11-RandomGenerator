fun main(){
    print("Введите целое число от 1 до 30: ")
    val n = readln().toIntOrNull()

    if (n!=null && n>=1 && n<=30){
        val generator = Generator()
        val q = generator.generate(n)
        print(q)
    }
    else print("Некорректный ввод")
}
