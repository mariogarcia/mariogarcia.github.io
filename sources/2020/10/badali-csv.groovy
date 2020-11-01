// https://mvnrepository.com/artifact/org.jsoup/jsoup
@Grapes(
    @Grab(group='org.jsoup', module='jsoup', version='1.11.3')
)
import static groovy.io.FileType.FILES

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

CSV_HEADERS = [
    productID: "ID", 
    name: "NAME",    
    brandID: "BRAND ID", 
    brand: "BRAND",
    groupID: "GROUP ID", 
    groupName: "GROUP NAME", 
    subgroupID: "SUBGROUP ID",
    subgroupName: "SUBGROUP NAME",
    special: "SPECIAL",
    trafficLightValue: "TRAFFICLIGHT VALUE",
    pyramidValue: "PYRAMID VALUE",
    carbs: "CARBS",
    sugar: "SUGAR",
    energy: "ENERGY",
    proteins: "PROTEINS",
    saturatedFat: "SATURATED FAT",
    fat: "FAT",
    salt: "SALT",
    sodium: "SODIUM",
    fiber: "FIBER"
]

Map getMetadata(Document html) {
    productID = html.getElementById('value_id')?.val()
    brandID = html.getElementById('value_id_marca')?.val()
    groupID = html.getElementById('value_id_grupo_alimento')?.val()
    groupName = html.getElementById('value_grupo_alimento_nombre')?.val()
    subgroupID = html.getElementById('value_id_subgrupo_alimento')?.val()
    subgroupName = html.getElementById('value_subgrupo_alimento_nombre')?.val()
    trafficLightValue = html.getElementById('value_semaforo')?.val()
    pyramidValue = html.getElementById('value_piramide')?.val()

    return [
        productID: productID,
        brandID: brandID,
        groupID: groupID,
        groupName: groupName,
        subgroupID: subgroupID,
        subgroupName: subgroupName,
        trafficLightValue: trafficLightValue,
        pyramidValue: pyramidValue
    ]
}

def has(String st) {
    return { it.contains(st) }
}

def getKeyOf(String title) {
    if (!title) return

    sample = title?.toLowerCase()

    // vitamins are missing
    switch(sample) {
        case has("hidr"):      return "carbs"
        case has("azu"):       return "sugar"
        case has("energ"):     return "energy"
        case has("prot"):      return "proteins"
        case has("grasas sat"): return "saturatedFat"
        case has("grasa"):     return "fat"
        case has("sal"):       return "salt"
        case has("sodi"):      return "sodium"
        case has("fibr"):      return "fiber"
        default:               return null
    }
}

Map getNutrition(Document html) {
    table = html.getElementById("tabla1_alimento")
    nutri = html.getElementById("tablaAlimentoCompleto")

    tableTds = table.getElementsByTag("td")
    nutriTds = nutri.getElementsByTag("td")

    nutritionEntries = (0..16).collectEntries { n ->
        title = nutriTds[n]?.text()
        key   = getKeyOf(title)
        entry = [:]
        
        if (key) {
            val = nutriTds[n + 1]?.text()
            entry = [(key): val]
        }

        return entry
    }

    return [
        name: tableTds[3].text(),
        brand: table.getElementById("tabla1_id_marca").text(),
        special: tableTds[11]?.text()
    ] + nutritionEntries
}

CSV_FILE = "/tmp/badali/csv/dump.csv"

void createCSV() {
    csvFile = new File(CSV_FILE)

    if (!csvFile.parentFile.exists()) {
        println("Creating CSV dir")
        csvFile.parentFile.mkdirs()        
    }

    if (!csvFile.exists()) {
        println("Adding CSV headers")
        csvFile << "${CSV_HEADERS.values().join(";")}\n"
    }
}

void addCSVLine(Map metadata, Map nutrition) {
    println "adding ${metadata.productID}"
    csvFile = new File(CSV_FILE)
    allData = metadata + nutrition
    csvLine = CSV_HEADERS.collectEntries { k, v -> 
        val = allData[k]
        return val ? [(k): val] : [(k): "NaN"]
    }
    csvFile << "${csvLine.values().join(";")}\n"
}

void htmlToCSV() {
    new File("/tmp/badali").eachFile(FILES) { file ->
        html      = Jsoup.parse(file, "UTF-8")
        metadata  = getMetadata(html)
        nutrition = getNutrition(html)

        addCSVLine(metadata, nutrition)
    }

    println "Finished!"
}

createCSV()
htmlToCSV()