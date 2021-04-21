xmlDeclaration()
stats {
    entriesByYear {
        all_content
            .sort { next -> next.date }
            .groupBy { next -> next.date.format('yyyy') }
            .each { next ->
                entry(year: next.key, count: next.value.size())
                newLine()
            }
    }
    newLine()
    tags {
        tags
        .collectEntries { tag -> 
            [
                (tag.name): [
                    posts: tag.tagged_posts.sort { it.date }, 
                    count: tag.tagged_posts.size()
                ]
            ] 
        }
        .findAll { next -> next.value.count > 5 }
        .sort { next -> next.value.count }
        .each { next ->
            tag {
                name(next.key)
                newLine()
                count(next.value.count)
                newLine()
                entries {
                    next.value.posts.each { post ->
                        entry(title: post.title, date: post.date)
                        newLine()
                    }
                }
            }
            newLine()
        }
    }
    newLine()
    terms {
        published_posts
            .collectMany    { post -> post.body.split(' ')*.replaceAll("[<|>]*", '')*.replaceAll('[\n|\r]', '')*.toLowerCase()*.trim() }
            .groupBy        { nextTerm -> nextTerm }
            .collectEntries { nextTerm -> [(nextTerm.key): nextTerm.value.size()] }
            .findAll        { nextTerm -> !(nextTerm.key.contains('\"') || nextTerm.key.contains('=') || nextTerm.key.contains('&') || nextTerm.key.contains(';') || nextTerm.key.contains('.') || nextTerm.key.contains('quote')) }
            .findAll        { nextTerm -> nextTerm.value > 10 && nextTerm.key.size() > 3 && nextTerm.key.size() < 10 } 
            .sort           { nextTerm -> nextTerm.value }
            .takeRight(80)
            .each { nextTerm ->
                term(name: nextTerm.key, count: nextTerm.value)
                newLine()
            }                
    }
}