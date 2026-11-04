fun main() {
    val studentsList = listOf<String>("Muslima", "E'zoza", "Shahrizoda", "Muslima", "Ruxshona")
    val studentsSet = studentsList.toSet()
    val studentsMap = mutableMapOf<String, Boolean>()
    for (student in studentsSet){
        if (studentsMap.keys.contains(student)){
            studentsMap[student] = false
        }
        else {
            studentsMap[student] = true
        }
    }
    println("# of present students: " + studentsSet.count())

}