from blog.repositories import BlogEntryRepository


def test_find_by_id(session, factories):
    # given: a new blog entry
    saved_entry = factories.create_blog_entry()

    # and: a blog entry repository
    repository = BlogEntryRepository(session)

    # when: trying to find it by its id
    blog_entry = repository.find_by_id(saved_entry.id)

    # then: I should be able to get it back
    assert str(blog_entry.id) == str(saved_entry.id)


def test_find_all_by_startswith(session, factories):
    # given: a new blog entry
    for i in range(2):
        factories.create_blog_entry(
            title="Pytest introduction {}".format(i)
        )

    # and: saving all those entries 
    repository = BlogEntryRepository(session)

    # when: looking for entries starting with Pytest
    results = repository.find_all_by_title_starts_with("Pytest")

    # then: there should be
    assert len(results) == 2
