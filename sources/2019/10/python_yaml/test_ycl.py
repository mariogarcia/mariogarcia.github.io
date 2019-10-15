from ycl import YamlConfigLoader

# tag::simple[]
def test_simple():
    yaml = YamlConfigLoader().load("config.yml")

    assert yaml.database.user     == "john"
    assert yaml.database.password == "supersecret"
    assert yaml.database.dialect  == "postgres+pg8000"
    assert yaml.database.host     == "localhost"
    assert yaml.database.port     == 5432
    
    assert len(yaml.log.loggers) == 2
# end::simple[]