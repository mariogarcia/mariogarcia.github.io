from ycl_env import YamlConfigLoader

# tag::environment[]
def test_simple():
    # given: a yaml loader
    loader = YamlConfigLoader()
    
    # when: loading a pro configuration file (myapp-pro.yml)
    yaml_pro = loader.load("pro", "myapp.yml")

    # then: we should get a value from pro environment
    assert yaml_pro.database.user == "john_from_pro"

    # when: loading a test configuration file (myapp-test.yml)
    yaml_pro = loader.load("test", "myapp.yml")

    # then: we should get a value from test environment
    assert yaml_pro.database.user == "john_from_test"

# end::environment[]