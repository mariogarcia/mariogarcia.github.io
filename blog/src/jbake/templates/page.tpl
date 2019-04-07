layout 'layout/main.tpl', true,
projects: projects,
bodyContents: contents {
    section(class:"wrap"){
        yieldUnescaped content.body
    }
}
