import groovy.json.JsonSlurper

def dumpProductDetail(product) {
    Thread.sleep(300)

    id = product.id
    slug = product.url_slug
    url = "https://badali.umh.es/alimento/$slug/$id/listado-alfabetico"    

    println "Downloading $url"   
    new File("/tmp/badali/${id}.html").text = new URL(url).text
}

directory = new File("/tmp/badali/")

if (!directory.exists()) {
    directory.mkdirs()
    aurl = "https://badali.umh.es/models/funciones.php?funcion=listaralfabeticamente"
    json = new URL(aurl).text.substring(1) // first character makes slurper to fail
    prod = new JsonSlurper()
        .parseText(json)
        .each { p -> 
            dumpProductDetail(p) 
        }
}


    
