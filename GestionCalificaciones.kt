// MIguel Angel David Carranza Osorio

data class Estudiante(
    val nombre: String,
    val carne: String,
    var nota1: Double,
    var nota2: Double,
    var nota3: Double
) {
    // Promedio del estudiantge:
    fun calcularPromedio(): Double = (nota1 + nota2 + nota3) / 3.0

    // Ver si si logro pasar el curso
    fun estaAprobado(): Boolean = calcularPromedio() >= 60.0

    // INFO del estudiante
    fun mostrarInfo(): String {
        val promedio = calcularPromedio()
        val estado = if (estaAprobado()) "APROBADO" else "REPROBADO"
        return "Carné: $carne | Nombre: $nombre | Notas: $nota1, $nota2, $nota3 | Promedio: %.2f | Estado: $estado".format(promedio)
    }
}

class GestorCalificaciones {
    private val estudiantes = mutableListOf<Estudiante>()

    init {



        // Lista de 8 estudiantes
        crearListaInicial()
    }

    // 1. Crear lista inicial con 8 estudiantes predefinidos
    private fun crearListaInicial() {
        val estudiantesPredefinidos = listOf(
            Estudiante("Miguel Carranza", "2024001", 85.0, 92.0, 78.0),
            Estudiante("Hermenegildo Mendoza", "2024002", 45.0, 52.0, 48.0),
            Estudiante("Luis Rodríguez", "2024003", 90.0, 88.0, 95.0),
            Estudiante("María López", "2024004", 72.0, 65.0, 80.0),
            Estudiante("Pedro Sánchez", "2024005", 58.0, 62.0, 55.0),
            Estudiante("Sofia Herrera", "2024006", 95.0, 97.0, 93.0),
            Estudiante("Diego Morales", "2024007", 40.0, 35.0, 42.0),
            Estudiante("Isabella Cruz", "2024008", 88.0, 84.0, 91.0)
        )
        estudiantes.addAll(estudiantesPredefinidos)
    }

    // Promedio por aparte por cas=da estudiante
    fun mostrarPromedios() {
        println("\n=== PROMEDIOS DE ESTUDIANTES ===")
        estudiantes.forEach { estudiante ->
            println("${estudiante.nombre} (${estudiante.carne}): Promedio = %.2f".format(estudiante.calcularPromedio()))
        }
    }

    // Buscar los mejores y peores promedio
    fun encontrarMejorYPeorEstudiante() {
        if (estudiantes.isEmpty()) {
            println("No hay estudiantes registrados.")
            return
        }

        val mejorEstudiante = estudiantes.maxByOrNull { it.calcularPromedio() }
        val peorEstudiante = estudiantes.minByOrNull { it.calcularPromedio() }

        println("\n TOP GLOBALES Y PEOR ESTUDIANTE ")
        mejorEstudiante?.let {
            println("MEJOR ESTUDIANTE:")
            println("${it.mostrarInfo()}")
        }

        peorEstudiante?.let {
            println("\nMalas notas:")
            println("${it.mostrarInfo()}")
        }
    }

    // 4. Contar aprobados y reprobados
    fun contarAprobadosYReprobados() {
        val aprobados = estudiantes.count { it.estaAprobado() }
        val reprobados = estudiantes.size - aprobados

        println("\nEstudiantes")
        println("Total de estudiantes: ${estudiantes.size}")
        println("Estudiantes aprobados: $aprobados")
        println("Estudiantes reprobados: $reprobados")
        println("Porcentaje de aprobación: %.2f%%".format((aprobados.toDouble() / estudiantes.size) * 100))
    }

    //
    fun filtrarEstudiantesPorEstado(aprobados: Boolean = true) {
        val estudiantesFiltrados = if (aprobados) {
            estudiantes.filter { it.estaAprobado() }
        } else {
            estudiantes.filter { !it.estaAprobado() }
        }

        val estado = if (aprobados) "APROBADOS" else "REPROBADOS"
        println("\n=== ESTUDIANTES $estado ===")

        if (estudiantesFiltrados.isEmpty()) {
            println("No hay estudiantes en estado: $estado")
        } else {
            estudiantesFiltrados.forEach { estudiante ->
                println(estudiante.mostrarInfo())
            }
        }
    }

