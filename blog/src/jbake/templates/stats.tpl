// SECTION
model.put("section", "Statistics")

// TAGS LAYOUT
layout 'layout/main.tpl', true, projects: projects, bodyContents: contents {
  section {
        header(class: "main") {
            h1 content.title
        }
        yieldScaped content.body
    }
}