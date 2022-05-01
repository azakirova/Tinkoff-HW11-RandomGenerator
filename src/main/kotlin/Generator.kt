import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

class Generator {
    private val data = DataSource()
    private val formatter = SimpleDateFormat("dd.MM.YYYY")
    private fun generate(): Person {
        val name: String
        val lastName: String
        val patronymic: String
        val gender: String

        if (Random.nextBoolean()) {
            val nameIndex = Random.nextInt(data.femaleName.size)
            name = data.femaleName[nameIndex]
            val lastNameIndex = Random.nextInt(data.femaleLastname.size)
            lastName = data.femaleLastname[lastNameIndex]
            val patronymicIndex = Random.nextInt(data.femalePatronymic.size)
            patronymic = data.femalePatronymic[patronymicIndex]
            gender = "Жен"
        } else {
            val nameIndex = Random.nextInt(data.maleName.size)
            name = data.maleName[nameIndex]
            val lastNameIndex = Random.nextInt(data.maleLastname.size)
            lastName = data.maleLastname[lastNameIndex]
            val patronymicIndex = Random.nextInt(data.malePatronymic.size)
            patronymic = data.malePatronymic[patronymicIndex]
            gender = "Муж"
        }
        val birthdayDate = Date(Random.nextLong(BIRTHDAY_BOUND, System.currentTimeMillis()))
        val birthday = formatter.format(birthdayDate)
        val age = ((System.currentTimeMillis() - birthdayDate.time) / YEAR).toString()
        val nativeCity = data.city[Random.nextInt(data.city.size)]
        val index = Random.nextInt(100000, 999999).toString()
        val country = "Россия"
        val district = data.district[Random.nextInt(data.district.size)]
        val city = data.city[Random.nextInt(data.city.size)]
        val street = data.street[Random.nextInt(data.street.size)]
        val buildingNumber = Random.nextInt(1, 170).toString()
        val apartmentNumber = Random.nextInt(1, 500).toString()
        return Person(
            name,
            lastName,
            patronymic,
            gender,
            age,
            birthday,
            nativeCity,
            index,
            country,
            district,
            city,
            street,
            buildingNumber,
            apartmentNumber
        )
    }

    fun generate(n: Int): List<Person>{
        val list = ArrayList<Person>()
        for (i in 0 until n){
            list.add(generate())
        }
        return list
    }

    private companion object {
        const val YEAR = 365 * 24 * 60 * 60 * 1000L
        const val BIRTHDAY_BOUND = -48L * YEAR
    }
}