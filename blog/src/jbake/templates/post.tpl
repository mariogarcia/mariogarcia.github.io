// SECTION
model.put("section", "POST")
model.put("content", content)

// POST ENTRY LAYOUT
layout 'layout/main.tpl', true, projects: projects, bodyContents: contents {
    section {
        header(class: "main") {
            div(class: "metadata") {
                /*
                em(class: "fa fa-user-o") {}
                b content.author
                */
                em(class: "fa fa-calendar-o") {}
                b content.date.format("yyyy-MM-dd")
            }                                
            h1 content.title
        }
        yieldScaped content.body
    }
}