    // Actualizador de la nota
    fun actualizarNotas(carne: String, nuevaNota1: Double, nuevaNota2: Double, nuevaNota3: Double): Boolean {
        val estudiante = estudiantes.find { it.carne == carne }

        return if (estudiante != null) {
            val notasAnteriores = "Notas anteriores: ${estudiante.nota1}, ${estudiante.nota2}, ${estudiante.nota3}"
            val promedioAnterior = estudiante.calcularPromedio()

            estudiante.nota1 = nuevaNota1
            estudiante.nota2 = nuevaNota2
            estudiante.nota3 = nuevaNota3

            println("\nNotas actualizadas")
            println("Estudiante: ${estudiante.nombre} (${estudiante.carne})")
            println(notasAnteriores)
            println("Promedio anterior: %.2f".format(promedioAnterior))
            println("Notas nuevas: $nuevaNota1, $nuevaNota2, $nuevaNota3")
            println("Nuevo promedio: %.2f".format(estudiante.calcularPromedio()))
            println("Actualización exitosa!")
            true
        } else {
            println("No se encontro al estudiante o al carnet: $carne")
            false
        }
    }


    fun buscarEstudiantePorCarne(carne: String): Estudiante? {
        return estudiantes.find { it.carne == carne }
    }


    fun mostrarTodosLosEstudiantes() {
        println("\n=== LISTA COMPLETA DE ESTUDIANTES ===")
        estudiantes.forEach { estudiante ->
            println(estudiante.mostrarInfo())
        }
    }


    fun mostrarEstadisticasDetalladas() {
        if (estudiantes.isEmpty()) {
            println("No hay estudiantes registrados.")
            return
        }

        val promedios = estudiantes.map { it.calcularPromedio() }
        val promedioGeneral = promedios.average()
        val notaMasAlta = promedios.maxOrNull() ?: 0.0
        val notaMasBaja = promedios.minOrNull() ?: 0.0

        println("\n=== ESTADÍSTICAS DETALLADAS ===")
        println("Promedio general del grupo: %.2f".format(promedioGeneral))
        println("Nota más alta: %.2f".format(notaMasAlta))
        println("Nota más baja: %.2f".format(notaMasBaja))
        contarAprobadosYReprobados()
    }
}

// el mero main
fun main() {
    val gestor = GestorCalificaciones()

    println("🎓 SISTEMA DE GESTIÓN DE CALIFICACIONES 🎓")
    println("=".repeat(50))

    while (true) {
        println("\n" + "=".repeat(30))
        println("MENÚ PRINCIPAL")
        println("\uD83D\uDC7B".repeat(20))
        println("1. Mostrar todos los estudiantes")
        println("2. Mostrar promedios")
        println("3. Encontrar mejor y peor estudiante")
        println("4. Mostrar estadísticas generales")
        println("5. Filtrar estudiantes aprobados")
        println("6. Filtrar estudiantes reprobados")
        println("7. Actualizar notas de un estudiante")
        println("8. Buscar estudiante por carné")
        println("9. Mostrar estadísticas detalladas")
        println("0. Salir")
        println("\uD83D\uDC7B".repeat(20))
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> gestor.mostrarTodosLosEstudiantes()
            2 -> gestor.mostrarPromedios()
            3 -> gestor.encontrarMejorYPeorEstudiante()
            4 -> gestor.contarAprobadosYReprobados()
            5 -> gestor.filtrarEstudiantesPorEstado(true)
            6 -> gestor.filtrarEstudiantesPorEstado(false)
            7 -> {
                print("Ingrese el carné del estudiante: ")
                val carne = readLine() ?: ""
                print("Ingrese la nueva nota 1: ")
                val nota1 = readLine()?.toDoubleOrNull() ?: continue
                print("Ingrese la nueva nota 2: ")
                val nota2 = readLine()?.toDoubleOrNull() ?: continue
                print("Ingrese la nueva nota 3: ")
                val nota3 = readLine()?.toDoubleOrNull() ?: continue
                gestor.actualizarNotas(carne, nota1, nota2, nota3)
            }
            8 -> {
                print("Ingrese el carné a buscar: ")
                val carne = readLine() ?: ""
                val estudiante = gestor.buscarEstudiantePorCarne(carne)
                if (estudiante != null) {
                    println("\n=== ESTUDIANTE ENCONTRADO ===")
                    println(estudiante.mostrarInfo())
                } else {
                    println("No se encontró un estudiante con carné: $carne")
                }
            }
            9 -> gestor.mostrarEstadisticasDetalladas()
            0 -> {
                println("¡Gracias por usar el sistema de gestión de calificaciones!")
                break
            }
            else -> println("Opción inválida Seleccione una opción del menú.")
        }

        println("\nPresione Enter para continuar")
        readLine()
    }
}