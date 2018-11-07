def go = { link -> "${content.rootpath ?: ''}${link}" }

nav(class: "navigation") {
    section(class: 'container') {
        a(href: "${go('index.html')}", config.site_title)
        input(type: 'checkbox', id: 'menu-control')
        label(class: 'menu-mobile float-right ', for: 'menu-control')
        span(class: 'btn-mobile float-right', '☰')
        ul(class: 'navigation-list') {
            li(class: 'navigation-item align-center') {
                a(href: "${go('index.html')}", class: 'navigation-link', 'Home')
            }
            li(class: 'navigation-item align-center') {
                a(href: "${go('about.html')}", class: 'navigation-link', 'About')
            }
            li(class: 'navigation-item align-center') {
                a(href: "${go('archive.html')}", class: 'navigation-link', 'Archive')
            }
            li(class: 'mobile-menu-lang-separator-centered') {
                hr()
            }
        }
    }
}
