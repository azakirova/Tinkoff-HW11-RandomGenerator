import com.itextpdf.text.*
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import model.Person
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PDFManager {

    private val mainFont: Font

    init {
        val baseFont = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
        mainFont = Font(baseFont, FONT_SIZE, Font.NORMAL)
    }

    fun fillPdfFile(people: List<Person>) {
        var fos: FileOutputStream? = null
        try {
            val file = File(DEFAULT_FILE_NAME)
            fos = FileOutputStream(file)

            val document = Document(PageSize.A3.rotate())
            PdfWriter.getInstance(document, fos)

            document.open()

            val table = initTable(people)

            val paragraph = Paragraph()
            paragraph.add(table)
            document.add(paragraph)

            document.close()
            println("Файл создан. Путь: ${file.absolutePath}")
        } catch (e: Exception) {
            println("Ошибка при работе с файлом.")
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (_: IOException) {
            } /*Failed to close*/
        }
    }

    private fun initTable(peopleList: List<Person>): PdfPTable {
        val pdfPTable =
            PdfPTable(floatArrayOf(140F, 140F, 140F, 60F, 50F, 100F, 120F, 50F, 50F, 140F, 120F, 120F, 50F, 80F))
        pdfPTable.widthPercentage = 100F

        pdfPTable.addCell(Phrase("Имя", mainFont))
        pdfPTable.addCell(Phrase("Фамилия", mainFont))
        pdfPTable.addCell(Phrase("Отчество", mainFont))
        pdfPTable.addCell(Phrase("Возраст", mainFont))
        pdfPTable.addCell(Phrase("Пол", mainFont))
        pdfPTable.addCell(Phrase("Дата рождения", mainFont))
        pdfPTable.addCell(Phrase("Место рождения", mainFont))
        pdfPTable.addCell(Phrase("Индекс", mainFont))
        pdfPTable.addCell(Phrase("Страна", mainFont))
        pdfPTable.addCell(Phrase("Регион", mainFont))
        pdfPTable.addCell(Phrase("Город", mainFont))
        pdfPTable.addCell(Phrase("Улица", mainFont))
        pdfPTable.addCell(Phrase("Дом", mainFont))
        pdfPTable.addCell(Phrase("Квартира", mainFont))

        for (person in peopleList) {
            pdfPTable.addCell(Phrase(person.name, mainFont))
            pdfPTable.addCell(Phrase(person.lastName, mainFont))
            pdfPTable.addCell(Phrase(person.patronymic, mainFont))
            pdfPTable.addCell(Phrase(person.age, mainFont))
            pdfPTable.addCell(Phrase(person.gender, mainFont))
            pdfPTable.addCell(Phrase(person.birthday, mainFont))
            pdfPTable.addCell(Phrase(person.nativeCity, mainFont))
            pdfPTable.addCell(Phrase(person.index, mainFont))
            pdfPTable.addCell(Phrase(person.country, mainFont))
            pdfPTable.addCell(Phrase(person.district, mainFont))
            pdfPTable.addCell(Phrase(person.city, mainFont))
            pdfPTable.addCell(Phrase(person.street, mainFont))
            pdfPTable.addCell(Phrase(person.buildingNumber, mainFont))
            pdfPTable.addCell(Phrase(person.apartmentNumber, mainFont))
        }

        return pdfPTable
    }

    private companion object {
        const val DEFAULT_FILE_NAME = "people.pdf"
        const val FONT_SIZE = 10F
    }
}