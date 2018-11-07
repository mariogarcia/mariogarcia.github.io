xmlDeclaration()
rss(version:"2.0", 'xmlns:atom':"http://www.w3.org/2005/Atom") {
    channel {
        title('JBake')
        link(config.site_host)
        'atom:link'(href: "${config.site_host}/${config.feed_file}", rel: 'self', type: 'application/rss+xml')
        description('Blog in progress')
        language('en-gb')
        pubDate(published_date?.format("EEE, d MMM yyyy HH:mm:ss Z"))
        lastBuildDate(published_date?.format("EEE, d MMM yyyy HH:mm:ss Z"))
        published_posts.each { post ->
            item {
                title(post.title)
                link("${config.site_host}/${post.uri}")
                pubDate(post.date?.format("EEE, d MMM yyyy HH:mm:ss Z"))
                guid(isPermaLink: 'false', post.uri)
            }
        }
    }
}
